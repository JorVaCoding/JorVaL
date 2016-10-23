package com.jorva.jorval.interpeter.funcs;

import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public class FunctionPrint implements Function {

	private boolean line;

	public FunctionPrint(boolean line) {
		this.line = line;
	}

	@Override
	public Variable exec(Variable... params) {
		String msg = "";
		for (Variable v : params) {
			if (v != null)
				msg += v.getData();
		}
		
		System.out.println(msg);

		return null;
	}

	@Override
	public String getKey() {
		if (line)
			return "println";
		return "print";
	}

	@Override
	public VariableTypes getReturnType() {
		return null;
	}

}
