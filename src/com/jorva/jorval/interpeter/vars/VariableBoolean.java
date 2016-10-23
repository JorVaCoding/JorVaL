package com.jorva.jorval.interpeter.vars;

public class VariableBoolean extends Variable {

	public VariableBoolean(boolean o) {
		super(o);
	}

	@Override
	public VariableTypes getType() {
		return VariableTypes.BOOLEAN;
	}
	
	@Override
	public Boolean getData() {
		return (Boolean)super.getData();
	}

}
