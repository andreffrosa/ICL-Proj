package ast;

import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTNeg implements ASTNode {
	
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
			return IntType.getInstance();
		else
			throw new TypeException("-", IntType.getInstance(), t);
	}
	
	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s = this.node.compile(env);
		
		String code = String.format("%s\n%s\n%s\n", 
				";neg", 
				s, 
				"ineg"
				);

		return code;
	}

}
