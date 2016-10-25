package com.jorva.jorval.interpeter.funcs;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public interface Function {
	public Variable exec(Interpeter interpeter, Variable... params);
	
	public String getKey();
	
	public VariableTypes getReturnType();
	
	
}
