package ast;

import environment.EnvironmentClass;
import types.IValue;

public class ASTId implements ASTNode {
	
	private String name;
	
	public ASTId(String name) {
		this.name = name;
	}
	
	public IValue eval(EnvironmentClass env) {
		return env.find(this.name);
	}
}