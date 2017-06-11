package com.github.miniyk2012.coding2017.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.github.miniyk2012.coding2017.coderising.jvm.attr.CodeAttr;
import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.cmd.ByteCodeCommand;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.MethodRefInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.method.Method;

/**
 * 我们只考虑单线程的执行引擎
 */
public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();  // 函数栈帧
	
	public ExecutorEngine() {
		
	}

	/**
	 * 这是一个对函数栈帧的操作过程
	 * @param mainMethod
	 */
	public void execute(Method mainMethod){
		
		
		
	}


    /**
     * 把当前栈帧的参数给下一个函数栈帧
     * @param currentFrame
     * @param nextFrame
     */
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		
		
		
	}
	
}
