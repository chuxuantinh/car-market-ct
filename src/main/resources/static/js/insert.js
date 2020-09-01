validateForm = (inputCarModel, inputYear, inputColor) => {
	//Allow only cars between 1998-2025
	let yearRange = /^(199[8-9]|20[0-4]\d|2025)$/;
	//Car model should only have letters and numbers
	let modelCharacters = /^[a-zA-Z0-9]*$/;
	let colorCharacters = /^[a-zA-Z]*$/;
	if(!modelCharacters.test(inputCarModel.value)){
		alert("Car model must have letters and number characters only");
		return false;
	}
	if(!yearRange.test(inputYear.value)){
		alert("Car year must be between 1998 and 2025");
		return false;
	}
	if(!colorCharacters.test(inputColor.value)){
		alert("Car color must only contain letters");
		return false;
	}
}