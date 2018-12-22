package ast;

import environment.FrameEnvironment;
import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;
import compiler.Compiler;

public class ASTIf extends ASTNodeClass {
	
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

		IType type = this.if_body.typecheck(env);
		this.else_body.typecheck(env);

		return (super.nodeType = type);
	}

	@Override
	public String compile(FrameEnvironment env) {

		String l1 = Compiler.newLabel();
		String l2 = Compiler.newLabel();
		String le = Compiler.newLabel();
		
		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
				this.condition.cc(env, l1, l2), 
				l1 + ": ",
				if_body.compile(env),
				"goto " + le,
				l2 + ": ",
				else_body.compile(env),
				le + ": "
		);
	}
}
