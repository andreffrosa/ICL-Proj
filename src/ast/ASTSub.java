package ast;

import ast.ASTNode;
import compiler.StackCoordinates;
import environment.Environment;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTSub implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTSub(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		IValue v1, v2;
		v1 = left.eval(e);
		v2 = right.eval(e);
		
		return new Int(((Int)v1).getValue() - ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return IntType.getInstance();
		else
			throw new TypeException("-", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);
		
		String code = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
				";left + right", 
				";left", s1, 
				";right",
				s2, 
				"ineg",
				"iadd"
				);

		return code;
	}
}