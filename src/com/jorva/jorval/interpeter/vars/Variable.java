package com.jorva.jorval.interpeter.vars;

import java.util.LinkedList;
import java.util.List;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.Main;
import com.jorva.jorval.interpeter.MathUtils;
import com.jorva.jorval.interpeter.exceptions.InterpeterException;
import com.jorva.jorval.interpeter.funcs.Function;

public abstract class Variable {
	private Object data;

	public Variable(Object data) {
		this.data = data;
	}

	public abstract VariableTypes getType();

	protected void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public static Variable interpet(VariableTypes type, String input, Interpeter i) throws InterpeterException {

		for (String string : i.getVariables().keySet()) {
			if (string.equals(input)) {
				Variable v = i.getVariables().get(string);
				if (type == null || type == v.getType())
					return v;
				else if (type == VariableTypes.TEXT)
					return new VariableText(v.getData() + "");
			}
		}

		try {
			Variable funcOut = i.runFunction(input);
			if (funcOut != null) {
				return funcOut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (type == null)
			for (VariableTypes type1 : VariableTypes.values()) {
				try {
					Variable v = interpet(type1, input, i);
					if (v != null)
						return v;
				} catch (Exception e) {
				}
			}

		if (type != null)
			switch (type) {
			case TEXT:
				String text = input;
				if (input.startsWith("\"") && input.endsWith("\"")) {
					text = input.substring(1, input.length() - 1);
					return new VariableText(text);
				}
			case NUMBER:
				return new VariableNumber(MathUtils.evalWithVariables(input, Main.getGlobalVariables()));
			case BOOLEAN:
				if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false"))
					return new VariableBoolean(Boolean.parseBoolean(input));
			default:
				break;
			}
		throw new InterpeterException("Couldn't create/find variable [" + input + "]", i, null);

	}

	public List<Function> getFunctions() {
		List<Function> out = new LinkedList<Function>();

		return out;
	}
}
