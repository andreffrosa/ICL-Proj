package ast;

import compiler.Compiler;
import environment.FrameEnvironment;
import itypes.IType;

public abstract class ASTNodeClass implements ASTNode {

    protected IType nodeType;
    
    @Override
    public IType getType() {
    	return nodeType;
    }
    
    @Override
    public String cc(FrameEnvironment env, String tl, String fl) {
    	String label1 = Compiler.newLabel();
    	String label2 = Compiler.newLabel();
    	return String.format("\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s\n%s%s\n%s\n\n",
    			this.compile(env),
    			"sipush 1", // true
    			"isub",
    			"ifeq ", label1,
    			"goto ", fl,
    			"goto ", label2,
    			label1 + ": ",
    			"goto ", tl,
    			label2 + ": "
    			);
    }
}
