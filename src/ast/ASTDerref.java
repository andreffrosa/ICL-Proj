package ast;

import itypes.IType;
import itypes.RefType;
import itypes.TypeException;
import ivalues.Cell;
import ivalues.IValue;
import compiler.Compiler;
import environment.Environment;

public class ASTDerref extends ASTNodeClass {
	
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

		return (super.nodeType = ((RefType) refType).getReferencedType());
	}

	@Override
	public String compile(Environment<String> env) {
		String ref_class = Compiler.getRefType(this.nodeType);
		
		String code = String.format("%s\n%s%s\n%s%s%s%s\n", 
				node.compile(env),
				"checkcast ", ref_class,
				"getfield ", ref_class, "/v ", Compiler.ITypeToJasminType2(this.nodeType)
				);

		return code;
	}

}

