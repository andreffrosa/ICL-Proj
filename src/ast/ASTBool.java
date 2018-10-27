package ast;

import common.Environment;
import types.Bool;
import types.IValue;

public class ASTBool implements ASTNode {

    private boolean truthVal;

    public ASTBool(boolean truthVal) {
        this.truthVal = truthVal;
    }

    @Override
    public IValue eval(Environment env) {
        return new Bool(this.truthVal);
    }
}