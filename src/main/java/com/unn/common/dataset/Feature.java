package com.unn.common.dataset;

public class Feature {
    String namespace;
    String column;

    public Feature() { }

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

    public Feature withNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Feature withColumn(String column) {
        this.column = column;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s@%s", this.column, this.namespace);
    }
}
