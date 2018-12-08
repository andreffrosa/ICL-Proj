package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTIf implements ASTNode {
	
	private ASTNode condition, if_body, else_body;
	
	public ASTIf(ASTNode condition, ASTNode if_body, ASTNode else_body) {
		this.condition = condition;
		this.if_body = if_body;
		this.else_body = else_body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue cond = condition.eval(env);
		if( cond instanceof Bool ) {
			
			if( ((Bool) cond).getValue() ) {
				if_body.eval(env);
			} else {
				else_body.eval(env);
			}
			return cond;
		}
		throw new RuntimeException("TypeError: Condition does not evaluate to a boolean!");
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType cond = this.condition.typecheck(env);

		if(!(cond instanceof BoolType))
			throw new TypeException("if", BoolType.getInstance(), cond);

		this.if_body.typecheck(env);
		this.else_body.typecheck(env);

		return BoolType.getInstance();
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}
}
