<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_home.jsp"/>

<section>
    <h1>Login Form</h1>
    <p>Please enter your username and password to continue.</p>
    <!--<form action="j_security_check" method="post">-->
    <form action="login" method="post">
        <input type="hidden" name="action" value="login"/>
        <label>Username : </label><input type="text" name="j_username" required><br/>
        <label>Password : </label><input type="password" name="j_password" required><br/>
        <input type="submit" value="Login"/>
    </form>
</section>

<jsp:include page="/includes/column_right_news.jsp"/>
<jsp:include page="/includes/footer.jsp"/>