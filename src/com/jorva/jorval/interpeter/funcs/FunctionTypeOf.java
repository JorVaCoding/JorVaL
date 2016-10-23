package com.jorva.jorval.interpeter.funcs;

import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableText;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public class FunctionTypeOf implements Function {

	@Override
	public Variable exec(Variable... params) {
		if(params[0] != null){
			return new VariableText(params[0].getType().toString());
		}
		return null;
	}

	@Override
	public String getKey() {
		return "typeof";
	}

	@Override
	public VariableTypes getReturnType() {
		return VariableTypes.TEXT;
	}

}
