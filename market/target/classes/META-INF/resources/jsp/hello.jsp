<%--
  Created by IntelliJ IDEA.
  User: DING
  Date: 2020-02-26
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="title" value="Hello JSP"/>
    <title>${title}</title>
</head>
<body>
<h1>Hello JSP!</h1>
<h1>${title}</h1>
<h1>Hello ${name}</h1>
<h1>Hello ${sessionScope.name}</h1>
<c:if test="${2 < 1}">
    <h1>2 gt 1</h1>
</c:if>
<h1>------------------</h1>
<c:forEach items="${names}" var="name">
    <h1>${name}</h1>
</c:forEach>
</body>
</html>
