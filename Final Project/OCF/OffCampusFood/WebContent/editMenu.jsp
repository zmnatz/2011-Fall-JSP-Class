<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Restaurant" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Item" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap<String,Object> model = (HashMap<String,Object>) request.getAttribute("model");
	Restaurant restaurant = (Restaurant)request.getSession().getAttribute("currentUser");
%>
<script>
function formSubmit(arg1,arg2){
	with(document.frmMain){
		action='editMenu';
		switch(arg1){
		case 'Cancel':
			document.getElementById('itemInfoDiv').style.display='none';
		case 'Reset':
			var errors = document.getElementById('content').getElementsByClassName('error');
			for(i in errors){
				errors[i].innerHTML = '';
			}
			return reset();
		case 'Add Menu Item':
			if(document.getElementById('itemInfoDiv').style.display='none')
				document.getElementById('itemInfoDiv').style.display = '';
			break;
		case 'Save':
			if(!validateForm()) return false;
			document.getElementById('submitType').value='ADD';
			return submit();
		case 'Remove':
			document.getElementById('submitType').value='REMOVE';
			document.getElementById('itemId').value=arg2;
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
	formValid = checkParam('name') && formValid;
	formValid = checkParam('description') && formValid;
	formValid = checkParam('price') && formValid;
	if(!formValid)
		alert('Fix the indicated validation errors first.');
	return formValid;
}
</script>
<div class="section" id="itemInfoDiv" style="display: none">
	<span class="sectionTitle">New Menu Item</span>
	<div><label>Name:</label><input type="text" name="name" id="name" value="${params.name}"/>
		<span class="error" id="nameError"></span>
	</div>
	<div><label>Description:</label>
		<textarea cols="30" rows="5" name="description" id="description">${params.description}</textarea>
		<span class="error" id="descriptionError"></span>
	</div>
	<div><label>Price:</label><input type="text" name="price" id="price" value="${params.price}"/>
		<span class="error" id="priceError"></span>
	</div>
	<div style="text-align: right">
		<input type="Button" value="Save Item" onClick="formSubmit('Save')"/>
		<input type="Button" value="Reset" onClick="formSubmit('Reset')"/>
		<input type="Button" value="Cancel" onClick="formSubmit('Cancel')"/>
	</div>
</div>
<% if(restaurant.getMenu()!=null && !restaurant.getMenu().isEmpty()){ %>
<div class="section rows">
	<span class="sectionTitle right">Price</span>
	<span class="sectionTitle">Menu</span>
	<% for(Item item : restaurant.getMenu()) {
		if(Boolean.TRUE.equals(item.getActive())){ %>
		<div>
			<input type="button" value="Remove" onClick="formSubmit('Remove',<%= item.getItemId()%>)"/>
			<span class="right"><%= item.getPrice()%></span>
			<%= item.getName() %><br/>
			<%= item.getDescription() %>
		</div>
	<% }} %>
</div>
<% } %>
<input type="hidden" id="submitType" name="submitType" value="ADD"/>
<input type="hidden" id="itemId" name="itemId" value=""/>
