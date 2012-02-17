package com.sterling.digicheck.user.view;

import java.io.Serializable;
import java.util.List;

import com.sterling.digicheck.user.permission.view.UserPermissionView;

public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1197299016292404309L;
	private String sucursalId;
	private String login;
	private String name;
	private String password;
	private boolean scannerPermission = false;
	private boolean digitalizePermission = false;
	private boolean digitalizeCashPermission = false;
	private boolean branchOfficePermission = false;
	private boolean itselfCheckPermission = false;
	private boolean allCheckPermission = false;
	private boolean itselftReportPermission = false;
	private boolean allReportsPermission = false;
	private boolean addUser = false;
	private boolean editUser = false;
	private boolean delUser = false;
	private boolean userDataPermission = false;
	private boolean catalogsDataPermission = false;
	private boolean delDocumentsPermission = false;
	private List<UserPermissionView> userPermissionViews;

	public UserView() {
		
	}
	
	public UserView(String nLogin){
		login = nLogin;
	}
	
	public UserView(String nSucursalId, String nLogin, String nNombre, String nPassword) {
		sucursalId = nSucursalId;
		login = nLogin;
		name= nNombre;
		password = nPassword;
	}
	
	public void goEditUser(){
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}		
	
	public List<UserPermissionView> getUserPermissionViews() {
		return userPermissionViews;
	}

	public void setUserPermissionViews(List<UserPermissionView> userPermissionViews) {
		this.userPermissionViews = userPermissionViews;
	}		

	public boolean isScannerPermission() {
		return scannerPermission;
	}

	public void setScannerPermission(boolean scannerPermission) {
		this.scannerPermission = scannerPermission;
	}

	public boolean isDigitalizePermission() {
		return digitalizePermission;
	}

	public void setDigitalizePermission(boolean digitalizePermission) {
		this.digitalizePermission = digitalizePermission;
	}

	public boolean isBranchOfficePermission() {
		return branchOfficePermission;
	}

	public void setBranchOfficePermission(boolean branchOfficePermission) {
		this.branchOfficePermission = branchOfficePermission;
	}

	public boolean isItselfCheckPermission() {
		return itselfCheckPermission;
	}

	public void setItselfCheckPermission(boolean itselfCheckPermission) {
		this.itselfCheckPermission = itselfCheckPermission;
	}

	public boolean isAllCheckPermission() {
		return allCheckPermission;
	}

	public void setAllCheckPermission(boolean allCheckPermission) {
		this.allCheckPermission = allCheckPermission;
	}

	public boolean isItselftReportPermission() {
		return itselftReportPermission;
	}

	public void setItselftReportPermission(boolean itselftReportPermission) {
		this.itselftReportPermission = itselftReportPermission;
	}

	public boolean isAllReportsPermission() {
		return allReportsPermission;
	}

	public void setAllReportsPermission(boolean allReportsPermission) {
		this.allReportsPermission = allReportsPermission;
	}

	public boolean isAddUser() {
		return addUser;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public boolean isEditUser() {
		return editUser;
	}

	public void setEditUser(boolean editUser) {
		this.editUser = editUser;
	}

	public boolean isDelUser() {
		return delUser;
	}

	public void setDelUser(boolean delUser) {
		this.delUser = delUser;
	}
	

	public boolean isDigitalizeCashPermission() {
		return digitalizeCashPermission;
	}

	public void setDigitalizeCashPermission(boolean digitalizeCashPermission) {
		this.digitalizeCashPermission = digitalizeCashPermission;
	}

	public boolean isUserDataPermission() {
		return userDataPermission;
	}

	public void setUserDataPermission(boolean userDataPermission) {
		this.userDataPermission = userDataPermission;
	}

	public boolean isCatalogsDataPermission() {
		return catalogsDataPermission;
	}

	public void setCatalogsDataPermission(boolean catalogsDataPermission) {
		this.catalogsDataPermission = catalogsDataPermission;
	}

	public boolean isDelDocumentsPermission() {
		return delDocumentsPermission;
	}

	public void setDelDocumentsPermission(boolean delDocumentsPermission) {
		this.delDocumentsPermission = delDocumentsPermission;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof UserView))
			return false;
		UserView userView = (UserView)obj;		
		return this.getLogin().equals(userView.getLogin());		
	}
	
}