package com.unn.common.mining;

import com.unn.common.operations.AgentRole;

import java.util.HashMap;

public class MiningReport {
	AgentRole role;
	public HashMap<String, ConfusionMatrix> confusionMatrixes;

	public MiningReport() { }

	public MiningReport(AgentRole role) {
		this.confusionMatrixes = new HashMap<>();
		this.role = role;
	}

	public void setConfusionMatrixes(HashMap<String, ConfusionMatrix> confusionMatrixes) {
		this.confusionMatrixes = confusionMatrixes;
	}

	public HashMap<String, ConfusionMatrix> getConfusionMatrixes() {
		return confusionMatrixes;
	}

	public AgentRole getRole() {
		return role;
	}

	public void setRole(AgentRole role) {
		this.role = role;
	}
}
