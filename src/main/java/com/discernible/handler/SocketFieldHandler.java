package com.discernible.handler;

import com.discernible.message.IP;
import com.discernible.message.Socket;

public class SocketFieldHandler implements FieldHandler<Socket> {

  private IpFieldHandler ipHandler = new IpFieldHandler();
  private UnsignedIntegerFieldHandler portHandler = new UnsignedIntegerFieldHandler();

  public Socket decode(ByteInputStream in) {

    in.read(); // We don't need the field length, but we need to take the byte off the queue.

    IP ip = ipHandler.decode(in);
    Integer port = portHandler.decode(in);

    return new Socket(ip, port);
  }

  @Override
  public void encode(Socket socket, ByteOutputStream out) {
    out.write(0x06); // Message length
    ipHandler.encode(socket.getIp(), out);
    portHandler.encode(socket.getPort(), out);
  }

}
