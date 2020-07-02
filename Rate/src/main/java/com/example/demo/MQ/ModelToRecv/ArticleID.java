package com.example.demo.MQ.ModelToRecv;

import java.io.Serializable;

public class ArticleID implements Serializable {

	private static final long serialVersionUID = 1L;

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
