package com.example.Articles;

import com.example.ExternalModel.CommentModel;

import java.util.List;

public class ArticleCommentRating {

    public ArticleModel article ;
    public List<CommentModel> comment;
    public float rating;

    public ArticleCommentRating() {
    }

    public ArticleCommentRating(ArticleModel article, List<CommentModel> comment, float rating) {
        this.article = article;
        this.comment = comment;
        this.rating = rating;
    }

    public ArticleModel getArticle() {
        return article;
    }

    public List<CommentModel> getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public void setArticle(ArticleModel article) {
        this.article = article;
    }

    public void setComment(List<CommentModel> comment) {
        this.comment = comment;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
