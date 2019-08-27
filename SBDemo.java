package Implementing_Language_Interpreters_in_Java;

import java.io.File;

//Demonstrate the Small BASIC Interpreter.

class SBDemo {
	public static void main(String args[])
	{
		String path = new File("src\\Implementing_Language_Interpreters_in_Java\\TEST.BAS").getAbsolutePath();
		/*
		if(args.length != 1) {
			System.out.println("Usage: sbasic <filename>");
			return;
		}
		*/
		try {
			SBasic ob = new SBasic(path);
			ob.run();
		} catch(InterpreterException exc) {
			System.out.println(exc);
		}
	}
}

/*
 * errors...
 *
 * 1. 컴파일 오류(compile error)
 * error while writing **: **.class (액세스가 거부되었습니다)
 * javac -Xlint SBasic.java SBDemo.java
 *
 * 2. 실행 오류(execution error)
 * 오류: 기본 클래스 **을(를) 찾거나 로드할 수 없습니다.
 * java -classpath . SBDemo TEST.BAS
 *
 */