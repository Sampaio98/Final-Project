package com.project.finalproject.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    private Integer page;
    private Integer linesPerPage;
    private String orderBy;
    private String direction;

    public Pagination() {
    }

    public String getDirection() {
        return direction.toUpperCase();
    }

    public void setDirection(String direction) {
        this.direction = direction.toUpperCase();
    }
}
