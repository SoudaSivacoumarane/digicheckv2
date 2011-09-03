package com.sterling.web.batch.components.buttons;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageConsumer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import SK.gnome.morena.Morena;
import SK.gnome.morena.MorenaImage;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

import com.cpi.sterling.check.view.CheckView;
import com.sterling.web.batch.components.ImagePanel;
import com.sterling.web.batch.components.MainPanel;
import com.sterling.web.batch.components.ToolBar;

public class ScanAction implements ActionRunneable {
	private MainPanel mainPanel;
	public ScanAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	
	@Override
	public void run() {
		mainPanel.setStatusBar("Scanning ... ");
		TwainSource source = null;
		MorenaImage morenaImage = null;
		int imageStatus = 0;
		ImagePanel imagePanel = null;
		int indexImage = 0;
		Image image = null;
		List<Image> arrayImages = new ArrayList<Image>();
		List<CheckView> arrayCheckInfo = new ArrayList<CheckView>();
		try {
			source = TwainManager.selectSource(null);
			if (source != null) {
				source.setVisible(false);
				source.setGrayScaleMode();
				source.setResolution(100);
				source.setDuplexEnabled(true);
				source.setPrinterEnabled(false);
				source.setSupportedSizes(0);
				do{
					morenaImage = new MorenaImage(source);
					imageStatus = morenaImage.getStatus();
					if (imageStatus == ImageConsumer.STATICIMAGEDONE) {
						image = Toolkit.getDefaultToolkit().createImage(morenaImage);
						imagePanel = new ImagePanel(image);
						if( indexImage%2 == 0 ){
							arrayCheckInfo.add(new CheckView());//Aqui falta agregar los datos del abba
						}
						if( indexImage++ < 2 ){
							mainPanel.add(imagePanel);
						}
						arrayImages.add(image);
						mainPanel.validate();
					}else if (imageStatus == ImageConsumer.IMAGEABORTED){
						mainPanel.setStatusBar("Aborted, try again ...");
						break;
					}else if (imageStatus == ImageConsumer.IMAGEERROR){
						mainPanel.setStatusBar("Failed, try again ...");
						break;
					}
				}while(source.hasMoreImages());
				
				if( arrayCheckInfo.size() > 0 ){
					((ToolBar)mainPanel.getParent().getComponent(0)).isScan(true);
					if(  arrayImages.size()/2 > 1 ){
						((ToolBar)mainPanel.getParent().getComponent(0)).onlyRightEnable();
					}else{
						((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(false);
					}
					
					mainPanel.resetPosition();
					mainPanel.setStatusBar("# Cheques : " + arrayImages.size()/2);
					
					mainPanel.setImages(arrayImages);
					mainPanel.setChecks(arrayCheckInfo);
				}
				
			}else{
				mainPanel.setStatusBar("Failed, try again ...");
			}
		} catch (TwainException twainException){
			JOptionPane.showMessageDialog(mainPanel, twainException.toString(), "Twain Error", JOptionPane.ERROR_MESSAGE);
			twainException.printStackTrace();
			mainPanel.setStatusBar("Failed, try again ...");
		} finally{
			try {
				Morena.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}