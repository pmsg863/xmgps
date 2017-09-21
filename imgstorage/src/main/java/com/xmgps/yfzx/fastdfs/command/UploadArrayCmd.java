package com.xmgps.yfzx.fastdfs.command;


import com.xmgps.yfzx.fastdfs.data.Result;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class UploadArrayCmd extends AbstractCmd<String> {

	private byte[] fileArray;

	@Override
	public Result<String> exec(Socket socket) throws IOException {
		InputStream is = new ByteArrayInputStream(fileArray);
		request(socket.getOutputStream(), is);
		Response response = response(socket.getInputStream());
		if(response.isSuccess()){
			byte[] data = response.getData();
			String group = new String(data, 0,	FDFS_GROUP_NAME_MAX_LEN).trim();
			String remoteFileName = new String(data,FDFS_GROUP_NAME_MAX_LEN, data.length - FDFS_GROUP_NAME_MAX_LEN);
			Result<String> result = new Result<>(response.getCode());
			result.setData(group + "/" + remoteFileName);
			return result;
		}else{
			Result<String> result = new Result<>(response.getCode());
			result.setMessage("Error");
			return result;
		}
	}

	public UploadArrayCmd(byte[] fileArray, String fileName, byte storePathIndex){
		super();
		this.fileArray = fileArray;
		this.requestCmd = STORAGE_PROTO_CMD_UPLOAD_FILE;
		this.body2Len = fileArray.length;
		this.responseCmd = STORAGE_PROTO_CMD_RESP;
		this.responseSize = -1;
		this.body1 = new byte[15];
		Arrays.fill(body1, (byte) 0);
		this.body1[0] = storePathIndex;
		byte[] fileSizeByte = long2buff(fileArray.length);
		byte[] fileExtNameByte = getFileExtNameByte(fileName);
		int fileExtNameByteLen = fileExtNameByte.length;
		if(fileExtNameByteLen>FDFS_FILE_EXT_NAME_MAX_LEN){
			fileExtNameByteLen = FDFS_FILE_EXT_NAME_MAX_LEN;
		}
		System.arraycopy(fileSizeByte, 0, body1, 1, fileSizeByte.length);
		System.arraycopy(fileExtNameByte, 0, body1, fileSizeByte.length + 1, fileExtNameByteLen);
	}

	private byte[] getFileExtNameByte(String fileName) {
		String fileExtName = null;
		int nPos = fileName.lastIndexOf('.');
		if (nPos > 0 && fileName.length() - nPos <= FDFS_FILE_EXT_NAME_MAX_LEN + 1) {
			fileExtName = fileName.substring(nPos + 1);
		}
		if (fileExtName != null && fileExtName.length() > 0) {
			return fileExtName.getBytes(charset);
		}else{
			return new byte[0];
		}
	}
}
