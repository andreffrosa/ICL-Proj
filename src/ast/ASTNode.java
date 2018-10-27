package ast;

import common.Environment;
import types.IValue;

public interface ASTNode {
	IValue eval(Environment env);
}