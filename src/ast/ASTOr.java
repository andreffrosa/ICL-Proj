package ast;

import environment.Environment;
import ivalues.Bool;
import ivalues.IValue;

public class ASTOr implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTOr(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment env) {
	    IValue leftVal = this.left.eval(env);
	    IValue rightVal = this.right.eval(env);

	    if(!(leftVal instanceof Bool) || !(rightVal instanceof Bool)) {
            throw new RuntimeException("Operator could not be applied!");
        }

		return Bool.disjunction((Bool) leftVal, (Bool) rightVal);
	}
}