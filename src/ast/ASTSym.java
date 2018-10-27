package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTSym implements ASTNode {

    private ASTNode original;

    public ASTSym(ASTNode original) {
        this.original = original;
    }

    @Override
    public IValue eval(EnvironmentClass env) {
        return Int.symmetry((Int) this.original.eval(env));
    }
}