package environment;

public interface FrameEnvironment {

    String beginScope(int locals);

    String associate(String id, String exp, String type);

    String find(String id);

    String endScope();

    Frame getCurrentFrame();
}
