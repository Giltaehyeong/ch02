<%@ page contentType="text/html;charset=utf-8" isErrorPage = "true"%>
                                        <!-- 이 페이지는 에러를 처리할 때 사용하는 페이지 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
<!-- isErrorPage = "true" 지시자가 있다면 기본객체 exception을 쓸 수 있다.-->
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>