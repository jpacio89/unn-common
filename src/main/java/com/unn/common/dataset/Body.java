package com.unn.common.dataset;

import java.io.Serializable;

public class Body implements Serializable {
    Row[] rows;

    public Body() {

    }

    public Row[] getRows() {
        return this.rows;
    }

    public Body withRows(Row[] rows) {
        this.rows = rows;
        return this;
    }


}
