package com.jorva.jorval.interpeter.vars;

public class VariableNumber extends Variable{

	public VariableNumber(Double o) {
		super(o);
	}

	@Override
	public VariableTypes getType() {
		return VariableTypes.NUMBER;
	}
	
	@Override
	public Double getData() {
		return (Double)super.getData();
	}

}
