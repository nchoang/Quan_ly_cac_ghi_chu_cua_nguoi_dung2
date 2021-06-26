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
            <li><a href="/note-list.jsp"
                   class="nav-link">Notes</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${note != null}">

            </c:if>
            <c:if test="${note == null}">
            <form action="update" method="post" id="form1">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${note != null}">
                            Edit Note
                        </c:if>
                        <c:if test="${note == null}">
                            Edit Note
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${note != null}">
                    <input type="text" name="id" value="${Notemodel.id}">
                </c:if>


                <fieldset class="form-group">
                    <label>Your Id</label> <input type="text"
                                                  value="${id}" class="form-control"
                                                  name="id" readonly>
                </fieldset>

                <fieldset class="form-group">
                    <label>Note Title</label> <input type="text"
                                                     value="${Notemodel.title}" class="form-control"
                                                     name="title">
                </fieldset>

                <fieldset class="form-group">
                    <label>Content</label> <input type="text"
                                                  value="${Notemodel.content}" class="form-control"
                                                  name="content">
                </fieldset>

                <button type="submit" form="form1" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

