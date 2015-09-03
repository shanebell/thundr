/*
 * This file is a component of thundr, a software library from 3wks.
 * Read more: http://3wks.github.io/thundr/
 * Copyright (C) 2015 3wks, <thundr@3wks.com.au>
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
package com.threewks.thundr.view.negotiating.strategy;

import org.apache.commons.lang3.StringUtils;

import com.threewks.thundr.request.Request;
import com.threewks.thundr.view.negotiating.NegotiatingView;
import com.threewks.thundr.view.negotiating.Negotiator;
import com.threewks.thundr.view.negotiating.ViewNegotiatorRegistry;

import jodd.util.MimeTypes;

/**
 * Finds a {@link Negotiator} using the file extension on the request.
 * If no matching negotiator exists, returns null
 */
public class FileExtensionNegotiationStrategy implements NegotiationStrategy {

	@Override
	public Negotiator<?> findNegotiator(Request req, NegotiatingView view, ViewNegotiatorRegistry viewNegotiatorRegistry) {
		String requestUri = req.getRequestPath();
		String extension = StringUtils.trimToEmpty(StringUtils.substringAfterLast(requestUri, "."));
		String mimeType = MimeTypes.lookupMimeType(extension);
		return viewNegotiatorRegistry.getNegotiator(mimeType);
	}

}
