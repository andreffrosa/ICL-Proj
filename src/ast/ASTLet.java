package ast;

import itypes.IType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Undefined;

import java.util.Map;
import java.util.Map.Entry;

import compiler.StackCoordinates;
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

	@Override
	public IType typecheck(Environment<IType> env) {

		Environment<IType> newEnv = env.beginScope();

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			IType declarationType = entry.getValue().typecheck(env);
			if(!entry.getKey().getValue().equalsType(declarationType))
				throw new TypeException("=", entry.getKey().getValue(), declarationType);

			newEnv.associate(entry.getKey().getKey(), entry.getKey().getValue());
		}

		IType type = this.body.typecheck(newEnv);

		newEnv.endScope();

		return type;
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}
}