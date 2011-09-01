package com.sterling.web.batch.components.buttons;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.helper.LotHelper;
import com.cpi.sterling.lot.service.LotService;
import com.cpi.sterling.lot.view.LotView;
import com.sterling.web.batch.components.MainPanel;
import com.sterling.web.batch.components.ToolBar;

public class SaveAction implements ActionRunneable {
	private MainPanel mainPanel;
	public SaveAction(Container container) {
		mainPanel = (MainPanel)container;
	}
	
	@Override
	public void run() {
		mainPanel.setStatusBar("Saving ... ");
		LotService lotService = null;
		LotHelper lotHelper = null;
		LotView lotView = null;
		int loteId = 0;
		try{
			lotService = new LotService();
			lotHelper = new LotHelper();
			lotView = mainPanel.getLotView();
			
			lotHelper.setChecks(lotView, mainPanel.getChecks(), mainPanel.getImages());
			
			loteId = lotService.saveLote(lotView);
			mainPanel.removeAll();
			mainPanel.repaint();
			
			((ToolBar)mainPanel.getParent().getComponent(0)).isScan(false);
			((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(false);
			
			mainPanel.setStatusBar("Lote <"+loteId+"> Guardado.");
		}catch(LotException lotException){
			JOptionPane.showMessageDialog(mainPanel, lotException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception exception){
			JOptionPane.showMessageDialog(mainPanel, exception.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}