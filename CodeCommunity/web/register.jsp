<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_home.jsp"/>

<section>
    <h1>Register Form</h1>
    <p>Please enter a username and password to continue.</p>
    <!--<form action="j_security_check" method="post">-->
    <form action="login" method="post">
        <input type="hidden" name="action" value="register"/>
        <label>Username/Verify :</label><input type="text" name="j_username1" placeholder="username" min="8" max="30" id="un1"  pattern=".{8,30}" title="8 To 30 Character Username required" required/>
        <input type="text" name="j_username2" placeholder="verify username" oninput="check(this,'un1')" min="2" max="30" required/>
        <span id="un1message"></span>
        <br/>
        <label>Password/Verify : </label><input type="password" name="j_password1" placeholder="password" min="8" max="30" 
                        pattern="^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,30}$"  id="pw1" 
                        title="8 To 30 Character Password -- 2 Uppercase, 3 Lowercase, 2 Numbers, 1 Special Character of !@#$&*  ). "
                        required/>                                  
        <input type="password" name="j_password2" oninput="check(this, 'pw1')" placeholder="verify password" min="8" max="30" required/>
        <span id="pw1message"></span>
        <script>
            function check(input, other){
                var updateElem = other+"message";
                if (input.value != document.getElementById(other).value) {
                    //console.log(input.value);
                    //console.log(document.getElementById(other).value);
                     input.setCustomValidity('Match Not Found');
                     
                     document.getElementById(updateElem).innerText = "*";
                } else {
                 // input is valid -- reset the error message
                 document.getElementById(updateElem).innerText = "";
                 input.setCustomValidity('');
                }
            }
        </script>
        <!-- 
            ^                         Start anchor
            (?=.*[A-Z].*[A-Z])        Ensure string has two uppercase letters.
            (?=.*[!@#$&*])            Ensure string has one special case letter.
            (?=.*[0-9].*[0-9])        Ensure string has two digits.
            (?=.*[a-z].*[a-z].*[a-z]) Ensure string has three lowercase letters.
            .{8,30}                   Ensure string between 8 and 30 characters long.
            $   
        -->
        <br/>
        <input type="submit" value="Register"/>
        <!-- does the chosen user name currently exists-->
        <span name="regMessage">${rejectionMessage}</span>
    </form>
</section>

<jsp:include page="/includes/column_right_news.jsp"/>
<jsp:include page="/includes/footer.jsp"/>