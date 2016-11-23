package com.greg.gmall.model;

public class Category implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -627236948764421438L;
	private Integer id;
	// private Account account;
	private String type;
	private Boolean hot;

	public Category() {
	}
	
	public Category(String type, boolean hot) {
		this.type = type;
		this.hot = hot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

}
