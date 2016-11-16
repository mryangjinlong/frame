package com.design_pattern.proxy.compiler;
import java.lang.reflect.Method;
import com.design_pattern.proxy.InvocationHandler;
public class SomeBodyProxy implements com.design_pattern.proxy.Moveable {
    public SomeBodyProxy(InvocationHandler invocationHandler) {
        super();
        this.invocationHandler = invocationHandler;
    }
    InvocationHandler invocationHandler;
    @Override    
    public boolean stop(int arg0)  {
	 try{	
	 	Object[] objs = new Object[]{arg0};
	 	Method method = com.design_pattern.proxy.Moveable.class.getMethod("stop",int.class);
	 	invocationHandler.invoke(this,method,objs);	
	 }catch(Exception e){
		System.out.println("报错咯");
	 }	
		 return false;
    }
    @Override    
    public void move()  {
	 try{	
	 	Object[] objs = new Object[]{};
	 	Method method = com.design_pattern.proxy.Moveable.class.getMethod("move");
	 	invocationHandler.invoke(this,method,objs);	
	 }catch(Exception e){
		System.out.println("报错咯");
	 }	
		 
    }
}