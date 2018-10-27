package ast;

import types.Closure;
import types.IValue;

import java.util.List;

import environment.EnvironmentClass;

public class ASTFun implements ASTNode {
	
	private List<String> params;
	private ASTNode body;

	public ASTFun(List<String> params, ASTNode body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public IValue eval(EnvironmentClass env) {
		return new Closure(this.params, this.body, env);
	}
	
}