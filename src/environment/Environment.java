package environment;

import java.util.Map.Entry;

public interface Environment<T> {

	Environment<T> beginScope();

	Environment<T> beginScope(String newEnvId);
	
	void associate(String id, T value);
	
	T find(String id);

	Entry<T, Integer> findLevel(String id);

	void setCurrEnvId(String id);

	String getCurrEnvId();

	void setStaticLinkIndex(int index);

	int getStaticLinkIndex();

	Environment<T> endScope();

	Environment<T> getParentEnv();

	void smash(String id, T value);

}
