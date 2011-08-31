package com.sterling.web.batch.components.buttons;

import java.awt.Container;
import java.awt.Image;
import java.util.List;

import com.sterling.web.batch.components.ImagePanel;
import com.sterling.web.batch.components.MainPanel;
import com.sterling.web.batch.components.ToolBar;

public class LeftAction implements ActionRunneable {
	private MainPanel mainPanel;
	public LeftAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	@Override
	public void run() {
		List<Image> images = mainPanel.getImages();
		mainPanel.previousPosition();
		int position = mainPanel.getPosition();
		
		mainPanel.removeAll();
		mainPanel.repaint();
		
		mainPanel.add(new ImagePanel(images.get(position*2)));
		mainPanel.add(new ImagePanel(images.get(position*2+1)));
		mainPanel.validate();
		
		if( ( position-1 )*2 < 0 ){
			((ToolBar)mainPanel.getParent().getComponent(0)).onlyRightEnable();
		}else{
			((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(true);
		}
	}
}