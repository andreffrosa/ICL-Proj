package ast;

import itypes.DoubleType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IDouble;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTNeg extends ASTNodeClass {
	
	private ASTNode node;
	
	public ASTNeg(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v = node.eval(env);
		
		if( v instanceof Int )
			return Int.symmetry((Int)v);
		else if( v instanceof IDouble )
			return IDouble.symmetry((IDouble)v);
		
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t = this.node.typecheck(env);

		if(t instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else if(t instanceof DoubleType)
			return (super.nodeType = DoubleType.getInstance());
		else
			throw new TypeException("Wrong operands' type for operator -.");
	}

    @Override
    public String compile(Environment<String> env) {
		String s = this.node.compile(env);

		IType t = this.node.getType();
		
		if(t instanceof IntType)
			return String.format("%s\n%s\n%s\n",
					";neg",
					s,
					"ineg"
			);
		else if(t instanceof DoubleType)
			return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
					";neg",
					"new java/lang/Double",
					"dup",
					s,
					"invokevirtual java/lang/Double/doubleValue()D",
					"dneg",
					"invokespecial java/lang/Double/<init>(D)V"
			);
		
		return null;
    }
}
