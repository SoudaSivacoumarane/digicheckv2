package com.sterling.web.batch.components.buttons;

import java.awt.Container;
import java.util.List;

import javax.swing.JOptionPane;

import com.cpi.sterling.check.view.CheckView;
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
		((ToolBar)mainPanel.getParent().getComponent(0)).saveBotton(false);
		LotService lotService = null;
		LotHelper lotHelper = null;
		LotView lotView = null;
		List<CheckView> checks = null;
		int loteId = 0;
		Object[] options = {"Aceptar", "Cancelar"};
		try{
			lotService = new LotService();
			lotHelper = new LotHelper();
			lotView = mainPanel.getLotView();
			checks = mainPanel.getChecks();
			
			if( lotView.getType() == LotView.CATCH_TYPE || checks.size() == lotView.getNoDocs() || JOptionPane.showOptionDialog(mainPanel, "No. de documentos<"+lotView.getNoDocs()+"> es diferente a los cheques scanneados<"+checks.size()+">, desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == 0 ){
				if( checks.size() != lotView.getNoDocs() ){
					lotView.setNoDocs(checks.size());
				}
				lotHelper.setChecks(lotView, checks, mainPanel.getImages());
				
				loteId = lotService.saveLote(lotView);
				mainPanel.removeAll();
				mainPanel.repaint();
				
				((ToolBar)mainPanel.getParent().getComponent(0)).isScan(false);
				((ToolBar)mainPanel.getParent().getComponent(0)).arrowEnable(false);
				
				mainPanel.setStatusBar("Lote <"+loteId+"> Guardado.");
				JOptionPane.showMessageDialog(mainPanel, "Lote <"+loteId+"> Guardado con Exito.");
				
				mainPanel.setImages(null);
				mainPanel.setChecks(null);
			}else{
				mainPanel.setStatusBar("No. Docs <"+lotView.getNoDocs()+"> != Cheques Scanneados <"+checks.size()+">");
				((ToolBar)mainPanel.getParent().getComponent(0)).saveBotton(true);
			}
			
		}catch(LotException lotException){
			((ToolBar)mainPanel.getParent().getComponent(0)).saveBotton(true);
			JOptionPane.showMessageDialog(mainPanel, lotException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception exception){
			((ToolBar)mainPanel.getParent().getComponent(0)).saveBotton(true);
			JOptionPane.showMessageDialog(mainPanel, exception.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}