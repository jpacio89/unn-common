package com.unn.common.operations;

public class MiningTarget {
    String feature;
    String value;

    public String getFeature() {
        return feature;
    }

    public MiningTarget withFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public String getValue() {
        return value;
    }

    public MiningTarget withValue(String value) {
        this.value = value;
        return this;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
