<%-- 
    Document   : menu
    Created on : 14.12.2014, 0:06:32
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="menuheader">
    <h2 class="menuhead"><bean:message key="menu.header"/></h2>
</div>
<div class="menulist">
    <ul type="disc">
        <li><html:link action="/newsaction.do?action=list"><bean:message key="menu.label.list"/></html:link></li>
        <li><html:link action="/newsaction.do?action=add"><bean:message key="menu.label.add"/></html:link></li>
    </ul>
</div>
