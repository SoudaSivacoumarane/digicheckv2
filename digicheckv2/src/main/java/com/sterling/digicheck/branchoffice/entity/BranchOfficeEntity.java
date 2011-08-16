package com.sterling.digicheck.branchoffice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sterling.digicheck.state.entity.StateEntity;

@Entity
@Table(name="SUCURSAL")
@NamedQueries({@NamedQuery(name = "BranchOfficeEntity.findAll", query = "select b from BranchOfficeEntity b")})
public class BranchOfficeEntity implements Serializable{
	private static final long serialVersionUID = 1621438969371389248L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUC_ID")
	private Integer sucId;
	@Column(name="SUC_NOMBRE")
	private String name;
	@Column(name="SUC_DIRECCION")
	private String address;
	@Column(name="SUC_COLONIA")
	private String community;
	@Column(name="SUC_CODIGO_POSTAL")
	private String zip;	
	@JoinColumn(name = "EST_CODIGO", referencedColumnName = "EST_CODIGO")
	@ManyToOne
	private StateEntity stateEntity;
	
	@Column(name="SUC_CIUDAD")
	private String city;
	
	public BranchOfficeEntity() {

	}
	
	public BranchOfficeEntity(Integer argSucId, String argName, String argAddress, String argCommunity, String argZip, String argCity, StateEntity argStateEntity) {
		this.sucId = argSucId;
		this.name = argName;
		this.address = argAddress;
		this.community = argCommunity;
		this.zip = argZip;
		this.city = argCity;
		this.stateEntity = argStateEntity;
	}
		
	public Integer getSucId() {
		return sucId;
	}

	public void setSucId(Integer sucId) {
		this.sucId = sucId;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
		
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public StateEntity getStateEntity() {
		return stateEntity;
	}

	public void setStateEntity(StateEntity stateEntity) {
		this.stateEntity = stateEntity;
	}
		
}
