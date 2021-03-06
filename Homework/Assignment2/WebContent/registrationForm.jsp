<html>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<script type="text/Javascript">
function validateForm(doc){
	with(frmMain){
		if(!checkEl(name,'name') || !checkEl(email,'e-mail'))
			return false;
		if(course.selectedIndex<0){
			alert('You must select at least 1 course.');
			return false;
		}
		var statusSelected = false;
		for(var i=0;i<status.length;i++)
			if(status[i].checked){
				statusSelected = true;
				break;
			}
		if(!statusSelected){
			alert('You must select an employement status.');
			return false;
		}
	}	
}

function checkEl(el,name){
	if(el.value=='' || !el.value){
		alert('You must enter your '+name+'.');
		return false;
	}
	return true;
}
</script>
<body>
<form style="width: 510px" name="frmMain" action="registrationReceipt.jsp" method="post" onSubmit="return validateForm(this)">
<div class="header">Johns Hopkins Annual Software Development Seminar</div>
<div class="section">
	<span class="sectionTitle">Contact Information</span><br/>
	<span class="label">Name:</span><input type="text" id="name" name="name"/><br/>
	<span class="label">E-mail:</span><input type="text" id="email" name="email"/>
</div>
<div class="section">
	<span class="sectionTitle">Select your Courses</span><br/>
	<select id="course" name="course" size="6" multiple>
		<option value="A1 - Web Services">A1 - Web Services
		<option value="A2 - J2EE Design Patterns">A2 - J2EE Design Patterns
		<option value="A3 - Service Oriented Architectures">A3 - Service Oriented Architectures
		<option value="A4 - Enterprise Service Bus">A4 - Enterprise Service Bus
		<option value="A6 - Secure Messaging">A6 - Secure Messaging
		<option value="A7 - Web Services Security">A7 - Web Services Security
	</select>
</div>
<div class="section">
	<span class="sectionTitle">Employment Status</span><br/>
	<input type="radio" id="status" name="status" value="JHU Employee"/>JHU Employee
	<input type="radio" id="status" name="status" value="JHU Student"/>JHU Student
	<input type="radio" id="status" name="status" value="Speaker"/>Speaker
	<input type="radio" id="status" name="status" value="Other"/>Other
</div>
<div class="section">
	<span class="sectionTitle">Additional Fees and Charges</span><br/>
	<input type="checkbox" name="hotelFee" id="hotelFee"/>Hotel Accommodation (Conference Guest Special Fee - Parking Included)<br/>
	<input type="checkbox" name="parkingFee" id="parkingFee"/>Parking Permit
</div>
<div class="footer">
	<input type="reset" value="Reset"/>
	<input type="submit" value="Compute Seminar Costs"/>
</div>
</form>
</body>
</html>