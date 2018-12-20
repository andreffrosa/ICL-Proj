package environment;

public class FrameEnvironmentClass implements FrameEnvironment {

    private static final String DUP = "dup\n";
    private static final String PUT_FIELD_TEMPLATE = "putfield %s/%s %s\n";
    private static final String GET_FIELD_TEMPLATE = "getfield %s/%s %s\n";
    private static final String NEW_SCOPE_TEMPLATE = "\nnew %s\n" + DUP + "invokespecial %s/<init>()V\n";

    private static final String STORE_SL = "astore %s\n";
    private static final String LOAD_SL = "aload %s\n";
    private static final String STATIC_LINK_FIELD_NAME = "SL";

    private static final String REF_TYPE_TEMPLATE = "L%s;";

    private Frame frame;

    public FrameEnvironmentClass() {
        this(new FrameClass());
    }

    public FrameEnvironmentClass(Frame frame) {
        this.frame = frame;
    }

    @Override
    public String beginScope(int locals) {

        StringBuilder builder = new StringBuilder();

        this.frame = new FrameClass(this.frame, locals);

        builder.append(String.format(NEW_SCOPE_TEMPLATE, this.frame.getFrameId(), this.frame.getFrameId()));

        if(this.frame.getAncestorFrame().getAncestorFrame() != null) {
            builder.append(DUP);
            builder.append(String.format(LOAD_SL, locals - 1));
            builder.append(String.format(PUT_FIELD_TEMPLATE, this.frame.getFrameId(), STATIC_LINK_FIELD_NAME, String.format(REF_TYPE_TEMPLATE, this.frame.getAncestorFrame().getFrameId())));
        }
        this.frame.addField(STATIC_LINK_FIELD_NAME, String.format(REF_TYPE_TEMPLATE, this.frame.getAncestorFrame().getFrameId()));
        builder.append(String.format(STORE_SL, locals - 1));

        return builder.toString();
    }

    @Override
    public String associate(String id, String exp, String type) {
        this.frame.addField(id, type);
        return String.format(LOAD_SL, this.frame.getNumberLocals() - 1) + exp + String.format(PUT_FIELD_TEMPLATE, this.frame.getFrameId(), id, type);
    }

    @Override
    public String find(String id) {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(LOAD_SL, this.frame.getNumberLocals() - 1));

        Frame targetFrame = this.frame;
        while(targetFrame.getAncestorFrame() != null) {

            String fieldType = targetFrame.getFieldType(id);
            if(fieldType != null) {
                builder.append(String.format(GET_FIELD_TEMPLATE, targetFrame.getFrameId(), id, fieldType));
                return builder.toString();
            }
            builder.append(String.format(GET_FIELD_TEMPLATE, targetFrame.getFrameId(), STATIC_LINK_FIELD_NAME, targetFrame.getFieldType(STATIC_LINK_FIELD_NAME)));

            targetFrame = targetFrame.getAncestorFrame();
        }
        return null;
    }

    @Override
    public String endScope() {

        String result = String.format(LOAD_SL, this.frame.getNumberLocals()- 1) +
                String.format(GET_FIELD_TEMPLATE, this.frame.getFrameId(), STATIC_LINK_FIELD_NAME, this.frame.getFieldType(STATIC_LINK_FIELD_NAME)) +
                String.format(STORE_SL, this.frame.getNumberLocals() - 1);

        this.frame = this.frame.getAncestorFrame();

        return result;
    }

    @Override
    public Frame getCurrentFrame() {
        return this.frame;
    }
}
