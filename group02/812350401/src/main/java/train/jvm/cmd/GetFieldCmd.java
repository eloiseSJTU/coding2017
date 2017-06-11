package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.constant.FieldRefInfo;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String fieldName = fieldRef.getFieldName();
		JavaObject jo = frame.getOprandStack().pop();
		JavaObject fieldValue = jo.getFieldValue(fieldName);
		
		frame.getOprandStack().push(fieldValue);
		
		
		
	}
	

}
