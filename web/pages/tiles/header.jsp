<%-- 
    Document   : header
    Created on : 14.12.2014, 0:06:14
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="titlecontainer">
    <h1 class="titletext"><a class="title" href="index.do">
            <bean:message key="welcome.title"/></a></h1>
</div>

<div class="engcontainer">
    <html:link styleClass="constColor blue" action="/newsaction.do?action=locale&locale=EN">
        <bean:message key="label.en"/></html:link> 
    <html:link styleClass="constColor blue" action="/newsaction.do?action=locale&locale=RU">
        <bean:message key="label.ru"/></html:link> 
</div>


