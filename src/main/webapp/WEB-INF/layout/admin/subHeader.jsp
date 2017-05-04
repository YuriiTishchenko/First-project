<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container-fluid">
	<div class="row">
		<ul class="nav navbar-nav navbar-left">
		             <li><a href="/admin/producer" class="btn btn-primary cart">Producer <span class="badge">${producer.count}</span></a></li>
		            </ul>
	</div>
</div>