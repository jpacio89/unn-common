package com.unn.common.server.services;

import com.unn.common.dataset.DatasetDescriptor;
import com.unn.common.transformers.TransformerDescriptor;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DatacenterService {
    @Headers({"Accept:text/plain"})
    @POST("/agent/miner/dataset/body")
    Call<String> fetchDataset(@Body HashMap<String, List<String>> options);

    @GET("/dataset/features/random/layer/{layer}")
    Call<HashMap<String, List<String>>> getRandomFeatures(
        @Path("layer") int layer,
        @Query("whitelist") List<String> whitelist
    );

    @POST("/dataset/register")
    Call<String> registerAgent(@Body DatasetDescriptor descriptor);

    @POST("/dataset/{namespace}/store/raw")
    Call<String> storeDataset(
        @Path("namespace") String namespace,
        @Body RequestBody body
    );

    @POST("/brain/reset")
    Call<String> resetBrain();

    @GET("/dataset/{namespace}/body/unpredicted")
    Call<String> fetchUnpredicted(
        @Path("namespace") String namespace
    );

    @GET("/dataset/namespaces")
    Call<ArrayList<DatasetDescriptor>> getNamespaces();

    @GET("/dataset/{namespace}/data")
    Call<String> getNamespaceData(
        @Path("namespace") String namespace,
        @Query("fromPrimer") Integer fromPrimer
    );

    @GET("/brain/transformers")
    Call<ArrayList<TransformerDescriptor>> getTransformers();
}
