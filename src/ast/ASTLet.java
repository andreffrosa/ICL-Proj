package ast;

import ivalues.IValue;

import java.util.List;
import java.util.Map.Entry;

import environment.Environment;

public class ASTLet implements ASTNode {
	
	private List<Entry<String, ASTNode>> definitions;
	private ASTNode expBody;
	
	public ASTLet(List<Entry<String, ASTNode>> definitions, ASTNode expBody) {
		this.definitions = definitions;
		this.expBody = expBody;
	}

	@Override
	public IValue eval(Environment env) {
		
		Environment newEnv = env.beginScope();
		
		for(Entry<String, ASTNode> entry : this.definitions) {

			newEnv.associate(entry.getKey(), entry.getValue().eval(env));
		}
		
		IValue value = expBody.eval(newEnv);
		
		newEnv.endScope();
		
		return value;
	}
}