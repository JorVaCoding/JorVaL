package com.jorva.jorval.interpeter.exceptions;

@SuppressWarnings("serial")
public class ExceptionVariableNull extends Exception{
	private String variable, file;
	private int line;
	public ExceptionVariableNull(String variable, int line, String file) {
		this.variable = variable;
		this.line = line;
		this.file = file;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(String.format("Variable[%s] is not set at line[%s] in file[%s]", variable,line, file));
	}
}
