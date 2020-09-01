validateForm = (inputCarModel) => {
	//Car model should only have letters and numbers
	let modelCharacters = /^[a-zA-Z0-9]*$/;
	if(!modelCharacters.test(inputCarModel.value)){
		alert("Car model must have letters and number characters only");
		return false;
	}
}