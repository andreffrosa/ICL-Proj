package ast;

import environment.Environment;
import ivalues.IValue;

public class ASTId implements ASTNode {
	
	private String name;
	
	public ASTId(String name) {
		this.name = name;
	}
	
	public IValue eval(Environment<IValue> env) {
		IValue value = env.find(this.name);

		if(value == null) {
			throw new RuntimeException("Iligal definition of id: " + this.name);
		}

		return value;
	}
}
