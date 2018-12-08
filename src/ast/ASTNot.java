package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTNot implements ASTNode {
	
	private ASTNode node;
	
	public ASTNot(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = node.eval(env);
		
		return new Bool(!((Bool)v).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t = this.node.typecheck(env);

		if( t instanceof BoolType )
			return BoolType.getInstance();
		else
			throw new TypeException("~", BoolType.getInstance(), t);
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s = this.node.compile(env);
		
		String code = String.format("%s\n%s\n%s\n", 
				";~E", 
				s, 
				"inot"
				);

		return code;
	}

}
