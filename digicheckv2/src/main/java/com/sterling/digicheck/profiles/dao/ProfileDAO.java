package com.sterling.digicheck.profiles.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.profiles.entity.ProfileEntity;
import com.sterling.digicheck.profiles.exception.ProfileException;

@Repository
public class ProfileDAO extends GenericDAO {
	
	private static final Logger logger = Logger.getLogger(ProfileDAO.class);

	@SuppressWarnings("unchecked")
	public List<ProfileEntity> getProfileEntity() throws ProfileException{
		List<ProfileEntity> profileList = null;
		Query query = null;
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			query = em.createNamedQuery("ProfileEntity.findAll");
			profileList = query.getResultList();
			em.getTransaction().commit();
		}catch (Exception exception){
			ProfileException profileException = null;
			profileException = new ProfileException(exception, ProfileException.LAYER_DAO, ProfileException.ACTION_LISTS);
    		logger.error(profileException);
    		exception.printStackTrace(System.out);
    		throw profileException;
		}
		return profileList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProfileEntity> getProfileByUser(String userId) throws ProfileException{
		List<ProfileEntity> profileList = null;
		Query query = null;
		try{
			query = em.createNativeQuery("SELECT * FROM PERFIL p INNER JOIN USUARIO u ON  p.PRF_ID = u.PRF_ID AND u.USU_LOGIN = ?");
			query.setParameter(1, userId);			
			profileList = query.getResultList();				
		}catch (Exception exception){
			ProfileException profileException = null;
			profileException = new ProfileException(exception, ProfileException.LAYER_DAO, ProfileException.ACTION_LISTS);
    		logger.error(profileException);
    		exception.printStackTrace(System.out);
    		throw profileException;
		}
		return profileList;
	}
	
}
