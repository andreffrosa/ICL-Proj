package ast;

import types.Closure;
import types.IValue;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

import environment.EnvironmentClass;

public class ASTApply implements ASTNode {

	private ASTNode function;
	private List<ASTNode> args;
	
	public ASTApply(ASTNode function, List<ASTNode> args) {
		this.function = function;
		this.args = args;
		
	}

    @Override
	public IValue eval(EnvironmentClass env) {
		List<IValue> argValues = this.evalArgs(env, this.args);
		IValue functionClosure = this.function.eval(env);

		EnvironmentClass functionEnv = ((Closure)functionClosure).getDefinitionEnv();
		List<String> functionParams = ((Closure)functionClosure).getParams();
		ASTNode body = ((Closure)functionClosure).getBody();
		
		EnvironmentClass execEnv = functionEnv.beginScope();
		
		associateArgs(execEnv, functionParams, argValues);
		
		IValue functionCallResult = body.eval(execEnv);
		
		execEnv.endScope();
		
		return functionCallResult;
	}

	private List<IValue> evalArgs(EnvironmentClass evalEnv, List<ASTNode> args) {
		List<IValue> argValues = new LinkedList<IValue>();
		for(ASTNode node : args) {
			argValues.add(node.eval(evalEnv));
		}
		return argValues;
	}
	
	private void associateArgs(EnvironmentClass associationEnv, List<String> names, List<IValue> values) {
		ListIterator<String> namesIt = names.listIterator();
		ListIterator<IValue> valuesIt = values.listIterator();
		while(namesIt.hasNext()) {
			associationEnv.associate(namesIt.next(), valuesIt.next());
		}
	}
}