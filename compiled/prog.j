.class public Prog
.super java/lang/Object

.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 10
       .limit stack 256
       
      ; ---- END OF INTRO CODE
     ;PrintStream object held in java.lang.out
     getstatic java/lang/System/out Ljava/io/PrintStream;

;~E
;left && right
;left
sipush 1 ;true

;right
sipush 0 ;false

iand

if_icmpeq true_label
sipush 1
true_label:
sipush 0

     ;convert to String;
     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

     ; call println 
     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; ---- START OF EPILOGUE CODE
       return
.end method

