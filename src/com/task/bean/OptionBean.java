package com.task.bean;

import java.util.List;

public class OptionBean {
	
	private int id;
	private String label;
	private boolean defaultOption;
	private List<TaskBean> tasks;
	
	
	
	public OptionBean(int id, String label, List<TaskBean> tasks) {
		super();
		this.id = id;
		this.label = label;
		this.tasks = tasks;
	}


	public OptionBean(String label, List<TaskBean> tasks) {
		super();
		this.label = label;
		this.tasks = tasks;
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


	public List<TaskBean> getTasks() {
		return tasks;
	}


	public void setTasks(List<TaskBean> tasks) {
		this.tasks = tasks;
	}


	public boolean isDefaultOption() {
		return defaultOption;
	}


	public void setDefaultOption(boolean defaultOption) {
		this.defaultOption = defaultOption;
	}
		
	
	

}
