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
ldc "hello world"
putfield frame_0/loc_x Ljava/lang/String;
aload 5
getfield frame_0/loc_x Ljava/lang/String;

ldc "!"

invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

