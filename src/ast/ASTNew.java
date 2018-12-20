package ast;

import itypes.IType;
import itypes.RefType;
import ivalues.Cell;
import ivalues.IValue;
import compiler.Compiler;
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
		
		String ref_class = Compiler.getRefType(type);
		
		String code = String.format("%s%s\n%s\n%s%s%s\n%s\n%s\n%s%s%s%s\n", 
				"new ", ref_class,
				"dup",
				"invokespecial ", ref_class, "/<init>()V",
				"dup",
				node.compile(env),
				"putfield ", ref_class, "/v ", Compiler.ITypeToJasminType(type)
				);

		return code;
	}

}
