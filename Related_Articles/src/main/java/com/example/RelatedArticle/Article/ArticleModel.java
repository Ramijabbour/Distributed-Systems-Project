package com.example.RelatedArticle.Article;

public class ArticleModel {


    int id;
    String subject;
    String text;
    String Category;

    public ArticleModel() {

    }

    public ArticleModel(String subject, String text, String category) {
        this.subject = subject;
        this.text = text;
        Category = category;
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


}
