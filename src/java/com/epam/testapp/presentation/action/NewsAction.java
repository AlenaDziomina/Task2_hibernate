/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.action;

import com.epam.testapp.dao.INewsDao;
import com.epam.testapp.manager.DataManager;
import com.epam.testapp.presentation.form.News;
import com.epam.testapp.presentation.form.NewsForm;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Alena_Grouk
 */
public class NewsAction extends DispatchAction {
    
    private static final String FORWARD_INDEX = "index";
    private static final String FORWARD_NEWSLIST = "newslist";
    private static final String FORWARD_NEWSEDIT = "newsedit";
    private static final String FORWARD_NEWSVIEW = "newsview";
    
    private INewsDao newsDao;
    
    /**
     * @return the newsDao
     */
    public INewsDao getNewsDao() {
        return newsDao;
    }

    /**
     * @param newsDao the newsDao to set
     */
    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        List list = getNewsDao().getList();
        newsForm.setNewsList(list);
        return mapping.findForward(FORWARD_NEWSLIST);
    }
    
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        Integer id = Integer.decode(newsForm.getSelectedId());
        newsForm.setNewsMessage(getNewsDao().fetchById(id));
        return mapping.findForward(FORWARD_NEWSVIEW);
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        Integer id = Integer.decode(newsForm.getSelectedId());
        newsForm.setNewsMessage(getNewsDao().fetchById(id));
        newsForm.setStringDate(DataManager.toFormatString(newsForm.getNewsMessage().getDate()));
        return mapping.findForward(FORWARD_NEWSEDIT);
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        String[] deletedId = newsForm.getDeletedId();
        //validation!!!!
        List idList = new ArrayList();
        for(String strId : deletedId) {
            idList.add(Integer.decode(strId));
        }
        getNewsDao().remove(idList);
        getNewsDao().getList();
        newsForm.setNewsList(getNewsDao().getList());
        return mapping.findForward(FORWARD_NEWSLIST);
    }
    
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        return mapping.findForward(newsForm.getForwardName());
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        ActionErrors errors = newsForm.validate(mapping, request);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return mapping.findForward(FORWARD_NEWSEDIT);
        }
        try {
            newsForm.getNewsMessage().setDate(DataManager.toSqlDate(newsForm.getStringDate()));
        } catch (ParseException ex) {}
        
        getNewsDao().save(newsForm.getNewsMessage());
        return mapping.findForward(FORWARD_NEWSVIEW);
    }
    
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        newsForm.setNewsMessage(new News());
        newsForm.getNewsMessage().setDate(DataManager.toSqlDate());
        newsForm.setStringDate(DataManager.toFormatString(null));
        newsForm.setForwardName(FORWARD_INDEX);
        return mapping.findForward(FORWARD_NEWSEDIT);
    }

    public ActionForward locale(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        Locale locale = new Locale(newsForm.getLocale(), newsForm.getLocale());
        request.getSession().setAttribute(Globals.LOCALE_KEY, locale);
        return mapping.findForward(FORWARD_INDEX);
    }
    

}
