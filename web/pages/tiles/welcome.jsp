<%-- 
    Document   : welcome
    Created on : 14.12.2014, 0:07:38
    Author     : Alena.Grouk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
    <div  style="color: red">
        ERROR:  Application resources not loaded -- check servlet container
        logs for error messages.
    </div>
</logic:notPresent>
<h3><bean:message key="welcome.heading"/></h3>
<p><bean:message key="welcome.message"/></p>
