<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ page import="ua.entity.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	padding-bottom: 70px; 
	padding-top: 70px;
}

   .size {
    white-space: nowrap; 
    overflow: hidden; 
    padding: 5px; 
    text-overflow: ellipsis;
   }
  
  
</style>
</head>
<body>
 <div class="col-md-3">
                <p class="lead">Shop Name</p>
		<div class="list-group">
			<c:forEach items="${typeDetails}" var="typeDetail">
				<a href="/typeDetailPage/${typeDetail.id}" class="list-group-item">${typeDetail.name}</a>
				</c:forEach>
		</div>
	</div>
	
	  <div class="col-md-9">
	  	  <div class="row">
				<c:forEach items="${page.content}" var="item">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="/images/item/${item.id}.jpg?version=${item.version}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">${item.price}<span class="price">$</span></h4>
                                <h4><a href="/pageItem/${item.id}">${item.nameDetail.name}</a>
                                </h4>
                                <p class="size">${item.description.name}.</p>
                            </div>

                        </div>
                    </div>

                    
				</c:forEach>
                </div>
	  </div>
</body>
</html>