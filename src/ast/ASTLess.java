package ast;

import environment.FrameEnvironment;
import itypes.BoolType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTLess extends ASTNodeClass {
	
	ASTNode left, right;
	
	public ASTLess(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);

		return new Bool(((Int)v1).getValue() < ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("<", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(FrameEnvironment env) {
        return null;
    }

}
