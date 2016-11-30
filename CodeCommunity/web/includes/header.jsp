<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>">
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/>">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <title>Code Community</title>
        <script>
            function initScreen(){
                //alert("I'm Alive");
                
            }
        </script>
        
    </head>
    <body onload="initScreen()">
        <header>
            <!--<img src="<c:url value='images/logo.jpg'/>" alt="Code Community" width="58">-->
            <h1>Welcome to Code Community</h1>

        </header>
            <nav id="nav_bar">
                <ul>
                     <%@ page import="codecomm.data.CodeData" %>
                     <%
                         CodeData RegisteredUser = (CodeData) session.getAttribute("RegisteredUser");
                         if(RegisteredUser == null)
                         {
                             RegisteredUser = new CodeData();
                         }
                     %>
                     <c:if test="${!(RegisteredUser.registered).equals('isRegistered')}"> 
                         <li><a  href="<c:url value='/login.jsp'/>">Login</a>
                             
                             <%--<span>${RegisteredUser.registered} show Object</span>
                             <span>and the result ${!RegisteredUser.equals('isRegistered')}</span>--%>
                         </li>
                     </c:if>
                     <c:if test="${(RegisteredUser.registered).equals('isRegistered')}"> 
                        <li><a  href="<c:url value='/logout.jsp'/>">Logout</a></li>
                     </c:if>
                     
                    <li><a  href="<c:url value='/register.jsp'/>">Register</a></li>
                    <li><a  class="current" href="<c:url value='/home'/>">Home</a></li>
                    <li><a  href="<c:url value='/about.jsp'/>">About</a></li>
                </ul>
            </nav>
                
                 <%--
                 <%
                     request.getSession().setAttribute("Hello", "hi");
                     //request.getSession().setAttribute("Registered", "test");
                     
                 %> 
                 <%= session.getAttribute( "Hello" ) %>
                 ${Hello}
                 <%= request.getSession().getAttribute( "Registered" ) %>
                 ${Registered}
                <c:forEach var="c" items="${cookie}">
                        <p> ${c.value.name} , ${c.value.value} </p>
                </c:forEach>
                --%>
