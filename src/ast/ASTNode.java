package ast;

import environment.Environment;
import ivalues.IValue;

public interface ASTNode {
	
	IValue eval(Environment<IValue> env);

// 	IType typecheck(Environment<IType> env);
}
