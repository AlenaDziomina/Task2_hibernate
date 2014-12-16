/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.action;

import com.epam.testapp.dao.INewsDAO;
import com.epam.testapp.dao.NewsDAO;
import com.epam.testapp.presentation.form.News;
import com.epam.testapp.presentation.form.NewsForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Alena_Grouk
 */
public class NewsAction extends DispatchAction {
    
    private String forwardName;
    
    private INewsDAO dao = new NewsDAO();
    
    public void setNewsdao(){
        
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        if (newsForm == null) {
            newsForm = new NewsForm();
        }
        List list = getDao().getList();
        //newsForm.setNewsList(list);
        return mapping.findForward("newslist");
    }
    
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        News news = newsForm.getNewsMessage();
        if (news != null) {
            Integer id = news.getId();
            news = getDao().fetchById(id);
        } else {
            news = new News();
            news.setTitle("unnownTitle");
            news.setBrief("unnownBrief");
        }
        newsForm.setNewsMessage(news);
        return mapping.findForward("newsview");
    }
    
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        
        News news = newsForm.getNewsMessage();
        if (news != null) {
            Integer id = news.getId();
            news = getDao().fetchById(id);
        } else {
            news = new News();
            news.setTitle("unnownTitle");
            news.setBrief("unnownBrief");
        }
        newsForm.setNewsMessage(news);
        return mapping.findForward("newsedit");
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        News news = newsForm.getNewsMessage();
        if (news != null) {
            getDao().remove(news);
        }
        newsForm.setNewsMessage(null);
        return mapping.findForward("newslist");
    }
    
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        newsForm.setNewsMessage(null);
        return mapping.findForward("newslist");
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        News news = newsForm.getNewsMessage();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY");
            Date currDate = formatter.parse(newsForm.getStringDate());
            news.setDate(currDate);
        } catch (ParseException ex) {}
        return mapping.findForward("newsview");
    }
    
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse responce)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        News news = new News();
        Date date;
        news.setDate(Date.from(Instant.EPOCH));
        return mapping.findForward("newsedit");
    }

    /**
     * @return the dao
     */
    public INewsDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(INewsDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the forwardName
     */
    public String getForwardName() {
        return forwardName;
    }

    /**
     * @param forwardName the forwardName to set
     */
    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }
    
}
