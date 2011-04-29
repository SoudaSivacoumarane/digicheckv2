package com.sterling.digicheck.user.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.user.converter.UserConverter;
import com.sterling.digicheck.user.dao.UserDAO;
import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.view.UserView;

@Service("userService")
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDAO;
	
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
		UserConverter userConverter = null;
		try{
			userConverter = new UserConverter();
			entity = userConverter.convertViewToEntity(view);
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
		try{
			userConverter = new UserConverter();
			entity = userConverter.convertViewToEntity(view);
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
}
