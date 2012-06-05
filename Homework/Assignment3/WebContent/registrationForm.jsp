<html>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<body>
<form style="width: 510px" name="frmMain" action="RegistrationSubmit.htm" method="post">
<div class="header">Johns Hopkins Annual Software Development Seminar</div>
<div class="error"><%= request.getAttribute("errors")==null ? "" : 
		request.getAttribute("errors")
%></div>
<div class="section">
	<div><span class="sectionTitle">Contact Information</span></div>
	<div><span class="label">Name:</span><input type="text" id="name" name="name" value="${param.name}"/></div>
	<div><span class="label">E-mail:</span><input type="text" id="email" name="email" value="${param.email}"/></div>
</div>
<div class="section">
	<div><span class="sectionTitle">Select your Courses</span></div>
	<select id="course" name="course" size="6" multiple>
		<option value="A1 - Web Services">A1 - Web Services
		<option value="A2 - J2EE Design Patterns">A2 - J2EE Design Patterns
		<option value="A3 - Service Oriented Architectures">A3 - Service Oriented Architectures
		<option value="A4 - Enterprise Service Bus">A4 - Enterprise Service Bus
		<option value="A6 - Secure Messaging">A6 - Secure Messaging
		<option value="A7 - Web Services Security">A7 - Web Services Security
	</select>
<% 
	String[] courses = request.getParameterValues("course");
	if(courses!=null){
		String courseStr = "|";
		for(String course : courses) 
			courseStr += course+"|";
%>	<script type="text/javascript">
		with(document.frmMain.course)
			for(var i=0;i<length;i++)
				if('<%= courseStr %>'.indexOf('|'+options[i].value+'|')>-1)
					options[i].selected = true;
	</script>
<%}%></div>
<div class="section">
	<div><span class="sectionTitle">Employment Status</span></div>
	<input type="radio" id="status" name="status" value="JHU Employee" ${param.status=="JHU Employee" ? "CHECKED" : ""}/>JHU Employee
	<input type="radio" id="status" name="status" value="JHU Student" ${param.status=="JHU Student" ? "CHECKED" : ""}/>JHU Student
	<input type="radio" id="status" name="status" value="Speaker" ${param.status=="Speaker" ? "CHECKED" : ""}/>Speaker
	<input type="radio" id="status" name="status" value="Other" ${param.status=="Other" ? "CHECKED" : ""}/>Other
</div>
<div class="section">
	<div><span class="sectionTitle">Additional Fees and Charges</span></div>
	<input type="checkbox" name="hotelFee" id="hotelFee" ${param.hotelFee=="on" ? "CHECKED" : ""}/>Hotel Accommodation (Conference Guest Special Fee - Parking Included)
	<input type="checkbox" name="parkingFee" id="parkingFee" ${param.parkingFee=="on"  ? "CHECKED" : ""}/>Parking Permit
</div>
<div class="footer">
	<input type="reset" value="Reset"/>
	<input type="submit" value="Compute Seminar Costs"/>
</div>
</form>
</body>
</html>