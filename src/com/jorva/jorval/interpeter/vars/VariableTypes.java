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
		return TEXT;
	}
}
