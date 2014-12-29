/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.form;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Alena.Grouk
 */
public class NewsForm extends ValidatorForm {
    private News newsMessage;
    private List newsList;
    private String stringDate;
    private String locale;
    private String forwardName;
    private String selectedId;
    private String[] deletedId;
    
    private static final int TITLE_MAXLENGTH = 100;
    private static final int BRIEF_MAXLENGTH = 500;
    private static final int CONTENT_MAXLENGTH = 2048;

    @Override
    @SuppressWarnings("empty-statement")
    public void reset(ActionMapping mapping, HttpServletRequest request) {
//        newsMessage = new News();
//        newsList = new ArrayList();
//        stringDate = "";
//        selectedId = "";
//        deletedId = new String[0];
//        forwardName = "";
//        newsMessage.setId(0);
//        newsMessage.setTitle("unnTitle");
//        newsMessage.setBrief("unnBrief");
//        newsMessage.setContent("unnContent");
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        stringDate = formatter.format(new Date());
        //newsMessage.setDate();
//        newsList.add(newsMessage);
        //locale = new Locale("RU");
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping,
                    HttpServletRequest request) {
            ActionErrors actionErrors = new ActionErrors();
            validateTitle(actionErrors, newsMessage);
            validateBrief(actionErrors, newsMessage);
            validateContent(actionErrors, newsMessage);
            return actionErrors;
    }
    
    /**
     * @return the newsList
     */
    public List getNewsList() {
        return newsList;
    }

    /**
     * @param newsList the newsList to set
     */
    public void setNewsList(List newsList) {
        this.newsList = newsList;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the stringDate
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * @param stringDate the stringDate to set
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }
    
    
    /**
     * @return the newsMessage
     */
    public News getNewsMessage() {
        return newsMessage;
    }

    /**
     * @param newsMessage the newsMessage to set
     */
    public void setNewsMessage(News newsMessage) {
        this.newsMessage = newsMessage;
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

    /**
     * @return the selectedId
     */
    public String getSelectedId() {
        return selectedId;
    }

    /**
     * @param selectedId the selectedId to set
     */
    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    /**
     * @return the deletedId
     */
    public String[] getDeletedId() {
        return deletedId;
    }

    /**
     * @param deletedId the deletedId to set
     */
    public void setDeletedId(String[] deletedId) {
            this.deletedId = deletedId;
        
    }
    
    private void validateTitle(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getTitle())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.title"));
        } else if (newsMessage.getTitle().length() > TITLE_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.title", TITLE_MAXLENGTH));
        }
    }
    
    private void validateBrief(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getBrief())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.brief"));
        } else if (newsMessage.getBrief().length() > BRIEF_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.brief", BRIEF_MAXLENGTH));
        }
    }
    
    private void validateContent(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getContent())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.content"));
        } else if (newsMessage.getContent().length() > CONTENT_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.content", CONTENT_MAXLENGTH));
        }
    }
    
}

