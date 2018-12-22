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

;left
ldc "asd_"


;right
sipush 1

     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;


dup

     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

swap
invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   ; ---- START OF EPILOGUE CODE
   pop
   return
.end method

