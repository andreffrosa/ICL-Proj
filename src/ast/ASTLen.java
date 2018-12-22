package ast;

import itypes.IType;
import itypes.IntType;
import itypes.StrType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Str;
import environment.Environment;

public class ASTLen extends ASTNodeClass {
	
	private ASTNode node;
	
	public ASTLen(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = node.eval(env);
		return ((Str)v).length();
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		IType t = this.node.typecheck(env);

		if( t instanceof StrType )
			return (this.nodeType = IntType.getInstance());
		else
			throw new TypeException("#", StrType.getInstance(), t);
	}

    @Override
    public String compile(Environment<String> env) {
    	
		return String.format("%s\n%s\n",
				this.node.compile(env),
				"invokevirtual java/lang/String/length(Ljava/lang/String;)I"
		);
    }

}
