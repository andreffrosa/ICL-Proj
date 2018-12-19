package environment;

import java.util.HashMap;
import java.util.Map;

public class FrameClass implements Frame {

    private static final String FRAME_TEMPLATE =
            ".class public %s\n" +
            ".super java/lang/Object\n" +
            "%s\n" +
            ".method public <init>()V\n" +
            "   aload_0\n" +
            "   invokenonvirtual java/lang/Object/<init>()V\n" +
            "   return\n" +
            ".end method";
    private static final String FIELD_TEMPLATE = ".field public %s %s\n";

    private static long frameIdSeed = 0;
    private static String generateFrameId() {
        return "Frame" + frameIdSeed++;
    }

    private Map<String,String> fields;
    private String frameId;
    private Frame ancestorFrame;

    FrameClass() {
        this(null);
    }

    FrameClass(Frame ancestorFrame) {
        this.frameId = generateFrameId();
        this.ancestorFrame = ancestorFrame;
        this.fields = new HashMap<>();
    }

    @Override
    public void addField(String fieldName, String fieldType) {
        this.fields.put(fieldName, fieldType);
    }

    @Override
    public String getFieldType(String fieldName) {
        return this.fields.get(fieldName);
    }

    @Override
    public String getFrameId() {
        return this.frameId;
    }

    @Override
    public Frame getAncestorFrame() {
        return this.ancestorFrame;
    }

    @Override
    public void setAncestorFrame(Frame ancestorFrame) {
        this.ancestorFrame = ancestorFrame;
    }

    @Override
    public String getFrameString() {
        StringBuilder builder = new StringBuilder();
        for(String field : this.fields.keySet())
            builder.append(String.format(FIELD_TEMPLATE, field, fields.get(field)));

        return String.format(FRAME_TEMPLATE, this.frameId, builder.toString());
    }
}
