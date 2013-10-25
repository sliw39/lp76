package model;

import java.sql.Date;

public class Station {
	private int id;
	private String label;
	private int areaId;
	private Date lastCom;
	private boolean valid;
	private Area area;
	
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public Date getLastCom() {
		return lastCom;
	}
	public void setLastCom(Date lastCom) {
		this.lastCom = lastCom;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
