package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTDiff implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTDiff(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(EnvironmentClass env) {
        return Int.different((Int) this.left.eval(env), (Int) this.right.eval(env));

    }
}