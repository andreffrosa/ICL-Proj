package ast;

import ivalues.Closure;
import ivalues.IValue;

import java.util.List;

import environment.Environment;

public class ASTFun implements ASTNode {
	
	private List<String> params;
	private ASTNode body;

	public ASTFun(List<String> params, ASTNode body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new Closure(this.params, this.body, env);
	}
	
}