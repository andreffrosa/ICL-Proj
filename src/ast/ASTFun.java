package ast;

import java.util.Map.Entry;

import compiler.Compiler;
import environment.FrameEnvironment;
import itypes.FunType;
import itypes.IType;
import ivalues.Closure;
import ivalues.IValue;

import java.util.LinkedList;
import java.util.List;

import environment.Environment;

public class ASTFun extends ASTNodeClass {
	
	private List<Entry<String, IType>> params;
	private ASTNode body;

	public ASTFun(List<Entry<String, IType>> params, ASTNode body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		List<String> paramIds = new LinkedList<>();

		for(Entry<String, IType> entry : this.params) {
			paramIds.add(entry.getKey());
		}

		return new Closure(paramIds, this.body, env);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		List<IType> paramTypes = new LinkedList<>();

		Environment<IType> env2 = env.beginScope();

		for(Entry<String, IType> entry : this.params) {
			env2.associate(entry.getKey(), entry.getValue());
			paramTypes.add(entry.getValue());
		}

		IType retType = this.body.typecheck(env2);

		return (super.nodeType = new FunType(paramTypes, retType));
	}

	@Override
	public String compile(FrameEnvironment env) {
		String closure_id = Compiler.newClosure(params, "", null); // TODO : add return type
		
		String type = "";
		
		String code = String.format("%s%s\n%s\n%s\n%s\n%s%s%s%s\n", 
				"new ", closure_id,
				"dup",
				"aload_1 ; SL",
				"; set environment field of the closure",
				"putfield ", closure_id, "/sl ", type
				);
		
		return code;
	}
}

