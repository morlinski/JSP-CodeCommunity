<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_home.jsp"/>

<section>
    <%
                         String topic = (String) session.getAttribute("topic");
                         if(topic == null)
                         {
                             topic = "HTML/CSS";
                         }
                         /*String byUser = (String) session.getAttribute("UserName");
                         if(byUser == null){
                             //username cannot exist in database because of the 8 character minumim restriction.
                             byUser = "Guest";
                         }*/
    %>
    <h1>Submit Your ${topic} Question ${sessionScope.UserName}:</h1>
    <form action="testqpages" method="post" id="Q">
        <input type="hidden" name="Qn_user" value="${sessionScope.UserName}"/>
        <label>Brief Description : </label>
         <textarea rows="4" cols="75" name="Qn_desc" form="Q" required></textarea> 
       <br/>
        <label>Code Snippet : </label>
        <textarea rows="4" cols="75" name="Qn_code"  form="Q"></textarea> 
         <br/>
        <input type="submit" value="Submit"/>
    </form>
    <a  href="<c:url value='/index.jsp'/>">Back Home</a> 
</section>

<jsp:include page="/includes/column_right_news.jsp"/>
<jsp:include page="/includes/footer.jsp"/>
