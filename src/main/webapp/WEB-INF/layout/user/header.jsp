<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Welcome</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">Shop</a>
                    </li>
                    <li>
                        <a href="#">Shipping and payment</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
              <security:authorize access="!isAuthenticated()">
	                    <form:form class="navbar-form navbar-right" action="/login" method="POST">
	                        <div class="form-group">
	                            <input class="form-control" placeholder="Login" name="login">
	                        </div>
	                        <div class="form-group">
	                            <input class="form-control" placeholder="Password" type="password" name="password">
	                        </div>
	                        <div class="checkbox">
							    <label>
							      <input name="remember-me" type="checkbox"> Remember me
							    </label>
							</div>
	                        <button class="btn btn-primary">Sign in</button>
	                           
	                    </form:form>
	                      <ul class="nav navbar-nav navbar-right">
		                        <li><a href="/registration	">Registration</a></li>
		                    </ul>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
	                    <form:form class="navbar-form navbar-right" action="/logout" method="POST">
	                        <button class="btn btn-primary">Sign out</button>
	                    </form:form>
                    	<security:authorize access="hasRole('ROLE_ADMIN')">
		                    <ul class="nav navbar-nav navbar-right">
		                        <li><a href="/admin">Admin</a></li>
		                    </ul>
                    	</security:authorize>
                    	<security:authorize access="hasRole('ROLE_USER')">
                    		 <ul class="nav navbar-nav navbar-right">
                    		 <li><a href="shoppingCart">ShoppingCart</a></li>
                    		 </ul>
                    	</security:authorize>
                    </security:authorize>
            </div>
        </div>
    </nav>