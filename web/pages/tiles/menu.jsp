<%-- 
    Document   : menu
    Created on : 14.12.2014, 0:06:32
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<div class="menuheader">
    <h2><bean:message key="menu.header"/></h2>
</div>
<div class="menulist">
    <ul type="disc">
        <li><bean:message key="menu.label.list"/></li>
        <li><bean:message key="menu.label.add"/></li>
    </ul>
</div>
