package com.java.barc.reports;

/*
 * All reports need to implement this interface
 * 
 */
public interface ReportService<T> {

	public T execute() throws Exception;
}
