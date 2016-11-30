<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<% 
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>   

<footer>
    <p>&copy; Copyright <%= currentYear %> : <script>document.write("( thank your for enabling javascript )");</script><noscript>document.write("( javascript is currently disabled )");</noscript></p>
</footer>
</body>
</html>
