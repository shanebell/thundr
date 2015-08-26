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
package com.threewks.thundr.transformer.date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateTimeToDateTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private DateTimeToDate transformer = new DateTimeToDate();

	@Test
	public void shouldNotTransformNull() {
		assertThat(transformer.from(null), is(nullValue()));
	}

	@Test
	public void shouldTransform() {
		verifyTransform(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.forOffsetHours(10)));
		verifyTransform(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.forOffsetHours(-10)));
	}

	private void verifyTransform(DateTime date) {
		Date expected = new Date(date.getMillis());
		assertThat(transformer.from(date), is(expected));
	}
}