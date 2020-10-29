package com.unn.common.mining;

import com.unn.common.operations.AgentRole;

import java.util.HashMap;

public class MiningReport {
	AgentRole role;
	public HashMap<String, ConfusionMatrix> confusionMatrixes;

	public MiningReport(AgentRole role) {
		this.confusionMatrixes = new HashMap<>();
		this.role = role;
	}

	public HashMap<String, ConfusionMatrix> getConfusionMatrixes() {
		return confusionMatrixes;
	}

}
