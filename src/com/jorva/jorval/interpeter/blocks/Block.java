package com.jorva.jorval.interpeter.blocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import com.jorva.jorval.interpeter.Interpeter;
import com.jorva.jorval.interpeter.vars.Variable;

public class Block {
	@SuppressWarnings("unchecked")
	public static void execute(Collection<String> code,HashMap<String, Variable> current, HashMap<String, HashMap<String, Variable>> vars, String variableKey){
		if(variableKey == null){
			variableKey = "";
		}
		long l = Math.abs(System.currentTimeMillis());
		int i = new Random().nextInt((int) l/19);
		variableKey = l + variableKey + i;
		vars.put(variableKey, (HashMap<String, Variable>) current.clone());
		new Interpeter(code, vars.get(variableKey), variableKey);
		vars.remove(variableKey);
	}
}
