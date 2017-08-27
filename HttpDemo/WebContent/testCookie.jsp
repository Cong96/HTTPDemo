<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="cookies" value="${cookie}"></c:set>
<c:out value="${cookie} "></c:out>
<c:forEach items="${cookies}" var="item">
name:<c:out value="${item.key}"></c:out>
</c:forEach>
<!--  name:<c:out value="${cookie.username.name} "></c:out>
value:<c:out value="${cookie.username.value} "></c:out>-->
</body>
</html>