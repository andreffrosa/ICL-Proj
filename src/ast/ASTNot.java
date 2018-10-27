package ast;

import common.Environment;
import types.Bool;
import types.IValue;

public class ASTNot implements ASTNode {

    private ASTNode original;

    public ASTNot(ASTNode original) {
        this.original = original;
    }

    @Override
    public IValue eval(Environment env) {
        return Bool.negation((Bool) this.original.eval(env));
    }
}