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
sipush 4
putfield frame_0/loc_x I

new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 5
putfield frame_1/sl Lframe_0;
astore 5
aload 5
new struct_I
dup
invokespecial struct_I/<init>()V
dup
aload 5
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_x I
putfield struct_I/d I
putfield frame_1/loc_s Ljava/lang/Object;
aload 5
getfield frame_1/loc_s Ljava/lang/Object;
checkcast struct_I
getfield struct_I/d I

dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

