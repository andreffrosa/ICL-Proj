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
import environment.Environment;
import itypes.BoolType;
import itypes.IType;
import itypes.IntType;

public class Compiler {

	//private static String PREAMBLE = "./compiled/preamble.j";
	
	private static String PREAMBLE = ".class public Prog\n" + 
	".super java/lang/Object\n" + 
	"\n" + 
	".method public <init>()V\n" + 
	"   aload_0\n" + 
	"   invokenonvirtual java/lang/Object/<init>()V\n" + 
	"   return\n" + 
	".end method\n" + 
	"\n" + 
	".method public static main([Ljava/lang/String;)V\n" + 
	"       ; set limits used by this method\n" + 
	"       .limit locals 10\n" + 
	"       .limit stack 256\n" + 
	"       \n" + 
	"      ; ---- END OF INTRO CODE";
	
	private static String EPILOGUE = "\n; ---- START OF EPILOGUE CODE\n" + 
			"       return\n" + 
			".end method\n\n";
	
	private static int frameCounter = 0;
	private static int closureCounter = 0;
	private static Map<String, String> closure_interfaces = new HashMap<>();
	private static Map<String, String> ref_classes = new HashMap<>();
	private static String code = "";
	
	private static String SL = "";

	public static String emit(String inst) {
		code += "\n" + inst;
		return code;
	}

	public static void dump(String fileName) {

		// First Frame
		SL = newFrame(null, null);
		String frame_code = "\n" + 
				  "new " + SL + "\n" +
				  "dup\n" + 
				  "invokespecial " + SL + "/<init>()V\n" +
				  "astore_0\n"; 
		
		
		code = PREAMBLE + frame_code + code + EPILOGUE;
		System.out.println( code );
		
		// Writes code in file
		writeToDisk(fileName, code);
	}
	
	public static String ITypeToJasminType(IType type) {
		if( type instanceof IntType )
			return "I";
		else if (type instanceof BoolType)
			return "Z";
		else
			return "Ljava/lang/Object;";
	}
	
	public static String getRefType(IType type) {
		
		String key = ITypeToJasminType(type);
		String ref = ref_classes.get(key);
		if( ref == null ) {
			ref = "ref_" + key;
			ref_classes.put(key, ref);
			String code = ".class " + ref + "\n"
					+ ".super java/lang/Object\n"
					+ ".field public v " + key + "\n"
					+ ".end method";
			
			String path = "./compiled/";
			String fileName = path + ref + ".j";
			writeToDisk(fileName, code);
		}
			
		return ref;
	}
	
	public static String newClosureInterface(String key) {
		
		String closure_interace_id = "closure_interface_" + key;
		
		String code = ".interface " + closure_interace_id + "\n"
					+ ".method call" + key + "\n"
					+ ".end\n";
		
		String path = "./compiled/";
		String fileName = path + closure_interace_id + ".j";
		writeToDisk(fileName, code);
		
		return closure_interace_id;
	}
	
	public static String getClosureInterface(List<Entry<String, IType>> params, IType return_type) {
		String hash = "(";
		for(Entry<String, IType> e : params) {
			hash += ITypeToJasminType(e.getValue());

		}
		hash += ")" + ITypeToJasminType(return_type);
		
		String intr = closure_interfaces.get(hash);
		if( intr == null ) {
			intr = newClosureInterface(hash);
			closure_interfaces.put(hash, intr);
		}
		
		return intr;
	}
	
	public static String newClosure(List<Entry<String, IType>> params, String ancestor_frame_id, IType result_type, ASTNode body, Environment<> env) {
		String current_closure_id = "closure_" + closureCounter++;
		
		String current_interface = getClosureInterface(params, result_type);
		
		String call = "call(";
		int counter = 0;
		for(Entry<String, IType> e : params) {
			if(counter++ == params.size()-1)
				call += ITypeToJasminType(e.getValue());
			else
				call += ITypeToJasminType(e.getValue()) + ",";
		}
		call += ")" + ITypeToJasminType(result_type);
		
		String frame_id = newFrame(declarations, ancestor_frame_id); // Não tem de ser criado um frame cada vez que a funcao é chamada?
		
		String load_args = "";
		counter = 0;
		for(Entry<String, IType> e : params) {
			load_args += "dup\n"
				  + "aload " + counter + "; " + counter + "th arg\n"
				  + "putfield " + frame_id + "/loc_" + counter + ITypeToJasminType(e.getValue())
				  + "\n";
		}
		
		String body_code = "";
		
		//String code = "";
		String code = String.format("%s%s\n%s\n%s%s\n%s%s\n%s%d\n%s%s\n%s%s\n%s\n%s\n%s%s%s\n%s%s%s\n%s\n%s%s\n%s\n%s\n%s\n\n",
				".class ", current_closure_id,
				".super java/lang/Object",
				".implements ", current_interface,
				".field public sl L", ancestor_frame_id,
				".locals ", params.size()+1,
				"method ", call,
				"new ", frame_id,
				"dup",
				"aload_0 ; get this",
				"getfield ", current_closure_id, "/sl L" + ancestor_frame_id + "; get the closure's env",
				"putfield ", frame_id, "/sl L" + frame_id,
				load_args,
				"astore_1 ", "L" + frame_id,
				body_code,
				"return",
				".end"
				);
		
		String path = "./compiled/";
		String fileName = path + current_closure_id + ".j";
		writeToDisk(fileName, code);
		
		return current_closure_id;
	}

	public static String newFrame(Map<Entry<String, IType>, ASTNode> declarations, String ancestor_frame_id) {
		int current_frame_id = frameCounter++;
		
		String code = ".class frame_" + current_frame_id + "\n" +
					  ".super java/lang/Object\n" +
					  ".field public sl L" + ancestor_frame_id + ";\n\n"; // Como fazer com o null?
		
		int counter = 0;
		if( declarations != null ) {
			for(Entry<Entry<String, IType>, ASTNode> entry : declarations.entrySet()) {
				String type = ITypeToJasminType(entry.getKey().getValue());
				code += String.format(".field public loc_%d %s;\n", counter, type);
				
				counter++;
			}
		}
		
		code += "\n\n.method public <init>()V\n" + 
				"  aload_0\n" + 
				"  invokenonvirtual java/lang/Object/<init>()V\n" + 
				"  return\n" + 
				".end method\n";
		
		String path = "./compiled/";
		String fileName = path + "frame_" + current_frame_id + ".j";
		writeToDisk(fileName, code);
		
		return "frame_" + current_frame_id;
	}
	
	private static void writeToDisk(String fileName, String txt) {
		try {
			PrintWriter out = new PrintWriter(fileName);
			out.print(txt);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String loadFile(String path) {
		String out = "";
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			out = new String(encoded, Charset.forName("utf_8"));
		} catch (IOException e) {
		}
		
		return out;
	}
}
