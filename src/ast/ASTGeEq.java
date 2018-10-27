package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTGeEq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTGeEq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }


    public IValue eval(EnvironmentClass env) {
        return Int.greaterEqual((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
