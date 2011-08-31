package com.sterling.web.batch;

import javax.swing.JApplet;

import com.sterling.web.batch.components.MainPanel;

public class BatchScreenNew extends JApplet {
	private static final long serialVersionUID = 2822443467875661297L;
	
	@Override
	public void init() {
		super.init();
		System.out.println("Init method.");
		new MainPanel(getContentPane());
	}
	
	@Override
	public void start() {
		super.start();
		System.out.println("Start method.");
	}
	
	@Override
	public void stop() {
		super.stop();
		System.out.println("Stop method.");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Destroy method.");
	}
}