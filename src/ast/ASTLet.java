package ast;

import compiler.Compiler;
import environment.*;
import itypes.IType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Undefined;

import java.util.Map;
import java.util.Map.Entry;

public class ASTLet extends ASTNodeClass {


	// Compilation info
	private static final String DUP = "dup\n";
	private static final String PUT_FIELD_TEMPLATE = "putfield %s/loc_%s %s\n";
	private static final String NEW_SCOPE_TEMPLATE = "\nnew %s\n" + DUP + "invokespecial %s/<init>()V\n";
	private static final String STORE_SL = "astore " + "%s" + "\n";
	private static final String LOAD_SL = "aload " + "%s" + "\n";
	private static final String STATIC_LINK_FIELD_NAME = "sl";
	private static final String REF_TYPE_TEMPLATE = "L%s;";

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

			newEnv.associate(entry.getKey().getKey(), declarationType);
		}

		IType type = this.body.typecheck(newEnv);

		newEnv.endScope();

		return (super.nodeType = type);
	}

    @Override
    public String compile(Environment<String> env) {

    	StringBuilder builder = new StringBuilder();
		String prevFrameId = env.getCurrEnvId();

		String frameId = Compiler.newFrame(this.declarations, prevFrameId);
		Environment<String> newEnv = env.beginScope(frameId);
		newEnv.setStaticLinkIndex(Compiler.STATIC_LINK_DEFAULT_INDEX);

		builder.append(String.format(NEW_SCOPE_TEMPLATE, frameId, frameId));

		if(prevFrameId != null) {
			builder.append(DUP);
			builder.append(String.format(LOAD_SL, newEnv.getStaticLinkIndex()));
			builder.append(String.format(PUT_FIELD_TEMPLATE, frameId, STATIC_LINK_FIELD_NAME, String.format(REF_TYPE_TEMPLATE, prevFrameId)));
		}

		builder.append(String.format(STORE_SL, newEnv.getStaticLinkIndex()));

		for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
			String id = entry.getKey().getKey();
			IType entryType = entry.getKey().getValue();

			newEnv.associate(id, Compiler.ITypeToJasminType(entryType));

			builder.append(String.format(LOAD_SL, newEnv.getStaticLinkIndex()));
			builder.append(entry.getValue().compile(newEnv));
			builder.append(String.format(PUT_FIELD_TEMPLATE, frameId, id, Compiler.ITypeToJasminType(entryType)));
		}

		builder.append(this.body.compile(newEnv));

		newEnv.endScope();

		return builder.toString();
    }
}