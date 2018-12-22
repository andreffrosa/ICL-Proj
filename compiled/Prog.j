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

ldc "hey"

ldc "hey"

invokevirtual java/lang/String/equals(Ljava/lang/Object;)Z
ifne label_2
sipush 0
goto label_3
label_2: 
sipush 1
label_3: 

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

