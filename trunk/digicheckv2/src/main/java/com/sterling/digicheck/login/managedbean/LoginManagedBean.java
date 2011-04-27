package com.sterling.digicheck.login.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.sterling.common.util.JSFUtil;

@ManagedBean(name="loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable{		
	/** Serial Version UID */
	private static final long serialVersionUID = -897899477960100712L;
	private static final String VIEW_HOME = "home.xhtml";
	private static final String VIEW_BRANCH_OFFICES = "sucursal.xhtml";
	private static final String VIEW_OUT = "index.xhtml";
	private static final String VIEW_USERS = "usuario.xhtml";
	private static final String VIEW_CHECKS = "cheques.xhtml";
	private static final String VIEW_MONTHLY_REPORT = "reporte_mensual.xhtml";
	private static final String VIEW_DAILY_REPORT = "reporte_diario.xhtml";
	private static final String VIEW_ADD_LOT = "digitalizacion.xhtml";

	public void goHomeAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_HOME);
	}
	
	public void goBrachOfficesAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_BRANCH_OFFICES);
	}
	
	public void goOutAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_OUT);
	}
	
	public void goUserAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_USERS);
	}
	
	public void goChecksAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_CHECKS);
	}
	
	public void goMonthlyReportAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_MONTHLY_REPORT);
	}
	
	public void goAddLotAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_ADD_LOT);
	}
	
	public void goDailyReportAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_DAILY_REPORT);
	}
}
