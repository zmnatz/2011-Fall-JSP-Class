<%@ page import="edu.jhu.cs605782.offcampusfood.entities.BasicInfo" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap<String,Object> model = (HashMap<String,Object>) request.getAttribute("model");
	BasicInfo currentUser = (BasicInfo)request.getSession().getAttribute("currentUser");
	if(currentUser==null)
		currentUser = new BasicInfo();
%>
<script>
function formSubmit(arg1){
	with(document.frmMain){
		switch(arg1){
		case 'Reset':
			var errors = document.getElementById('content').getElementsByClassName('error');
			for(i in errors){
				errors[i].innerHTML = '';
			}
			return reset();
		case 'Save Info':
			if(!validateForm()) 
				return false;
			action = 'updateInfo';
			submit();
			break;
		case 'Edit Menu':
			window.location = 'editMenu';
			break;
		case 'Edit Credit Cards':
			window.location = 'editCC';
			break;
		default: 
			return;	
		}
	}
}
function validateForm(){
	var formValid = true;
	formValid = checkParam('email') && formValid;
	formValid = checkParam('name') && formValid;
	formValid = checkParam('phoneNumber') && formValid;
	formValid = checkParam('streetAddr1') && formValid;
	formValid = checkParam('city') && formValid;
	formValid = checkParam('state') && formValid;
	formValid = checkParam('zipCode') && formValid;
	
	
	var types = document.getElementsByName('loginType');
	if(types.length>0){
		var typeSelected = false;
		for(i in types)
			if(types[i].checked){
				typeSelected = true;
				break;
			}
		document.getElementById('loginTypeError').innerHTML = typeSelected ? '' : 
			'You must select a registration type.';
		formValid = formValid && typeSelected;
	}
	var pass = document.getElementById('password');
	if(pass){
		if(pass.value!=document.getElementById('repassword').value){
			document.getElementById('passwordError').innerHTML = 'The passwords entered do not match.'; 
			formValid = false;
		} else if(!checkParam('password')){
			formValid = false; 
		} else {
			document.getElementById('passwordError').innerHTML = ''; 
		}
	}
	if(!formValid)
		alert('Fix the indicated validation errors first.');
	return formValid;
}
</script>
<% if(currentUser.getLogin()==null) {%>
<div style="padding: 20px">
	<label style="width: 120px; padding-left: 10px;">Registration Type:</label>
	<input type="radio" id="loginType" name="loginType" value="USER"/> User
	<input type="radio" id="loginType" name="loginType" value="RESTAURANT"/> Restaurant
	<span class="error" id="loginTypeError"></span>
</div>
<% } %>
<div class="section">
	<span class="sectionTitle">Registration Info</span>
<% if(currentUser.getLogin()==null) {%>
	<div><label>Email:</label><input type="text" name="email" id="email"/>
		<span class="error" id="emailError"></span>
	</div>
	<div><label>Password:</label><input type="password" id="password" name="password"/>
		<span class="error" id="passwordError"></span>
	</div>
	<div><label>Retype Password:</label><input type="password" id="repassword" name="repassword"/></div>
<% } else { %>
	<div><label>Email:</label> <%= currentUser.getLogin().getEmail() %></div>
<% } %>
</div>
<div class="section">
	<span class="sectionTitle">Basic Information</span>
	<div><label>Name:</label><input type="text" name="name" id="name" value="<%= currentUser.getName() %>"/>
		<span class="error" id="nameError"></span>
	</div>
	<div><label>Phone Number:</label><input type="text" name="phoneNumber" id="phoneNumber" value="<%= currentUser.getPhoneNumber() %>"/>
		<span class="error" id="phoneNumberError"></span>
	</div>
</div>
<div class="section">
	<span class="sectionTitle">Address</span>
	<div><label>Street Address 1:</label><input type="text" name="streetAddr1" id=streetAddr1 value="<%= currentUser.getAddress().getStreetAddr1() %>"/>
		<span class="error" id="streetAddr1Error"></span>
	</div>
	<div><label>Street Address 2:</label><input type="text" name="streetAddr2" id=streetAddr2 value="<%= currentUser.getAddress().getStreetAddr2() %>"/>
		<span class="error" id="streetAddr2Error"></span>
	</div>
	<div><label>City:</label><input type="text" name="city" id=city value="<%= currentUser.getAddress().getCity() %>"/>
		<span class="error" id="cityError"></span>
	</div>
	<div><label>State:</label><input type="text" name="state" id=state value="<%= currentUser.getAddress().getState() %>"/>
		<span class="error" id="stateError"></span>
	</div>
	<div><label>Zip Code:</label><input type="text" name="zipCode" id=zipCode value="<%= currentUser.getAddress().getZipCode()==null ? "" : currentUser.getAddress().getZipCode() %>"/>
		<span class="error" id="zipCodeError"></span>
	</div>
</div>
<% if(currentUser.getLogin()!=null) { 
	if("RESTAURANT".equals(currentUser.getLogin().getLoginType())) { 
%>
<% 	} else { 
%> 

<% 	}
}%>

