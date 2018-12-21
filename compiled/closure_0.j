.class closure_0
.super java/lang/Object
.implements closure_interface_0
.field public sl LFrame1

.method public call(I)I
	.limit locals 5
	new frame_0
	dup
	invokenonvirtual frame_0/<init>()V
	dup
	; get this
	aload 0
	getfield closure_0/sl LFrame1; ;get the closure's env
	putfield frame_0/sl Lframe_0
	dup
	; 0th arg
	aload 1
	putfield frame_0/loc_0 I
	astore_1 
	getfield frame_0/loc_0 I
	return
.end

