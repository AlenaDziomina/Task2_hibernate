<%-- 
    Document   : header
    Created on : 14.12.2014, 0:06:14
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<h1><a href="index.do"><bean:message key="welcome.title"/></a></h1>
<div class="right">
    <html:link action="/newsaction.do?action=locale&locale=EN">
        <bean:message key="label.en"/></html:link> 
    <html:link action="/newsaction.do?action=locale&locale=RU">
        <bean:message key="label.ru"/></html:link> 
</div>


