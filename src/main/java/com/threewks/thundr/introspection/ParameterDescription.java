/*
 * This file is a component of thundr, a software library from 3wks.
 * Read more: http://3wks.github.io/thundr/
 * Copyright (C) 2014 3wks, <thundr@3wks.com.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.threewks.thundr.introspection;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.atomicleopard.expressive.Cast;

import jodd.util.ReflectUtil;

public class ParameterDescription {
	private String name;
	private Type type;

	public ParameterDescription(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
	}

	public boolean isA(Class<?> is) {
		Class<?> clazz = classType();
		return clazz == null ? false : clazz.isAssignableFrom(is);
	}

	public Type getGenericType(int index) {
		ParameterizedType pt = Cast.as(type, ParameterizedType.class);
		if (pt != null) {
			Type[] generics = pt.getActualTypeArguments();
			if (index < 0) {
				index = generics.length + index;
			}
			if (index < generics.length) {
				return generics[index];
			}
		}
		return null;
	}

	public Type getArrayType() {
		Class<?> clazz = Cast.as(type, Class.class);
		if (clazz != null) {
			return clazz.getComponentType();
		}
		GenericArrayType gat = Cast.as(type, GenericArrayType.class);
		if (gat != null) {
			return gat.getGenericComponentType();
		}
		return null;
	}

	public boolean isGeneric() {
		return ReflectUtil.getComponentType(type) != null;
	}

	public String name() {
		return name;
	}

	public Class<?> classType() {
		return ReflectUtil.toClass(type);
	}

	public Type type() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("%s %s", type, name);
	}
}
