package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.constant.ConstantInfo;
import train.jvm.constant.ConstantPool;
import train.jvm.constant.StringInfo;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.Heap;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString() {
		
		ConstantInfo info = getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}
	public void  execute(StackFrame frame, ExecutionResult result){
		
		ConstantPool pool = this.getConstantPool();
		ConstantInfo info = (ConstantInfo)pool.getConstantInfo(this.getOperand());
		
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			String value = strInfo.toString();
			JavaObject jo = Heap.getInstance().newString(value);
			frame.getOprandStack().push(jo);
		}
		else{
			//TBD 处理其他类型
			throw new RuntimeException("Only support StringInfo constant");
		}
		
		
	}
}
