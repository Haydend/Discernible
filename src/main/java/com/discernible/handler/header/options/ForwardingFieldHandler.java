package com.discernible.handler.header.options;

import com.discernible.handler.FieldHandler;
import com.discernible.handler.IpFieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.message.IP;
import com.discernible.message.header.options.ForwardingField;
import com.discernible.message.header.options.ForwardingOperationType;
import com.discernible.message.header.options.Protocol;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.model.JBBPFieldArrayUByte;
import com.igormaznitsa.jbbp.model.JBBPFieldStruct;
import com.igormaznitsa.jbbp.model.JBBPFieldUByte;
import com.igormaznitsa.jbbp.model.JBBPFieldUShort;

public class ForwardingFieldHandler implements FieldHandler<ForwardingField> {

  private static final JBBPParser FIELD =
      JBBPParser.prepare("ubyte fieldLength;ubyte[4] ip;ushort port;ubyte protocol;ubyte forwardingOperationType;");

  private final IpFieldHandler ipFieldHandler = new IpFieldHandler();
  private final UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private final ProtocolFieldHandler protocolFieldHandler = new ProtocolFieldHandler();
  private final ForwardingOperationTypeFieldHandler forwardingOperationTypeFieldHandler = new ForwardingOperationTypeFieldHandler();

  public ForwardingField decode(JBBPBitInputStream messageBytes) {

    JBBPFieldStruct field = ByteUtils.parse(messageBytes, FIELD);

    JBBPFieldArrayUByte ipBytes = field.findFieldForNameAndType("ip", JBBPFieldArrayUByte.class);
    IP ipField = new IP(ipBytes.getAsInt(0), ipBytes.getAsInt(1), ipBytes.getAsInt(2), ipBytes.getAsInt(3));

    Integer portField = field.findFieldForNameAndType("port", JBBPFieldUShort.class).getAsInt();

    byte protocolByte = (byte) field.findFieldForNameAndType("protocol", JBBPFieldUByte.class).getAsInt();
    Protocol forwardingProtocol = decodeProtocol(protocolByte);

    byte forwardingOperationTypeByte = (byte) field.findFieldForNameAndType("forwardingOperationType", JBBPFieldUByte.class).getAsInt();
    ForwardingOperationType forwardingOperationType = decodeForwardingOperationType(forwardingOperationTypeByte);

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

  private Protocol decodeProtocol(byte byteToMatch) {
    switch (byteToMatch) {
      case 0x06:
        return Protocol.TCP;
      case 0x11:
        return Protocol.UDP;
      default:
        throw new IllegalArgumentException(String.format("No Protocol found for byte: %s", byteToMatch));
    }
  }

  private ForwardingOperationType decodeForwardingOperationType(byte byteToMatch) {
    switch (byteToMatch) {
      case 0x00:
        return ForwardingOperationType.FORWARD;
      case 0x01:
        return ForwardingOperationType.PROXY;
      case 0x02:
        return ForwardingOperationType.FORWARD_WITH_LOOKUP;
      default:
        throw new IllegalArgumentException(String.format("No Forwarding Operation Type found for byte: %s", byteToMatch));
    }
  }

}
