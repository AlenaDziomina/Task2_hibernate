/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Alena.Grouk
 */
public class SaveNewsAction extends Action{
    @Override
    public ActionForward execute(ActionMapping  actionMapping,
        ActionForm actionForm, HttpServletRequest request,
 	HttpServletResponse response) throws Exception {
        String path = "newsview";
        return actionMapping.findForward(path);
    }

}
