package com.jorva.jorval.interpeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.jorva.jorval.interpeter.exceptions.InterpeterException;
import com.jorva.jorval.interpeter.funcs.Function;
import com.jorva.jorval.interpeter.funcs.FunctionRegistry;
import com.jorva.jorval.interpeter.vars.Variable;
import com.jorva.jorval.interpeter.vars.VariableTypes;

public class Interpeter {
	private URI filePath = null;
	private File file = null;
	int lineNum = 0;
	public final String name;

	public Interpeter(URI filePath, HashMap<String, Variable> variables, String name) {
		this.filePath = filePath;
		file = new File(this.filePath);
		this.name = name;
		new Interpeter(read(file), variables, name);
	}

	public Interpeter(ArrayList<String> al, HashMap<String, Variable> variables, String name) {
		this.name = name;
		for (String line : al) {
			lineNum++;
			line = line.trim();
			if (line.startsWith("//") || line.isEmpty())
				continue;
			if (line.endsWith(";"))
				line = line.substring(0, line.length() - 1);

			if (line.startsWith("var")) {
				createVariable(line, null);
				continue;
			}

			for (String s : variables.keySet()) {
				if (line.startsWith(s) && (line.split(s)[1].trim().startsWith("="))) {
					createVariable(line, s);
					break;
				}
			}

			try {
				runFunction(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Variable runFunction(String line) throws Exception {
		for (Function func : FunctionRegistry.getFunctions()) {
			if (line.startsWith(func.getKey()) && line.split(func.getKey())[1].startsWith("(")) {
				String paramsString = line.substring(line.indexOf('(') + 1, line.length() - 1);
				String params[] = paramsString.split(",");
				LinkedList<Variable> paramsAL = new LinkedList<Variable>();
				for (String p : params) {
					p = p.trim();
					if (Main.getGlobalVariables().containsKey(p)) {
						paramsAL.add(Main.getGlobalVariables().get(p));
					} else {
						Variable var = Variable.interpet(null, p, this);
						if (var != null) {
							paramsAL.add(var);
						} else {
							try {
								throw new InterpeterException("Couldn't find variable " + var, this, lineNum);
							} catch (InterpeterException e) {
								e.printStackTrace();
								continue;
							}
						}
					}
				}
				return func.exec(paramsAL.toArray(new Variable[] {}));
			}
		}
		return null;
	}

	public void createVariable(String s, String vName) {
		String vType = null;
		String variableData = s.split("=", 2)[1].trim();
		if (s.contains("<") && s.contains(">")) {
			vType = s.substring(s.indexOf('<') + 1, s.indexOf('>'));
		} else {
			vType = VariableTypes.getByData(variableData).toString().toLowerCase();
		}
		
		try {
			Variable variable = Variable.interpet(VariableTypes.fromString(vType), variableData, this);
			Main.getGlobalVariables().put(vName != null ? vName : s.split(" ")[1], variable);
		} catch (InterpeterException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> read(File f) {
		ArrayList<String> output = new ArrayList<String>();
		if (!f.exists())
			return output;
		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line = reader.readLine();
			while (line != null) {
				output.add(line);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;

	}

	public int getLine() {
		return lineNum;
	}
}
