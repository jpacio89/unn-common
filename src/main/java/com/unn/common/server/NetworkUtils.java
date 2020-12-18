package com.unn.common.server;

import com.unn.common.dataset.Dataset;
import com.unn.common.dataset.DatasetDescriptor;
import com.unn.common.dataset.Header;
import com.unn.common.server.services.DatacenterService;
import com.unn.common.utils.CSVHelper;
import com.unn.common.utils.SparkUtils;
import com.unn.common.utils.Utils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import java.io.IOException;

public class NetworkUtils {

    public static void registerAgent(String namespace, String csv) {
        String[] rows = csv.split("\n");
        Header header = new Header()
                .withNames(rows[0].split(","));
        DatasetDescriptor descriptor = new DatasetDescriptor()
                .withNamespace(namespace)
                .withHeader(header);
        NetworkUtils.registerAgent(descriptor);
    }

    public static void registerAgent(DatasetDescriptor descriptor) {
        try {
            DatacenterService service = Utils.getDatacenter();
            service.registerAgent(descriptor).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadDataset(String namespace, String csv) {
        try {
            DatacenterService service = Utils.getDatacenter();
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), csv);
            Call<String> call = service.storeDataset(namespace, body);
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadDataset(Dataset dataset) {
        String csv = new CSVHelper().toString(dataset);
        uploadDataset(dataset.getDescriptor().getNamespace(), csv);
    }
}
