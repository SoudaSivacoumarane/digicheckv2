package com.sterling.digicheck.profiles.view;

import java.io.Serializable;
/**
 * ProfilePermissions View
 * @author rioslore
 *
 */
public class ProfilePermissionsView implements Serializable {

	private Integer prfId;
	private Integer perId;
	private String perDescription;
	private boolean selected;
	
	public Integer getPrfId() {
		return prfId;
	}
	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}
	public Integer getPerId() {
		return perId;
	}
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	public String getPerDescription() {
		return perDescription;
	}
	public void setPerDescription(String perDescription) {
		this.perDescription = perDescription;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
		
}
