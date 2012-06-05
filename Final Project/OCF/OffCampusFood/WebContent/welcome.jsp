<p>Welcome to OffCampusFood.com!</p>
<p> Have a local restaurant in mind? </p>
<p> Then type in your address and search for restaurants near you! </p>

<script>
function formSubmit(arg1){
	with(document.frmMain){
		switch(arg1){
		
		case 'Search Restaurants':
			if(!validateForm()) 
				return false;
			action = 'restSearch';
			submit();
			break;
		}
	}
}

function validateForm(){
	if(document.getElementById('rest').value=='' && document.getElementById('city').value=='' &&
			document.getElementById('city').value=='' && document.getElementById('state').value==''){
		alert('You must enter a name or address to search');
		return false;
	}
	return true;
}

</script>

<div class="section">

	<span class="sectionTitle">Restaurant Info</span>
    <div><label>Restaurant Name:</label><input type="text" name="restaurantName" id="rest"/>
		<span class="error" id="restError"></span>
	</div>
	<div><label>City:</label><input type="text" name="city" id="city"/>
		<span class="error" id="cityError"></span>
	</div>
	<div><label>State:</label><input type="text" name="state" id="state"/>
		<span class="error" id="stateError"></span>
	</div>
	<div><label>Zip Code:</label><input type="text" name="zipCode" id="zipCode"/>
		<span class="error" id="zipCodeError"></span>
	</div>
	
	 <input type="button" value="Search Restaurants" onClick="formSubmit('Search Restaurants')"/>
	
</div> 
