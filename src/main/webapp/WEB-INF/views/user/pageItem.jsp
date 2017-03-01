<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ page import="ua.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
<head>
<title>item</title>
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">${item.nameDetail.name}</h1>
		</div>
	</div>


	<div class="row">

		<div class="col-md-8">
			<img class="img-responsive"
				src="/images/item/${item.id}.jpg?version=${item.version} " alt="">
		</div>

		<div class="col-md-4">
			<h3>Item Description</h3>
			<p>${item.description.name}</p>
			<h3>Item Details</h3>
			<ul>
				<li>Producer : ${item.producer.name}</li>
				<li>Type Detail : ${item.typeDetail.name}</li>
				<li>Name spec: ${item.specification.nameStringSpecification.name}</li>
				<li>value: ${item.specification.value }</li>
				<li>Price : ${item.price }</li>
			</ul>
			<security:authorize access="isAuthenticated()">
				<div>
					<a href="/ordernow/${item.id}">
						<button class="btn btn-cart" type="submit" id="add-to-cart"> Add to shopping cart</button>
					</a>
				</div>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
				<h2> If you want buy item you must login or <a href="/registration">register</a></h2>
				</security:authorize>
		</div>

	</div>


</body>
</html>