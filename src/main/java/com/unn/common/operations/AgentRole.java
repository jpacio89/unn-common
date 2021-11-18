package com.unn.common.operations;

import java.io.Serializable;
import java.util.Objects;

public class AgentRole implements Serializable {
    String id;
    MiningTarget target;
    Agent agent;
    int layer;
    boolean isInSync;

    public AgentRole() { }

    public AgentRole(Agent _agent, int _layer, boolean _isInSync, MiningTarget _target) {
        this.agent = _agent;
        this.layer = _layer;
        this.isInSync = _isInSync;
        this.target = _target;
    }

    public MiningTarget getTarget() {
        return target;
    }

    public AgentRole withTarget(MiningTarget _target) {
        this.target = _target;
        return this;
    }

    public Agent getAgent() {
        return agent;
    }

    public AgentRole withAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public int getLayer() {
        return layer;
    }

    public AgentRole withLayer(int layer) {
        this.layer = layer;
        return this;
    }

    public boolean isInSync() {
        return isInSync;
    }

    public AgentRole withSync(boolean inSync) {
        isInSync = inSync;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentRole agentRole = (AgentRole) o;
        return layer == agentRole.layer &&
                agent.equals(agentRole.agent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agent, layer);
    }
}
