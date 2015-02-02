<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<a class="constColor grey" href="index.do"><bean:message key="menu.header"/></a>
<a class="constColor grey"> >> </a>
<bean:message key="menu.label.add"/>

<logic:notPresent  name="newsForm" property="newsMessage">
    <div class="errors"><html:errors/></div>
</logic:notPresent>
<logic:present  name="newsForm" property="newsMessage">
    <table class="newstable">
        <html:form action="/newsaction?action=save" onsubmit="return validateNewsForm(this)">
            <jsp:include page="/WEB-INF/jsp/validationMsg.jsp" />           
        <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        <tr><td class="addrow"><bean:message key="news.title"/></td>
            <td colspan="2" class="mainrow"><html:text property="newsMessage.title" 
                       size="100"/></td></tr>   
        <tr><td class="addrow"><bean:message key="news.date"/></td>
            <td colspan="2" class="mainrow"><html:text property="stringDate" size="10">
                    <bean:write name="newsForm" property="newsMessage.date" 
                                formatKey="format.date"/>
                </html:text></td></tr>
        <tr><td class="addrow"><bean:message key="news.brief"/></td>
            <td colspan="2" class="mainrow"><html:textarea property="newsMessage.brief" 
                           cols="75" rows="5"/></td></tr>
        <tr><td class="addrow"><bean:message key="news.content"/></td>
            <td colspan="2" class="mainrow"><html:textarea property="newsMessage.content" 
                           cols="75" rows="10"/></td></tr>
        <tr><td class="addrow"></td>
            <td class="mainrow right">
                <html:submit styleClass="butSize"><bean:message key="button.save"/></html:submit>
                </html:form>
            </td>
            <td class="mainrow">
            <html:form action="/newsaction?action=cancel">
                <html:cancel styleClass="butSize"><bean:message key="button.cancel"/></html:cancel>
            </html:form>
            </td></tr>
    </table>
    <logic:notPresent  name="newsForm" property="newsMessage.id">
        <script>
            setAddingVisited();
        </script>
    </logic:notPresent>
</logic:present>
