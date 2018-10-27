package ast;

import common.Environment;
import types.IValue;

public class ASTId implements ASTNode {
	
	private String name;
	
	public ASTId(String name) {
		this.name = name;
	}
	
	public IValue eval(Environment env) {
		return env.find(this.name);
	}
}