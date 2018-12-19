package environment;

public interface FrameEnvironment {

    String beginScope();

    String associate(String id, String exp, String type);

    String find(String id);

    String endScope();

    Frame getCurrentFrame();
}
