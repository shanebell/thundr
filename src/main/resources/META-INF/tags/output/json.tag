<%--

    This file is a component of thundr, a software library from 3wks.
    Read more: http://3wks.github.io/thundr/
    Copyright (C) 2014 3wks, <thundr@3wks.com.au>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@tag import="com.threewks.thundr.json.GsonSupport"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" description=""%>
<%@ tag import="com.google.gson.Gson" %>
<%@ attribute name="value" required="true" type="java.lang.Object" description="Object to convert to JSON." %>
<%@ attribute name="gson" required="false" type="com.google.gson.Gson" description="Override the default Gson instance to perform the JSON conversion with" %>
<%@ taglib prefix="t" uri="http://threewks.com/thundr/tags" %>
<%
	if (gson == null) {
		gson = GsonSupport.createBasicGsonBuilder().create();
	}
	out.print(gson.toJson(value));
%>