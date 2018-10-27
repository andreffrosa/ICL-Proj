package ast;

import environment.Environment;
import types.IValue;

public interface ASTNode {
	
	IValue eval(Environment env);
}