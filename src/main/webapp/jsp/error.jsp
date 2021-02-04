<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/errorPage.css">
</head>

<body>

<main>
    <div class="container">
        <div class="row">
            <div class="col-md-6 align-self-center">

                <h1>You do not have permission to view this page</h1>
                <h2>UH OH! You're lost.</h2>
                <p>The page you are looking for is for admins only.
                    How you got here is a mystery. But you can click the button below
                    to go back to the homepage.
                </p>
                <form action="/jsp/controller" method="post">
                    <input type="hidden" name="command" value="goToMainPage">
                    <input type="submit" name="submit" value="HOME" class="btn blue">
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>