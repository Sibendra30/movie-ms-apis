package com.moviestar.services;

public abstract class BaseService {

	protected void validate(){}
	
	public void execute() throws Exception {
		this.validate();
		this.executeImpl();
	}
	
	protected abstract void executeImpl() throws Exception;
}
