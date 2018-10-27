package ast;

import common.Environment;
import types.Closure;
import types.IValue;

import java.util.List;

public class ASTFun implements ASTNode {
	
	private List<String> params;
	private ASTNode body;

	public ASTFun(List<String> params, ASTNode body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public IValue eval(Environment env) {
		return new Closure(this.params, this.body, env);
	}
	
}