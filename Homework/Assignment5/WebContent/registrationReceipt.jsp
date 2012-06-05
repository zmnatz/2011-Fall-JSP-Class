<%@ page import="Beans.RegistrationInfo" %>
<%@ page import="java.util.Collections" %>
<% RegistrationInfo ri = (RegistrationInfo) session.getAttribute("regInfo"); %>
<html>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<style>
.rows div {
	padding: 8px 0px 8px 0px;
	border-top-style: solid;
	border-top-width: 1px;
}

.rows input {
	position: relative;
	float: right;
	top: -3px;
	margin-left: -60px
}

.rows .cost { float: right; margin-right: 80px}

p {padding-left: 5px}
</style>
<body>
<form style="width: 510px" id="frmMain" name="frmMain" action="registrationForm.jsp" method="post">
<script type="text/Javascript">
function form_submit(arg1,arg2){
	with(document.frmMain){
		switch(arg1){
		case 'Remove':
			action = 'RemoveCourse.htm?course='+arg2;
			return submit();
		case 'Edit':
			action = 'registrationForm.jsp';
			return submit();
		case 'Add Courses':
			action = 'registrationForm.jsp?addCourses=true';
			return submit();
		}
	}
}
</script>
<div class="header">Johns Hopkins Annual Software Development Seminar</div>
<p><b><%= ri.getName() %></b></p>
<p>You are registered as a <b><%= ri.getStatus() %></b>.</p>
<p>Your e-mail confirmation will be sent to <b><%= ri.getEmail() %></b>.</p>
<div class="section rows">
	<span class="sectionTitle cost">Cost</span>
	<span class="sectionTitle">Your Courses</span>
<%
short courseCost = RegistrationInfo.STATUS_FEE.get(ri.getStatus());
short totalCost = (short) (courseCost * ri.getCourse().size());
//Loops through courses, adding to the total cost and displaying each row.
for(String course : ri.getCourse()) {%>
	<div>
		<% if(ri.getCourse().size()>1) {%>
			<input type="button" value="Remove" onClick="form_submit('Remove','<%= course %>')"/>
		<% } %>
		<span class="cost"><%= courseCost>0 ? "$"+courseCost+".00":"Free"%></span>
		<%= course %>
	</div>
<%}
//Adds hotel fee if checked
if(Boolean.TRUE.equals(ri.isHotel())) { 
	totalCost += 185;
%>	<div style="padding-top: 20px"><span class="cost">$185.00</span>Hotel Accommodation</div> <%
}
//Adds parking fee if checked
if(Boolean.TRUE.equals(ri.isParking())) { 
	totalCost += 10;
%>	<div style="padding-top: 20px"><span class="cost">$10.00</span>Parking Fee</div> <%
}%>
 	<div class="footer"><span class="cost">$<%= totalCost %>.00</span><span style="padding-right: 60px">Total</span></div>
</div>
<% if(!Boolean.TRUE.equals(request.getParameter("confirmed"))) { %>
<div class="footer">
	<input type="button" value="Edit Information" onClick="form_submit('Edit')"/>
	<input type="button" value="Add More Courses" onClick="form_submit('Add Courses')"/>
	<input type="button" value="Confirm Registration"/>
</div>
<% } %>
</form>
</body>
</html>