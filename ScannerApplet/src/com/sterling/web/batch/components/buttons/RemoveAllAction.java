package com.sterling.web.batch.components.buttons;

import java.awt.Container;

import com.sterling.web.batch.components.MainPanel;
import com.sterling.web.batch.components.ToolBar;

public class RemoveAllAction implements ActionRunneable {
	private MainPanel mainPanel;
	public RemoveAllAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	
	@Override
	public void run() {
		mainPanel.removeAll();
		mainPanel.repaint();
		
		((ToolBar)mainPanel.getParent().getComponent(0)).isScan(false);
		((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(false);
		
		mainPanel.setImages(null);
	}
}