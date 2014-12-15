/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.presentation.form;

import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Alena.Grouk
 */
public class NewsForm extends ValidatorForm {
    private String title;
    private String date;
    private String brief;
    private String content;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the brief
     */
    public String getBrief() {
        return brief;
    }

    /**
     * @param brief the brief to set
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}

