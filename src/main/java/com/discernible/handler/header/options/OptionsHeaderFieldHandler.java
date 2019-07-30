package com.discernible.handler.header.options;

import java.util.Objects;
import java.util.Queue;

import com.discernible.handler.ByteFieldHandler;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.SocketFieldHandler;
import com.discernible.handler.header.options.extension.OptionExtensionFieldHandler;
import com.discernible.message.Socket;
import com.discernible.message.header.options.ForwardingField;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.OptionsHeader;
import com.discernible.message.header.options.extension.OptionExtension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsHeaderFieldHandler implements FieldHandler<OptionsHeader> {

  private final ByteFieldHandler byteFieldHandler = new ByteFieldHandler();
  private final MobileIdTypeFieldHandler mobileIdTypeFieldHandler = new MobileIdTypeFieldHandler();
  private final ForwardingFieldHandler forwardingFieldHandler = new ForwardingFieldHandler();
  private final SocketFieldHandler socketFieldHandler = new SocketFieldHandler();
  private final OptionExtensionFieldHandler optionExtensionFieldHandler = new OptionExtensionFieldHandler();

  @Override
  public OptionsHeader decode(Queue<Byte> messageBytes) {

    byte flagByte = messageBytes.poll();

    byte[] mobileId = null;
    if ((flagByte & 0b00000001) == 0b00000001) {
      mobileId = byteFieldHandler.decode(messageBytes);
    }

    MobileIdTypeField mobileIdTypeField = null;
    if ((flagByte & 0b00000010) == 0b00000010) {
      mobileIdTypeField = mobileIdTypeFieldHandler.decode(messageBytes);
    }

    byte[] authentication = null;
    if ((flagByte & 0b00000100) == 0b00000100) {
      authentication = byteFieldHandler.decode(messageBytes);
    }

    byte[] routing = null;
    if ((flagByte & 0b00001000) == 0b00001000) {
      routing = byteFieldHandler.decode(messageBytes);
    }

    ForwardingField forwarding = null;
    if ((flagByte & 0b00010000) == 0b00010000) {
      forwarding = forwardingFieldHandler.decode(messageBytes);
    }

    Socket responseRedirection = null;
    if ((flagByte & 0b00100000) == 0b00100000) {
      responseRedirection = socketFieldHandler.decode(messageBytes);
    }

    OptionExtension optionExtension = null;
    if ((flagByte & 0b01000000) == 0b01000000) {
      optionExtension = optionExtensionFieldHandler.decode(messageBytes);
    }

    return new OptionsHeader(mobileId, mobileIdTypeField, authentication, routing, forwarding, responseRedirection, optionExtension);
  }

  @Override
  public void encode(OptionsHeader optionsHeader, ByteOutputStream out) {
    out.write(buildFlagByte(optionsHeader));

    if (Objects.nonNull(optionsHeader.getMobileId())) {
      byteFieldHandler.encode(optionsHeader.getMobileId(), out);
    }

    if (Objects.nonNull(optionsHeader.getMobileIdType())) {
      mobileIdTypeFieldHandler.encode(optionsHeader.getMobileIdType(), out);
    }

    if (Objects.nonNull(optionsHeader.getAuthentication())) {
      byteFieldHandler.encode(optionsHeader.getAuthentication(), out);
    }

    if (Objects.nonNull(optionsHeader.getRouting())) {
      byteFieldHandler.encode(optionsHeader.getRouting(), out);
    }

    if (Objects.nonNull(optionsHeader.getForwarding())) {
      forwardingFieldHandler.encode(optionsHeader.getForwarding(), out);
    }

    if (Objects.nonNull(optionsHeader.getResponseRedirection())) {
      socketFieldHandler.encode(optionsHeader.getResponseRedirection(), out);
    }

    if (Objects.nonNull(optionsHeader.getOptionExtension())) {
      optionExtensionFieldHandler.encode(optionsHeader.getOptionExtension(), out);
    }
  }

  private byte buildFlagByte(OptionsHeader optionsHeader) {

    StringBuilder bits = new StringBuilder("10000000");

    if (Objects.nonNull(optionsHeader.getMobileId())) {
      bits.replace(7, 8, "1");
    }

    if (Objects.nonNull(optionsHeader.getMobileIdType())) {
      bits.replace(6, 7, "1");
    }

    if (Objects.nonNull(optionsHeader.getAuthentication())) {
      bits.replace(5, 6, "1");
    }

    if (Objects.nonNull(optionsHeader.getRouting())) {
      bits.replace(4, 5, "1");
    }

    if (Objects.nonNull(optionsHeader.getForwarding())) {
      bits.replace(3, 4, "1");
    }

    if (Objects.nonNull(optionsHeader.getResponseRedirection())) {
      bits.replace(2, 3, "1");
    }

    if (Objects.nonNull(optionsHeader.getOptionExtension())) {
      bits.replace(1, 2, "1");
    }

    return (byte) Integer.parseInt(bits.toString(), 2);
  }

}
