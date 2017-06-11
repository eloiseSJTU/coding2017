package train.jvm.cmd;


import train.jvm.clz.ClassFile;
import train.jvm.constant.ClassInfo;
import train.jvm.constant.ConstantInfo;
import train.jvm.constant.FieldRefInfo;
import train.jvm.constant.MethodRefInfo;

public abstract class TwoOperandCmd extends ByteCodeCommand{
	
	int oprand1 = -1;
	int oprand2 = -1;
	
	public int getOprand1() {
		return oprand1;
	}

	public void setOprand1(int oprand1) {
		this.oprand1 = oprand1;
	}

	public void setOprand2(int oprand2) {
		this.oprand2 = oprand2;
	}

	public int getOprand2() {
		return oprand2;
	}
	
	public TwoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	public int getIndex(){
		int oprand1 = this.getOprand1();
		int oprand2 = this.getOprand2();
		int index = oprand1 << 8 | oprand2;
		return index;
	}
	
	protected String getOperandAsClassInfo(){
		int index = getIndex();
		String codeTxt = getReadableCodeText();
		ClassInfo info = (ClassInfo)getConstantInfo(index);
		return this.getOffset()+":"+this.getOpCode()+" "+ codeTxt +"  "+ info.getClassName();
	}
	
	protected String getOperandAsMethod(){
		int index = getIndex();
		String codeTxt = getReadableCodeText();
		ConstantInfo constInfo = this.getConstantInfo(index);
		MethodRefInfo info = (MethodRefInfo)this.getConstantInfo(index);
		return this.getOffset()+":"+this.getOpCode()+" " + codeTxt +"  "+ info.toString();
	}

	protected String getOperandAsField(){
		int index = getIndex();
		
		String codeTxt = getReadableCodeText();
		FieldRefInfo info = (FieldRefInfo)this.getConstantInfo(index);
		return this.getOffset()+":"+this.getOpCode()+" " + codeTxt +"  "+ info.toString();
	}
	public  int getLength(){
		return 3;
	}
}
