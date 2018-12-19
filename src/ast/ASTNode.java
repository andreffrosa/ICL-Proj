package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import ivalues.IValue;

public interface ASTNode {
	
	IValue eval(Environment<IValue> env);

 	IType typecheck(Environment<IType> env);

 	String compile(FrameEnvironment env);
}
