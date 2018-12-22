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


new frame_0
dup
invokespecial frame_0/<init>()V
astore 5
aload 5
new struct_T_I
dup
invokespecial struct_T_I/<init>()V
dup
ldc "bina"
putfield struct_T_I/name Ljava/lang/String;
dup
sipush 21
putfield struct_T_I/age I
putfield frame_0/loc_bina Ljava/lang/Object;
aload 5
getfield frame_0/loc_bina Ljava/lang/Object;
checkcast struct_T_I
getfield struct_T_I/name Ljava/lang/String;

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

pop

new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 5
putfield frame_1/loc_sl Lframe_0;
astore 5
aload 5
new ref_I
dup
invokespecial ref_I/<init>()V
dup
sipush 0

putfield ref_I/v I
putfield frame_1/loc_i Ljava/lang/Object;
label_0: 
aload 5
getfield frame_1/loc_i Ljava/lang/Object;

checkcast ref_I
getfield ref_I/v I

sipush 5

isub
iflt label_1
goto label_2

label_1: 
aload 5
getfield frame_1/loc_sl frame_0
getfield frame_0/loc_bina Ljava/lang/Object;
checkcast struct_T_I
getfield struct_T_I/age I

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

pop
;left
aload 5
getfield frame_1/loc_i Ljava/lang/Object;

checkcast ref_I
getfield ref_I/v I

;right
sipush 1

iadd

dup
aload 5
getfield frame_1/loc_i Ljava/lang/Object;

checkcast ref_I
swap
putfield ref_I/v I


pop
goto label_0
label_2: 
sipush 1


   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

