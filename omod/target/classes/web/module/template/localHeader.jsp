<spring:htmlEscape defaultHtmlEscape="true" />
<openmrs:htmlInclude file="moduleResources\webapp\resources\styles.css"/>
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/patientlist/manage.form"><spring:message
				code="patientlist.manage" /></a>
	</li>
	<li
			<c:if test='<%= request.getRequestURI().contains("/addPatient") %>'>class="active"</c:if>>
		<a
				href="${pageContext.request.contextPath}/module/patientlist/addPatient.form"><spring:message
				code="Register" /></a>
	</li>

	<li
			<c:if test='<%= request.getRequestURI().contains("/listPatients") %>'>class="active"</c:if>>
		<a
				href="${pageContext.request.contextPath}/module/patientlist/listPatients.form"><spring:message
				code="List patients" /></a>
	</li>

	<!-- Add further links here -->
</ul>
<h2>
	<spring:message code="patientlist.title" />
</h2>
