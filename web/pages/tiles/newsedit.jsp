<%-- 
    Document   : newsedit
    Created on : 14.12.2014, 0:08:56
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<a href="index.do"><bean:message key="menu.header"/></a> >> <bean:message key="menu.label.add"/>
<html:form action="/newsaction?action=save" onsubmit="return validateNewsForm(this)">
    <table class="newstable">
        <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        <tr><td class="addrow"><bean:message key="news.title"/></td>
            <td class="mainrow"><html:text property="newsMessage.title" 
                       size="100"/></td></tr>   
        <tr><td class="addrow"><bean:message key="news.date"/></td>
            <td class="mainrow"><html:text property="stringDate" size="10">
                    <bean:write name="newsForm" property="newsMessage.date" 
                                formatKey="format.date"/>
                </html:text></td></tr>
        <tr><td class="addrow"><bean:message key="news.brief"/></td>
            <td class="mainrow"><html:textarea property="newsMessage.brief" 
                           cols="75" rows="5"/></td></tr>
        <tr><td class="addrow"><bean:message key="news.content"/></td>
            <td class="mainrow"><html:textarea property="newsMessage.content" 
                           cols="75" rows="10"/></td></tr>
        <tr><td class="addrow"></td>
            <td class="mainrow">
                <html:submit><bean:message key="button.save"/></html:submit>
            </td></tr>
    </table>
</html:form>
<html:form action="/newsaction?action=cancel">
    <html:cancel><bean:message key="button.cancel"/></html:cancel>
</html:form>

<html:javascript formName="newsForm" staticJavascript="true"/>