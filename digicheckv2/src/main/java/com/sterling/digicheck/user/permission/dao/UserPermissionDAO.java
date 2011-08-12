package com.sterling.digicheck.user.permission.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.permission.entity.PermissionEntity;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.permission.entity.UserPermissionEntity;

@Repository
public class UserPermissionDAO extends GenericDAO {
	
	private static final Logger logger = Logger.getLogger(UserPermissionDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<UserPermissionEntity> getUserPermissionEntitiesList() throws UserException{
		List<UserPermissionEntity> userPermissionList = null;
		Query query = null;
		try{
			query = em.createNamedQuery("UserPermissionEntity.findAll");
			userPermissionList = query.getResultList();				
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_LISTS);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userPermissionList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PermissionEntity> getAllPermissionEntitiesList() throws UserException {
		List<PermissionEntity> list = null;
		Query query = null;
		try{
			query = em.createNamedQuery("PermissionEntity.findAll");
			list = query.getResultList();				
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_LISTS);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return list;
	}
	
	public boolean hasPermission(String permission, String login) throws UserException{
		boolean haspermission = Boolean.FALSE;
		Long result = null;
		Query query = null;
		try{
			query = em.createNamedQuery("UserPermissionEntity.findByPerId");
			query.setParameter("perId", Integer.parseInt(permission));
			query.setParameter("usuLogin", login);
			result = (Long) query.getSingleResult();
			if(result.intValue() > 0){
				haspermission = Boolean.TRUE;
			}			
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_LISTS);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return haspermission;
	}
	
}
