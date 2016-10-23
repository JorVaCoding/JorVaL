package com.jorva.jorval.interpeter.funcs;

import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public interface Function {
	public abstract Variable exec(Variable... params);
	
	public abstract String getKey();
	
	public abstract VariableTypes getReturnType();
}
