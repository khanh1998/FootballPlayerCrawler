<%-- 
    Document   : index
    Created on : Mar 20, 2020, 8:08:09 PM
    Author     : KHANHBQSE63463
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Player Recommender</title>
        <link rel="stylesheet" href="./css/index.css" type="text/css"/>
    </head>
    <body>

        <c:set var="initSize" value="${10}"/>
        <c:set var="initOffset" value="${0}"/>

        <h1>Player Recommender</h1>
        <form action="DispatcherServlet">
            <input type="text" name="searchValue" value="${param.searchValue}"/>
            <input type="submit" name="action" value="search"/>
            <input type="hidden" name="offset" value="${initOffset}"/>
            <input type="hidden" name="pageSize" value="${initSize}"/>
        </form>
        <a href="DispatcherServlet">Home</a>

        <c:if test="${not empty sessionScope.USERNAME}">
            <p>Welcome ${sessionScope.NAME}</p>
            <a href="DispatcherServlet?action=logout">Logout</a>
        </c:if>
        <c:if test="${empty sessionScope.USERNAME}">
            <a href="login.html">Login</a>
            <a href="register.jsp">Register</a>
        </c:if>
        <c:if test="${requestScope.FROM_SAVED eq true}">
            <h1>Your saved players</h1>
            <c:if test="${empty sessionScope.FBREF}">
                <p>You haven't save any player yet!</p>
            </c:if>
        </c:if>
        <c:if test="${requestScope.FROM_SEARCH eq true}">
            <h1>Search results</h1>
            <c:if test="${requestScope.RESULT_SIZE eq 0}">
                <p>Search result is empty!</p>
            </c:if>
        </c:if>
        <c:set var="xsl" value="${applicationScope.XSL_PLAYER_TABLE}"/>
        <c:set var="xml" value="${requestScope.XML_RESULT}"/>
        <c:if test="${not empty xml and not empty xsl}">
            <x:transform xml="${xml}" xslt="${xsl}"/>
        </c:if>





        <c:if test="${ not empty param.offset}">
            <c:if test="${param.offset >= initSize}">
                <c:url var="prev" value="DispatcherServlet">
                    <c:param name="searchValue" value="${param.searchValue}"/>
                    <c:param name="action" value="search"/>
                    <c:param name="size" value="${initSize}"/>
                    <c:param name="offset" value="${param.offset - initSize}"/>
                </c:url>
                <a href="${prev}">Previous</a>
            </c:if>
            <c:if test="${initSize eq requestScope.RESULT_SIZE}">
                <c:url var="next" value="DispatcherServlet">
                    <c:param name="searchValue" value="${param.searchValue}"/>
                    <c:param name="action" value="search"/>
                    <c:param name="size" value="${initSize}"/>
                    <c:param name="offset" value="${param.offset + initSize}"/>
                </c:url>
                <a href="${next}">Next</a>
            </c:if>  
        </c:if>

    </body>
</html>
