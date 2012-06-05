<%@ page import="edu.jhu.cs605782.offcampusfood.entities.User" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.CreditCard" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Address" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%
	User user = (User)request.getSession().getAttribute("currentUser");
	Address addr = user.getAddress();
	List<CreditCard> ccs = user.getCreditCards();
%>
<script>
function formSubmit(arg1,arg2){
	with(document.frmMain){
		action='editCC';
		switch(arg1){
		case 'Cancel':
			document.getElementById('ccInfoDiv').style.display='none';
		case 'Reset':
			var errors = document.getElementById('content').getElementsByClassName('error');
			for(i in errors){
				errors[i].innerHTML = '';
			}
			return reset();
		case 'Add Credit Card':
			if(document.getElementById('ccInfoDiv').style.display='none')
				document.getElementById('ccInfoDiv').style.display = '';
			break;
		case 'Fill Address':
			streetAddr1.value = '<%= addr.getStreetAddr1() %>';
			streetAddr1.value = '<%= addr.getStreetAddr2() %>';
			city.value = '<%= addr.getCity() %>';
			state.value = '<%= addr.getState() %>';
			zipCode.value = '<%= addr.getZipCode() %>';
			break;
		case 'Save':
			if(!validateForm()) return false;
			document.getElementById('submitType').value='ADD';
			return submit();
		case 'Remove':
			document.getElementById('submitType').value='REMOVE';
			document.getElementById('ccId').value=arg2;
			return submit();
		case 'Back to Info':
			window.location = 'editInfo';
			break;
		default:
			return;	
		}
	}
}

function validateForm(){
	var formValid = true;
	if(document.getElementById('submitType')=='REMOVE')
		return true;
	formValid = checkParam('name') && formValid;
	formValid = checkParam('phoneNumber') && formValid;
	formValid = checkParam('ccNumber') && formValid;
	formValid = checkParam('expirationDt') && formValid;
	formValid = checkParam('streetAddr1') && formValid;
	formValid = checkParam('city') && formValid;
	formValid = checkParam('state') && formValid;
	formValid = checkParam('zipCode') && formValid;
	if(!formValid)
		alert('Fix the indicated validation errors first.');
	return formValid;
}
</script>
<div class="section" id="ccInfoDiv" style="display: <%= (ccs==null || ccs.isEmpty()) ? "" : "none" %>">
	<span class="sectionTitle">New Credit Card</span>
	<div><label>CC Number:</label><input type="text" name=ccNumber id=ccNumber/>
		<span class="error" id="ccNumberError"></span>
	</div>
	<div><label>Expiration Date:</label><input type="text" name=expirationDate id=expirationDate/>
		<span class="error" id="expirationDateError"></span>
	</div>
	<div><label>Name:</label><input type="text" name="name" id="name" value="<%= user.getName() %>"/>
		<span class="error" id="nameError"></span>
	</div>
	<div><label>Phone Number:</label><input type="text" name="phoneNumber" id="phoneNumber" value="<%= user.getPhoneNumber() %>"/>
		<span class="error" id="phoneNumberError"></span>
	</div>
	<div class="section">
			<span class="sectionTitle">Address</span>
			<div><label>Street Address 1:</label><input type="text" name="streetAddr1" id=streetAddr1/>
				<span class="error" id="streetAddr1Error"></span>
			</div>
			<div><label>Street Address 2:</label><input type="text" name="streetAddr2" id=streetAddr2/>
				<span class="error" id="streetAddr2Error"></span>
			</div>
			<div><label>City:</label><input type="text" name="city" id=city/>
				<span class="error" id="cityError"></span>
			</div>
			<div><label>State:</label><input type="text" name="state" id=state/>
				<span class="error" id="stateError"></span>
			</div>
			<div><label>Zip Code:</label><input type="text" name="zipCode" id=zipCode"/>
				<span class="error" id="zipCodeError"></span>
			</div>
	</div>
	<div style="text-align: right">
		<input type="Button" value="Same Address" onClick="formSubmit('Fill Address')"/>
		<input type="Button" value="Save Item" onClick="formSubmit('Save')"/>
		<input type="Button" value="Reset" onClick="formSubmit('Reset')"/>
		<input type="Button" value="Cancel" onClick="formSubmit('Cancel')"/>
	</div>
</div>
<% if(ccs!=null && !ccs.isEmpty()) { %>
<div class="section rows">
	<span class="sectionTitle">Credit Cards</span>
	<% for(CreditCard cc : ccs) { %><div>
		<input type="button" value="Remove" onClick="formSubmit('Remove',<%= cc.getCcId()%>)"/>
		<%= cc.getName() %><br/>
		********<%= cc.getCcNumber().substring(cc.getCcNumber().length()-4,cc.getCcNumber().length()) %>
	</div><% } %>
</div>
<% } %>
<input type="hidden" id="submitType" name="submitType" value="ADD"/>
<input type="hidden" id="ccId" name="ccId"/>