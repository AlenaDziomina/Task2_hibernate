package com.epam.testapp.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS")
@NamedQueries({
		@NamedQuery(name = "Delete", query = "DELETE FROM News n where n.id in (:id)"),
		@NamedQuery(name = "Select", query = "SELECT n FROM News n ORDER BY n.date DESC") })
public class News implements Serializable {
    @Id  
    @SequenceGenerator(name = "NEWS_ID", sequenceName = "NEWS_SEQ")
    @GeneratedValue(generator = "NEWS_ID")
    @Column(name = "NEWS_ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "NEWS_DATE")
    private Date date;
    @Column(name = "BRIEF")
    private String brief;
    @Column(name = "CONTENT")
    private String content;

    public News() {
    }

    public News(Integer newsId) {
        this.id = newsId;
    }
    
    public News(Integer id, String title, Date date, String brief, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer newsId) {
        this.id = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.epam.testapp.model.News[ newsId=" + id + " ]";
    }
    
}
