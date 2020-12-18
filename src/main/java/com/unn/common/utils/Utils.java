package com.unn.common.utils;

import com.unn.common.globals.NetworkConfig;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import com.unn.common.server.services.DatacenterService;
import com.unn.common.server.services.MaestroService;

public class Utils {
    private static DatacenterService datacenter;
    private static MaestroService maestro;

    public static DatacenterService getDatacenter() {
        if (datacenter == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .baseUrl(String.format("%s://%s:%d",
                            NetworkConfig.DATACENTER_PROTOCOL,
                            NetworkConfig.DATACENTER_HOST,
                            NetworkConfig.DATACENTER_PORT));
            retrofit.addConverterFactory(ScalarsConverterFactory.create());
            retrofit.addConverterFactory(JacksonConverterFactory.create());
            datacenter = retrofit.build().create(DatacenterService.class);
        }
        return datacenter;
    }

    public static MaestroService getMaestro() {
        if (maestro == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(String.format("%s://%s:%d",
                            NetworkConfig.MAESTRO_PROTOCOL,
                            NetworkConfig.MAESTRO_HOST,
                            NetworkConfig.MAESTRO_PORT))
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            maestro = retrofit.create(MaestroService.class);
        }
        return maestro;
    }
}
