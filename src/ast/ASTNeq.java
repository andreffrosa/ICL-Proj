package ast;

import itypes.*;
import ivalues.*;
import compiler.Compiler;
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

		if( v1 instanceof Int && v2 instanceof Int)
			return new Bool(((Int)v1).getValue() != ((Int)v2).getValue());
		else if(v1 instanceof Bool && v2 instanceof Bool)
			return new Bool(((Bool)v1).getValue() != ((Bool)v2).getValue());
		else if(v1 instanceof IDouble && v2 instanceof IDouble)
			return  new Bool(((IDouble)v1).getValue() != ((IDouble)v2).getValue());
		else if(v1 instanceof Int && v2 instanceof IDouble)
			return  new Bool(((Int)v1).getValue() != ((IDouble)v2).getValue());
		else if(v1 instanceof IDouble && v2 instanceof Int)
			return  new Bool(((IDouble)v1).getValue() != ((Int)v2).getValue());
		else if(v1 instanceof Str && v2 instanceof Str)
			return  new Bool(!((Str)v1).getValue().equals(((Str)v2).getValue()));

		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if((t1 instanceof IntType && t2 instanceof IntType)
				|| (t1 instanceof BoolType && t2 instanceof BoolType)
				|| (t1 instanceof DoubleType && t2 instanceof DoubleType)
				|| (t1 instanceof IntType && t2 instanceof DoubleType)
				|| (t1 instanceof DoubleType && t2 instanceof IntType)
				|| (t1 instanceof StrType && t2 instanceof StrType))
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("operation != expects INTxINT or BOOLxBOOL or INTXBOOL or STRxSTR");
	}

    @Override
    public String compile(Environment<String> env) {
    	String label1 = Compiler.newLabel();
    	String label2 = Compiler.newLabel();

		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if((t1 instanceof BoolType && t2 instanceof BoolType) ||
				(t1 instanceof IntType && t2 instanceof IntType)) {
			return String.format("%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
					s1,
					s2,
					"isub",
					"ifne ", label1,
					"sipush 0",
					"goto ", label2,
					label1 + ": ",
					"sipush 1",
					label2 + ": "
			);
		} else if(t1 instanceof DoubleType && t2 instanceof DoubleType) {
			return 	s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dcmpl\n" +
					"ifne " + label1 + "\n" +
					"sipush 0\n" +
					"goto " + label2 + "\n" +
					label1 + ": \n" +
					"sipush 1\n" +
					label2 + ": \n";
		} else if(t1 instanceof IntType && t2 instanceof DoubleType) {
			return 	s1 + "\n" +
					"i2d\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dcmpl\n" +
					"ifne " + label1 + "\n" +
					"sipush 0\n" +
					"goto " + label2 + "\n" +
					label1 + ": \n" +
					"sipush 1\n" +
					label2 + ": \n";
		} else if(t1 instanceof DoubleType && t2 instanceof IntType) {
			return 	s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"i2d\n" +
					"dcmpl\n" +
					"ifne " + label1 + "\n" +
					"sipush 0\n" +
					"goto " + label2 + "\n" +
					label1 + ": \n" +
					"sipush 1\n" +
					label2 + ": \n";
		} else if(t1 instanceof StrType && t2 instanceof StrType) {
			return 	s1 + "\n" +
					s2 + "\n" +
					"invokevirtual java/lang/String/equals(Ljava/lang/Object;)Z\n" +  // return boolean 0 is false, 1 is true
					"ifeq " + label1 + "\n" +
					"sipush 0\n" +
					"goto " + label2 + "\n" +
					label1 + ": \n" +
					"sipush 1\n" +
					label2 + ": \n";
		}
		return null;
    }
    
    @Override
    public String cc(Environment<String> env, String tl, String fl) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		IType t1 = this.left.getType();
		IType t2 = this.right.getType();

		if((t1 instanceof BoolType && t2 instanceof BoolType) ||
				(t1 instanceof IntType && t2 instanceof IntType)) {
			return String.format("%s\n%s\n%s\n%s%s\n%s%s\n",
					s1,
					s2,
					"isub",
					"ifne ", tl,
					"goto ", fl
			);
		} else if(t1 instanceof DoubleType && t2 instanceof DoubleType) {
			return 	s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dcmpl\n" +
					"ifne " + tl + "\n" +
					"goto " + fl + "\n";
		} else if(t1 instanceof IntType && t2 instanceof DoubleType) {
			return 	s1 + "\n" +
					"i2d\n" +
					s2 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					"dcmpl\n" +
					"ifne " + tl + "\n" +
					"goto " + fl + "\n";
		} else if(t1 instanceof DoubleType && t2 instanceof IntType) {
			return 	s1 + "\n" +
					"invokevirtual java/lang/Double/doubleValue()D\n" +
					s2 + "\n" +
					"i2d\n" +
					"dcmpl\n" +
					"ifne " + tl + "\n" +
					"goto " + fl + "\n";
		} else if(t1 instanceof StrType && t2 instanceof StrType) {
			return 	s1 + "\n" +
					s2 + "\n" +
					"invokevirtual java/lang/String/equals(Ljava/lang/Object;)Z\n" + // return boolean 0 is false, 1 is true
					"ifeq " + tl + "\n" +
					"goto " + fl + "\n";
		}
		return null;
    }

}
