<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPage2.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
<jsp:include page="header.jsp" />
<body>
    <div id="bigContainer">
        <div id="containerForMap">

            <jsp:include page="map2.jsp" />
        </div>

            <jsp:include page="parkingCheckboxes.jsp" />

        <div id="containerForPagination">
            <jsp:include page="parkingPagination.jsp">
                <jsp:param name="currentPage" value="1"/>
            </jsp:include>

        </div>

    </div>

</body>
</html>
