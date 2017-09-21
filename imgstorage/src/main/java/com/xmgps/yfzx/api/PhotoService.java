package com.xmgps.yfzx.api;


import com.xmgps.yfzx.api.mon.photo.PhotoRequest;
import com.xmgps.yfzx.api.mon.photo.PhotoResponse;
import com.xmgps.yfzx.api.mon.photo.UploadRequest;
import com.xmgps.yfzx.api.mon.photo.UploadResponse;
import com.xmgps.yfzx.fastdfs.FastdfsClient;
import com.xmgps.yfzx.fastdfs.FastdfsClientFactory;
import com.xmgps.yfzx.redis.RedisClient;
import com.xmgps.yfzx.redis.RedisClientFactory;
import org.apache.commons.configuration.ConfigurationException;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangwb on 2015/7/13.
 */
@Path("/photo")
@Produces("application/json")
@Consumes("application/json")
public class PhotoService {

    RedisClient rdsClient;
    FastdfsClient dfsClient;

    PhotoService() {
        try {
            dfsClient = FastdfsClientFactory.getFastdfsClient("FastdfsClient.properties");
            rdsClient = RedisClientFactory.getFastdfsClient("RedisClient.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("upload")
    public UploadResponse upoload(UploadRequest request) {
        UploadResponse response = new UploadResponse();

        //TODO
        return response;
    }

    @POST
    @Path("query")
    public PhotoResponse testService(PhotoRequest request) {
        PhotoResponse response = new PhotoResponse();
        //TODO
        return response;
    }

}
