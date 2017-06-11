package com.github.miniyk2012.coding2017.coderising.jvm.cmd;


import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.ExecutionResult;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.StackFrame;

/**
 * 调用实例初始化方法
 */
public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		
		return super.getOperandAsMethod();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {

	}


}
