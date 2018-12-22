package ast;

import environment.Environment;
import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;

public class ASTSub extends ASTNodeClass {
	
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

		if(v1 instanceof Int && v2 instanceof Int)
			return new Int(((Int)v1).getValue() - ((Int)v2).getValue());
		else if(v1 instanceof Int && v2 instanceof IDouble)
			return new IDouble(((Int)v1).getValue() - ((IDouble)v2).getValue());
		else if(v1 instanceof IDouble && v2 instanceof Int)
			return new IDouble(((IDouble)v1).getValue() - ((Int)v2).getValue());
		else
			return new IDouble(((IDouble)v1).getValue() - ((IDouble)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else if((t1 instanceof DoubleType && t2 instanceof DoubleType) ||
				(t1 instanceof IntType && t2 instanceof DoubleType) ||
				(t1 instanceof DoubleType && t2 instanceof  IntType))
			return (super.nodeType = DoubleType.getInstance());
		else
			throw new TypeException("Invalid Operands for operation \"-\"");
	}

    @Override
    public String compile(Environment<String> env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if(t1 instanceof IntType && t2 instanceof IntType) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					";left + right",
					";left", s1,
					";right",
					s2,
					"ineg",
					"iadd"
			);
		} else if(t1 instanceof DoubleType && t2 instanceof DoubleType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dsub\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		} else if(t1 instanceof IntType && t2 instanceof DoubleType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"i2d\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dsub\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		} else if(t1 instanceof DoubleType && t2 instanceof IntType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"i2d\n" +
					"dsub\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		}
		return null;
    }
}