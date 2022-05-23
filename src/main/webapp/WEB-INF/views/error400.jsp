<%@ page contentType="text/html;charset=utf-8" isErrorPage = "false"%>
											  <!-- true이면 상태코드를 무조건 500으로 바꿔줌. -->
                                              <!-- 이 페이지는 에러를 처리할 때 사용하는 페이지 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error400.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>