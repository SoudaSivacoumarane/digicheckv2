package com.sterling.digicheck.user.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.view.UserView;

public class UserConverter {

	private static final Log log = LogFactory.getLog(UserConverter.class);
	public List<UserView> converterEntitiesToViews(List<UserEntity> userEntities)throws UserException{
		List<UserView> userViews = null;
		UserView userView = null;
		UserEntity userEntity = null;
		Iterator<UserEntity> iteraror = null;
		try{
			userViews = new ArrayList<UserView>(0);
			iteraror = userEntities.iterator();
			while(iteraror.hasNext()){
				userEntity = iteraror.next();
				userView = converterEntityToAuthView(userEntity);
				userViews.add(userView);
			}
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_CONVERTER, UserException.ACTION_SELECT);
			log.error(userException);
			exception.printStackTrace(System.out);
			throw userException;
		}
		return userViews;
	}
	public UserView converterEntityToAuthView(UserEntity userEntity)throws UserException{
		UserView userView = null;

		try{
			userView = new UserView();
			
			userView.setLogin(userEntity.getLogin());
			userView.setPassword(userEntity.getPassword());
			userView.setName(userEntity.getNombre());
			userView.setSucursalId(String.valueOf(userEntity.getSucursalId()));
			if(userView.getPassword()!=null)
				userView.setPassword(userView.getPassword().trim());
		} catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_CONVERTER, UserException.ACTION_SELECT);
			log.error(userException);
			exception.printStackTrace(System.out);
			throw userException;
		}
		return userView;
	}
	public UserEntity convertViewToEntity(UserView view)throws UserException{
		UserEntity entity = null;		
		try{
			entity = new UserEntity();
			
			entity.setLogin(view.getLogin());
			entity.setPassword(view.getPassword());
			entity.setNombre(view.getName());
			if(view.getSucursalId()!=null)
				entity.setSucursalId(Integer.parseInt(view.getSucursalId()));
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_CONVERTER, UserException.ACTION_SELECT);
			log.error(userException);
			exception.printStackTrace(System.out);
			throw userException;
		}
		return entity;
	}
}
