package com.xmgps.yfzx.fastdfs.command;

import com.xmgps.yfzx.fastdfs.data.Result;
import com.xmgps.yfzx.fastdfs.data.Result;

import java.io.IOException;
import java.net.Socket;

public class CloseCmd extends AbstractCmd<Boolean> {
	
	public CloseCmd() {
		super();
		this.requestCmd = FDFS_PROTO_CMD_QUIT;
	}

	@Override
	public Result<Boolean> exec(Socket socket) throws IOException {
		request(socket.getOutputStream());
		return new Result<Boolean>(SUCCESS_CODE,true);
	}


}
