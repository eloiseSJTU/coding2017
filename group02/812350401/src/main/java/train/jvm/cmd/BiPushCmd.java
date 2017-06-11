package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.Heap;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
	
		return this.getOffset()+":"+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	public void execute(StackFrame frame, ExecutionResult result){
		int value = this.getOperand();
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOprandStack().push(jo);
	}
	

}
