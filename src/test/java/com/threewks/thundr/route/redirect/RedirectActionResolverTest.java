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
package com.threewks.thundr.route.redirect;

import static com.atomicleopard.expressive.Expressive.map;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.threewks.thundr.route.HttpMethod;
import com.threewks.thundr.route.RouteResolverException;

public class RedirectActionResolverTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private RedirectRouteResolver resolver = new RedirectRouteResolver();

	@Test
	public void shouldSendRedirectToClient() throws IOException {
		Redirect action = new Redirect("/redirect/{to}");
		HttpMethod method = HttpMethod.POST;
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		Map<String, String> pathVars = map("to", "new");
		resolver.resolve(action, method, req, resp, pathVars);

		verify(resp).sendRedirect("/redirect/new");
	}

	@Test
	public void shouldThrowActionExceptionWhenRedirectFails() throws IOException {
		thrown.expect(RouteResolverException.class);
		thrown.expectMessage("Failed to redirect /requested/path to /redirect/new");

		Redirect action = new Redirect("/redirect/{to}");
		HttpMethod method = HttpMethod.POST;
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getRequestURI()).thenReturn("/requested/path");
		HttpServletResponse resp = mock(HttpServletResponse.class);
		Map<String, String> pathVars = map("to", "new");

		doThrow(new IOException("expected")).when(resp).sendRedirect(anyString());
		resolver.resolve(action, method, req, resp, pathVars);
	}
}
