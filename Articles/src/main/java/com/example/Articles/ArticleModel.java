package com.example.Articles;

import javax.persistence.*;


@Entity
@Table(name = "Articles")
public class ArticleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String subject;
    String brief;
    String Category;
    @Column(columnDefinition = "varchar(5000)")
    String text;

    public ArticleModel() {

    }

    public ArticleModel(String subject, String brief, String category, String text) {
        this.subject = subject;
        this.brief = brief;
        Category = category;
        this.text = text;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
