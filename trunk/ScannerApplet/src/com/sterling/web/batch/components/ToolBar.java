package com.sterling.web.batch.components;

import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import com.cpi.sterling.lot.view.LotView;
import com.sterling.web.batch.components.buttons.ButtonAction;
import com.sterling.web.batch.components.buttons.LeftAction;
import com.sterling.web.batch.components.buttons.RemoveAllAction;
import com.sterling.web.batch.components.buttons.RightAction;
import com.sterling.web.batch.components.buttons.SaveAction;
import com.sterling.web.batch.components.buttons.ScanAction;
import com.sterling.web.batch.components.buttons.ScanCatchAction;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = -3737125446189470311L;
	private ButtonAction removeAction;
	private ButtonAction scanAction;
	private ButtonAction scanCatchAction;
	private ButtonAction saveAction;
	private ButtonAction leftAction;
	private ButtonAction rightAction;
	
	public void saveBotton(boolean enable){
		saveAction.setEnabled(enable);
	}
	
	public void isScan(boolean scan){
		saveAction.setEnabled(scan);
		scanAction.setEnabled(!scan);
		scanCatchAction.setEnabled(!scan);
		removeAction.setEnabled(scan);
	}
	
	public void isScanNotFinish(){
		saveAction.setEnabled(true);
		scanAction.setEnabled(true);
		scanCatchAction.setEnabled(true);
		removeAction.setEnabled(true);
	}
	
	public void onlyRightEnable() {
		rightAction.setEnabled(true);
		leftAction.setEnabled(false);
	}
	
	public void onlyLeftEnable() {
		rightAction.setEnabled(false);
		leftAction.setEnabled(true);
	}
	
	public void arrowEnable(boolean enable) {
		leftAction.setEnabled(enable);
		rightAction.setEnabled(enable);
	}
	
	public ToolBar(Container mainPanel) {
		try {
			InputStream inputSream = ToolBar.class.getResourceAsStream("buttons/images/remove.png");
			byte [] image = new byte[inputSream.available()];
			inputSream.read(image);
			removeAction = new ButtonAction(new RemoveAllAction(mainPanel), "RemoveAll", new ImageIcon(image), "Borrar", 'R');
			
			inputSream = ToolBar.class.getResourceAsStream("buttons/images/scan.png");
			image = new byte[inputSream.available()];
			inputSream.read(image);
			scanAction = new ButtonAction(new ScanAction(mainPanel), "ScanCheque", new ImageIcon(image), "Scannear Cheque", 'S');
			
			inputSream = ToolBar.class.getResourceAsStream("buttons/images/scan.png");
			image = new byte[inputSream.available()];
			inputSream.read(image);
			scanCatchAction = new ButtonAction(new ScanCatchAction(mainPanel), "ScanEfectivo", new ImageIcon(image), "Scannear Efectivo", 'S');
			
			inputSream = ToolBar.class.getResourceAsStream("buttons/images/save.png");
			image = new byte[inputSream.available()];
			inputSream.read(image);
			saveAction = new ButtonAction(new SaveAction(mainPanel), "Save", new ImageIcon(image), "Guardar", 'G');
			
			inputSream = ToolBar.class.getResourceAsStream("buttons/images/left.png");
			image = new byte[inputSream.available()];
			inputSream.read(image);
			leftAction = new ButtonAction(new LeftAction(mainPanel), "Left", new ImageIcon(image), "Anterior", 'A');
			
			inputSream = ToolBar.class.getResourceAsStream("buttons/images/right.png");
			image = new byte[inputSream.available()];
			inputSream.read(image);
			rightAction = new ButtonAction(new RightAction(mainPanel), "Right", new ImageIcon(image), "Siguiente", 'S');
			
			add(leftAction);
			add(removeAction);
			if( ((MainPanel)mainPanel).getLotView().getType() == LotView.CATCH_TYPE ){
				add(scanCatchAction);
			}else{
				add(scanAction);
			}
			add(saveAction);
			add(rightAction);
			
			removeAction.setEnabled(false);
			saveAction.setEnabled(false);
			leftAction.setEnabled(false);
			rightAction.setEnabled(false);
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}