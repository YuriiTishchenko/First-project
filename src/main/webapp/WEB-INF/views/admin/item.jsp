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
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/admin/item">Item</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li><a href="/admin/nameDetail">NameDetail</a></li>
					<li><a href="/admin/nameStringSpecification">Name of
							specification string</a></li>
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
	<form:form class="form-horizontal" action="/admin/item" method="GET" modelAttribute="filter" id="filter">
		<custom:hiddenInputs excludeParams="minPrice,maxPrice,producerIds,nameDetailIds,typeDetailIds	
		" />
	    			<div class="form-group">
					<div class="col-sm-12">
					<label for="id">Type Detail:</label>
					<form:checkboxes  items="${typeDetails}" path="typeDetailIds" itemLabel="name" itemValue="id" />
					</div>
				    </div>
	    			<div class="form-group">
					<div class="col-sm-12">
					<label for="id">Name Detail:</label>
					<form:checkboxes  items="${nameDetails}" path="nameDetailIds" itemLabel="name" itemValue="id" />
					</div>
				    </div>
	    			<div class="form-group">
					<div class="col-sm-12">
					<label for="id">Producer:</label>
					<form:checkboxes  items="${producers}" path="producerIds" itemLabel="name" itemValue="id" />
					</div>
				    </div>
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
					<label >Min Price</label>
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	    			<label >Max Price</label>
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/>
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
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" enctype="multipart/form-data">
				<custom:hiddenInputs excludeParams="price,producer,nameDetail,typeDetail,nameStringSpecification,description" />
					<div class="form-group">
						<label for="producer" class="col-sm-2 control-label">Producer</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="producer" id="producer" items="${producers}" itemValue="id" itemLabel="name"/>
						</div>
						</div>
						<div class="form-group">
						<label for="nameDetail" class="col-sm-2 control-label">Name
							Detail</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="nameDetail" id="nameDetail" items="${nameDetails}" itemValue="id" itemLabel="name"/>
						</div>
						</div>
						<div class="form-group">
						<label for="typeDetail" class="col-sm-2 control-label">Type
							Detail</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="typeDetail" id="typeDetail" items="${typeDetails}" itemValue="id" itemLabel="name"/>
						</div>
						</div>
						<div class="form-group">
						<label for="specefication" class="col-sm-2 control-label">Specification</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="specification" id="specification" items="${specifications}" itemValue="id" itemLabel="value"/>
						</div>
						</div>
						<div class="form-group">
						<label for="specefication" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="description" id="description" items="${descriptions}" itemValue="id" itemLabel="name"/>
						</div>
						</div>
						<form:errors path = "*"/>
						<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
						<label for="price" class="col-sm-2 control-label">Price</label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="price" id="price"/>
						</div>
					</div>
						<div class="form-group">
  						<div class="col-sm-offset-2 col-sm-10">
		  					<label class="btn btn-default btn-file">
		        				Browse <input type="file" name="file" style="display: none;">
		    				</label>
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
			<div class="col-md-1 col-xs-1">
				<h3>Image</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Item name</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Type detail</h3>
			</div>
			<div class="col-md-1 col-xs-1">
				<h3>Producer</h3>
			</div>
			<div class="col-md-1 col-xs-1">
				<h3>Spec value</h3>
			</div>
			<div class="col-md-1 col-xs-1">
				<h3>Price</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Update</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Delete</h3>
			</div>
		</div>
		<c:forEach items="${page.content}" var="item">
			<div class="row">
				<div class="col-md-1 col-xs-1"><img class="img-rounded" width="100%" src="/images/item/${item.id}.jpg?version=${item.version}"></div>
				<div class="col-md-2 col-xs-2">${item.nameDetail.name}</div>
				<div class="col-md-2 col-xs-2">${item.typeDetail.name}</div>
				<div class="col-md-1 col-xs-1">${item.producer.name}</div>
				<div class="col-md-1 col-xs-1">${item.specification.value}</div>
				<div class="col-md-1 col-xs-1">${item.price}</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-warning" href="/admin/item/update/${item.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-danger" href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a>
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