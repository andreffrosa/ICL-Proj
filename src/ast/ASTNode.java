package ast;

import environment.Environment;
import ivalues.IValue;

public interface ASTNode {
	
	IValue eval(Environment env);
}