<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>


</head>
<body>

</div>
    <div id="wrapper">
        <div class="menu">
                    <h1>MAIN PAGE</h1>
            <div class="preheaderTextItem">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToMapPage">
                    <input type="submit"  name="submit" value="map" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goSignInPage">
                    <input type="submit"  name="submit" value="Sign in" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToRegistrationPage">
                    <input type="submit"  name="submit" value="Registration" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="error">
                    <input type="submit"  name="submit" value="error" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToAdiminPage">
                    <input type="hidden" name="currentPage" value="1">
                    <input type="submit"  name="submit" value="AdiminPage" >
                </form>

            </div>
    </div>
</div>



</body>
</html>
