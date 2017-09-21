package com.xmgps.yfzx.fastdfs.client;

import com.xmgps.yfzx.fastdfs.data.GroupInfo;
import com.xmgps.yfzx.fastdfs.data.Result;
import com.xmgps.yfzx.fastdfs.data.StorageInfo;
import com.xmgps.yfzx.fastdfs.data.UploadStorage;

import java.io.IOException;
import java.util.List;

public interface TrackerClient {

	public Result<UploadStorage> getUploadStorage() throws IOException;
	public Result<String> getUpdateStorageAddr(String group, String fileName) throws IOException;
	public Result<String> getDownloadStorageAddr(String group, String fileName) throws IOException;
	public Result<List<GroupInfo>> getGroupInfos() throws IOException;
	public Result<List<StorageInfo>> getStorageInfos(String group) throws IOException;
	public void close() throws IOException;
	
}
