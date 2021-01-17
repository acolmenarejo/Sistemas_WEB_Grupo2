
const email = document.getElementById('email');
document.getElementById('submit').onclick = function() {
	validateEmail(email);

	function validateEmail(mail) {
		let mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if (mail.value.match(mailformat))
		{
			alert("You have entered a valid email address!");
			document.form.email.focus();
			return (true);
		}
		alert("You have entered an invalid email address!");
		location.reload();
		return (false);
	}

}


