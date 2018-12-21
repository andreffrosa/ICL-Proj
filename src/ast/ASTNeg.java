package ast;

import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
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
		
		return Int.symmetry((Int)v);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t = this.node.typecheck(env);

		if(t instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else
			throw new TypeException("-", IntType.getInstance(), t);
	}

    @Override
    public String compile(Environment<String> env) {
		String s = this.node.compile(env);

		return String.format("%s\n%s\n%s\n",
				";neg",
				s,
				"ineg"
		);
    }

}
