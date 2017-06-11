package train.jvm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import train.jvm.engine.MiniJVM;


public class MiniJVMTest {
	static final String PATH = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\answer\\bin";
	static String PATH1 = MiniJVMTest.class.getClassLoader().getResource("struts").getPath();
	static String PATH2 = MiniJVMTest.class.getClassLoader().getResource("classfile").getPath();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() throws Exception{
		String[] classPaths = {PATH, PATH2, PATH2};
		MiniJVM jvm = new MiniJVM();

        jvm.run(classPaths, "com.coderising.jvm.test.EmployeeV1");
//        jvm.run(classPaths, "com.coderising.jvm.test.HourlyEmployee");
//        jvm.run(classPaths, "com.coderising.jvm.test.EmployeeV2");

	}

}
