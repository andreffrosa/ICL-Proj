package ast;

import ivalues.IValue;

import java.util.Map;
import java.util.Map.Entry;

import environment.Environment;

public class ASTLet implements ASTNode {
	
	private Map<String, ASTNode> declarations;
	private ASTNode body;
	
	public ASTLet(Map<String, ASTNode> declarations, ASTNode body) {
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		Environment<IValue> newEnv = env.beginScope();
		
		for(Entry<String, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey();
			IValue val = entry.getValue().eval(env);
			newEnv.associate(id, val);
		}
		
		IValue value = body.eval(newEnv);
		
		newEnv.endScope();
		
		return value;
	}
}