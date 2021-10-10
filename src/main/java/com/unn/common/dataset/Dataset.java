package com.unn.common.dataset;

import java.io.Serializable;

public class Dataset implements Serializable {
    DatasetDescriptor descriptor;
    Body body;

    public Dataset() {

    }

    public DatasetDescriptor getDescriptor() {
        return descriptor;
    }

    public Body getBody() {
        return body;
    }

    public Dataset withDescriptor(DatasetDescriptor descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    public Dataset withBody(Body body) {
        this.body = body;
        return this;
    }

    public int size() {
        return this.body.getRows().length;
    }
}
