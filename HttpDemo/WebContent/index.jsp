<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="username" value="${cookie.username.value}"></c:set>
<c:set var="password" value="${cookie.password.value}"></c:set>
<c:set var="flag" value="first"></c:set>

<c:if test="${username!=null}">
<c:set var="flag" value="notFirst"></c:set></c:if>
<c:out value="${flag}"></c:out>
<form action="LoginSave" method="post">  
        用户名：<input type="text" name="username" value="${username}"/><br/>  
        密码：<input type="password" name="password" value="${password}"/><br/>  
                  <input type="hidden" name="flag" value="${flag}">   
        <input type="submit" value="提交"/>  
</form>  
</body>
</html>