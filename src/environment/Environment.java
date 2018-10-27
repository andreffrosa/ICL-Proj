package environment;

import ivalues.IValue;

public interface Environment {

	Environment beginScope();
	
	void associate(String id, IValue value);
	
	IValue find(String id);
	
	Environment endScope();

}
