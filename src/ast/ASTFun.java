package ast;

import java.util.Map.Entry;

import itypes.IType;
import ivalues.Closure;
import ivalues.IValue;

import java.util.LinkedList;
import java.util.List;

import environment.Environment;

public class ASTFun implements ASTNode {
	
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
}