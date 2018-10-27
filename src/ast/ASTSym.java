package ast;

import common.Environment;
import types.IValue;
import types.Int;

public class ASTSym implements ASTNode {

    private ASTNode original;

    public ASTSym(ASTNode original) {
        this.original = original;
    }

    @Override
    public IValue eval(Environment env) {
        return Int.symmetry((Int) this.original.eval(env));
    }
}