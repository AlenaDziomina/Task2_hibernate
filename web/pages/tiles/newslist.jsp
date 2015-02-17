<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<a class="constColor grey" href="index.do"><bean:message key="menu.header"/></a>
<a class="constColor grey"> >> </a>
<bean:message key="menu.label.list"/>
<script>
    setListVisited();
</script> 
<html:form action="/newsaction?action=delete" 
           onsubmit="return confirmSubmit('deletedId')">
    <jsp:include page="/WEB-INF/jsp/validationMsg.jsp" />
    <logic:notEmpty  name="newsForm" property="newsList">
        <table cellpadding="8" class="newstable">
            <logic:iterate id="iter" name="newsForm" property="newsList" indexId="newsIndex">
                <bean:define id="tmpMod" value="${newsIndex + 1}"/>
                <tr><td class="mainrow bold" colspan="2"
                        id="<bean:write name="iter" property="id"/>" >
                        <div class="constWid add">
                            <bean:write name="tmpMod"/>. 
                            <bean:write name="iter" property="title"/></td>
                        </div>
                    <td class="addrow line center"><bean:write name="iter" property="date" 
                                formatKey="format.date"/></td>
                </tr>
                <tr><td class="mainrow"><div class="constWid">
                            <bean:write name="iter" property="brief"/></div></td>
                    <td class="addrow line center"></td>
                    <td class="addrow line center"></td>
                </tr>
                <tr><td class="addrow right" colspan="3">
                        <html:link styleClass="constColor nodecor" action="/newsaction.do?action=view"
                                    paramId="selectedId" paramName="iter" paramProperty="id">
                            <bean:message key="label.view"/></html:link>
                        <html:link styleClass="constColor nodecor" action="/newsaction?action=edit" 
                                   paramId="selectedId" paramName="iter" paramProperty="id">
                            <bean:message key="label.edit"/></html:link>
                        <html:multibox property="deletedId">
                            <bean:write name="iter" property="id"/></html:multibox>
                    </td></tr>
            </logic:iterate>
            <tr><td class="mainrow" colspan="2"></td>
                <td class="addrow center"><html:submit  styleClass="butSize">
                        <bean:message key="button.delete"/></html:submit>
                </td>
            </tr>
            <tr><td colspan="2"><div class="errors"><html:errors/></div></td></tr>
        </table>       
    </logic:notEmpty>
    
    <logic:empty name="newsForm" property="newsList">
        <h5><bean:message key="newslist.empty"/></h5>
    </logic:empty>
        
</html:form>

