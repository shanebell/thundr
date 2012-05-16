package com.threewks.thundr.action.method.bind.http;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jodd.bean.BeanTool;

import com.atomicleopard.expressive.Expressive;
import com.threewks.thundr.action.method.bind.BindException;
import com.threewks.thundr.introspection.ParameterDescription;

public class JavaBeanParameterBinder implements ParameterBinder<Object> {
	private static Set<Class<?>> classesToSkip = createClassesToSkip();

	public Object bind(ParameterBinderSet binders, ParameterDescription parameterDescription, HttpPostDataMap pathMap) {
		Map<String, Object> stringMap = pathMap.toStringMap(parameterDescription.name());
		if (!stringMap.isEmpty()) {
			try {
				Object bean = parameterDescription.classType().newInstance();
				BeanTool.load(bean, stringMap);
				return bean;
			} catch (Exception e) {
				throw new BindException(e, "Failed to bind onto %s: %s", parameterDescription.classType(), e.getMessage());
			}
		}
		return null;
	}

	private boolean shouldProcess(Class<?> type) {
		return !classesToSkip.contains(type);
	}

	@Override
	public boolean willBind(ParameterDescription parameterDescription) {
		try {
			Class<?> type = parameterDescription.classType();
			return shouldProcess(type) && type.getConstructor(new Class[0]) != null;
		} catch (Exception e) {
			return false;
		}
	}

	private static Set<Class<?>> createClassesToSkip() {
		return new HashSet<Class<?>>(Expressive.<Class<?>> list(String.class, int.class, byte.class, short.class, float.class, double.class, long.class, void.class, Integer.class, Byte.class,
				Short.class, Float.class, Double.class, Long.class, Void.class));
	}

}
