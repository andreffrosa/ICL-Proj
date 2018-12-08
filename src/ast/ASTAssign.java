package ast;

import itypes.IType;
import itypes.RefType;
import itypes.TypeException;
import ivalues.Cell;
import ivalues.IValue;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTAssign implements ASTNode {
	
	private ASTNode left, right;
	
	public ASTAssign(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);

		((Cell) v1).setValue(v2);
		return v2;
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		IType left = this.left.typecheck(env);
		IType right = this.right.typecheck(env);

		if(!(left instanceof RefType))
			throw new TypeException("Only Cells can be assigned to!");

		if(!((RefType) left).getReferencedType().equalsType(right))
			throw new TypeException(":=", left, right);

		return right;
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}

}

