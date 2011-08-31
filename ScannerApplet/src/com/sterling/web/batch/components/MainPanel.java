package com.sterling.web.batch.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = -744677556128177512L;
	private StatusBar statusBar;
	private List<Image> arrayImages;
	private int position;

	public MainPanel(Container container) {
		statusBar = new StatusBar();
		container.add(new ToolBar(this), BorderLayout.NORTH);
		container.add(this, BorderLayout.CENTER);
		container.add(statusBar, BorderLayout.SOUTH);
		setLayout(new GridLayout(1, 1));
	}

	public void setStatusBar(String status) {
		statusBar.setStatus(status);
	}
	
	public List<Image> getImages(){
		return arrayImages;
	}
	
	public void setImages(List<Image> images){
		arrayImages = images;
	}
	
	public void resetPosition(){
		position = 0;
	}
	
	public void nextPosition(){
		position++;
	}
	
	public void previousPosition(){
		position--;
	}
	
	public int getPosition(){
		return position;
	}
}