package com.xmgps.yfzx;


import com.xmgps.yfzx.fastdfs.FastdfsClient;
import com.xmgps.yfzx.fastdfs.FastdfsClientFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FastdfsClientTest {
	

	public void testFastdfsClient() throws Exception {
		FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient("FastdfsClient.properties");
		URL fileUrl = this.getClass().getResource("/Koala.jpg");
		File file = new File(fileUrl.getPath());
		String fileId = fastdfsClient.upload(file);
		System.out.println("fileId:"+fileId);
		assertNotNull(fileId);
		String url = fastdfsClient.getUrl(fileId);
		assertNotNull(url);
		System.out.println("url:"+url);
		Map<String,String> meta = new HashMap<String, String>();
		meta.put("fileName", file.getName());
		boolean result = fastdfsClient.setMeta(fileId, meta);
		assertTrue(result);
		Map<String,String> meta2 = fastdfsClient.getMeta(fileId);
		assertNotNull(meta2);
		System.out.println(meta2.get("fileName"));
		result = fastdfsClient.delete(fileId);
		assertTrue(result);
		fastdfsClient.close();
	}

	public void testFastdfsArrayClient() throws Exception {
		FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient("FastdfsClient.properties");
		String fileId = fastdfsClient.upload(new byte[]{0x5B,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11
				,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11
				,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11
				,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x5D
		},"arraytest.file");
		System.out.println("fileId:"+fileId);
		assertNotNull(fileId);
		String url = fastdfsClient.getUrl(fileId);
		assertNotNull(url);
		System.out.println("url:"+url);
		boolean result = fastdfsClient.delete(fileId);
		assertTrue(result);
		fastdfsClient.close();
	}

	public static void main(String[] args) {
		FastdfsClientTest test = new FastdfsClientTest();
		try {
			test.testFastdfsArrayClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
