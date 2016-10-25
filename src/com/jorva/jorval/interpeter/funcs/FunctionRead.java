package com.jorva.jorval.interpeter.funcs;

import java.util.Scanner;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.exceptions.InterpeterException;
import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableText;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public class FunctionRead implements Function {

	Scanner in;

	public FunctionRead() {
		in = new Scanner(System.in);
	}

	@Override
	public Variable exec(Interpeter i, Variable... params) {
		for (Variable v : params) {
			System.out.print(v.getData());
		}
		String line = in.nextLine();
		try {
			return Variable.interpet(null, line, i);
		} catch (InterpeterException e) {
		}
		return new VariableText(line);
	}

	@Override
	public String getKey() {
		return "read";
	}

	@Override
	public VariableTypes getReturnType() {
		return VariableTypes.TEXT;
	}

}
