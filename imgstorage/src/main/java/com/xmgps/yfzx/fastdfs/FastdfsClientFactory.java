package com.xmgps.yfzx.fastdfs;

import com.xmgps.yfzx.fastdfs.client.StorageClient;
import com.xmgps.yfzx.fastdfs.client.StorageClientFactory;
import com.xmgps.yfzx.fastdfs.client.TrackerClient;
import com.xmgps.yfzx.fastdfs.client.TrackerClientFactory;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import java.util.List;

public class FastdfsClientFactory {
	
	private static volatile FastdfsClient fastdfsClient;

	public static FastdfsClient getFastdfsClient(List<String> trackerAddrs){
		FastdfsClientConfig config = new FastdfsClientConfig();
		config.setTrackerAddrs(trackerAddrs);
		return getFastdfsClient(new FastdfsClientConfig());
	}
	
	public static FastdfsClient getFastdfsClient(String configFileName) throws ConfigurationException{
		FastdfsClientConfig config = new FastdfsClientConfig(configFileName);
		return getFastdfsClient(config);
	}
	
	public static FastdfsClient getFastdfsClient(FastdfsClientConfig config){
		if (fastdfsClient == null) {
			synchronized (FastdfsClient.class) {
				if (fastdfsClient == null) {
					int connectTimeout = config.getConnectTimeout();
					int networkTimeout = config.getNetworkTimeout();
					TrackerClientFactory trackerClientFactory = new TrackerClientFactory(connectTimeout, networkTimeout);
					StorageClientFactory storageClientFactory = new StorageClientFactory(connectTimeout, networkTimeout);
					GenericKeyedObjectPoolConfig trackerClientPoolConfig = config.getTrackerClientPoolConfig();
					GenericKeyedObjectPoolConfig storageClientPoolConfig = config.getStorageClientPoolConfig();
					GenericKeyedObjectPool<String,TrackerClient> trackerClientPool = new GenericKeyedObjectPool<>(trackerClientFactory, trackerClientPoolConfig);
					GenericKeyedObjectPool<String,StorageClient> storageClientPool = new GenericKeyedObjectPool<>(storageClientFactory, storageClientPoolConfig);
					List<String> trackerAddrs = config.getTrackerAddrs();
					fastdfsClient = new FastdfsClientImpl(trackerAddrs,trackerClientPool,storageClientPool);
				}
			}
		}
		return fastdfsClient;
	}
	
}
