/*
let result = prompt("¿Cuantosañostienes?","")
while(result<18){

if (result<18){
	alert("Acceso no permitido para menores de 18")
	result = prompt("¿Cuantosañostienes?","")
	}
}
*/


const email=document.getElementById('email');
document.getElementById('submit').onclick=function(){validateEmail(email)};


function validateEmail(mail) 
{
 var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
if(mail.value.match(mailformat))
  {
	alert("You have entered a valid email address!");
	document.form.email.focus();
    return (true);
  }
    alert("You have entered an invalid email address!");
	location.reload();
    return (false);
}



















