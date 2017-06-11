package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.Heap;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

public class IncrementCmd extends TwoOperandCmd {

	public IncrementCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
		
	}

	@Override
	public String toString() {
		
		return this.getOffset()+":"+this.getOpCode()+ " " +this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		int index = this.getOprand1();
		
		int constValue = this.getOprand2();
		
		int currentValue = frame.getLocalVariableValue(index).getIntValue();
		
		JavaObject jo = Heap.getInstance().newInt(constValue+currentValue);
		
		frame.setLocalVariableValue(index, jo);
		

	}

}