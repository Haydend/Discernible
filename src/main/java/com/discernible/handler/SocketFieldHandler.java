package com.discernible.handler;

import com.discernible.message.IP;
import com.discernible.message.Socket;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class SocketFieldHandler implements FieldHandler<Socket> {

  private IpFieldHandler ipHandler = new IpFieldHandler();
  private UnsignedIntegerFieldHandler portHandler = new UnsignedIntegerFieldHandler();

  public Socket decode(JBBPBitInputStream messageBytes) {

    ByteUtils.getFieldLength(messageBytes); // We don't need the field length, but we need to take the byte off the queue.

    IP ip = ipHandler.decode(messageBytes);
    Integer port = portHandler.decode(messageBytes);

    return new Socket(ip, port);
  }

  @Override
  public byte[] encode(Socket socket) {
    byte[] messageBytes = new byte[6];

    byte[] ipBytes = ipHandler.encode(socket.getIp());
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = portHandler.encode(socket.getPort());
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
