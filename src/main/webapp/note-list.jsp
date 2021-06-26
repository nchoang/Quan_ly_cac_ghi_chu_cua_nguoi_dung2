<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Notes Management Application</title>
	<link rel="stylesheet"
		  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		  crossorigin="anonymous">
</head>
<body>

<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		 style="background-color: #a429bc">
		<div>
			<a> Notes Management App </a>
		</div>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/index.jsp"
				   class="nav-link">Switch User</a></li>
		</ul>
	</nav></header>
<br>

<div class="row">
	<div class="container">
		<h3 class="text-center">List of Notes</h3>
		<hr>
		<div class="container text-left">

			<a href="note-form.jsp" class="btn btn-success">Add
				New Note</a>
		</div>
		<br>
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Content</th>
				<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="note" items="${listnote}">

				<tr>
					<td><c:out value="${note.id}" /></td>
					<td><c:out value="${note.title}" /></td>
					<td><c:out value="${note.content}" /></td>
					<td><a href="update?id=${note.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="#" onclick="showMess(${note.id})">Delete</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
<script>
	function showMess(id) {
		var option = confirm('Are you sure to delete this note?')
		if(option === true) {
			window.location.href = 'delete?id='+id;
		}
	}
</script>
</html>
