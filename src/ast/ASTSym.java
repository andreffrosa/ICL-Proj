package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTSym implements ASTNode {

    private ASTNode original;

    public ASTSym(ASTNode original) {
        this.original = original;
    }

    @Override
    public IValue eval(Environment env) {

        IValue value = this.original.eval(env);

        if(!(value instanceof Int)) {
            throw new RuntimeException("Operator could not be applied!");
        }

        return Int.symmetry((Int) value);
    }
}