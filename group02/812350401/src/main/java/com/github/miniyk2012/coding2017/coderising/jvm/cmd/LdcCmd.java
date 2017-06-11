package com.github.miniyk2012.coding2017.coderising.jvm.cmd;


import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.StringInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.ExecutionResult;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.Heap;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.JavaObject;
import com.github.miniyk2012.coding2017.coderising.jvm.engine.StackFrame;


/**
 * 如果运行时常量池项是一个 int 或者 float 类型的运行时常量，则将对应的数值 value 将被压入到操作数栈之中(暂时不处理)。
 * 如果运行时常量池项是一个代表字符串字面量的 String 类的引用，那这个实例的引用所对应的 reference 类型数据
 * value 将被压入 到操作数栈之中。
 */
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

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
        ConstantInfo info = getConstantInfo(getOperand());
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo)info;
            String value = strInfo.toString();
            JavaObject jo = Heap.getInstance().newString(value);
            frame.getOprandStack().push(jo);
            return;
        }
        // todo 处理其他类型（int 或者 float 类型）
        throw new RuntimeException("Only support StringInfo constant");
	}

}
