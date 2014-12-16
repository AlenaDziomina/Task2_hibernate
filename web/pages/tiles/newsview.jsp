<%-- 
    Document   : newsview
    Created on : 14.12.2014, 0:08:42
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<a href="index.do"><bean:message key="menu.header"/></a> >> <bean:message key="menu.label.view"/>

    <table>
        <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        <tr><td><bean:message key="news.title"/></td><td><bean:write name="newsForm" property="newsMessage.title" /></td></tr>   
        <tr><td><bean:message key="news.date"/></td><td><bean:write name="newsForm" property="newsMessage.date" /></td></tr>
        <tr><td><bean:message key="news.brief"/></td><td><bean:write name="newsForm" property="newsMessage.brief" /></td></tr>
        <tr><td><bean:message key="news.content"/></td><td><bean:write name="newsForm" property="newsMessage.content" /></td></tr>
    </table>