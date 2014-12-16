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
    <table>
        <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        <tr><td><bean:message key="news.title"/></td><td><html:text property="newsMessage.title" value="title1"/></td></tr>   
        <tr><td><bean:message key="news.date"/></td><td><html:text property="stringDate" value="01/01/2015"/></td></tr>
        <tr><td><bean:message key="news.brief"/></td><td><html:textarea property="newsMessage.brief" value="brief1"/></td></tr>
        <tr><td><bean:message key="news.content"/></td><td><html:textarea property="newsMessage.content" value="content1"/></td></tr>
    </table>
    <html:submit><bean:message key="button.save"/></html:submit>
</html:form>
<input type="reset" value="" name="cancel" />
<html:javascript formName="newsForm" staticJavascript="true"/>