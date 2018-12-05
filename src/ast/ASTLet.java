package ast;

import itypes.IType;
import ivalues.IValue;
import ivalues.Undefined;

import java.util.Map;
import java.util.Map.Entry;

import environment.Environment;

public class ASTLet implements ASTNode {
	
	private Map<Entry<String, IType>, ASTNode> declarations;
	private ASTNode body;
	
	public ASTLet(Map<Entry<String, IType>, ASTNode> declarations, ASTNode body) {
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		Environment<IValue> newEnv = env.beginScope();
		
		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			//IValue val = entry.getValue().eval(env);
			newEnv.associate(id, new Undefined());
		}

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			IValue val = entry.getValue().eval(newEnv);
			newEnv.smash(id, val);
		}
		
		IValue value = body.eval(newEnv);
		
		newEnv.endScope();
		
		return value;
	}
}