package com.epam.testapp.presentation.form;

import com.epam.testapp.model.News;
import static com.epam.testapp.util.validator.NewsValidator.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class NewsForm extends ValidatorForm {
    private News newsMessage;
    private List newsList;
    private String stringDate;
    private String locale;
    private String forwardName;
    private String selectedId;
    private String[] deletedId;
    
    

    @Override
    @SuppressWarnings("empty-statement")
    public void reset(ActionMapping mapping, HttpServletRequest request) {}
    
    @Override
    public ActionErrors validate(ActionMapping mapping,
                    HttpServletRequest request) {
            ActionErrors actionErrors = new ActionErrors();
            validateTitle(actionErrors, newsMessage);
            validateStringDate(actionErrors, stringDate);
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
    
    
}

