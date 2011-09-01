package com.sterling.web.batch;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.helper.LotHelper;
import com.cpi.sterling.lot.view.LotView;
import com.sterling.web.batch.components.MainPanel;

public class BatchScreenNew extends JApplet {
	private static final long serialVersionUID = 2822443467875661297L;
	
	@Override
	public void init() {
		super.init();
		System.out.println("Init method.");
		LotView lotView = null;
		LotHelper lotHelper = null;
		try {
			lotHelper = new LotHelper();
			lotView = lotHelper.createLotViewFromApplet(this);
			new MainPanel(getContentPane(), lotView);
		} catch (LotException lotException) {
			JOptionPane.showMessageDialog(this, lotException.getMessage(), "Init Error", JOptionPane.ERROR_MESSAGE);
		}
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