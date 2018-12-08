package ast;

import compiler.StackCoordinates;
import environment.Environment;

import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTDiv implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTDiv(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

		Int v1 = (Int)left.eval(env);
		Int v2 = (Int)right.eval(env);
		
		return Int.division(v1, v2);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.left.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return IntType.getInstance();
		else
			throw new TypeException("/", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}
	
	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);
		
		String code = String.format("%s\n%s\n%s\n%s\n%s\n%s\n", 
				";left / right", 
				";left", s1, 
				";right", s2,
				"idiv"
				);

		return code;
	}
}