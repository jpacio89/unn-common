package com.unn.common.transformers;

public class TransformerDescriptor {
    private String name;
    private String code;

    public TransformerDescriptor() { }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TransformerDescriptor withCode(String code) {
        this.setCode(code);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransformerDescriptor withName(String name) {
        this.setName(name);
        return this;
    }
}
