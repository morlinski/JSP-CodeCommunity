<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/column_left_home.jsp"/>

<section>
    <p>Submitted By: <c:out value="${sessionScope.UserName}" /></p>
    <p>Brief Description: <br/><c:out value="${Q_desc}" /></p>
    <p>Sample Code: <br/><c:out value="${Q_code}" /></p>
</section>

<jsp:include page="/includes/column_right_news.jsp"/>
<jsp:include page="/includes/footer.jsp"/>
