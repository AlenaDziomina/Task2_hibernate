package com.epam.testapp.util.validator;

import com.epam.testapp.model.News;
import com.epam.testapp.util.converter.DataConverter;
import java.text.ParseException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class NewsValidator {
    private static final int TITLE_MAXLENGTH = 100;
    private static final int STRDATE_MAXLENGTH = 10;
    private static final int BRIEF_MAXLENGTH = 500;
    private static final int CONTENT_MAXLENGTH = 2048;
    
    public static void validateTitle(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getTitle())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.title"));
        } else if (newsMessage.getTitle().length() > TITLE_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.title", TITLE_MAXLENGTH));
        }
    }
    
    public static void validateBrief(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getBrief())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.brief"));
        } else if (newsMessage.getBrief().length() > BRIEF_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.brief", BRIEF_MAXLENGTH));
        }
    }
    
    public static void validateContent(ActionErrors errors, News newsMessage) {
        if ("".equals(newsMessage.getContent())) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.content"));
        } else if (newsMessage.getContent().length() > CONTENT_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.content", CONTENT_MAXLENGTH));
        }
    }

    public static void validateStringDate(ActionErrors errors, String stringDate) {
        if ("".equals(stringDate)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.strdate"));
        } else if (stringDate.length() > STRDATE_MAXLENGTH) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.maxLength.strdate", STRDATE_MAXLENGTH));
        } else {
            try {
                DataConverter.toSqlDate(stringDate);
            } catch (ParseException ex) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, 
                    new ActionMessage("errors.strdate.parse"));
            }
        }
    }
}
