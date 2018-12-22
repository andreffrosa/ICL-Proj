package compiler;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ast.ASTNode;
import itypes.BoolType;
import itypes.DoubleType;
import itypes.FunType;
import itypes.IType;
import itypes.IntType;
import itypes.StrType;
import itypes.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
            "   ; set limits used by this method\n" + 
            "   .limit stack 128\n" +
            "   .limit locals 10\n" +
           // "   ; push System.out onto the stack\n" +
           // "   getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
            "   ; ---- END OF PREAMBLE CODE\n\n" +
            "%s" +
            "\n" +
            "   ; ---- START OF EPILOGUE CODE\n" +
            //"   ; convert to String;\n" +
            //"   invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n" +
            //"   ;invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;\n" +
            //"\n" +
            //"   ; call the PrintStream.println() method.\n" +
            //"   invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" +
            //"\n" +
            "   pop\n" +
            "   return\n" +
            ".end method\n\n";
    
    private static final String COMPILATION_PATH_TEMPLATE = "./compiled/%s.j";

    private static final int COMPILATION_FUNCTION_BODY_STACK_SIZE = 128;

    public static final int STATIC_LINK_DEFAULT_INDEX = 5;

	private static int frameCounter = 0;
	private static int closureCounter = 0;
    private static int labelCounter = 0;
	private static Map<String, String> closure_interfaces = new HashMap<>();
	private static Map<String, String> ref_classes = new HashMap<>();
	private static Map<String, String> struct_classes = new HashMap<>();
	private static String code = "";

    public static String newLabel() {
        return "label_" + labelCounter++;
    }

	public static String emit(String inst) {
		code += "\n" + inst;
		return code;
	}

	public static String ITypeToJasminType(IType type) {
		if( type instanceof IntType )
			return "I";
		else if (type instanceof BoolType)
			return "Z";
		else if (type instanceof DoubleType)
			return "Ljava/lang/Double;";
		else if (type instanceof StrType)
			return "Ljava/lang/String;";
		else			
			return "Ljava/lang/Object;";
	}

	public static String getStructType(IType itype) {

		List<Entry<String, IType>> fields = ((StructType) itype).getFields();

		StringBuilder keyBuilder = new StringBuilder();

		for (Entry<String, IType> field: fields) {
			keyBuilder.append("_");

			IType type = field.getValue();
			if( type instanceof BoolType || type instanceof IntType )
				keyBuilder.append(ITypeToJasminType(type));
			else
				keyBuilder.append("T");
		}

		String key = keyBuilder.toString();

		String struct = struct_classes.get(key);
		if( struct == null ) {
			struct = "struct" + key;
			struct_classes.put(key, struct);

			StringBuilder codeBuilder = new StringBuilder();
			codeBuilder.append(".class ").append(struct).append("\n");
			codeBuilder.append(".super java/lang/Object\n\n");
			for (Entry<String, IType> field: fields) {
				codeBuilder.append(".field public ").append(field.getKey()).append(" ").append(ITypeToJasminType(field.getValue())).append("\n");
			}
			codeBuilder.append("\n.method public <init>()V\n");
			codeBuilder.append("aload_0\n");
			codeBuilder.append("invokenonvirtual java/lang/Object/<init>()V\n");
			codeBuilder.append("return\n");
			codeBuilder.append(".end method\n\n");

			writeToDisk(COMPILATION_PATH_TEMPLATE, struct, codeBuilder.toString());
		}

		return struct;
	}

	public static String getRefType(IType type) {
		
		String key;
		if( type instanceof BoolType || type instanceof IntType )
			key = ITypeToJasminType(type);
		else
			key = "T";

		String ref = ref_classes.get(key);
		if( ref == null ) {
			ref = "ref_" + key;
			ref_classes.put(key, ref);
			String code = ".class " + ref + "\n"
					+ ".super java/lang/Object\n"
					+ ".field public v " + ITypeToJasminType(type) + "\n"
					+ ".method public <init>()V\n"
					+ ".limit locals 5\n"
					+ "aload_0\n"
					+ "invokenonvirtual java/lang/Object/<init>()V\n"
					+ "return\n"
					+ ".end method\n\n";
			
			writeToDisk(COMPILATION_PATH_TEMPLATE, ref, code);
		}
			
		return ref;
	}

	public static String computeSignature(IType type) {

		FunType funType = (FunType) type;

		StringBuilder builder = new StringBuilder();
		builder.append("(");

		for(IType t : funType.getParamTypes())
			builder.append(ITypeToJasminType(t));

		builder.append(")");
		builder.append(ITypeToJasminType(funType.getReturnType()));

		return builder.toString();
	}
	
	public static String getClosureInterface(IType closure_type) {
		String signature = computeSignature(closure_type);
		
		String closure_interface_id = closure_interfaces.get(signature);
		if( closure_interface_id == null ) {
			closure_interface_id = "closure_interface_" + closure_interfaces.size();
			
			String code = ".interface " + closure_interface_id + "\n"
						+ ".super java/lang/Object\n"
						+ ".method public abstract call" + signature + "\n"
						+ ".end method\n";
			
			writeToDisk(COMPILATION_PATH_TEMPLATE, closure_interface_id, code);
			closure_interfaces.put(signature, closure_interface_id);
		}
		
		return closure_interface_id;
	}
	
	public static String newClosure(IType closure_type, List<Entry<String, IType>> params, String ancestor_frame_id, String frame_id, String body_code) {
		String current_closure_id = "closure_" + closureCounter++;
		
		String current_interface = getClosureInterface(closure_type);
		
		String call = "call(";
		for(Entry<String,IType> e : params) {
			call += ITypeToJasminType(e.getValue());
		}
		call += ")" + ITypeToJasminType(((FunType)closure_type).getReturnType());
		
		String load_args = "";
		int counter = 1;
		for(Entry<String,IType> e : params) {
			String loadString = ITypeToJasminType(e.getValue());
			loadString = loadString.endsWith(";") ? "aload" : "iload";

			load_args += "aload " + (params.size()+1) + "\n" +
						loadString + "_" + counter + " ; " + counter + "th arg\n" +
						"putfield " + frame_id + "/loc_" + e.getKey() + " " + ITypeToJasminType(e.getValue()) + "\n";

			counter++;
		}

		String returnString = call.endsWith(";") ? "areturn" : "ireturn";

		String closureInitMethod = "\n.method public <init>()V\n" +
				"  aload_0\n" +
				"  invokenonvirtual java/lang/Object/<init>()V\n" +
				"  return\n" +
				".end method";
		
		//String code = "";
		String code = String.format("%s%s\n%s\n%s%s\n%s%s\n%s\n%s%s\n%s%s\n%s\n%s%s\n%s\n%s%s\n%s\n%s%s%s\n%s%s%s\n%s\n%s\n%s\n%s\n%s\n\n",
				".class ", current_closure_id,
				".super java/lang/Object",
				".implements ", current_interface,
				".field public sl L", ancestor_frame_id + ";",
				closureInitMethod,
				"\n.method public ", call,
				".limit locals ", (params.size()+1+1), // +1 for this, +1 for static link
				String.format(".limit stack %d", COMPILATION_FUNCTION_BODY_STACK_SIZE),
				"new ", frame_id,
				"dup",
				String.format("invokespecial %s/<init>()V\n", frame_id),
				"dup",
				"aload_0 ; get this",
				"getfield ", current_closure_id, "/sl L" + ancestor_frame_id + ";",
				"putfield ", frame_id, "/sl L" + ancestor_frame_id + ";",
				"astore " + (params.size()+1),
				load_args,
				body_code,
				returnString,
				".end method"
				);
		
		writeToDisk(COMPILATION_PATH_TEMPLATE, current_closure_id, code);
		
		return current_closure_id;
	}

	public static String newFrame(Map<Entry<String, IType>, ASTNode> declarations, String ancestor_frame_id) {
		int current_frame_id = frameCounter++;
		
		String code = ".class frame_" + current_frame_id + "\n" +
					  ".super java/lang/Object\n";

		code += ancestor_frame_id == null ? "\n\n" : (".field public sl L" + ancestor_frame_id + ";\n\n");

		if( declarations != null ) {
			for(Entry<Entry<String, IType>, ASTNode> entry : declarations.entrySet()) {
				String type = ITypeToJasminType(entry.getKey().getValue());
				code += String.format(".field public loc_%s %s\n", entry.getKey().getKey(), type);
			}
		}
		
		code += "\n\n.method public <init>()V\n" + 
				"  aload_0\n" + 
				"  invokenonvirtual java/lang/Object/<init>()V\n" +
				"  return\n" + 
				".end method\n";
		
		writeToDisk(COMPILATION_PATH_TEMPLATE, "frame_" + current_frame_id, code);
		
		return "frame_" + current_frame_id;
	}
	
	private static boolean writeToDisk(String path_template, String name, String content) {
		try {
			PrintWriter out = new PrintWriter(String.format(path_template, name));
			out.print(content);
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static String loadFile(String path) {
		String out = "";
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			out = new String(encoded, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// DO nothing
		}
		
		return out;
	}

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
