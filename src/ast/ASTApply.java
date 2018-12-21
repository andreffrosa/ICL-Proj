package ast;

import compiler.Compiler;
import itypes.FunType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Closure;
import ivalues.IValue;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import environment.Environment;

public class ASTApply extends ASTNodeClass {

	private ASTNode function;
	private List<ASTNode> args;
	
	public ASTApply(ASTNode function, List<ASTNode> args) {
		this.function = function;
		this.args = args;
	}

    @Override
	public IValue eval(Environment<IValue> env) {
    	
    	IValue v = function.eval(env);

		Closure functionClosure = (Closure)v;
		Environment<IValue> execution_env = functionClosure.getDefinitionEnv().beginScope();
		associateArgs(execution_env, env, functionClosure);
		IValue result = functionClosure.getBody().eval(execution_env);
		execution_env.endScope();

		return result;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t = this.function.typecheck(env);

		if( t instanceof FunType) {
			FunType ft = (FunType) t;

			if( args.size() != ft.getParamTypes().size() )
				throw new RuntimeException("Number of parameters is different from the number of arguments of the function!");

			Iterator<IType> paramTypesIt = ft.getParamTypes().iterator();
			Iterator<ASTNode> argsIt = this.args.iterator();

			while (paramTypesIt.hasNext() && argsIt.hasNext()) {
				IType paramType = paramTypesIt.next();
				IType argType = argsIt.next().typecheck(env);

				if(!paramType.equalsType(argType))
					throw new TypeException("function", paramType, argType);
			}

			return (super.nodeType = ft.getReturnType());
		} else
			throw  new TypeException("Only functions may be applied!");
	}

    private void associateArgs(Environment<IValue> execution_env, Environment<IValue> call_env, Closure functionClosure) {
		ListIterator<String> idsIt = functionClosure.getParams().listIterator();
		ListIterator<ASTNode> valuesIt = args.listIterator();
		while(idsIt.hasNext() && valuesIt.hasNext()) {
			String id = idsIt.next();
			IValue value = valuesIt.next().eval(call_env);
			execution_env.associate(id, value);
		}
	}


	@Override
	public String compile(Environment<String> env) {
		
		String intr = Compiler.getClosureInterface(((ASTNodeClass)this.function).nodeType);
		
		String args_code = "";
		for( ASTNode node : args ) {
			args_code += node.compile(env) + "\n";
		}
		
		String code = String.format("%s\n%s%s\n%s\n%s\n%s\n%s\n", 
				function.compile(env), 
				"checkcast ", intr, 
				args_code, 
				"invokeinterface ", intr, "/call()" // TODO: como fazer a call?
				);

		return code;
	}

}