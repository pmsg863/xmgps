package com.xmgps.yfzx.hwb.vertx;

import com.xmgps.yfzx.hwb.vertx.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Client extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(Client.class);
  }

  @Override
  public void start() throws Exception {
   vertx.createNetClient().connect(1234, "localhost", res -> {

      if (res.succeeded()) {
        NetSocket socket = res.result();
        socket.handler(buffer -> {
          System.out.println("Net client receiving: " + buffer.toString("UTF-8"));
        });
        socket.exceptionHandler(throwable -> {
          System.out.println("Net client Exception: " + throwable.getMessage());
        });
        //socket.drainHandler( drain -> System.out.println("Net client drain! "));
        socket.closeHandler(close -> System.out.println("Net client close! " + socket.localAddress().toString()));
        socket.endHandler(end -> System.out.println("Net client end! " + socket.localAddress().toString()));

        // Now send some data
        for (int i = 0; i < 10; i++) {
          String str = "hello " + i + "\n";
          System.out.println("Net client sending: " + str);
          socket.write(str);
        }
      } else {
        System.out.println("Failed to connect " + res.cause());
        try {
          this.start();
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    });

  }
}
