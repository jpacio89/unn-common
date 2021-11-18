package com.unn.common.operations;

import java.io.Serializable;

public class MiningTarget implements Serializable {
    String feature;

    public String getFeature() {
        return feature;
    }

    public MiningTarget withFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
