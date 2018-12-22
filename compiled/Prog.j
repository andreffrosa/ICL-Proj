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

sipush 2

i2d
new java/lang/Double
dup
ldc2_w 1.0
invokespecial java/lang/Double/<init>(D)V

invokevirtual java/lang/Double/doubleValue()D
dcmpl
ifgt label_0
sipush 0
goto label_1
label_0: 
sipush 1
label_1: 


dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
invokestatic     java/lang/String.valueOf(Z)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

