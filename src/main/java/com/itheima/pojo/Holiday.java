package com.itheima.pojo;

import java.io.Serializable;

public class Holiday implements Serializable {

    private static final long serialVersionUID = -8401328168626165346L;

    private int id;

    private Double num;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Double getNum() {
        return num;
    }

    public void setNum( Double num ) {
        this.num = num;
    }
}
