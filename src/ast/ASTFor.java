package ast;

import java.util.Map;
import java.util.Map.Entry;

import compiler.Compiler;
import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTFor extends ASTNodeClass {
	
	private Map<Entry<String, IType>, ASTNode> declarations;
	private ASTNode condition, step, body;
	
	public ASTFor(Map<Entry<String, IType>, ASTNode> decls, ASTNode condition, ASTNode step, ASTNode body) {
		this.declarations = decls;
		this.condition = condition;
		this.body = body;
		this.step = step;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		Environment<IValue> e2 = e.beginScope();
		
		for( Entry<Entry<String, IType>, ASTNode> dec : declarations.entrySet() ) {
			String id = dec.getKey().getKey();
			IValue val = dec.getValue().eval(e2);
			e2.associate(id, val);
		}
		
		while(true) {
			IValue cond = condition.eval(e2);

			if( ((Bool) cond).getValue() ) {
				body.eval(e2);
				step.eval(e2);
			} else {
				e2.endScope();
				return cond;
			}
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		Environment<IType> env2 = env.beginScope();

		for( Entry<Entry<String, IType>, ASTNode> dec : declarations.entrySet() ) {
			String id = dec.getKey().getKey();
			IType val = dec.getValue().typecheck(env2);
			env2.associate(id, val);
		}

		IType t = this.condition.typecheck(env2);

		if(!(t instanceof BoolType))
			throw new TypeException("for", BoolType.getInstance(), t);

		this.body.typecheck(env2);
		this.step.typecheck(env2);

		env2.endScope();

		return (super.nodeType = BoolType.getInstance());
	}
	
	@Override
    public String compile(Environment<String> env) {

    	StringBuilder builder = new StringBuilder();
		String prevFrameId = env.getCurrEnvId();

		String frameId = Compiler.newFrame(this.declarations, prevFrameId);
		Environment<String> newEnv = env.beginScope(frameId);
		newEnv.setStaticLinkIndex(Compiler.STATIC_LINK_DEFAULT_INDEX);

		builder.append(String.format("\nnew %s\n" + "dup\n" + "invokespecial %s/<init>()V\n", frameId, frameId));

		if(prevFrameId != null) {
			builder.append("dup\n");
			builder.append(String.format("aload " + "%s" + "\n", newEnv.getStaticLinkIndex()));
			builder.append(String.format("putfield %s/loc_%s %s\n", frameId, "sl", String.format("L%s;", prevFrameId)));
		}

		builder.append(String.format("astore " + "%s" + "\n", newEnv.getStaticLinkIndex()));

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			IType entryType = entry.getKey().getValue();

			newEnv.associate(id, Compiler.ITypeToJasminType(entryType));

			builder.append(String.format("aload " + "%s" + "\n", newEnv.getStaticLinkIndex()));
			builder.append(entry.getValue().compile(newEnv));
			builder.append(String.format("putfield %s/loc_%s %s\n", frameId, id, Compiler.ITypeToJasminType(entryType)));
		}

		//builder.append(this.body.compile(newEnv));
		builder.append((new ASTWhile(this.condition, new ASTSeq(this.body, this.step))).compile(newEnv));

		newEnv.endScope();

		return builder.toString();
    }
}
