package environment;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class EnvironmentClass<T> implements Environment<T> {
	
	private static final int DEFAULT_SIZE = 10;
	
	private EnvironmentClass<T> parentEnv;
	private Map<String, T> associations;

	private String currId;
	
	public EnvironmentClass() {
		this(null, null);
	}

	public EnvironmentClass(EnvironmentClass<T> parentEnv) {
		this(parentEnv, null);
	}
	
	public EnvironmentClass(EnvironmentClass<T> parentEnv, String newEnvId) {
		this.currId = newEnvId;
		this.parentEnv = parentEnv;
		this.associations = new HashMap<>(DEFAULT_SIZE);
	}
	
	public EnvironmentClass<T> beginScope() {
		return new EnvironmentClass<T>(this);
	}

	@Override
	public Environment<T> beginScope(String newEnvId) {
		Environment<T> newEnv = this.beginScope();
		newEnv.setCurrEnvId(newEnvId);
		return newEnv;
	}

	public EnvironmentClass<T> endScope() {
		this.currId = this.parentEnv.getCurrEnvId();
		return this.parentEnv;
	}

	@Override
	public Environment<T> getParentEnv() {
		return this.parentEnv;
	}

	public T find(String id) {
		T value = this.associations.get(id);
		if(value == null && this.parentEnv != null) {
			value = this.parentEnv.find(id);
		}		
		return value;
	}

	@Override
	public Entry<T, Integer> findLevel(String id) {
		Entry<T, Integer> entry = null;

		T value = this.associations.get(id);
		int level = 0;

		if(value == null && this.parentEnv != null) {
			entry = this.parentEnv.findLevel(id);
			entry.setValue(entry.getValue() + 1);
		} else {
			entry = new SimpleEntry<T, Integer>(value, level);
		}

		return entry;
	}

	@Override
	public void setCurrEnvId(String id) {
		this.currId = id;
	}

	@Override
	public String getCurrEnvId() {
		return this.currId;
	}

	@Override
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