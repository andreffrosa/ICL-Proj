package ast;

import java.util.Map.Entry;

import environment.Environment;
import itypes.IType;
import ivalues.IValue;

public class ASTId extends ASTNodeClass {

	// Compilation info
	private static final String GET_FIELD_TEMPLATE = "getfield %s/%s %s\n";
	private static final String LOAD_SL = "aload " + "%s" + "\n";
	private static final String STATIC_LINK_FIELD_NAME = "sl";
	private static final String REF_TYPE_TEMPLATE = "L%s;";
	private static final String LOC_PREFIX = "loc_";

	private String name;
	
	public ASTId(String name) {
		this.name = name;
	}
	
	public IValue eval(Environment<IValue> env) {
		IValue value = env.find(this.name);

		if(value == null) {
			throw new RuntimeException("Illegal definition of id: " + this.name);
		}

		return value;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType type = env.find(this.name);

		if(type == null)
			throw new RuntimeException("Illegal definition of id: " + this.name);

		return (super.nodeType = type);
	}

    @Override
    public String compile(Environment<String> env) {

    	Entry<String, Integer> entry = env.findLevel(this.name);
		if(entry == null || entry.getKey() == null)
			throw new RuntimeException("Illegal definition of id: " + this.name);

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(LOAD_SL, env.getStaticLinkIndex()));

		Environment<String> targetEnv = env;
		for(int i = 0; i < entry.getValue(); i++) {
			builder.append(String.format(GET_FIELD_TEMPLATE, targetEnv.getCurrEnvId(), STATIC_LINK_FIELD_NAME, String.format(REF_TYPE_TEMPLATE, targetEnv.getParentEnv().getCurrEnvId())));
			targetEnv = targetEnv.getParentEnv();
		}

		builder.append(String.format(GET_FIELD_TEMPLATE, targetEnv.getCurrEnvId(), LOC_PREFIX + this.name, entry.getKey()));

        return builder.toString();
    }
}
