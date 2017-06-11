package com.github.miniyk2012.coding2017.coderising.jvm.cmd;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.ExecutionResult;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString() {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {

	}


	public  int getLength(){
		return 1;
	}

}
