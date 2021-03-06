
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<table>
    <thead>
    <tr>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Age</th>
        <th>Gender</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="patient" items="${patientList}" varStatus="status">
        <tr>
            <td>${patient.givenName}</td>
            <td>${patient.middleName}</td>
            <td>${patient.age}</td>
            <td>${patient.gender}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>