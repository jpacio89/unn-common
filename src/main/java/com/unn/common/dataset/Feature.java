package com.unn.common.dataset;

public class Feature {
    String namespace;
    String column;

    public Feature(String payload) {
        init(payload);
    }

    public void init(String payload) {
        String[] parts = payload.split("@");
        this.namespace = parts[1];
        this.column = parts[0];
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
