package compiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Compiler {

    private static final String COMPILATION_TEMPLATE =
            ".class public %s\n" +
            ".super java/lang/Object\n" +
            "\n" +
            ".method public <init>()V\n" +
            "   aload_0\n" +
            "   invokenonvirtual java/lang/Object/<init>()V\n" +
            "   return\n" +
            ".end method\n" +
            "\n" +
            ".method public static main([Ljava/lang/String;)V\n" +
            "   .limit stack 128\n" +
            "   .limit locals 10\n" +
            "   ; push System.out onto the stack\n" +
            "   getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
            "\n" +
            "%s\n" +
            "\n" +
            "   ; convert to String;\n" +
            "   invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n" +
            "   ;invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;\n" +
            "\n" +
            "   ; call the PrintStream.println() method.\n" +
            "   invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" +
            "\n" +
            "   return\n" +
            ".end method";

    private static final String COMPILATION_PATH_TEMPLATE = "./compiled/%s.j";

    public static boolean emitAndDump(String content, String name) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(String.format(COMPILATION_PATH_TEMPLATE, name)))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean emitAndDumpProgram(String toEmit, String name) {
        return emitAndDump(String.format(COMPILATION_TEMPLATE, name, toEmit), name);
    }
}
