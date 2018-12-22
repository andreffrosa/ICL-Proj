package ast;

import java.util.*;
import java.util.Map.Entry;

import compiler.Compiler;
import itypes.FunType;
import itypes.IType;
import ivalues.Closure;
import ivalues.IValue;

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
	public String compile(Environment<String> env) {

		Map<Entry<String, IType>, ASTNode> declarations = new HashMap<>(this.params.size());
		for(Entry<String, IType> e : this.params) {
			declarations.put(e, null);
		}

		String prevFrameId = env.getCurrEnvId();
		String frameId = Compiler.newFrame(declarations, prevFrameId);
		Environment<String> env2 = env.beginScope(frameId);
		env2.setStaticLinkIndex(this.params.size() + 1);

		for(Entry<String, IType> entry : this.params) {
			env2.associate(entry.getKey(), Compiler.ITypeToJasminType(entry.getValue()));
		}

		String closure_id = Compiler.newClosure(this.nodeType, this.params, prevFrameId, env2.getCurrEnvId(), this.body.compile(env2));
		
		String code = "new " + closure_id + "\n" +
					"dup\n"+
					"invokespecial " + closure_id + "/<init>()V\n";

		return code;
	}
}

