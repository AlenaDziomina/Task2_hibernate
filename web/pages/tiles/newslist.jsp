<%-- 
    Document   : newslist
    Created on : 14.12.2014, 0:08:17
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<a href="index.do"><bean:message key="menu.header"/></a> >> <bean:message key="menu.label.list"/>

<logic:iterate id="iter" name="resultNewsList">
    <table>
        <tr><td><bean:write name="iter" property="title"/></td>
            <td><bean:write name="iter" property="date"/></td></tr>
        <tr><td><bean:write name="iter" property="brief"/></td></tr>
        <tr><td></td><td>
            <a href="newsedit.do?selectId=<bean:write name="iter" property="title"/>"><bean:message key="label.edit"/></a>
            <a href="newsview.do?selectId=<bean:write name="iter" property="title"/>"><bean:message key="label.view"/></a>
            </td></tr>
    </table>
</logic:iterate>
