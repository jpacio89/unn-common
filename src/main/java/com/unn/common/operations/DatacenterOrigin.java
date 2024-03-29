package com.unn.common.operations;

public class DatacenterOrigin {
    String protocol;
    String host;
    int port;

    public DatacenterOrigin() {

    }

    public String getProtocol() {
        return protocol;
    }

    public DatacenterOrigin withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getHost() {
        return host;
    }

    public DatacenterOrigin withHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public DatacenterOrigin withPort(int port) {
        this.port = port;
        return this;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
