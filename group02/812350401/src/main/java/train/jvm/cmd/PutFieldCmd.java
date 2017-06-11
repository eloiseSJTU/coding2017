package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.constant.ClassInfo;
import train.jvm.constant.FieldRefInfo;
import train.jvm.constant.NameAndTypeInfo;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		
		ClassInfo clzInfo = (ClassInfo)this.getConstantInfo(fieldRef.getClassInfoIndex());
		NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRef.getNameAndTypeIndex());
		// for example : name
		String fieldName = nameTypeInfo.getName();
		// for example : Ljava/lang/String : 注意：我们不再检查类型
		String fieldType = nameTypeInfo.getTypeInfo();
		
		JavaObject fieldValue = frame.getOprandStack().pop();
		JavaObject objectRef = frame.getOprandStack().pop();
		
		objectRef.setFieldValue(fieldName, fieldValue);
		
	}



}
