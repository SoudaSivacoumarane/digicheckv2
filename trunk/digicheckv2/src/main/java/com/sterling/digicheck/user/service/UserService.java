package com.sterling.digicheck.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.permission.entity.PermissionEntity;
import com.sterling.digicheck.user.converter.UserConverter;
import com.sterling.digicheck.user.dao.UserDAO;
import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.permission.dao.UserPermissionDAO;
import com.sterling.digicheck.user.permission.entity.UserPermissionEntity;
import com.sterling.digicheck.user.view.UserView;

@Service("userService")
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDAO;	
	@Autowired
	private UserPermissionDAO userPermissionDAO;
	
	public UserView loginUser(UserView userView) throws UserException{		
		UserEntity userEntity = null;
		UserView view = null;
		UserConverter userConverter = null;
		try{
			userConverter = new UserConverter();
			userEntity = userDAO.loginUser(userConverter.convertViewToEntity(userView));
			if(userEntity != null){
				view = userConverter.converterEntityToAuthView(userEntity);
			}
		} catch (UserException userException){
			throw userException;
		} catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_SELECT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return view;
	}
	
	public UserView validateUser(String domainUserName)throws UserException{
		UserView userView = null;
		UserEntity userEntity = null;
		UserConverter userConverter = null;
		try{
			userConverter = new UserConverter();
			userEntity = userDAO.validateUser(domainUserName);
			if(userEntity!=null){
				userView = userConverter.converterEntityToAuthView(userEntity);
			}
		} catch (UserException userException){
			throw userException;
		} catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_SELECT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userView;
	}
	
	public List<UserView> getAllUsers()throws UserException{
		List<UserView> userViews = null;
		List<UserEntity> userEntities = null;
		UserConverter userConverter = null;		
		try{
			userConverter = new UserConverter();			
			userEntities = userDAO.getAllUsers();
			if(userEntities!=null){
				userViews = userConverter.converterEntitiesToViews(userEntities);
			}
		}catch (UserException userException){
			throw userException;
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_LISTS);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userViews;
		
	}
	
	public void insertUser(UserView view)throws UserException{
		UserEntity entity = null;		
		List<UserPermissionEntity> uList = null;
		List<PermissionEntity> pList = null;
		UserConverter userConverter = null;
		try{
			userConverter = new UserConverter();
			entity = userConverter.convertViewToEntity(view);
			pList = userPermissionDAO.getAllPermissionEntitiesList();
			
			uList = new ArrayList<UserPermissionEntity>(0);
			if(view.isScannerPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 1, new Date(), pList.get(0), entity));				
			}
			if(view.isDigitalizePermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 2, new Date(), pList.get(1), entity));
			}
			if(view.isBranchOfficePermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 3, new Date(), pList.get(2), entity));
			}
			if(view.isItselfCheckPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 4, new Date(), pList.get(3), entity));
			}
			if(view.isAllCheckPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 5, new Date(), pList.get(4), entity));
			}
			if(view.isItselftReportPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 6, new Date(), pList.get(5), entity));
			}
			if(view.isAllReportsPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 7, new Date(), pList.get(6), entity));
			}
			if(view.isAddUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 8, new Date(), pList.get(7), entity));
			}
			if(view.isEditUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 9, new Date(), pList.get(8), entity));
			}
			if(view.isDelUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 10, new Date(), pList.get(9), entity));
			}
			entity.setUserPermissionEntity(uList);			
			userDAO.insertUser(entity);
		}catch (UserException userException){
			throw userException;
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_INSERT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
	
	public void updateUser(UserView view)throws UserException{
		UserEntity entity = null;
		UserConverter userConverter = null;
		List<UserPermissionEntity> uList = null;
		List<PermissionEntity> pList = null;
		try{
			userConverter = new UserConverter();
			entity = userConverter.convertViewToEntity(view);
			entity.setUserPermissionEntity(null);
			pList = userPermissionDAO.getAllPermissionEntitiesList();			
			uList = new ArrayList<UserPermissionEntity>(0);
			if(view.isScannerPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 1, new Date(), pList.get(0), entity));				
			}
			if(view.isDigitalizePermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 2, new Date(), pList.get(1), entity));
			}
			if(view.isBranchOfficePermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 3, new Date(), pList.get(2), entity));
			}
			if(view.isItselfCheckPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 4, new Date(), pList.get(3), entity));
			}
			if(view.isAllCheckPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 5, new Date(), pList.get(4), entity));
			}
			if(view.isItselftReportPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 6, new Date(), pList.get(5), entity));
			}
			if(view.isAllReportsPermission()){
				uList.add(new UserPermissionEntity(view.getLogin(), 7, new Date(), pList.get(6), entity));
			}
			if(view.isAddUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 8, new Date(), pList.get(7), entity));
			}
			if(view.isEditUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 9, new Date(), pList.get(8), entity));
			}
			if(view.isDelUser()){
				uList.add(new UserPermissionEntity(view.getLogin(), 10, new Date(), pList.get(9), entity));
			}
			entity.setUserPermissionEntity(uList);
			userDAO.updateUser(entity);
		}catch (UserException userException){
			throw userException;
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_UPDATE);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
	
	public void deleteUser(UserView view)throws UserException{		
		UserConverter converter = null;
		try{				
			converter = new UserConverter();			 
			userDAO.deleteUser(converter.convertViewToEntity(view));						
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_DELETE);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
	
	public UserView getUserByLogin(String login) throws UserException{
		UserConverter converter = null;
		UserView userView = null;		
		try{
			converter = new UserConverter();
			userView = converter.converterEntityToAuthView(userDAO.getUserByLogin(login));
		} catch (UserException userException){
			throw userException;
		} catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_SERVICE, UserException.ACTION_SELECT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userView;		
	}
}
