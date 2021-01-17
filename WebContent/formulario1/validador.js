
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

let result = prompt("¿Cuantosañostienes?","");


if (result<18){
	alert("Acceso no permitido para menores de 18");
	
}
else {
	alert(result);
}


var bucle = prompt("¿CuantosBucles?","");
giro(bucle);

function giro(bucle){

for(let i=0;i<bucle;i++){
	alert(i);
		if(i>4){
			break;
		}
}
}
