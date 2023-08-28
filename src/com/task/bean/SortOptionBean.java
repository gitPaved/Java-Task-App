package com.task.bean;

public class SortOptionBean {
	private String label;
	private String field;

	public SortOptionBean(String label, String field) {
		super();
		this.label = label;
		this.field = field;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	@Override
    public String toString() {
        return label;
    }

}
