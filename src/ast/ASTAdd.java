package ast;

import itypes.BoolType;
import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.StrType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;
import ivalues.Str;
import environment.Environment;

public class ASTAdd extends ASTNodeClass {
	
	ASTNode left, right;
	
	public ASTAdd(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int  )
			return new Int(((Int)v1).getValue() + ((Int)v2).getValue());
		else if( v1 instanceof IDouble && v2 instanceof IDouble  )
			return new IDouble(((IDouble)v1).getValue() + ((IDouble)v2).getValue());
		else if( v1 instanceof Str && v2 instanceof Str  )
			return new Str(String.format("%s%s", ((Str)v1).getValue(), ((Str)v2).getValue()));
		else {
			if( v1 instanceof Str ) {
				if( v2 instanceof Int )
					return new Str(String.format("%s%d", ((Str)v1).getValue(), ((Int)v2).getValue()));
				else if( v2 instanceof Bool )
					return new Str(String.format("%s%b", ((Str)v1).getValue(), ((Bool)v2).getValue()));
				else if( v2 instanceof IDouble )
					return new Str(String.format("%s%f", ((Str)v1).getValue(), ((IDouble)v2).getValue()));
			} else if( v2 instanceof Str ) {
				if( v1 instanceof Int )
					return new Str(String.format("%s%s", ((Str)v1).getValue(), ((Int)v2).getValue()));
				else if( v1 instanceof Bool )
					return new Str(String.format("%s%b", ((Str)v1).getValue(), ((Bool)v2).getValue()));
				else if( v2 instanceof IDouble )
					return new Str(String.format("%s%f", ((Str)v1).getValue(), ((IDouble)v2).getValue()));
			} else if( v1 instanceof IDouble && v2 instanceof Int ) {
				return new IDouble(((IDouble)v1).getValue() + ((Int)v2).getValue());
			} else if( v1 instanceof Int && v2 instanceof IDouble) {
				return new IDouble(((Int)v1).getValue() + ((IDouble)v2).getValue());
			}
		}
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());

		if( t1 instanceof IntType && t2 instanceof IntType  )
			return (super.nodeType = IntType.getInstance());
		else if( t1 instanceof DoubleType && t2 instanceof DoubleType  )
			return (super.nodeType = DoubleType.getInstance());
		else if( t1 instanceof Str && t2 instanceof Str  )
			return (super.nodeType = StrType.getInstance());
		else {
			if( t1 instanceof StrType ) {
				if( t2 instanceof IntType || t2 instanceof BoolType || t2 instanceof DoubleType )
					return (super.nodeType = StrType.getInstance());
			} else if( t2 instanceof StrType ) {
				if( t1 instanceof IntType || t1 instanceof BoolType || t1 instanceof DoubleType )
					return (super.nodeType = StrType.getInstance());
			} else if( (t1 instanceof DoubleType && t2 instanceof IntType) || (t1 instanceof IntType && t2 instanceof DoubleType)) {
				return (super.nodeType = DoubleType.getInstance());
			} 
		}

		throw new TypeException("Wrong opperands to operator +.");
	}

    @Override
    public String compile(Environment<String> env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);
		
		IType t1 = this.left.getType();
		IType t2 = this.right.getType();
		
		if( t1 instanceof IntType && t2 instanceof IntType  )
			return String.format("%s\n%s\n%s\n%s\n%s\n",
					";left", s1,
					";right", s2,
					"iadd"
			);
		else if( t1 instanceof IDouble && t2 instanceof IDouble  )
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
					"new java/lang/Double",
					"dup",
					s1,
					"invokevirtual java/lang/Double/doubleValue()D",
					s2,
					"invokevirtual java/lang/Double/doubleValue()D",
					"dadd",
					"invokespecial java/lang/Double/<init>(D)V"
			);
		else if( t1 instanceof StrType && t2 instanceof StrType  )
			return String.format("%s\n%s\n%s\n",
					s1,
					s2,
					"invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;"
			); 
		else {
			if( t1 instanceof StrType ) {
				return String.format("%s\n%s\n%s\n",
						s1,
						s2,
						ASTToString.convertToString(t2),
						"invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;"
						);
			} else if( t2 instanceof StrType ) {
				return String.format("%s\n%s\n%s\n",
						s1,
						ASTToString.convertToString(t1),
						s2,
						"invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;"
						);
			} else if( t1 instanceof DoubleType && t2 instanceof IntType ) {
				return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
						"new java/lang/Double",
						"dup",
						s1,
						"invokevirtual java/lang/Double/doubleValue()D",
						s2,
						"i2d",
						"dadd",
						"invokespecial java/lang/Double/<init>(D)V"
				);
			} else if( t1 instanceof Int && t2 instanceof IDouble) {
				return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", 
						"new java/lang/Double",
						"dup",
						s1,
						"i2d",
						s2,
						"invokevirtual java/lang/Double/doubleValue()D",
						"dadd",
						"invokespecial java/lang/Double/<init>(D)V"
				);
			}
		}
		return null;
    }

}
