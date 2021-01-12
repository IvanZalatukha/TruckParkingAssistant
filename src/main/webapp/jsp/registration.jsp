<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="login">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">
    <form id="login_form" action="controller" method="post">
        <div class="field_container">
            <input type="text" placeholder="First Name" name ="firstName">
        </div>
        <div class="field_container">
            <input type="text" placeholder="Last Name" name ="lastName">
        </div>
        <div class="field_container">
            <input type="text" placeholder="Phone number" name ="phoneNumber">
        </div>
        <div class="field_container">
            <input type="text" placeholder="Login" name ="login">
        </div>
        <div class="field_container">
            <input type="Password" placeholder="Password" name ="password">
        </div>

        <div class="field_container">
            <input type="Password" placeholder="Confirm password" name="confirmPassword">
<%--                <button id="sign_in_button" >--%>
<%--                    <span class="button_text">Sign In</span>--%>
<%--                </button>--%>
        </div>
        <form  action="controller" method="post">
                <input type="hidden" name="command" value="goToRegistrationPage">
                <input type="submit" value="Register" class="btn blue"  >
        </form>

        <div id="sign_in_options" class="field_container">
            <div id="sign_in_alternatives_container">
                <span id="google_sign_in_option">or you can <a href="#" id="google_sign_in" class="login_link">sign in with Google</a></span>
                <span id="password_sign_in_option">or you can <a href="#" id="password_sign_in" class="login_link">sign in using a password</a></span>
            </div>
            <div id="remember_me_container">
                <input name="user[remember_me]" type="hidden" value="0"><input id="user_remember_me" name="user[remember_me]" type="checkbox" value="1">
                <label class="login_link" for="user_remember_me" id="remember_me_label">stay signed in</label>
            </div>
            <div class="clearfix"></div>
        </div>
    </form>
</div>

</body>
</html>
