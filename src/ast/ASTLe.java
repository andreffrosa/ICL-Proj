package ast;

import common.Environment;
import types.IValue;
import types.Int;

public class ASTLe implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTLe(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(Environment env) {
        return Int.lesser((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
