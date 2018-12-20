package environment;

public interface Frame {
    void addField(String fieldName, String fieldType);

    String getFieldType(String fieldName);

    String getFrameId();

    Frame getAncestorFrame();

    void setAncestorFrame(Frame ancestorFrame);

    String getFrameString();

    int getNumberLocals();
}
