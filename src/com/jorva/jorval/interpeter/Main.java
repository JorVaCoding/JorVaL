package com.jorva.jorval.interpeter;

import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import com.jorva.jorval.interpeter.funcs.FunctionRegistry;
import com.jorva.jorval.interpeter.vars.Variable;

public class Main {
	private static HashMap<String, HashMap<String, Variable>> variables;

	public static void main(String[] args) throws URISyntaxException {

		debug();
		FunctionRegistry.init();
		variables = new HashMap<String, HashMap<String, Variable>>();
		variables.put("global", new HashMap<String, Variable>());
		new Interpeter(Main.class.getResource("/test.jvl").toURI(), getGlobalVariables(), null);
		
	}

	public static HashMap<String, Variable> getGlobalVariables() {
		return variables.get("global");
	}
	
	public static void runBlock(List<String> code){
		
	}

	private static void debug() {
		System.setOut(new TracingPrintStream(System.out));
		System.setErr(new TracingPrintStream(System.err));
	}
	
	public static synchronized HashMap<String, HashMap<String, Variable>> getVariables(){
		return variables;
	}

}

class TracingPrintStream extends PrintStream {
	public TracingPrintStream(PrintStream original) {
		super(original);
	}

	@Override
	public void println(String line) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		// Element 0 is getStackTrace
		// Element 1 is println
		// Element 2 is the caller
		StackTraceElement caller = stack[2];
		super.println(String.format("[%s.%s<%s>] %s", caller.getClassName(), caller.getMethodName(),
				caller.getLineNumber(), line));
	}
}
