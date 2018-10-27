package environment;

import types.IValue;

import java.util.Map;
import java.util.HashMap;

public class EnvironmentClass implements Environment {
	
	private static final int DEFAULT_SIZE = 10;
	
	private EnvironmentClass parentEnv;
	private Map<String, IValue> associations;
	
	public EnvironmentClass() {
		this(null);
	}
	
	public EnvironmentClass(EnvironmentClass parentEnv) {
		this.parentEnv = parentEnv;
		this.associations = new HashMap<String, IValue>(DEFAULT_SIZE);		
	}
	
	public EnvironmentClass beginScope() {
		return new EnvironmentClass(this);
	}
	
	public EnvironmentClass endScope() {
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