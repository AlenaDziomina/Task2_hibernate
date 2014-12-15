/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.action;

import com.epam.testapp.presentation.form.NewsForm;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Alena.Grouk
 */
public class NewsListAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        List list = new ArrayList();
        NewsForm newsForm1 = new NewsForm();
        newsForm1.setTitle("title3");
        newsForm1.setDate("03/03/2015");
        newsForm1.setBrief("brief3");
        newsForm1.setContent("content3");
        NewsForm newsForm2 = new NewsForm();
        newsForm2.setTitle("title4");
        newsForm2.setDate("04/04/2015");
        newsForm2.setBrief("brief4");
        newsForm2.setContent("content4");
        list.add(newsForm1);
        list.add(newsForm2);
        request.setAttribute("resultNewsList", list);

        return mapping.findForward(SUCCESS);
    }
}
