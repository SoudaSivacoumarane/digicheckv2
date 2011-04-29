package com.sterling.digicheck.user.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.exception.UserException;

@Repository
public class UserDAO extends GenericDAO {

	private static final Logger logger = Logger.getLogger(UserDAO.class);
	
	public UserEntity validateUser(String domainUserName)throws UserException{		
		UserEntity userEntity = null;		
		try{			
			userEntity = em.find(UserEntity.class, domainUserName);						
		} catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userEntity;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers()throws UserException{		
		List<UserEntity> userEntities = null;		
		Query query = null;
		try{
			query = em.createNamedQuery("");
			userEntities = query.getResultList();				
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_LISTS);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
		return userEntities;
		
	}
	public void insertUser(UserEntity userEntity)throws UserException{		
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(userEntity);		
			em.getTransaction().commit();
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_INSERT);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
	
	public void updateUser(UserEntity userEntity)throws UserException{		
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(userEntity);
			em.getTransaction().commit();			
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_UPDATE);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
	
	public void deleteUser(UserEntity userEntity)throws UserException{
		UserEntity entity = null;		
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			entity = em.find(UserEntity.class, userEntity.getLogin());			
			em.remove(entity);
			em.getTransaction().commit();
		}catch (Exception exception){
			UserException userException = null;
			userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_DELETE);
    		logger.error(userException);
    		exception.printStackTrace(System.out);
    		throw userException;
		}
	}
}
