package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTLeEq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTLeEq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }


    public IValue eval(Environment env) {
        return Int.lesserEqual((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
