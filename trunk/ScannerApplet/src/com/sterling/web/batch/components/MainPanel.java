package com.sterling.web.batch.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.cpi.sterling.check.view.CheckView;
import com.cpi.sterling.lot.view.LotView;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = -744677556128177512L;
	private StatusBar statusBar;
	private List<Image> arrayImages;
	private List<CheckView> arrayChecks;
	private int position;
	private LotView lotView = null;

	public MainPanel(Container container, LotView nLotView) {
		statusBar = new StatusBar();
		lotView = nLotView;
		container.add(new ToolBar(this), BorderLayout.NORTH);
		container.add(this, BorderLayout.CENTER);
		container.add(statusBar, BorderLayout.SOUTH);
		setLayout(new GridLayout(1, 1));
	}

	public void setStatusBar(String status) {
		statusBar.setStatus(status);
	}
	
	public List<Image> getImages(){
		if(arrayImages==null){
			arrayImages = new ArrayList<Image>();
		}
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
	
	public LotView getLotView() {
		return lotView;
	}

	public void setChecks(List<CheckView> arrayCheckInfo) {
		this.arrayChecks = arrayCheckInfo;
	}
	
	public List<CheckView> getChecks(){
		if( arrayChecks == null ){
			arrayChecks = new ArrayList<CheckView>();
		}
		return arrayChecks;
	}
}