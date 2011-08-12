package com.sterling.digicheck.user.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.security.service.SecurityAuthorizationService;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.service.UserService;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="userManagedBean")
@ViewScoped
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
	UserService userService;
	@ManagedProperty("#{branchOfficeService}")
	BranchOfficeService branchOfficeService;	
	@ManagedProperty("#{securityAuthorizationService}")
	SecurityAuthorizationService securityAuthorizationService;	
	private List<UserView> userViewList = null;
	private String login;
	private int page = 1;
	private UserView userView = new UserView();
	private UserView currentUser = new UserView();
	private String sucId;
	private String password;
	
	public List<UserView> getUserViewList() {
		try{
			userViewList =	userService.getAllUsers();
		}catch(UserException userException){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
		return userViewList;
	}
	
	public void goEditUser(){
		try {
			currentUser = userService.getUserByLogin(login);			
		} catch (UserException userException) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
	}
	
	public void deleteUser(){
		UserView deleteUser = null;
		try{
			deleteUser = new UserView();
			deleteUser.setLogin(login);
			userService.deleteUser(deleteUser);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "El usuario se elimino correctamente");
		} catch (UserException userException) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
	}
	
	public void editUser(){
		try{						
			userService.updateUser(currentUser);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "El usuario se modifico correctamente");
		} catch (UserException userException) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
	}
	
	public void addUser(){
		try{		
			userService.insertUser(userView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "El usuario se agrego correctamente");
		} catch (UserException userException) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
	}
	
	public List<SelectItem> getBranchOfficeItems(){
		List<SelectItem> bItems = null;
		try {
			bItems = branchOfficeService.branchOfficeItems();
		} catch (BranchOfficeException e) {
			e.printStackTrace();
		}
		return bItems;
	}
	
	public void validatePassword(FacesContext context, UIComponent component, Object value) {		
		String pass = (String) value;		
		if (!pass.equals(password)) {
			((UIInput)component).setValid(false);		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contrasenias no coinciden","Las contrasenias no coinciden");
			context.addMessage(component.getClientId(context), message);
		}
	}
	
	public void cleanUser(){		
		this.setUserView(null);
		this.userView = new UserView();
		this.password = "";
	}
	
	public String getSucId() {
		return sucId;
	}

	public void setSucId(String sucId) {
		this.sucId = sucId;
	}
	
	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public UserView getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserView currentUser) {
		this.currentUser = currentUser;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setUserViewList(List<UserView> userViewList) {
		this.userViewList = userViewList;
	}		

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isAddUser() {
		boolean addUser = Boolean.FALSE;
		try {			
			addUser = this.securityAuthorizationService.hasPermission("8", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return addUser;
	}


	public boolean isDeleteUser() {
		boolean deleteUser = Boolean.FALSE;
		try {
			deleteUser = this.securityAuthorizationService.hasPermission("10", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return deleteUser;
	}


	public boolean isUpdateUser() {
		boolean updateUser = Boolean.FALSE; 
		try {
			updateUser = this.securityAuthorizationService.hasPermission("9", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return updateUser;
	}

	public void setSecurityAuthorizationService(
			SecurityAuthorizationService securityAuthorizationService) {
		this.securityAuthorizationService = securityAuthorizationService;
	}			
	
}
