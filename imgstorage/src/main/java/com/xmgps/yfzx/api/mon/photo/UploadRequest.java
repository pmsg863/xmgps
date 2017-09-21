package com.xmgps.yfzx.api.mon.photo;

import com.xmgps.yfzx.api.RequestBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangwb on 7/20/2016.
 */
public class UploadRequest extends RequestBase{
    String fileName;
    String hexFileArray;

    Map<String,String> metaInfo = new HashMap<>();
}
