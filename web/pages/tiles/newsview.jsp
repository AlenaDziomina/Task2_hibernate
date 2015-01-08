<%-- 
    Document   : newsview
    Created on : 14.12.2014, 0:08:42
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="confirmMessage">
    <bean:message key="msg.confirmation"/>
</bean:define>

<a class="constColor grey" href="index.do"><bean:message key="menu.header"/></a>
<a class="constColor grey"> >> </a>
<bean:message key="menu.label.view"/>
<logic:notPresent  name="newsForm" property="newsMessage">
    <div class="errors"><html:errors/></div>
</logic:notPresent>
<logic:present  name="newsForm" property="newsMessage">
    <table class="newstable">
        <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        <tr><td class="addrow"><bean:message key="news.title"/></td>
            <td class="mainrow"><div class="constWid">
                    <bean:write name="newsForm" property="newsMessage.title" />
                </div></td>
            <td class="addrow"></td>
        </tr>   
        <tr><td class="addrow"><bean:message key="news.date"/></td>
            <td class="mainrow"><div class="constWid">
                    <bean:write name="newsForm" property="newsMessage.date" 
                                formatKey="format.date"/>
                </div></td>
            <td class="addrow"></td>
        </tr>
        <tr><td class="addrow"><bean:message key="news.brief"/></td>
            <td class="mainrow"><div class="constWid"><bean:write name="newsForm" 
                        property="newsMessage.brief" />
                </div></td>
            <td class="addrow"></td>
        </tr>
        <tr><td class="addrow"><bean:message key="news.content"/></td>
            <td class="mainrow"><div class="constWid"><bean:write name="newsForm" 
                        property="newsMessage.content" />
                </div></td>
            <td class="addrow"></td>
        </tr>
        <tr><td class="addrow"></td>
            <td class="mainrow right">
                <html:form action="/newsaction?action=edit">
                    <input type="hidden" name="selectedId" 
                       value="<bean:write name="newsForm" property="newsMessage.id"/>"/>
                    <html:submit  styleClass="butSize" property="action">
                        <bean:message key="button.edit"/></html:submit>
                </html:form>
            </td>
            <td class="addrow">
                <html:form action="/newsaction?action=delete" 
                           onsubmit="return confirmation('${confirmMessage}')">
                    <input type="hidden" name="deletedId" 
                       value="<bean:write name="newsForm" property="newsMessage.id"/>"/>
                    <html:submit  styleClass="butSize" property="action">
                        <bean:message key="button.delete"/></html:submit>
                </html:form>
            </td>
        </tr>
    </table>
</logic:present>