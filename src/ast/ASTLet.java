package ast;

import compiler.Compiler;
import environment.*;
import itypes.BoolType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Undefined;

import java.util.Map;
import java.util.Map.Entry;

public class ASTLet extends ASTNodeClass {
	
	private Map<Entry<String, IType>, ASTNode> declarations;
	private ASTNode body;
	
	public ASTLet(Map<Entry<String, IType>, ASTNode> declarations, ASTNode body) {
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		Environment<IValue> newEnv = env.beginScope();
		
		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			//IValue val = entry.getValue().eval(env);
			newEnv.associate(id, new Undefined());
		}

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			IValue val = entry.getValue().eval(newEnv);
			newEnv.smash(id, val);
		}
		
		IValue value = body.eval(newEnv);
		
		newEnv.endScope();
		
		return value;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		Environment<IType> newEnv = env.beginScope();

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			IType declarationType = entry.getValue().typecheck(env);
			if(!entry.getKey().getValue().equalsType(declarationType))
				throw new TypeException("=", entry.getKey().getValue(), declarationType);

			newEnv.associate(entry.getKey().getKey(), entry.getKey().getValue());
		}

		IType type = this.body.typecheck(newEnv);

		newEnv.endScope();

		return (super.nodeType = type);
	}

    @Override
    public String compile(FrameEnvironment env) {

		StringBuilder builder = new StringBuilder();

		builder.append(env.beginScope(5));

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			String compiledExp =  entry.getValue().compile(env);

			IType entryType = entry.getKey().getValue();

			if((entryType instanceof IntType) || (entryType instanceof BoolType)) {
				builder.append(env.associate(id, compiledExp, "I"));
			}else {
				// TODO get real type
				builder.append(env.associate(id, compiledExp, "I"));
			}
		}

		builder.append(this.body.compile(env));

		// Compile Frame file
		Compiler.emitAndDump(env.getCurrentFrame().getFrameString(), env.getCurrentFrame().getFrameId());

		builder.append(env.endScope());

		return builder.toString();
    }
}