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

;left % right
;left
sipush 2

;right
sipush 4

irem

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

pop
new java/lang/Double
dup
sipush 4

i2d
new java/lang/Double
dup
ldc2_w 2.5
invokespecial java/lang/Double/<init>(D)V

invokevirtual java/lang/Double/doubleValue()D
drem
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

