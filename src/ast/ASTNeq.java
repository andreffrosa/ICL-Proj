package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTNeq extends ASTNodeClass {
	
	private ASTNode left, right;
	
	public ASTNeq(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int )
			return new Bool(((Int)v1).getValue() != ((Int)v2).getValue());
		else
			return new Bool(((Bool)v1).getValue() != ((Bool)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if((t1 instanceof IntType && t2 instanceof IntType)
				||	(t1 instanceof BoolType && t2 instanceof BoolType))
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("operation != expects INTxINT or BOOLxBOOL");
	}

    @Override
    public String compile(Environment<String> env) {
        return null;
    }

}
