package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTLe implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTLe(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(EnvironmentClass env) {
        return Int.lesser((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
