<%-- 
    Document   : main-layout
    Created on : 08.12.2014, 0:00:28
    Author     : Alena.Grouk
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <title><bean:message key="welcome.title"/></title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
        <script src="<%=request.getContextPath()%>/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/js/validation.js"></script>
    </head>
    
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="header">
                    <tiles:insert attribute="header"/>
                </div>
                <div id="main">
                    <div class="menucontainer">
                        <tiles:insert attribute="menu"/>
                    </div>
                    <div class="bodycontainer">
                        <tiles:insert attribute="body"/>
                    </div>
                </div>
            </div>
            <div id="footer">
                <tiles:insert attribute="footer"/>
            </div>
        </div>
    </body>
    
</html>
