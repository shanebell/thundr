/*
 * This file is a component of thundr, a software library from 3wks.
 * Read more: http://www.3wks.com.au/thundr
 * Copyright (C) 2013 3wks, <thundr@3wks.com.au>
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
package com.threewks.thundr.http.exception;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HttpStatusExceptionTest {

	@Test
	public void shouldFormatAndRetainStatusAndReason() {
		HttpStatusException httpStatusException = new HttpStatusException(123, "String %s", "format");
		assertThat(httpStatusException.getMessage(), is("String format"));
		assertThat(httpStatusException.getCause(), is(nullValue()));
		assertThat(httpStatusException.getStatus(), is(123));
	}

	@Test
	public void shouldFormatAndRetainCauseStatusAndAndReason() {
		Exception exception = new Exception("cause");
		HttpStatusException httpStatusException = new HttpStatusException(exception, 456, "String %s", "format");
		assertThat(httpStatusException.getMessage(), is("String format"));
		assertThat(httpStatusException.getCause(), is((Throwable) exception));
		assertThat(httpStatusException.getStatus(), is(456));
	}
}
