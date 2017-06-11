package com.github.miniyk2012.coding2017.coderising.jvm.cmd;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ClassInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.ExecutionResult;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.Heap;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.JavaObject;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		return super.getOperandAsClassInfo();
	}

    /**
     * 创建一个对象，并将其引用值压入栈顶。
     * @param frame 命令执行过程中需要访问函数栈帧中的操作数栈和局部变量表，因此把frame传入
     * @param result
     */
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int classIndex = getIndex();
		String className = ((ClassInfo)getConstantInfo(classIndex)).getClassName();
		JavaObject jo = Heap.getInstance().newObject(className);
		frame.getOprandStack().push(jo);
	}


}
