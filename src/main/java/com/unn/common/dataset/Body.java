package com.unn.common.dataset;

public class Body {
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
