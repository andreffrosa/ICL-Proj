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

new java/lang/Double
dup
;neg
new java/lang/Double
dup
new java/lang/Double
dup
ldc2_w 2.0
invokespecial java/lang/Double/<init>(D)V

invokevirtual java/lang/Double/doubleValue()D
dneg
invokespecial java/lang/Double/<init>(D)V

invokevirtual java/lang/Double/doubleValue()D
sipush 2

i2d
ddiv
invokespecial java/lang/Double/<init>(D)V

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

