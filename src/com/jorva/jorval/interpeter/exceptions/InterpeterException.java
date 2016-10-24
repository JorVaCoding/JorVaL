package com.jorva.jorval.interpeter.exceptions;

import com.jorva.jorval.interpeter.Interpeter;

@SuppressWarnings("serial")
public class InterpeterException extends Exception {
	String msg;
	Interpeter interpeter;
	int line;

	public InterpeterException(String msg, Interpeter interpeter, Integer line) {
		this.line = line != null ? line : interpeter.getLine();
		this.msg = msg;
		this.interpeter = interpeter;
	}

	@Override
	public String getMessage() {
		return String.format("%s [%s:%s]", msg, interpeter.name, line);
	}

}
