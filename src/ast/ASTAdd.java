package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.IntType;
import itypes.StrType;
import itypes.TypeException;
import ivalues.Bool;
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
		else {
			if( v1 instanceof Str ) {
				if( v2 instanceof Int )
					return new Str(String.format("%s%d", ((Str)v1).getValue(), ((Int)v2).getValue()));
				else if( v2 instanceof Bool )
					return new Str(String.format("%s%b", ((Str)v1).getValue(), ((Bool)v2).getValue()));
			} else if( v2 instanceof Str ) {
				if( v1 instanceof Int )
					return new Str(String.format("%s%s", ((Str)v1).getValue(), ((Int)v2).getValue()));
				else if( v1 instanceof Bool )
					return new Str(String.format("%s%b", ((Str)v1).getValue(), ((Int)v2).getValue()));
			}
			else
				return new Str(String.format("%s%s", ((Str)v1).getValue(), ((Str)v2).getValue()));
		}
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());

		boolean valid = false;
		if( t1 instanceof StrType ) {
			if( t2 instanceof IntType )
				valid = true;
			else if( t2 instanceof BoolType )
				valid = true;
		} else if( t2 instanceof StrType ) {
			if( t1 instanceof IntType )
				valid = true;
			else if( t1 instanceof BoolType )
				valid = true;
		} else if( t1 instanceof StrType && t2 instanceof StrType)
			valid = true;

		if(valid)
			return (super.nodeType = StrType.getInstance());

		throw new TypeException("+", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(Environment<String> env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);
		
		if( this.nodeType instanceof IntType ) {
			return String.format("%s\n%s\n%s\n%s\n%s\n",
					";left", s1,
					";right", s2,
					"iadd"
			);
		} else {
			String c1 = "", c2 = "";
			if( ! (this.left.getType() instanceof StrType) ) {
				// TODO c1 = ASTToString.convertToString(this.left.getType());
			}
			if( ! (this.right.getType() instanceof StrType) ) {
				// TODO c2 = ASTToString.convertToString(this.right.getType());
			}
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					";left", s1,
					c1,
					";right", s2,
					c2,
					"invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;\n"
			); 
		}		
    }

}
