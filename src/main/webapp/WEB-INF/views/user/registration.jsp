<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function validate(){
    var n= document.forms[form][name].value;
    var e= document.forms[form][name].value;
    var p= document.forms[form][password].value;
    if(n.length==0){
        document.getElementById("namef").innerHTML="can't be empty";
        return false;
    }
    if(e.length==0){
        document.getElementById("emailf").innerHTML="can't be empty";
        return false;
    }
    var at = e.indexOf("@");
    var dot = e.indexOf(".");
    if (at<1||dot<1){
         document.getElementById("emailf").innerHTML="not corect email";
        return false;
    }
    if(p.length==0){
        document.getElementById("passwordf").innerHTML="can't be empty";
        return false;
    }
}
</script>	
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form name="form"  class="form-horizontal" action="/registration" method="POST" modelAttribute="userForm" >
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-10"><span id="namef" style="color:red"></span></label>
			</div>
			<div class="form-group"  >
    			<label for="name" class="col-sm-2 control-label">Login</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="username" id="name"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="email" class="col-sm-offset-2 col-sm-10"><span id="emailf" style="color:red"></span></label>
			</div>
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="email" id="email"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="password" class="col-sm-offset-2 col-sm-10"><span id="passwordf" style="color:red"></span></label>
			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<form:password class="form-control" path="password" id="password"/>
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default" onclick="return validate()">Register</button>
    			</div>
  			</div>
		</form:form>
		
	</div>
</div>
