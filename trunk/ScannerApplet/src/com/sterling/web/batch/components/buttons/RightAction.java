package com.sterling.web.batch.components.buttons;

import java.awt.Container;
import java.awt.Image;
import java.util.List;

import com.sterling.web.batch.components.ImagePanel;
import com.sterling.web.batch.components.MainPanel;
import com.sterling.web.batch.components.ToolBar;

public class RightAction implements ActionRunneable {
	private MainPanel mainPanel;
	public RightAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	@Override
	public void run() {
		List<Image> images = mainPanel.getImages();
		mainPanel.nextPosition();
		int position = mainPanel.getPosition();
		
		mainPanel.removeAll();
		mainPanel.repaint();
		
		mainPanel.add(new ImagePanel(images.get(position*2)));
		mainPanel.add(new ImagePanel(images.get(position*2+1)));
		mainPanel.validate();
		
		if( ( position+1 )*2 >= images.size() ){
			((ToolBar)mainPanel.getParent().getComponent(0)).onlyLeftEnable();
		}else{
			((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(true);
		}
	}
}