package com.sterling.digicheck.login.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.batch.managedbean.BatchManagedBean;
import com.sterling.digicheck.security.service.SecurityAuthorizationService;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.service.UserService;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="loginManagedBean")
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
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	@ManagedProperty("#{securityAuthorizationService}")
	SecurityAuthorizationService securityAuthorizationService;
	private UserView view = new UserView();
	
	
	public void doLoginAction(){		
		try {
			HttpSession session = null;			
			if(userService.loginUser(view) != null){
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("user", userService.getUserByLogin(view.getLogin()));
				goHomeAction(null);							
			}
		} catch (UserException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Password incorrecto", "Login Error");
		}
	}
	
	public boolean isBranchOfficePermission() {
		boolean updateUser = Boolean.FALSE; 
		try {
			updateUser = this.securityAuthorizationService.hasPermission("3", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return updateUser;
	}
	
	public boolean isAllReportsPermission(){
		boolean allReportsPermission = Boolean.FALSE; 
		try {
			allReportsPermission = this.securityAuthorizationService.hasPermission("7", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return allReportsPermission;
	}
	
	public boolean isAllCheckPermission(){
		boolean allCheckPermission = Boolean.FALSE; 
		try {
			allCheckPermission = this.securityAuthorizationService.hasPermission("5", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return allCheckPermission;
	}
	
	/**
	 * Este permiso sirve para ver o no el 
	 * catalogo de usuarios
	 * @return
	 */
	public boolean isUserDataPermissions(){
		boolean userData = Boolean.FALSE;
		try{
			userData = this.securityAuthorizationService.hasPermission("12", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return userData;
	}
	
	public boolean isCatalogosAllowed(){
		boolean catalogs = Boolean.FALSE;
		try{
			catalogs = this.securityAuthorizationService.hasPermission("13", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return catalogs;
	}
	
	public void setSecurityAuthorizationService(
			SecurityAuthorizationService securityAuthorizationService) {
		this.securityAuthorizationService = securityAuthorizationService;
	}	
		
	public UserView getView() {
		return view;
	}

	public void setView(UserView view) {
		this.view = view;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void goHomeAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_HOME);
	}
	
	public void goBrachOfficesAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_BRANCH_OFFICES);
	}
	
	public void goOutAction(ActionEvent actionEvent){
		HttpSession session = null;
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("user", null);		
		JSFUtil.redirect(VIEW_OUT);
	}
	
	public void goUserAction(ActionEvent actionEvent){
		JSFUtil.redirect(VIEW_USERS);
	}
	
	public void goChecksAction(ActionEvent actionEvent){
		BatchManagedBean batchManagedBean = (BatchManagedBean) JSFUtil.getManagedBean("batchManagedBean", BatchManagedBean.class);
		batchManagedBean.cleanValues();
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
