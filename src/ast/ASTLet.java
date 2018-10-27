package ast;

import types.IValue;

import java.util.Map;

import environment.EnvironmentClass;

public class ASTLet implements ASTNode {
	
	private Map<String, ASTNode> definitions;
	private ASTNode expBody;
	
	public ASTLet(Map<String, ASTNode> definitions, ASTNode expBody) {
		this.definitions = definitions;
		this.expBody = expBody;
	}

	@Override
	public IValue eval(EnvironmentClass env) {
		
		EnvironmentClass newEnv = env.beginScope();
		
		for(String name : this.definitions.keySet()) {
			newEnv.associate(name, this.definitions.get(name).eval(env));
		}
		
		IValue value = expBody.eval(newEnv);
		
		newEnv.endScope();
		
		return value;
	}
}