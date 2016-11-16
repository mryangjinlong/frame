package com.design_pattern.proxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Proxy {
	public static String PACKAGE_PATH = "com.design_pattern.proxy.compiler"; 
	public static String PROXY_NAME = "SomeBodyProxy";
	public static String QUALIFIED_NAME = PACKAGE_PATH + "." + PROXY_NAME;
	public static String FILE_NAME = System.getProperty("user.dir") 
			+ "/src/com/design_pattern/proxy/compiler/"+PROXY_NAME+".java";
	public static String ARG_NAME = "arg";
	public static Object newProxyInstance(Class interfaces , InvocationHandler invocationHandler) throws Exception{
		
		Method[] methods = interfaces.getMethods();
		
		String rt = "\r\n";
		
		String source = 
			"package com.design_pattern.proxy.compiler;" +  rt +
			"import java.lang.reflect.Method;"  + rt +
 			"import com.design_pattern.proxy.InvocationHandler;" + rt +
			"public class "+PROXY_NAME+" implements "+interfaces.getName() +" {" + rt +
			"    public "+PROXY_NAME+"(InvocationHandler invocationHandler) {" + rt +
			"        super();" + rt +
			"        this.invocationHandler = invocationHandler;" + rt +
			"    }" + rt +
			
			"    InvocationHandler invocationHandler;" + rt ;
		
		String methodStr = "";
		
		for (Method method : methods) {
					String methodName = method.getName();
					String returnTypeName = method.getReturnType().getName();
					String returnStr = processReturnType(returnTypeName);
					String parameterStr = processParameterStr(method);
					String argsName = processArgsName(method);
					String getMethodNeedArgs = processMethodNeedArgs(method);
					
					methodStr +=
						"    @Override    " + rt +
						"    public "+returnTypeName+" "+methodName+"("+parameterStr+")  {" + rt +
						"	 try{	" + rt +	
						"	 	Object[] objs = new Object[]{"+argsName+"};" + rt +
						"	 	Method method = " + interfaces.getName() + 
						".class.getMethod(\"" + method.getName() + "\"" + getMethodNeedArgs+");"  + rt +
						"	 	invocationHandler.invoke(this,method,objs);	"  + rt +
						"	 }catch(Exception e){"  + rt +
						"		System.out.println(\"报错咯\");"	+ rt +
						"	 }	" + rt +
						"		 "+ returnStr + rt +
						"    }" + rt;
				}
			
			source += methodStr;
			source +=	"}";
		
		createFile(source , FILE_NAME);
		
		compiler(FILE_NAME);
		return loadIntoRam(QUALIFIED_NAME,invocationHandler);
	}

	private static String processMethodNeedArgs(Method method) {
		String getMethodNeedArgs = "";
		
		for (int i = 0 ; i < method.getParameterTypes().length ; i++) {
			String parameterName = method.getParameterTypes()[i].getName();
//			if(i == 0) getMethodNeedArgs += parameterName + ".class";
		/*	else*/ getMethodNeedArgs += "," +parameterName + ".class";
		}
		return getMethodNeedArgs;
	}

	private static String processArgsName(Method method) {
		String argsName = "";
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			if(i == 0) argsName += ARG_NAME+i;
			else argsName += "," + ARG_NAME+i;
		}
		return argsName;
	}

	private static String processParameterStr(Method method) {
		Class<?>[] args = method.getParameterTypes();
		String str = "";
		for (int i = 0 ; i < args.length ; i++ ) {
			String parameterName = args[i].getName();
			
			if(i == 0)
				str += parameterName + " "+ ARG_NAME + i;
			else
				str += ", " + parameterName + " " + ARG_NAME + i;
			
		}
		return str;
	}
	
	private static String processReturnType(String returnTypeName){
		if(returnTypeName.equals("void")) return "";
		else if(returnTypeName.equals("boolean")) return "return false;";
		else return "";
	}
	
	private static void createFile(String source , String fileName) throws Exception{
			File f = new File(fileName);
			FileWriter fw = new FileWriter(f);
			fw.write(source);
			fw.flush();
			fw.close();
	}
	
	private static void compiler(String fileName) throws IOException{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> untils = fileManager.getJavaFileObjects(fileName);
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, untils);
		task.call();
		fileManager.close();
	}
	private static Object loadIntoRam(String clazzName , InvocationHandler invocationHandler) throws Exception{
		URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/src")};
		
		URLClassLoader urlClassLoader = new URLClassLoader(urls);
		Class clazz = urlClassLoader.loadClass(clazzName);
		System.out.println("clazz======"+clazz);
		Constructor constructor = clazz.getConstructor(InvocationHandler.class);
		return constructor.newInstance(invocationHandler);
	}
	
}
