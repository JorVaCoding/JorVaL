package com.jorva.jorval.interpeter.vars;

import java.util.List;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.funcs.Function;

public class VariableText extends Variable {

	public VariableText(String o) {
		super(o);
	}

	@Override
	public VariableTypes getType() {
		return VariableTypes.TEXT;
	}

	@Override
	public String getData() {
		return (String) super.getData();
	}

	@Override
	public List<Function> getFunctions() {
		List<Function> out = super.getFunctions();
		out.add(new Function() {//toUpperCase

			@Override
			public VariableTypes getReturnType() {
				return VariableTypes.TEXT;
			}

			@Override
			public String getKey() {
				return "toUpperCase";
			}

			@Override
			public Variable exec(Interpeter i, Variable... params) {
				if (!(params[0] instanceof VariableText))
					return null;
				VariableText var = (VariableText) params[0];
				var.setData(var.getData().toUpperCase());
				return var;
			}
		});
		out.add(new Function() {//toLowerCase

			@Override
			public VariableTypes getReturnType() {
				return VariableTypes.TEXT;
			}

			@Override
			public String getKey() {
				return "toLowerCase";
			}

			@Override
			public Variable exec(Interpeter i, Variable... params) {
				if (!(params[0] instanceof VariableText))
					return null;
				VariableText var = (VariableText) params[0];
				var.setData(var.getData().toLowerCase());
				return var;
			}
		});
		return out;
	}

}
