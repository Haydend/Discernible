package com.discernible.handler.header.options;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.IpFieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.message.IP;
import com.discernible.message.header.options.ForwardingField;
import com.discernible.message.header.options.ForwardingOperationType;
import com.discernible.message.header.options.Protocol;

public class ForwardingFieldHandler implements FieldHandler<ForwardingField> {

  private final IpFieldHandler ipFieldHandler = new IpFieldHandler();
  private final UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private final ProtocolFieldHandler protocolFieldHandler = new ProtocolFieldHandler();
  private final ForwardingOperationTypeFieldHandler forwardingOperationTypeFieldHandler = new ForwardingOperationTypeFieldHandler();

  public ForwardingField decode(ByteInputStream in) {

    in.read(); // Throw away field length.

    IP ipField = ipFieldHandler.decode(in);
    Integer portField = unsignedIntegerFieldHandler.decode(in);
    Protocol forwardingProtocol = protocolFieldHandler.decode(in);
    ForwardingOperationType forwardingOperationType = forwardingOperationTypeFieldHandler.decode(in);

    return new ForwardingField(ipField, portField, forwardingProtocol, forwardingOperationType);
  }

  @Override
  public void encode(ForwardingField forwardingField, ByteOutputStream out) {
    out.write(0x08); // Field Length;

    ipFieldHandler.encode(forwardingField.getIp(), out);
    unsignedIntegerFieldHandler.encode(forwardingField.getPort(), out);
    protocolFieldHandler.encode(forwardingField.getForwardingProtocol(), out);
    forwardingOperationTypeFieldHandler.encode(forwardingField.getForwardingOperationType(), out);
  }

}
