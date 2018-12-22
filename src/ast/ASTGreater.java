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
import compiler.Compiler;

public class ASTGreater extends ASTNodeClass {
	
	private ASTNode left, right;
	
	public ASTGreater(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		return new Bool(((Int)v1).getValue() > ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException(">", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(FrameEnvironment env) {

    	String label1 = Compiler.newLabel();
    	String label2 = Compiler.newLabel();
    	
        return String.format("%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
        		left.compile(env),
        		right.compile(env),
				"isub",
				"ifgt ", label1,
				"sipush 0",
				"goto ", label2,
				label1 + ": ",
				"sipush 1",
				label2 + ": "
		);
    }
    
    @Override
    public String cc(FrameEnvironment env, String tl, String fl) {
    	return String.format("%s\n%s\n%s\n%s%s\n%s%s\n",
				left.compile(env),
				right.compile(env),
				"isub",
				"ifgt ", tl,
				"goto ", fl
				);
    }

}
