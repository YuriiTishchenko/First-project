function validate(){
    var f=document.getElementById("form");
    validateName(f);
    validateEmail(f);
    validatePass(f);
}

function validateName(form){

    var error=document.getElementById("nameError");

    var name=form["name"].value;

    error.innerHTML="";

    if( name==null || name==""){
        error.innerHTML="Input Your name";
    }

    else if(!isNaN(id)){
        error.innerHTML="Name Can Not be a number";
    }

   

}



function validateEmail(form){
     var error=document.getElementById("emailError");

     var email=form["email"].value;
     error.innerHTML="";
      var regx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|
      (".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-
       Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

   if( email==null || email==""){
      error.innerHTML="Input Your Email";
    }

 else if(!email.match(regx)){
    error.innerHTML="Invalid Email";
 }

 }
 

function  validatePass(form){
    var error=document.getElementById("passwordError");

    error.innerHTML="Give Password";

    var password=form["password"].value;

    error.innerHTML="";
   

    var confirmPassword=form["confirmPassword"].value;

    if( password==null || password==""){
        error.innerHTML="Give Password";
    }
     
    else if(password.length<5 || password.length>10){
        error.innerHTML="Password has to be 5 to 10 chars"
    }
 }
