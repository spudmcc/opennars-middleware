package org.opennars.middle.operatorreflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.opennars.entity.Task;
import org.opennars.interfaces.Timable;
import org.opennars.language.Term;
import org.opennars.operator.Operation;
import org.opennars.operator.Operator;
import org.opennars.storage.Memory;


/**
 * Exposes a method of a object as a callable operator
 */
public class MethodInvocationOperator extends Operator {
	private Method method;
	private Object object;
	private List<Class> argumentTypes;

	/**
	 * @param narsOperatorName name of the operator as called by NARS
	 * @param object object of method call
	 * @param method called method
	 */
	public MethodInvocationOperator(final String narsOperatorName, final Object object, final Method method, final List<Class> argumentTypes) {
	    super(narsOperatorName);

	    this.object = object;
	    this.method = method;
	    this.argumentTypes = argumentTypes;
	}

	@Override
	protected List<Task> execute(Operation operation, Term[] args, Memory memory, Timable time) {
	    try {
	        if (argumentTypes.size() == 0) {
                method.invoke(object);
            }
            else if (argumentTypes.size() == 1 && argumentTypes.get(0).equals(String.class) ) {
	            method.invoke(object, args[1].toString());
            }
            else if (argumentTypes.size() == 2 && argumentTypes.get(0).equals(String.class) && argumentTypes.get(1).equals(String.class) ) {
                method.invoke(object, args[1].toString(), args[2].toString());
            }
            else if (argumentTypes.size() == 3 && argumentTypes.get(0).equals(String.class) && argumentTypes.get(1).equals(String.class) && argumentTypes.get(2).equals(String.class) ) {
                method.invoke(object, args[1].toString(), args[2].toString(), args[3].toString());
            }
            else {
	            // not handled
            }

	    }
	    catch (IllegalAccessException e) {
		// ignore
	    }
	    catch (InvocationTargetException e) {
		// ignore
	    }

	    return null;
	}
}
