package ast;

import compiler.StackCoordinates;
import environment.Environment;
import itypes.IType;
import ivalues.IValue;

public interface ASTNode {
	
	IValue eval(Environment<IValue> env);

 	IType typecheck(Environment<IType> env);

 	String compile(Environment<StackCoordinates> env);
 	
}
