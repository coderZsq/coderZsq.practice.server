package cn.wolfcode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;

public final class EmployeeServiceProxy extends Proxy implements IEmployeeService {
	private static Method method_equals;
	private static Method method_toString;
	private static Method method_hashCode;
	private static Method method_update;
	private static Method method_save;

	public EmployeeServiceProxy(InvocationHandler paramInvocationHandler) {
		super(paramInvocationHandler);
	}
	
	static {
		try {
			method_equals = Class.forName("java.lang.Object").getMethod("equals",new Class[] { Class.forName("java.lang.Object") });
			method_toString = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			method_hashCode = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
			
			method_update = Class.forName("cn.wolfcode.service.IEmployeeService").getMethod("update",new Class[] { Class.forName("cn.wolfcode.domain.Employee") });
			method_save = Class.forName("cn.wolfcode.service.IEmployeeService").getMethod("save",new Class[] { Class.forName("cn.wolfcode.domain.Employee") });
		} catch (Exception e) {
		} 
	}

	public final boolean equals(Object paramObject) {
		try {
			return ((Boolean) this.h.invoke(this, method_equals, new Object[] { paramObject })).booleanValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	

	public final String toString() {
		try {
			return (String) this.h.invoke(this, method_toString, null);
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final int hashCode() {
		try {
			return ((Integer) this.h.invoke(this, method_hashCode, null)).intValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final void save(Employee paramEmployee) {
		try {
			this.h.invoke(this, method_save, new Object[] { paramEmployee });
			return;
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}
	
	public final void update(Employee paramEmployee) {
		try {
			this.h.invoke(this, method_update, new Object[] { paramEmployee });
			return;
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}



	public void delete(Long id) {
		
	}



	public List<Employee> listAll() {
		return null;
	}
}
