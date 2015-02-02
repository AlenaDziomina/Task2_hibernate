<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
    <div  style="color: red">
        ERROR:  Application resources not loaded -- check servlet container
        logs for error messages.
    </div>
</logic:notPresent>
<h3><bean:message key="welcome.heading"/></h3>
<p><bean:message key="welcome.message"/></p>
