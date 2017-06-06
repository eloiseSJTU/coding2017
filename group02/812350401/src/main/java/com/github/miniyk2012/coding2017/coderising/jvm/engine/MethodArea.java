package com.github.miniyk2012.coding2017.coderising.jvm.engine;

import java.util.HashMap;
import java.util.Map;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.MethodRefInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.loader.ClassFileLoader;
import com.github.miniyk2012.coding2017.coderising.jvm.method.Method;

public class MethodArea {
	
	public static final MethodArea instance = new MethodArea();
	
	/**
	 * 注意：我们做了极大的简化， ClassLoader 只有一个， 实际JVM中的ClassLoader,是一个双亲委托的模型
	 */
	
	private ClassFileLoader clzLoader = null;
	
	Map<String,ClassFile> map = new HashMap<String,ClassFile>();
	
	private MethodArea(){		
	}
	
	public static MethodArea getInstance(){
		return instance;
	}
	
	public void setClassFileLoader(ClassFileLoader clzLoader){
		this.clzLoader = clzLoader;
	}
	
	public Method getMainMethod(String className){
		
		ClassFile clzFile = this.findClassFile(className);
		
		return clzFile.getMainMethod();
	}
	
	
	public  ClassFile findClassFile(String className){
		
		if(map.get(className) != null){
			return map.get(className);
		}
		// 看来该class 文件还没有load过
		ClassFile clzFile = this.clzLoader.loadClass(className);
		
		map.put(className, clzFile);
		
		return clzFile;
		
	}
	
	
	public Method getMethod(String className, String methodName, String paramAndReturnType){
		ClassFile clz = findClassFile(className);
		Method m =  clz.getMethod(methodName, paramAndReturnType);
        if(m == null){

            throw new RuntimeException("method can't be found : \n"
                    + "class: " + className
                    + "method: " + methodName
                    + "paramAndReturnType: " + paramAndReturnType);
        }
        return m;
	}
	
	
	public Method getMethod(MethodRefInfo methodRef){
        ClassFile clz = findClassFile(methodRef.getClassName());
		String methodName = methodRef.getMethodName();
		String paramAndReturnType = methodRef.getParamAndReturnType();
        Method m =  clz.getMethod(methodName, paramAndReturnType);
        if (m == null) {
            throw new RuntimeException("method can't be found : " + methodRef.toString());
        }
        return m;
	}
}
