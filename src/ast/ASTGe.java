package ast;

import common.Environment;
import types.IValue;
import types.Int;

public class ASTGe implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTGe(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
        return Int.greater((Int) this.left.eval(env), (Int) this.right.eval(env));
    }
}
