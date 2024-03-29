package com.unn.common.dataset;

import java.io.Serializable;
import java.util.Arrays;

public class Header implements Serializable {
    String[] names;

    public Header() {

    }

    public String[] getNames() {
        return names;
    }

    public Header withNames(String[] names) {
        this.names = names;
        return this;
    }

    public Header withNames(String[] names, boolean copy) {
        if (!copy) {
            return this.withNames(names);
        }
        return this.withNames(Arrays.copyOf(names, names.length));
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
