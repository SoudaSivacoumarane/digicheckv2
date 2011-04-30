package com.sterling.digicheck.user.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.service.UserService;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="loginManagedBean")
public class UserManagedBean implements Serializable{

	/** Serial Version UID */
	private static final long serialVersionUID = 5909347434256740767L;
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
	private UserView view = new UserView();
	
	
	public void doLoginAction(){		
		try {
			HttpSession session = null;
			
			if(userService.loginUser(view) != null){
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("user", view);
				goHomeAction(null);
			}else{
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Login Error", "Usuario o Password incorrecto");
			}
		} catch (UserException e) {
			e.printStackTrace();
		}
	}	
	
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
	
	public UserView getView() {
		return view;
	}

	public void setView(UserView view) {
		this.view = view;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}								
	
}
