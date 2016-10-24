package com.jorva.jorval.interpeter.vars;

import com.jorva.jorval.interpeter.Main;
import com.jorva.jorval.interpeter.MathUtils;

public enum VariableTypes {
	TEXT, NUMBER, BOOLEAN, ARRAY;

	public static VariableTypes getByData(String variableData) {
		if (variableData.equalsIgnoreCase("true") || variableData.equalsIgnoreCase("false"))
			return BOOLEAN;
		try {
			new VariableNumber(MathUtils.evalWithVariables(variableData, Main.getGlobalVariables()));
			return VariableTypes.NUMBER;
		} catch (Exception e) {
		}
		if(variableData.startsWith("\"") && variableData.endsWith("\""))
			return TEXT;
		return null;
	}

	public static VariableTypes fromString(String s) {
		switch (s) {
		case "text":
		case "str":
		case "string":
			return TEXT;
		case "number":
		case "num":
			return NUMBER;
		case "boolean":
		case "bool":
			return BOOLEAN;
		default:
			return null;
		}
	}
}
