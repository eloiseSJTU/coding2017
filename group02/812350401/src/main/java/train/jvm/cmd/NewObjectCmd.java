package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.constant.ClassInfo;
import train.jvm.engine.ExecutionResult;
import train.jvm.engine.Heap;
import train.jvm.engine.JavaObject;
import train.jvm.engine.StackFrame;

/**
 * 创建一个对象,并将引用入栈
 */
public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		
		return super.getOperandAsClassInfo();
	}
	public void execute(StackFrame frame, ExecutionResult result){
		
		int index = this.getIndex();
		
		ClassInfo info = (ClassInfo)this.getConstantInfo(index);
		
		String clzName = info.getClassName();
		
		//在Java堆上创建一个实例
		JavaObject jo = Heap.getInstance().newObject(clzName);
		
		frame.getOprandStack().push(jo);
		
		
		
	}
	
}
