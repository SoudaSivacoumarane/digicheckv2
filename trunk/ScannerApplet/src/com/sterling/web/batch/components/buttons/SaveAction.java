package com.sterling.web.batch.components.buttons;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.sterling.web.batch.components.MainPanel;

public class SaveAction implements ActionRunneable {
	private MainPanel mainPanel;
	public SaveAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	
	@Override
	public void run() {
		mainPanel.setStatusBar("Saving ... ");
		JOptionPane.showMessageDialog(mainPanel, "Error saving data!", "Database Error", JOptionPane.ERROR_MESSAGE);
	}
}