validateForm = (inputUsername, inputPassword, inputConfirmPassword) => {
	let password = inputPassword.value;
	let confirmPassword = inputConfirmPassword.value;
	//Allow only cars between 1998-2025
	let usernameCharacters = /^[a-zA-Z0-9]*$/;
	let letter = /[a-zA-Z]/;
    let number = /[0-9]/;
	if(!usernameCharacters.test(inputUsername.value)){
		alert("Car year must be between 1998 and 2025");
		return false;
	}
	//Check that password is not null, and that is at least 5 characters
	let checkLength = password == null || password == "" || password.length < 5;
    let checkCharacters = !letter.test(password) || !number.test(password); 
	if(checkLength || checkCharacters){
		alert("Password must be at least 5 characters long, it must contain at least 1 letter and 1 number");
		return false;
	}
	//Check password matches the confirmation
	if( password != confirmPassword){
		alert("Password does not match");
		return false;
	}
}