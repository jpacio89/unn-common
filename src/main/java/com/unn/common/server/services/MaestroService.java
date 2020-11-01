package com.unn.common.server.services;

import com.unn.common.mining.MiningReport;
import com.unn.common.operations.Agent;
import com.unn.common.operations.AgentRole;
import com.unn.common.operations.DatacenterOrigin;
import com.unn.common.server.StandardResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MaestroService {
    @GET("/datacenter/origin")
    Call<DatacenterOrigin> findDatacenter();

    @POST("/agent/register")
    Call<StandardResponse> registerAgent(@Body Agent agent);

    @POST("/agent/heartbeat")
    Call<StandardResponse> heartbeat(@Body AgentRole me);

    @POST("/mining/report")
    Call<StandardResponse> sendMiningReport(@Body MiningReport report);

    @POST("/mining/deadend")
    Call<StandardResponse> deadEnd(AgentRole role);
}
