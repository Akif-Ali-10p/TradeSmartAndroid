package com.tenpearls.android.utilities;

import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
 * This utility can be used for Reflection
 */
public class JavaUtility {

    /**
     * Returns the Super {@link Class} object of {@link Type}
     * @param type
     * @return Default is {@link Object} Class
     */

    public static Class getSuperclassOfTokenType(Type type) {
        try {

            Class currentClass = JavaUtility.getClassOfTokenType(type);
            if(currentClass.equals(Object.class)) {
                return currentClass;
            }

            return currentClass.getSuperclass();

        } catch (Exception e) {
            return Object.class;
        }
    }


    /**
     * Returns the {@link Class} object of {@link Type}
     * @param type
     * @return Default is {@link Object} Class
     */

    public static Class getClassOfTokenType(Type type) {
        try {

            return Class.forName(type.toString().split(" ")[1]);

        } catch (Exception e) {
            return Object.class;
        }
    }
    /**
     * This method can be used to invoke a method on a
     * target object with a set of arguments
     *
     * @param methodToInvoke Name of method to invoke
     * @param targetObj Object to invoke method on
     * @param arguments Variable list of arguments
     *
     * @return This method will return whatever the method returns
     */

    public static Object invokeMethod(String methodToInvoke, Object targetObj, Object... arguments) throws Exception{

        Method[] allMethods = targetObj.getClass().getMethods();
        for (Method m : allMethods) {
            String methodName = m.getName();
            if (methodName.equals(methodToInvoke)) {
                return m.invoke(targetObj, arguments);
            }
        }
        return null;
    }
}
