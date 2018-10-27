package environment;

public interface Environment<T> {

	Environment<T> beginScope();
	
	void associate(String id, T value);
	
	T find(String id);
	
	Environment<T> endScope();

}
