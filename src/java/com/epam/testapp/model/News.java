package com.epam.testapp.model;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NEWS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.DeleteById", query = "DELETE FROM News where id = :id"),
    @NamedQuery(name = "News.SelectAll", query = "SELECT n FROM News n")})
    

public class News implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "NEWS_ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "NEWS_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "BRIEF")
    private String brief;
    @Basic(optional = false)
    @Column(name = "CONTENT")
    private String content;
    
    public News() {}

    public News(Integer id, String title, Date date, String brief, String content) {
        
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

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
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    

}
