<%@ page import="Beans.RegistrationInfo" %>
<%@ page import="java.util.Arrays" %>
<% 	RegistrationInfo ri = (RegistrationInfo) session.getAttribute("regInfo"); 
	if(ri==null) ri = new RegistrationInfo();
	boolean addCourses = Boolean.parseBoolean(request.getParameter("addCourses"));
%>
<html>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<body>
<form style="width: 510px" name="frmMain" action="<%= addCourses ? "AddCoursesSubmit.htm" : "RegistrationSubmit.htm" %>" method="post">
<div class="header">Johns Hopkins Annual Software Development Seminar</div>
<div class="error"><%= request.getAttribute("errors")==null ? "" : 
		request.getAttribute("errors")
%></div>
<% if(!addCourses) { %>
<div class="section">
	<div><span class="sectionTitle">Contact Information</span></div>
	<div><span class="label">Name:</span><input type="text" id="name" name="name" value="<%= ri.getName()==null ? "" : ri.getName() %>"/></div>
	<div><span class="label">E-mail:</span><input type="text" id="email" name="email" value="<%= ri.getEmail()==null ? "" : ri.getEmail() %>"/></div>
</div>
<%} %>
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
		String courseStr = "|";
		String[] courses = request.getParameterValues("course");
		
		for(String course : !addCourses ? ri.getCourse() : Arrays.asList(courses==null ? new String[]{} : courses))
			courseStr += course+"|";
%>	
<script type="text/javascript">
	with(document.frmMain.course)
		for(var i=0;i<length;i++)
			if('<%= courseStr %>'.indexOf('|'+options[i].value+'|')>-1)
				options[i].selected = true;
</script>
</div>
<% if(!addCourses) { %>
<div class="section">
	<div><span class="sectionTitle">Employment Status</span></div>
<% for(String status : RegistrationInfo.STATUS_FEE.keySet()) {%>
	<input type="radio" id="status" name="status" value="<%=status%>" <%=status.equals(ri.getStatus()) ? "CHECKED" : ""%>/><%=status %>
<% } %>
</div>
<% } %>
<div class="section">
	<div><span class="sectionTitle">Additional Fees and Charges</span></div>
	<input type="checkbox" name="hotel" <%= Boolean.TRUE.equals(ri.isHotel()) ? "CHECKED" : "" %>/>Hotel Accommodation (Conference Guest Special Fee - Parking Included)
	<input type="checkbox" name="parking" <%= Boolean.TRUE.equals(ri.isParking()) ? "CHECKED" : "" %>/>Parking Permit
</div>
<div class="footer">
	<input type="reset" value="Reset"/>
	<input type="submit" value="Compute Seminar Costs"/>
</div>
</form>
</body>
</html>