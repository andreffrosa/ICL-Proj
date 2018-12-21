.class public Prog
.super java/lang/Object

.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
   ; set limits used by this method
   .limit stack 128
   .limit locals 10
   ; ---- END OF PREAMBLE CODE

new ref_T
dup
invokespecial ref_T/<init>()V
dup
new ref_I
dup
invokespecial ref_I/<init>()V
dup
sipush 2

putfield ref_I/v I

putfield ref_T/v Ljava/lang/Object;

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     checkcast ref_T
getfield ref_T/v Ljava/lang/Object;
checkcast ref_I
getfield ref_I/v I
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
astore_3
new java/lang/StringBuilder
dup
ldc "[["
invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;
invokespecial java/lang/StringBuilder/<init>(Ljava/lang/String;)V
aload_3
invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;
ldc "]]"
invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;
invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

