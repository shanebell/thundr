package com.threewks.thundr.action.method.bind.path;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.TypeConverterManager;

import com.threewks.thundr.action.method.bind.ActionMethodBinder;
import com.threewks.thundr.introspection.ParameterDescription;

public class PathVariableBinder implements ActionMethodBinder {
	public static final List<Class<?>> PathVariableTypes = Arrays.<Class<?>> asList(String.class, int.class, Integer.class, double.class, Double.class, long.class, Long.class, short.class,
			Short.class, float.class, Float.class, BigDecimal.class, BigInteger.class);

	@Override
	public boolean canBind(String contentType) {
		return true;
	}

	@Override
	public List<Object> bindAll(List<ParameterDescription> parameterDescriptions, HttpServletRequest req, HttpServletResponse resp, Map<String, String> pathVariables) {
		List<Object> results = new ArrayList<Object>(parameterDescriptions.size());
		for (ParameterDescription parameterDescription : parameterDescriptions) {
			if (canBindFromPathVariable(parameterDescription)) {
				Object value = bind(parameterDescription, pathVariables);
				results.add(value);
			} else {
				results.add(null);
			}
		}
		return results;
	}

	private boolean canBindFromPathVariable(ParameterDescription parameterDescription) {
		return PathVariableTypes.contains(parameterDescription.type());
	}

	private Object bind(ParameterDescription parameterDescription, Map<String, String> pathVariables) {
		String value = pathVariables.get(parameterDescription.name());
		return TypeConverterManager.lookup(parameterDescription.classType()).convert(value);
	}
}
