package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTGeEq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTGeEq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }


    public IValue eval(Environment env) {
        return Int.greaterEqual((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
