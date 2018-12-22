package ast;

import environment.Environment;
import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;

public class ASTMult extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMult(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

        IValue leftVal = this.left.eval(env);
        IValue rightVal = this.right.eval(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if(t1 instanceof IntType && t2 instanceof IntType)
			return Int.multiplication((Int)leftVal, (Int)rightVal);
		else if(t1 instanceof IntType && t2 instanceof DoubleType)
			return new IDouble(((Int)leftVal).getValue() * ((IDouble)rightVal).getValue());
		else if(t1 instanceof DoubleType && t2 instanceof IntType)
			return new IDouble(((IDouble)leftVal).getValue() * ((Int)rightVal).getValue());
		else
			return new IDouble(((IDouble)leftVal).getValue() * ((IDouble)rightVal).getValue());

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
			throw new TypeException("*", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(Environment<String> env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if(t1 instanceof IntType && t2 instanceof IntType) {
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
					";left * right",
					";left", s1,
					";right", s2,
					"imul"
			);
		} else if(t1 instanceof DoubleType && t2 instanceof DoubleType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dmul\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		} else if(t1 instanceof IntType && t2 instanceof DoubleType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"i2d\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dmul\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		} else if(t1 instanceof DoubleType && t2 instanceof IntType) {
			return "new java/lang/Double\n" +
					"dup\n" +
					s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"i2d\n" +
					"dmul\n" +
					"invokespecial java/lang/Double/<init>(D)V\n";
		}
		return null;
    }
}