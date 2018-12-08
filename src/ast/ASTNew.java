package ast;

import itypes.IType;
import itypes.RefType;
import ivalues.Cell;
import ivalues.IValue;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTNew implements ASTNode {
	
	private ASTNode node;
	
	public ASTNew(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue value = node.eval(env);
		return new Cell(value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType type = this.node.typecheck(env);
		return new RefType(type);
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}

}
