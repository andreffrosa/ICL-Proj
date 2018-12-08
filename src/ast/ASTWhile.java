package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTWhile implements ASTNode {
	
	private ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		while(true) {
			IValue cond = condition.eval(e);

			if( ((Bool) cond).getValue() ) {
				body.eval(e);
				// System.out.println(result);
			} else {
				return cond;
			}
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType cond = this.condition.typecheck(env);

		if(!(cond instanceof BoolType))
			throw new TypeException("while", BoolType.getInstance(), cond);

		this.body.typecheck(env);

		return BoolType.getInstance();
	}
}
