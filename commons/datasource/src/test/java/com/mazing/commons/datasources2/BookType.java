package com.mazing.commons.datasources2;

import com.yogu.commons.datasource.annocation.TheTypeAlias;

@TheTypeAlias
public class BookType {
	private int id;
	private String type_id;
	private String super_type_id;
	private String type_name;
	private int weight;
	private int status;
	private String status_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getSuper_type_id() {
		return super_type_id;
	}

	public void setSuper_type_id(String super_type_id) {
		this.super_type_id = super_type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatus_name() {
		if (status == 1) {
			status_name = "有效";
		} else if (status == 0) {
			status_name = "无效";
		}
		return status_name;
	}

	public void setStatus_name(String statusName) {
		status_name = statusName;
	}
}
