package ast;

import itypes.IType;
import itypes.RefType;
import itypes.TypeException;
import ivalues.Cell;
import ivalues.IValue;

import environment.Environment;

public class ASTDerref implements ASTNode {
	
	private ASTNode node;
	
	public ASTDerref(ASTNode t) {
		this.node = t;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue reference = node.eval(env);

		return ((Cell) reference).getValue();

		/*System.out.println(reference);
		System.out.println(reference.getClass().getName());*/
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType refType = this.node.typecheck(env);

		if(!(refType instanceof RefType))
			throw new TypeException("Only Cells can be dereferenced!");

		return ((RefType) refType).getReferencedType();
	}

}

