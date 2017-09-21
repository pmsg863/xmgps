package com.xmgps.yfzx.fastdfs.client;


import com.xmgps.yfzx.fastdfs.data.Result;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface StorageClient {
	
	public Result<String> upload(File file, String fileName, byte storePathIndex) throws IOException;
	public Result<String> upload(byte[] fileArray, String fileName, byte storePathIndex) throws Exception;
	public Result<Boolean> delete(String group, String fileName) throws IOException;
	public Result<Boolean> setMeta(String group, String fileName, Map<String, String> meta) throws IOException;
	public Result<Map<String,String>> getMeta(String group, String fileName) throws IOException;
	public void close() throws IOException;
	
}
