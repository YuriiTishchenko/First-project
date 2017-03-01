<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
	#filter>.form-group>.col-sm-12>span{
		display: block;
	}
</style>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/item">Item</a>	</li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li><a href="/admin/nameDetail">NameDetail</a></li>
					<li><a href="/admin/nameStringSpecification">Name of specification string</a></li>
					<li class="active"><a href="/admin/specification">specification<span
						class="sr-only">(current)</span></a></li>
					<li><a href="/admin/typeDetail">typeDetail</a></li>
					<li><a href="/admin/description">description</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
	<form:form class="form-horizontal" action="/admin/specification" method="GET" modelAttribute="filter" id="filter">
		<custom:hiddenInputs excludeParams="minValue,maxValue,nameStringSprcificationIds" />
	    			<div class="form-group">
					<div class="col-sm-12">
					<form:radiobuttons  items="${nameStringSpecifications}" path="nameStringSpecificationIds" itemLabel="name" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="minValue" placeholder="Min value"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="maxValue" placeholder="Max value"/>
	    			</div>
	    			</div>
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
	</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/specification" method="POST" modelAttribute="specification">
				<custom:hiddenInputs excludeParams="value,nameStringSpecification"/>
					<div class="form-group">
						<label for="nameStringSpecification" class="col-sm-2 control-label">Name String Spec</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="nameStringSpecification" id="nameStringSpecification" items="${nameStringSpecifications}" itemValue="id" itemLabel="name"/>
						</div>
					</div>
					<div class="form-group">
					<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="value"/></label>
						<label for="name" class="col-sm-2 control-label">value</label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="value" id="value"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Create</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="row">
				<div class="col-md-3 col-xs-3">
					<h3>Name String Spec</h3>
				</div>
				<div class="col-md-3 col-xs-3">
					<h3>Value</h3>
				</div>
				<div class="col-md-3 col-xs-3">
					<h3>Update</h3>
				</div>
				<div class="col-md-3 col-xs-3">
					<h3>Delete</h3>
				</div>
			</div>
		</div>
		<c:forEach items="${page.content}" var="specification">
			<div class="row">
				<div class="col-md-3 col-xs-3">${specification.nameStringSpecification.name}</div>
				<div class="col-md-3 col-xs-3">${specification.value}</div>
				<div class="col-md-3 col-xs-3">
					<a class="btn btn-warning"
						href="/admin/specification/update/${specification.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-3 col-xs-3">
					<a class="btn btn-danger"
						href="/admin/specification/delete/${specification.id}<custom:allParams/>">delete</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>