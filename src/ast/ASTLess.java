package ast;

import itypes.BoolType;
import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;
import compiler.Compiler;
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

		if( v1 instanceof Int && v2 instanceof Int )
			return new Bool(((Int)v1).getValue() < ((Int)v2).getValue());
		else if( v1 instanceof Int && v2 instanceof IDouble )
			return new Bool(((Int)v1).getValue() < ((IDouble)v2).getValue());
		else if( v1 instanceof IDouble && v2 instanceof Int )
			return new Bool(((IDouble)v1).getValue() < ((Int)v2).getValue());
		else if( v1 instanceof IDouble && v2 instanceof IDouble )
			return new Bool(((IDouble)v1).getValue() < ((IDouble)v2).getValue());
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if((t1 instanceof IntType && t2 instanceof IntType) ||
				(t1 instanceof IntType && t2 instanceof DoubleType) ||
				(t1 instanceof DoubleType && t2 instanceof IntType) ||
				(t1 instanceof DoubleType && t2 instanceof DoubleType) )
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("Wrong operands to operator <.");
	}

    @Override
    public String compile(Environment<String> env) {

    	String label1 = Compiler.newLabel();
    	String label2 = Compiler.newLabel();
    	
		IType t1 = this.left.getType();
		IType t2 = this.right.getType();
		
		if( t1 instanceof IntType && t2 instanceof IntType )
			return String.format("%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
	        		left.compile(env),
	        		right.compile(env),
					"isub",
					"iflt ", label1,
					"sipush 0",
					"goto ", label2,
					label1 + ": ",
					"sipush 1",
					label2 + ": "
			);
		else if( t1 instanceof IntType && t2 instanceof DoubleType )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
	        		left.compile(env),
	        		"i2d",
	        		right.compile(env),
	        		"invokevirtual java/lang/Double/doubleValue()D",
					"dcmpl",
					"iflt ", label1,
					"sipush 0", // false
					"goto ", label2,
					label1 + ": ",
					"sipush 1", // true
					label2 + ": "
			);
		else if( t1 instanceof DoubleType && t2 instanceof IntType )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
	        		left.compile(env),
	        		"invokevirtual java/lang/Double/doubleValue()D",
	        		right.compile(env),
	        		"i2d",
					"dcmpl",
					"iflt ", label1,
					"sipush 0", // false
					"goto ", label2,
					label1 + ": ",
					"sipush 1", // true
					label2 + ": "
			);
		else if( t1 instanceof DoubleType && t2 instanceof DoubleType )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
	        		left.compile(env),
	        		"invokevirtual java/lang/Double/doubleValue()D",
	        		right.compile(env),
	        		"invokevirtual java/lang/Double/doubleValue()D",
					"dcmpl",
					"iflt ", label1,
					"sipush 0", // false
					"goto ", label2,
					label1 + ": ",
					"sipush 1", // true
					label2 + ": "
			);
		return null;        
    }
    
    @Override
    public String cc(Environment<String> env, String tl, String fl) {
    	
		IType t1 = this.left.getType();
		IType t2 = this.right.getType();
    	
		if( t1 instanceof IntType && t2 instanceof IntType )
	    	return String.format("%s\n%s\n%s\n%s%s\n%s%s\n",
					left.compile(env),
					right.compile(env),
					"isub",
					"iflt ", tl,
					"goto ", fl
					);
		else if( t1 instanceof IntType && t2 instanceof DoubleType )
	    	return String.format("%s\n%s\n%s\n%s\n%s\n%s%s\n%s%s\n",
					left.compile(env),
					"i2d",
					right.compile(env),
					"invokevirtual java/lang/Double/doubleValue()D",
					"dcmpl",
					"iflt ", tl,
					"goto ", fl
					);
		else if( t1 instanceof DoubleType && t2 instanceof IntType )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s%s\n%s%s\n",
					left.compile(env),
					"invokevirtual java/lang/Double/doubleValue()D",
					right.compile(env),
					"i2d",
					"dcmpl",
					"iflt ", tl,
					"goto ", fl
					);
		else if( t1 instanceof DoubleType && t2 instanceof DoubleType )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s%s\n%s%s\n",
					left.compile(env),
					"invokevirtual java/lang/Double/doubleValue()D",
					right.compile(env),
					"invokevirtual java/lang/Double/doubleValue()D",
					"dcmpl",
					"iflt ", tl,
					"goto ", fl
					);
		return null;     
    }

}
