package ast;

import environment.FrameEnvironment;
import itypes.IType;
import itypes.RefType;
import ivalues.Cell;
import ivalues.IValue;
import compiler.Compiler;
import environment.Environment;

public class ASTNew extends ASTNodeClass {
	
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
		return (super.nodeType = new RefType(type));
	}

	@Override
	public String compile(FrameEnvironment env) {
		
		String ref_class = Compiler.getRefType(this.nodeType);
		
		String code = String.format("%s%s\n%s\n%s%s%s\n%s\n%s\n%s%s%s%s\n", 
				"new ", ref_class,
				"dup",
				"invokespecial ", ref_class, "/<init>()V",
				"dup",
				node.compile(env),
				"putfield ", ref_class, "/v ", Compiler.ITypeToJasminType(this.nodeType)
				);

		return code;
	}

}
