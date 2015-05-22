
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>


<form action="<c:url value="/module/patientlist/addPatient.form"/>" method="POST">
    First Name:<br/>
    <input type="text" name="first"/>
    <br/>
    Middle Name:<br/>
    <input type="text" name="middle"/><br/>
    Surname: <br>
    <input type="text" name="last"/><br/>
    Date of Birth:<br><br/>
    <input type="date" name="dob"/><br/>
    National ID:<br>
    <input type="number" name="nationalId"><br/>
    Gender: <br>
    <input type="radio" name="gender" value="Female">Female</input>
    <input type="radio" name="gender" value="Male">Male</input>

    <input type="submit" name="submit" value="Save">

</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>