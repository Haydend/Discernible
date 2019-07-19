package com.discernible.handler.header.options;

import com.discernible.handler.FieldHandler;
import com.discernible.handler.IpFieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.message.IP;
import com.discernible.message.header.options.ForwardingField;
import com.discernible.message.header.options.ForwardingOperationType;
import com.discernible.message.header.options.Protocol;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class ForwardingFieldHandler implements FieldHandler<ForwardingField> {

  private final IpFieldHandler ipFieldHandler = new IpFieldHandler();
  private final UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private final ProtocolFieldHandler protocolFieldHandler = new ProtocolFieldHandler();
  private final ForwardingOperationTypeFieldHandler forwardingOperationTypeFieldHandler = new ForwardingOperationTypeFieldHandler();

  public ForwardingField decode(JBBPBitInputStream messageBytes) {

    ByteUtils.getByte(messageBytes); // Throw away field length.

    IP ipField = ipFieldHandler.decode(messageBytes);
    Integer portField = unsignedIntegerFieldHandler.decode(messageBytes);
    Protocol forwardingProtocol = protocolFieldHandler.decode(messageBytes);
    ForwardingOperationType forwardingOperationType = forwardingOperationTypeFieldHandler.decode(messageBytes);

    return new ForwardingField(ipField, portField, forwardingProtocol, forwardingOperationType);
  }

  @Override
  public byte[] encode(ForwardingField forwardingField) {

    byte[] messageBytes = new byte[8];

    byte[] ipBytes = ipFieldHandler.encode(forwardingField.getIp());
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = unsignedIntegerFieldHandler.encode(forwardingField.getPort());
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    messageBytes[6] = protocolFieldHandler.encode(forwardingField.getForwardingProtocol())[0];
    messageBytes[7] = forwardingOperationTypeFieldHandler.encode(forwardingField.getForwardingOperationType())[0];

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
