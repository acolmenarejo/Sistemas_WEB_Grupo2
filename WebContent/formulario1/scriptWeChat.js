let result = prompt("¿Cuantosañostienes?","");
while(result<18){

if (result<18){
	alert("Acceso no permitido para menores de 18");
	result = prompt("¿Cuantosañostienes?","");
	}
}


/*
<form name="datos">
	Email:<input name="email"/><br>
    Contraseña:<input name="contraseña"/><br>
</form>



function ValidateEmail(mail) 
{
 if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(myForm.emailAddr.value))
  {
    return (true)
  }
    alert("You have entered an invalid email address!")
    return (false)
}
*/