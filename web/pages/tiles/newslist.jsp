<%-- 
    Document   : newslist
    Created on : 14.12.2014, 0:08:17
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<a href="index.do"><bean:message key="menu.header"/></a> >> <bean:message key="menu.label.list"/>

<html:form action="/newsaction?action=delete">
    <table cellpadding="8">
        <logic:iterate id="iter" name="newsForm" property="newsList">
            <tr><td><bean:write name="iter" property="title"/></td>
                <td><bean:write name="iter" property="date" format="MM/DD/YYYY"/></td></tr>
            <tr><td><bean:write name="iter" property="brief"/></td></tr>
            <tr><td></td><td>
            <html:link action="/newsaction.do?action=edit" 
                       paramId="selectedId" paramName="iter" paramProperty="id">
                <bean:message key="label.edit"/></html:link>        
            <html:link action="/newsaction.do?action=view"
                        paramId="selectedId" paramName="iter" paramProperty="id">
                <bean:message key="label.view"/></html:link>
            <html:multibox property="deletedId">
                <bean:write name="iter" property="id"/></html:multibox>
                    <bean:write name="iter" property="id"/>
            </td></tr>
        </logic:iterate>
    </table>
    <html:submit><bean:message key="button.delete"/></html:submit>
</html:form>
<html:javascript formName="newsForm" staticJavascript="true"/>
