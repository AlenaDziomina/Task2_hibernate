<%-- 
    Document   : newsview
    Created on : 14.12.2014, 0:08:42
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<bean:define id="confirmMessage">
    <bean:message key="msg.confirmation"/>
</bean:define>

<a href="index.do"><bean:message key="menu.header"/></a> >> <bean:message key="menu.label.view"/>

<table class="newstable">
    <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
    <tr><td class="addrow"><bean:message key="news.title"/></td>
        <td class="mainrow"><bean:write name="newsForm" property="newsMessage.title" /></td></tr>   
    <tr><td class="addrow"><bean:message key="news.date"/></td>
        <td class="mainrow"><bean:write name="newsForm" property="newsMessage.date" formatKey="format.date"/></td></tr>
    <tr><td class="addrow"><bean:message key="news.brief"/></td>
        <td class="mainrow"><bean:write name="newsForm" property="newsMessage.brief" /></td></tr>
    <tr><td class="addrow"><bean:message key="news.content"/></td>
        <td class="mainrow"><bean:write name="newsForm" property="newsMessage.content" /></td></tr>
</table>

    
<div class="leftcontainer">
    <html:form action="/newsaction?action=edit">
        <html:hidden property="forwardName" value="newsview"/>
        <input type="hidden" name="selectedId" 
           value="<bean:write name="newsForm" property="newsMessage.id"/>"/>
    
        <html:submit property="action">
            <bean:message key="button.edit"/></html:submit>
    </html:form>
</div>
<div class="rigthcontainer">  
    <html:form action="/newsaction?action=delete" onsubmit="return confirmation('${confirmMessage}')">
        <input type="hidden" name="deletedId" 
           value="<bean:write name="newsForm" property="newsMessage.id"/>"/>
        <html:submit property="action">
            <bean:message key="button.delete"/></html:submit>
    </html:form>
</div>      