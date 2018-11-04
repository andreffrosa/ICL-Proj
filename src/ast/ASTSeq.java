package ast;

import ivalues.IValue;
import environment.Environment;

public class ASTSeq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        this.left.eval(env);
        return this.right.eval(env);
    }

}
