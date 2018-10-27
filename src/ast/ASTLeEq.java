package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTLeEq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTLeEq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }


    public IValue eval(EnvironmentClass env) {
        return Int.lesserEqual((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
