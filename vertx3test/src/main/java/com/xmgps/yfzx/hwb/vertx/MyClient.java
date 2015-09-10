package com.xmgps.yfzx.hwb.vertx;

import com.xmgps.yfzx.hwb.vertx.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.util.concurrent.*;

/**
 * Created by gps_hwb on 2015/9/8.
 */
public class MyClient extends AbstractVerticle {

    public NetSocket socket = null;

    public static void main(String[] args) {
        Runner.runExample(MyClient.class);
    }

    public boolean connect(String ip,int port){
        NetClient connect = vertx.createNetClient().connect(port, ip, handler -> {
            if (handler.succeeded()) {
                NetSocket socket = handler.result();
                this.socket = socket;

                socket.handler(buffer -> {
                    System.out.println("Net client receiving: " + buffer.toString("UTF-8"));
                });
                socket.exceptionHandler(throwable -> {
                    System.out.println("Net client Exception: " + throwable.getMessage());
                });
                //socket.drainHandler( drain -> System.out.println("Net client drain! "));
                socket.closeHandler(close -> System.out.println("Net client close! " + socket.localAddress().toString()));
                socket.endHandler(end -> {
                    System.out.println("Net client end! " + socket.localAddress().toString());
                    connect(ip, port);
                });
            } else {
                System.out.println("Failed to connect " + handler.cause());
                connect(ip, port);
            }

        });

        return true;
    }

    public boolean sendMessage(Buffer buffer){
        if(socket!=null)
            socket.write(buffer).endHandler(end -> System.out.println("Net client send end!"));
        else
            return false;
        //TODO
        return true;
    }

    public boolean keeplive(){
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                sendMessage(Buffer.buffer("keeplive"));
            }
        }, 10, 1, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void start() throws Exception {
        this.connect("127.0.0.1", 1234);
        this.sendMessage(Buffer.buffer("test123"));
        this.sendMessage(Buffer.buffer("test123"));
        this.sendMessage(Buffer.buffer("test123"));
        this.sendMessage(Buffer.buffer("test123"));
        this.keeplive();
    }
}
