package com.sterling.web.batch.components;

import javax.swing.JTextField;
import javax.swing.JToolBar;

public class StatusBar extends JToolBar {
	private static final long serialVersionUID = 6775617058148341975L;
	private JTextField status;
	public StatusBar() {
		status = new JTextField("Ready ...");
		status.setEditable(false);
		add(status);
	}
	
	public void setStatus(String newStatus){
		status.setText(newStatus);
	}
}