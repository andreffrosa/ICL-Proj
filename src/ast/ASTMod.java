package ast;

import environment.Environment;
import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;

public class ASTMod extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMod(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int && v2 instanceof Int )
			return Int.module((Int)v1, (Int)v2);
		else {
			IDouble d1 = (v1 instanceof IDouble) ? (IDouble)v1 : new IDouble(((Int)v1).getValue());
			IDouble d2 = (v2 instanceof IDouble) ? (IDouble)v2 : new IDouble(((Int)v2).getValue());
			return IDouble.module(d1, d2);
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else if((t1 instanceof DoubleType && (t2 instanceof DoubleType || t2 instanceof IntType))
				|| ((t1 instanceof DoubleType || t1 instanceof IntType ) && t2 instanceof DoubleType)) {
			return (super.nodeType = DoubleType.getInstance());
		} else
			throw new TypeException("Wrong operands' type for % operator.");
	}

    @Override
    public String compile(Environment<String> env) {

		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);
		
		IType t1 = this.left.getType();
		IType t2 = this.right.getType();
		
		if( t1 instanceof IntType && t2 instanceof IntType ) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
					";left % right",
					";left", s1,
					";right", s2,
					"irem"
			);
		} else if( t1 instanceof DoubleType && t2 instanceof IntType ) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					"new java/lang/Double",
					"dup",
					s1,
					"invokevirtual java/lang/Double/doubleValue()D",
					s2,
					"i2d",
					"drem",
					"invokespecial java/lang/Double/<init>(D)V"
			);
		} else if( t1 instanceof IntType && t2 instanceof DoubleType ) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					"new java/lang/Double",
					"dup",
					s1,
					"i2d",
					s2,
					"invokevirtual java/lang/Double/doubleValue()D",
					"drem",
					"invokespecial java/lang/Double/<init>(D)V"
			);
		} else if( t1 instanceof DoubleType && t2 instanceof DoubleType ) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					"new java/lang/Double",
					"dup",
					s1,
					"invokevirtual java/lang/Double/doubleValue()D",
					s2,
					"invokevirtual java/lang/Double/doubleValue()D",
					"drem",
					"invokespecial java/lang/Double/<init>(D)V"
			);
		}
		return null;
    }
}