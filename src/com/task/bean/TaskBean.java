package com.task.bean;

import java.util.Date;

public class TaskBean {

	private int id;
	private String title;
	private String description;
	private Date dueDate;
	private Date reminder;
	private Date createDate;
	private Date updateDate;
	private Boolean important;
	private Boolean finish;

	public TaskBean(int id, String title, String description, Date dueDate, Date reminder, Boolean important,
			Boolean finish) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.reminder = reminder;
		this.important = important;
		this.finish = finish;
	}

	public TaskBean(String title, String description, Date dueDate, Date reminder, Boolean important, Boolean finish) {
		super();
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.reminder = reminder;
		this.important = important;
		this.finish = finish;
	}

	public TaskBean(int id, String title, String description, Date dueDate, Date reminder, Date createDate,
			Date updateDate, Boolean important, Boolean finish) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.reminder = reminder;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.important = important;
		this.finish = finish;
	}

	public TaskBean(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	

}
