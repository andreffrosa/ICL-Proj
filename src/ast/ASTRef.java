package ast;

import environment.Environment;
import ivalues.Cell;
import ivalues.IValue;

public class ASTRef implements ASTNode {

    private ASTNode value;

    public ASTRef(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment env) {
        return new Cell(this.value.eval(env));
    }
}
