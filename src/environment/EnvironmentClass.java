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
	
	public T find(String id) {
		T value = this.associations.get(id);
		if(value == null && this.parentEnv != null) {
			value = this.parentEnv.find(id);
		}		
		return value;
	}
	
	public void associate(String id, T value) {
		if(associations.putIfAbsent(id, value) != null)
			throw new RuntimeException("Identifier already declared in the current environment!");
	}

	@Override
	public void smash(String id, T value) {
		if( associations.get(id) != null )
			this.associations.put(id, value);
		else
			throw new RuntimeException("Can't smash an identifier that is not declared in the current environment!");
	}
	
}