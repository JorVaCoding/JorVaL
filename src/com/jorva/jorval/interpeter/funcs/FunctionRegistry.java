package com.jorva.jorval.interpeter.funcs;

import java.util.LinkedList;

public class FunctionRegistry {
	
	private static LinkedList<Function> funcs;
	
	public static synchronized LinkedList<Function> getFunctions(){
		return funcs;
	}

	public static void init() {
		funcs = new LinkedList<Function>();
		getFunctions().add(new FunctionPrint(true));
		getFunctions().add(new FunctionPrint(false));
		getFunctions().add(new FunctionTypeOf());
		getFunctions().add(new FunctionRead());
		
	}

}
