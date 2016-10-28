package com.jorva.jorval.interpeter.funcs;

import java.util.Collection;
import java.util.HashMap;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.Main;
import com.jorva.jorval.interpeter.blocks.Block;
import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public class FunctionBlock implements Function{
	
	private String name;
	private Collection<String> lines;
	public FunctionBlock(String name, Collection<String> lines) {
		this.name = name;
		this.lines = lines;
	}

	@Override
	public Variable exec(Interpeter interpeter, Variable... params) {
		@SuppressWarnings("unchecked")
		HashMap<String, Variable> variables = (HashMap<String, Variable>)interpeter.getVariables().clone();
		for(int i = 0; i < params.length; i++){
			variables.put("param" + i, params[i]);
		}
		Block.execute(lines, variables, Main.getVariables(), name);
		return null;
	}

	@Override
	public String getKey() {
		return name;
	}

	@Override
	public VariableTypes getReturnType() {
		return null;
	}

}
