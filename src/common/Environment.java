package common;

import types.IValue;

import java.util.Map;
import java.util.HashMap;

public class Environment {
	
	private static final int DEFAULT_SIZE = 10;
	
	private Environment parentEnv;
	private Map<String, IValue> associations;
	
	public Environment() {
		this(null);
	}
	
	public Environment(Environment parentEnv) {
		this.parentEnv = parentEnv;
		this.associations = new HashMap<String, IValue>(DEFAULT_SIZE);		
	}
	
	public Environment beginScope() {
		return new Environment(this);
	}
	
	public Environment endScope() {
		return this.parentEnv;
	}
	
	public IValue find(String name) {
		IValue value = this.associations.get(name);
		if(value == null && this.parentEnv != null) {
			value = this.parentEnv.find(name);
		}		
		return value;
	}
	
	public void associate(String name, IValue value) {
		this.associations.put(name, value);
	}
	
}