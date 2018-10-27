package types;

import java.util.List;

import ast.ASTNode;
import environment.EnvironmentClass;

public class Closure implements IValue {

	private List<String> params;
	private ASTNode body;
	private EnvironmentClass definitionEnv;
	
	public Closure(List<String> params, ASTNode body, EnvironmentClass definitionEnv) {
		this.params = params;
		this.body = body;
		this.definitionEnv = definitionEnv;
	}

    @Override
    public String toString() {
        return "(" + this.params.size() + ")" + " => " + "(...)";
    }

    public List<String> getParams() {
		return this.params;
	}
	
	public ASTNode getBody() {
		return this.body;
	}
	
	public EnvironmentClass getDefinitionEnv() {
		return this.definitionEnv;
	}
}