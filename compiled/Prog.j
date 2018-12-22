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
new ref_I
dup
invokespecial ref_I/<init>()V
dup
sipush 0

putfield ref_I/v I
putfield Frame1/x Ljava/lang/Object;
label_0: 
aload 4
getfield Frame1/x Ljava/lang/Object;

checkcast ref_I
getfield ref_I/v I

sipush 5

isub
iflt label_1
goto label_2

label_1: 
aload 4
getfield Frame1/x Ljava/lang/Object;

checkcast ref_I
getfield ref_I/v I

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
          invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

pop
;left + right
;left
aload 4
getfield Frame1/x Ljava/lang/Object;

checkcast ref_I
getfield ref_I/v I

;right
sipush 1

iadd

dup
aload 4
getfield Frame1/x Ljava/lang/Object;

checkcast ref_I
swap
putfield ref_I/v I


goto label_0
label_2: 
aload 4
getfield Frame1/SL LFrame0;
astore 4

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

