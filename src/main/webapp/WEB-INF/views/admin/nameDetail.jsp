<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
					<li ><a href="/admin/item">Item</a>	</li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li class="active"><a href="/admin/nameDetail">NameDetail<span
						class="sr-only">(current)</span></a></li>
					<li><a href="/admin/nameStringSpecification">Name of specification string</a></li>
					<li><a href="/admin/specification">specification</a></li>
					<li><a href="/admin/typeDetail">typeDetail</a></li>
					<li><a href="/admin/description">description</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
		<div class="col-md-3 col-xs-12">
		<form:form modelAttribute="filter" action="/admin/nameDetail" method="get" class="form-inline">
			<div class="form-group">
				<form:input path="search" placeholder="search" class="form-control" />
				<custom:hiddenInputs excludeParams="search"/>
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/nameDetail" method="POST" modelAttribute="nameDetail">
				<custom:hiddenInputs excludeParams="name"/>
					<div class="form-group">
					<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
						<label for="name" class="col-sm-2 control-label">Name</label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="name" id="name"/>
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
			<div class="col-md-4 col-xs-4"><h3>Detail name</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
		</div>
		<c:forEach items="${page.content}" var="nameDetail">
				<div class="row">
					<div class="col-md-4 col-xs-4">${nameDetail.name}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/nameDetail/update/${nameDetail.id}<custom:allParams/>">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/nameDetail/delete/${nameDetail.id}<custom:allParams/>">delete</a></div>
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