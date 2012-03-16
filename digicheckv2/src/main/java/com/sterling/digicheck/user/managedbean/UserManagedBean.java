package com.sterling.digicheck.user.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.profiles.exception.ProfileException;
import com.sterling.digicheck.profiles.service.ProfileService;
import com.sterling.digicheck.profiles.view.ProfilePermissionsView;
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
	@ManagedProperty("#{profileService}")
	private ProfileService profileService;
	private List<UserView> userViewList = null;
	private String login;
	private int page = 1;
	private UserView userView = new UserView();
	private UserView currentUser = new UserView();
	private String sucId;
	private String password;
	private String profileSelected;
	
	public List<SelectItem> getProfilesList(){
		List<SelectItem> list = new ArrayList<SelectItem>(0);
		try {
		  list = profileService.getProfileList();
		} catch (ProfileException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void profileEditChanged(ValueChangeEvent e){
		profileSelected = e.getNewValue().toString();
		List<ProfilePermissionsView> list = new ArrayList<ProfilePermissionsView>(0);
		try {
			list = profileService.getProfilePermissionsById(profileSelected);
			currentUser.setScannerPermission(false);
			currentUser.setDigitalizePermission(false);
			currentUser.setBranchOfficePermission(false);
			currentUser.setItselfCheckPermission(false);		
			currentUser.setAllCheckPermission(false);	
			currentUser.setItselftReportPermission(false);
			currentUser.setAllReportsPermission(false);
			currentUser.setAddUser(false);
			currentUser.setEditUser(false);
			currentUser.setDelUser(false);
			currentUser.setDigitalizeCashPermission(false);
			currentUser.setUserDataPermission(false);
			currentUser.setCatalogsDataPermission(false);
			currentUser.setDelDocumentsPermission(false);
		
			for (ProfilePermissionsView pp : list) {
				if(pp.getPerId().intValue() == 1){
					currentUser.setScannerPermission(true);						
				}else if(pp.getPerId().intValue() == 2){
					currentUser.setDigitalizePermission(true);
				}else if(pp.getPerId().intValue() == 3){
					currentUser.setBranchOfficePermission(true);
				}else if(pp.getPerId().intValue() == 4){
					currentUser.setItselfCheckPermission(true);
				}else if(pp.getPerId().intValue() == 5){
					currentUser.setAllCheckPermission(true);
				}else if(pp.getPerId().intValue() == 6){
					currentUser.setItselftReportPermission(true);
				}else if(pp.getPerId().intValue() == 7){
					currentUser.setAllReportsPermission(true);
				}else if(pp.getPerId().intValue() == 8){
					currentUser.setAddUser(true);
				}else if(pp.getPerId().intValue() == 9){
					currentUser.setEditUser(true);
				}else if(pp.getPerId().intValue() == 10){
					currentUser.setDelUser(true);
				}else if(pp.getPerId().intValue() == 11){
					currentUser.setDigitalizeCashPermission(true);
				}else if(pp.getPerId().intValue() == 12){
					currentUser.setUserDataPermission(true);
				}else if(pp.getPerId().intValue() == 13){
					currentUser.setCatalogsDataPermission(true);
				}else if(pp.getPerId().intValue() == 14){
					currentUser.setDelDocumentsPermission(true);
				}
			}
		} catch (ProfileException e1) {
			e1.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().renderResponse();
	}
	/**
	 * Agregar permiso
	 * @param e
	 */
	public void profileAddChanged(ValueChangeEvent e){
		profileSelected = e.getNewValue().toString();
		List<ProfilePermissionsView> list = new ArrayList<ProfilePermissionsView>(0);
		try {
			list = profileService.getProfilePermissionsById(profileSelected);
			userView.setScannerPermission(false);
			userView.setDigitalizePermission(false);
			userView.setBranchOfficePermission(false);
			userView.setItselfCheckPermission(false);		
			userView.setAllCheckPermission(false);	
			userView.setItselftReportPermission(false);
			userView.setAllReportsPermission(false);
			userView.setAddUser(false);
			userView.setEditUser(false);
			userView.setDelUser(false);
			userView.setDigitalizeCashPermission(false);
			userView.setUserDataPermission(false);
			userView.setCatalogsDataPermission(false);
			userView.setDelDocumentsPermission(false);
		
			for (ProfilePermissionsView pp : list) {
				if(pp.getPerId().intValue() == 1){
					userView.setScannerPermission(true);						
				}else if(pp.getPerId().intValue() == 2){
					userView.setDigitalizePermission(true);
				}else if(pp.getPerId().intValue() == 3){
					userView.setBranchOfficePermission(true);
				}else if(pp.getPerId().intValue() == 4){
					userView.setItselfCheckPermission(true);
				}else if(pp.getPerId().intValue() == 5){
					userView.setAllCheckPermission(true);
				}else if(pp.getPerId().intValue() == 6){
					userView.setItselftReportPermission(true);
				}else if(pp.getPerId().intValue() == 7){
					userView.setAllReportsPermission(true);
				}else if(pp.getPerId().intValue() == 8){
					userView.setAddUser(true);
				}else if(pp.getPerId().intValue() == 9){
					userView.setEditUser(true);
				}else if(pp.getPerId().intValue() == 10){
					userView.setDelUser(true);
				}else if(pp.getPerId().intValue() == 11){
					userView.setDigitalizeCashPermission(true);
				}else if(pp.getPerId().intValue() == 12){
					userView.setUserDataPermission(true);
				}else if(pp.getPerId().intValue() == 13){
					userView.setCatalogsDataPermission(true);
				}else if(pp.getPerId().intValue() == 14){
					userView.setDelDocumentsPermission(true);
				}
			}
		} catch (ProfileException e1) {
			e1.printStackTrace();
		}
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	
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
			this.profileSelected = currentUser.getProfile();
			this.password = currentUser.getPassword();
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
		this.profileSelected = "";
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
	
	


	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
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
	/**
	 * Metodo que verifica si un usuario tiene permiso
	 * para borrar documentos.
	 * @return
	 */
	public boolean isDeleteDocumentAllowed(){
		boolean deleteDocuments = Boolean.FALSE;
		try{
			deleteDocuments = this.securityAuthorizationService.hasPermission("14", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		return deleteDocuments;
	}

	public void setSecurityAuthorizationService(
			SecurityAuthorizationService securityAuthorizationService) {
		this.securityAuthorizationService = securityAuthorizationService;
	}

	public String getProfileSelected() {
		return profileSelected;
	}

	public void setProfileSelected(String profileSelected) {
		this.profileSelected = profileSelected;
	}		
	
}
