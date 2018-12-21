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


new Frame1
dup
invokespecial Frame1/<init>()V
astore 4
aload 4
new closure_0
dup
invokespecial closure_0/<init>()V
dup
aload_4 ; SL
; set environment field of the closure
putfield closure_0/sl Ljava/lang/Object;
putfield Frame1/f Ljava/lang/Object;
aload 4
getfield Frame1/f Ljava/lang/Object;

checkcast closure_interface_0
sipush 2

invokeinterface closure_interface_0 /call(I)I

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
aload 4
getfield Frame1/SL LFrame0;
astore 4

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

