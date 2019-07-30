package com.discernible.handler;

import java.util.Queue;

import com.discernible.message.IP;
import com.discernible.message.Socket;
import com.discernible.util.ByteUtils;

public class SocketFieldHandler implements FieldHandler<Socket> {

  private IpFieldHandler ipHandler = new IpFieldHandler();
  private UnsignedIntegerFieldHandler portHandler = new UnsignedIntegerFieldHandler();

  public Socket decode(Queue<Byte> messageBytes) {

    ByteUtils.getFieldLength(messageBytes); // We don't need the field length, but we need to take the byte off the queue.

    IP ip = ipHandler.decode(messageBytes);
    Integer port = portHandler.decode(messageBytes);

    return new Socket(ip, port);
  }

  @Override
  public void encode(Socket socket, ByteOutputStream out) {
    out.write(0x06); // Message length
    ipHandler.encode(socket.getIp(), out);
    portHandler.encode(socket.getPort(), out);
  }

}
