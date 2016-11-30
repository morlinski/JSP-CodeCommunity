<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_home.jsp"/>

<section>
    <h1>Logging Out.</h1>
    <%
        session.removeAttribute("RegisteredUser");
        session.removeAttribute("UserName");
        //session.removeAttribute("Registered");
        //session.removeAttribute("CurrentRole"); //old attempt     
    %>
    <a  href="<c:url value='/index.jsp'/>">Back Home</a> 
</section>

<jsp:include page="/includes/column_right_news.jsp"/>
<jsp:include page="/includes/footer.jsp"/>