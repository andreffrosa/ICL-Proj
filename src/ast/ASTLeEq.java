package ast;

import common.Environment;
import types.IValue;
import types.Int;

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
