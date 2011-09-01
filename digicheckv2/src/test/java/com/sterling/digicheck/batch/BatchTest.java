package com.sterling.digicheck.batch;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.sterling.digicheck.bank.dao.BankDAO;
import com.sterling.digicheck.batch.dao.BatchDAO;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.branchoffice.dao.BranchOfficeDAO;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.currency.dao.CurrencyDAO;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.documenttype.entity.DocumentTypeEntity;
import com.sterling.digicheck.user.dao.UserDAO;
import com.sterling.digicheck.user.exception.UserException;

@ContextConfiguration(locations = "/applicationContext-tests.xml")
public class BatchTest extends AbstractTestNGSpringContextTests {

	@BeforeMethod
    public void setUp() {}
	
	@Autowired
	BatchDAO batchDAO;	
	@Autowired
	BankDAO bankDAO;
	@Autowired
	BranchOfficeDAO branchOfficeDAO;
	@Autowired
	CurrencyDAO currencyDAO;
	@Autowired
	UserDAO userDAO;	
	EntityManagerFactory emf;
	EntityManager em;
	
	
	//@Test
	public void pupulateBatchEntity(){
		BatchEntity batchEntity = null;		
		DocumentTypeEntity documentTypeEntity = null;
		
		documentTypeEntity = new DocumentTypeEntity();
		documentTypeEntity.setDocTypeId(new Character('C'));
		documentTypeEntity.setDocTypeDescription("Cheque");
		
		batchEntity = new BatchEntity();
		batchEntity.setBatchAmount(new BigDecimal(57384.89).setScale(2, BigDecimal.ROUND_DOWN));
		batchEntity.setBatchDate(new Date());
		batchEntity.setBatchDateAdded(new Date());
		batchEntity.setBatchDocuments(10);
		batchEntity.setLotReference("Ref1");		
		try {
			batchEntity.setBranchOfficeId(branchOfficeDAO.getBranchOfficeById(1));
		} catch (BranchOfficeException e1) {e1.printStackTrace();}				
		try {
			batchEntity.setCurrencyId(currencyDAO.getCurrencyById(10));
		} catch (CurrencyException e) {e.printStackTrace();}				
		try {
			batchEntity.setUserLogin(userDAO.getUserByLogin("edgar"));
		} catch (UserException e) {e.printStackTrace();}
		
		System.out.println("before persist Object BatchEntity");
				
		em = emf.createEntityManager();
		em.getTransaction().begin();
			em.persist(batchEntity);		
			BatchEntity be = em.find(BatchEntity.class, 1);
			Assert.assertNotNull(be);
		em.getTransaction().commit();						
	}
	
	//@Test
	public void searchCriteriaTest(){
		List<BatchEntity> batList = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String dateString = "2011-08-15";
		try {
			currentTime = formatter.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			batList = batchDAO.searchBatchEntity("12345", currentTime, 1);
			for(BatchEntity b : batList){
				System.out.println(b.getReference());				
				System.out.println(b.getBatchDateAdded());
				System.out.println(b.getBatchDocuments());
			}
		} catch (BatchException e) {
			e.printStackTrace();
		}
	}

	@PersistenceContext	
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
}
