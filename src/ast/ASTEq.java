package ast;

import itypes.*;
import ivalues.Bool;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;
import compiler.Compiler;
import environment.Environment;

public class ASTEq extends ASTNodeClass {
	
	private ASTNode left, right;
	
	public ASTEq(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int)
			return new Bool(((Int)v1).getValue() == ((Int)v2).getValue());
		else if(v1 instanceof Bool)
			return new Bool(((Bool)v1).getValue() == ((Bool)v2).getValue());
		else if(v1 instanceof IDouble)
			return  new Bool(((IDouble)v1).getValue() == ((IDouble)v2).getValue());

		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if((t1 instanceof IntType && t2 instanceof IntType)
		|| (t1 instanceof BoolType && t2 instanceof BoolType)
		|| (t1 instanceof DoubleType && t2 instanceof DoubleType))
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("Invalid Operands for operation \"==\"");
	}

    @Override
    public String compile(Environment<String> env) {
    	String label1 = Compiler.newLabel();
    	String label2 = Compiler.newLabel();

		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if(t1 instanceof IntType && t2 instanceof IntType) {
			return String.format("%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
					s1,
					s2,
					"isub",
					"ifeq ", label1,
					"sipush 0",
					"goto ", label2,
					label1 + ": ",
					"sipush 1",
					label2 + ": "
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
    
    @Override
    public String cc(Environment<String> env, String tl, String fl) {
    	return String.format("%s\n%s\n%s\n%s%s\n%s%s\n",
				left.compile(env),
				right.compile(env),
				"isub",
				"ifeq ", tl,
				"goto ", fl
				);
    }

}
