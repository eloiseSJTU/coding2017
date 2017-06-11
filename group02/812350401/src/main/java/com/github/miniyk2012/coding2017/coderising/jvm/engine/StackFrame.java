package com.github.miniyk2012.coding2017.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.github.miniyk2012.coding2017.coderising.jvm.cmd.ByteCodeCommand;
import com.github.miniyk2012.coding2017.coderising.jvm.method.Method;

public class StackFrame {
	
	private List<JavaObject> localVariableTable = new ArrayList<>();
	private Stack<JavaObject> oprandStack = new Stack<>();
	
	int index = 0;  // 保存接下来要执行的那个命令的下标
	
	private Method m = null;  // 栈帧和函数绑定
	
	private StackFrame callerFrame = null;
	
	public StackFrame getCallerFrame() {
		return callerFrame;
	}

	public void setCallerFrame(StackFrame callerFrame) {
		this.callerFrame = callerFrame;
	}
	
	public static  StackFrame create(Method m){
		
		StackFrame frame = new StackFrame( m );			
		
		return frame;
	}

	
	private StackFrame(Method m) {		
		this.m = m;
		
	}

	public JavaObject getLocalVariableValue(int index){

		return this.localVariableTable.get(index);
	}
	
	public Stack<JavaObject> getOprandStack(){
		return this.oprandStack;
	}
	
	public int getNextCommandIndex(int offset){
		
		ByteCodeCommand [] cmds = m.getCodeAttr().getCmds();
		for(int i=0;i<cmds.length; i++){
			if(cmds[i].getOffset() == offset){
				return i;
			}
		}
		throw new RuntimeException("Can't find next command");
	}

	/**
	 * 从某个index位置继续执行函数，返回结果。
     * 有可能直接运行到return结束，也有可能运行到中间调用了一个新函数，那么就保留现场，待下次执行
	 * @return
	 */
	public ExecutionResult execute(){
		return null;
		
	}

	
	
	
	public void setLocalVariableTable(List<JavaObject> values){
		this.localVariableTable = values;	
	}
	
	public void setLocalVariableValue(int index, JavaObject jo){
		//问题： 为什么要这么做？？
		if(this.localVariableTable.size()-1 < index){
			for(int i=this.localVariableTable.size(); i<=index; i++){
				this.localVariableTable.add(null);
			}
		}
		this.localVariableTable.set(index, jo);
		
		
	}
	
	public Method getMethod(){
		return m;
	}
	

}
