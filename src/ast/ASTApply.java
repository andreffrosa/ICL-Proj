package ast;

import ivalues.Closure;
import ivalues.IValue;

import java.util.List;
import java.util.ListIterator;

import environment.Environment;

public class ASTApply implements ASTNode {

	private ASTNode function;
	private List<ASTNode> args;
	
	public ASTApply(ASTNode function, List<ASTNode> args) {
		this.function = function;
		this.args = args;
	}

    @Override
	public IValue eval(Environment env) {
    	
    	IValue v = function.eval(env);
		if( v instanceof Closure ) {
			Closure functionClosure = (Closure)v;
			Environment execution_env = functionClosure.getDefinitionEnv().beginScope();
			associateArgs(execution_env, functionClosure);
			IValue result = functionClosure.getBody().eval(execution_env);
			execution_env.endScope();
			return result;
		} 
		
		throw new RuntimeException("Only Functions can be applied!");
	}
	
	private void associateArgs(Environment environment, Closure functionClosure) {
		if( args.size() != functionClosure.getParams().size() )
			throw new RuntimeException("Number of parameters is different from the number of arguments of the function!");
		
		ListIterator<String> idsIt = functionClosure.getParams().listIterator();
		ListIterator<ASTNode> valuesIt = args.listIterator();
		while(idsIt.hasNext() && valuesIt.hasNext()) {
			String id = idsIt.next();
			IValue value = valuesIt.next().eval(environment);
			environment.associate(id, value);
		}
	}

}