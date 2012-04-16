package com.atomicleopard.webFramework.bind;

import static com.atomicleopard.expressive.Expressive.list;
import jodd.typeconverter.TypeConverterManager;

import com.atomicleopard.webFramework.bind.http.PathMap;
import com.atomicleopard.webFramework.introspection.ParameterDescription;

public class BasicTypesParameterBinder implements ParameterBinder<Object> {
	public Object bind(Binders binder, ParameterDescription parameterDescription, PathMap pathMap) {
		String[] values = pathMap.get(list(parameterDescription.name()));
		return values != null && values.length > 0 ? TypeConverterManager.lookup(parameterDescription.classType()).convert(values[0]) : null;
	}

	@Override
	public boolean willBind(ParameterDescription parameterDescription) {
		return TypeConverterManager.lookup(parameterDescription.classType()) != null;
	}
}
