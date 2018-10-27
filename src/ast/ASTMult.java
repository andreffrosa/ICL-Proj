package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTMult implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMult(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment env) {

        IValue leftVal = this.left.eval(env);
        IValue rightVal = this.right.eval(env);

        if(!(leftVal instanceof Int) || !(rightVal instanceof Int)) {
            throw new RuntimeException("Operator could not be applied!");
        }

		return Int.multiplication((Int)leftVal, (Int)rightVal);
	}
}