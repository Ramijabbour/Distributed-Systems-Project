package com.example.comment.MQ.ModelToRecv;

import java.io.Serializable;

public class ArticleID implements Serializable {
    public int id ;
    public ArticleID() {
    }
    public ArticleID(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
