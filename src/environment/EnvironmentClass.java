package environment;

import java.util.Map;
import java.util.HashMap;

public class EnvironmentClass<T> implements Environment<T> {
	
	private static final int DEFAULT_SIZE = 10;
	
	private EnvironmentClass<T> parentEnv;
	private Map<String, T> associations;
	
	public EnvironmentClass() {
		this(null);
	}
	
	public EnvironmentClass(EnvironmentClass<T> parentEnv) {
		this.parentEnv = parentEnv;
		this.associations = new HashMap<String, T>(DEFAULT_SIZE);		
	}
	
	public EnvironmentClass<T> beginScope() {
		return new EnvironmentClass<T>(this);
	}
	
	public EnvironmentClass<T> endScope() {
		return this.parentEnv;
	}
	
	public T find(String name) {
		T value = this.associations.get(name);
		if(value == null && this.parentEnv != null) {
			value = this.parentEnv.find(name);
		}		
		return value;
	}
	
	public void associate(String name, T value) {
		this.associations.put(name, value);
	}
	
}