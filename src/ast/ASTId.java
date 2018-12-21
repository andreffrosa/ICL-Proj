package ast;

import java.util.Map.Entry;

import environment.Environment;
import itypes.IType;
import ivalues.IValue;

public class ASTId extends ASTNodeClass {

	// Compilation info
	private static final String GET_FIELD_TEMPLATE = "getfield %s/loc_%s %s\n";
	private static final String STATIC_LINK_VALUE = "5";
	private static final String LOAD_SL = "aload " + STATIC_LINK_VALUE + "\n";
	private static final String STATIC_LINK_FIELD_NAME = "SL";

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
		if(entry.getKey() == null)
			throw new RuntimeException("Illegal definition of id: " + this.name);

		StringBuilder builder = new StringBuilder();
		builder.append(LOAD_SL);

		Environment<String> targetEnv = env;
		for(int i = 0; i < entry.getValue(); i++) {
			builder.append(String.format(GET_FIELD_TEMPLATE, targetEnv.getCurrEnvId(), STATIC_LINK_FIELD_NAME, targetEnv.getParentEnv().getCurrEnvId()));
			targetEnv = targetEnv.getParentEnv();
		}

		builder.append(String.format(GET_FIELD_TEMPLATE, targetEnv.getCurrEnvId(), this.name, entry.getKey()));

        return builder.toString();
    }
}
