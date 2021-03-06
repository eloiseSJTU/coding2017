package com.coding.mini_jvm.src.com.coderising.jvm.engine;

import com.coding.mini_jvm.src.com.coderising.jvm.cmd.ByteCodeCommand;
import com.coding.mini_jvm.src.com.coderising.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class StackFrame {

	private List<JavaObject>  localVariableTable = new ArrayList<JavaObject>();
	private Stack<JavaObject> oprandStack        = new Stack<JavaObject>();

	int index = 0;

	private Method m = null;

	private StackFrame callerFrame = null;

	public StackFrame getCallerFrame() {
		return callerFrame;
	}

	public void setCallerFrame(StackFrame callerFrame) {
		this.callerFrame = callerFrame;
	}


	public static StackFrame create(Method m) {

		StackFrame frame = new StackFrame(m);

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
		
		ByteCodeCommand[] cmds = m.getCodeAttr().getCmds();
		for(int i=0;i<cmds.length; i++){
			if(cmds[i].getOffset() == offset){
				return i;
			}
		}
		throw new RuntimeException("Can't find next command");
	}
	
	public ExecutionResult execute(){
		System.out.printf("method %s is excuting\n", this.getMethod().getName());
		ByteCodeCommand[] commands = m.getCmds();
		if (commands.length > 0) {
			ExecutionResult result = new ExecutionResult();

			while (index < commands.length){
				ByteCodeCommand command = commands[index];
				System.out.println("\t " + command.toString(this.getMethod().getClzFile().getConstantPool()));
				command.execute(this, result);
				if (result.isPauseAndRunNewFrame()) {
					index++;
					return result;
				}
				else if (result.isExitCurrentFrame()) {
					return result;
				}
				else if (result.isRunNextCmd()) {
					index++;
				}
				else if (result.isJump()) {
					int offset = result.getNextCmdOffset();
					index = this.getNextCommandIndex(offset);
				} else {
					index++;
				}
			}
			return result;
		}
		ExecutionResult result = new ExecutionResult();
		result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
		return result;
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
