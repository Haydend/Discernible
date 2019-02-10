package com.discernible.message.header.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.message.ByteField;
import com.discernible.message.Field;
import com.discernible.message.SocketField;
import com.discernible.message.header.options.extension.OptionExtension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsHeader implements Field {

  private ByteField mobileId;
  private MobileIdTypeField mobileIdType;
  private ByteField authentication;
  private ByteField routing;
  private ForwardingField forwarding;
  private SocketField responseRedirection;
  private OptionExtension optionExtension;

  public static OptionsHeader decode(Queue<Byte> messageBytes) {

    byte flagByte = messageBytes.poll();

    ByteField mobileId = null;
    if ((flagByte & 0b00000001) == 0b00000001) {
      mobileId = ByteField.decode(messageBytes);
    }

    MobileIdTypeField mobileIdTypeField = null;
    if ((flagByte & 0b00000010) == 0b00000010) {
      mobileIdTypeField = MobileIdTypeField.decode(messageBytes);
    }

    ByteField authentication = null;
    if ((flagByte & 0b00000100) == 0b00000100) {
      authentication = ByteField.decode(messageBytes);
    }

    ByteField routing = null;
    if ((flagByte & 0b00001000) == 0b00001000) {
      routing = ByteField.decode(messageBytes);
    }

    ForwardingField forwarding = null;
    if ((flagByte & 0b00010000) == 0b00010000) {
      forwarding = ForwardingField.decode(messageBytes);
    }

    SocketField responseRedirection = null;
    if ((flagByte & 0b00100000) == 0b00100000) {
      responseRedirection = SocketField.decode(messageBytes);
    }

    OptionExtension optionExtension = null;
    if ((flagByte & 0b01000000) == 0b01000000) {
      optionExtension = OptionExtension.decode(messageBytes);
    }

    return new OptionsHeader(mobileId, mobileIdTypeField, authentication, routing, forwarding, responseRedirection, optionExtension);
  }

  @Override
  public byte[] encode() {

    List<Byte> messageBytes = new ArrayList<>();
    messageBytes.add(buildFlagByte());

    if (Objects.nonNull(mobileId)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(mobileId.encode())));
    }

    if (Objects.nonNull(mobileIdType)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(mobileIdType.encode())));
    }

    if (Objects.nonNull(authentication)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(authentication.encode())));
    }

    if (Objects.nonNull(routing)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(routing.encode())));
    }

    if (Objects.nonNull(forwarding)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(forwarding.encode())));
    }

    if (Objects.nonNull(responseRedirection)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(responseRedirection.encode())));
    }

    if (Objects.nonNull(optionExtension)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(optionExtension.encode())));
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private byte buildFlagByte() {

    StringBuilder bits = new StringBuilder("10000000");

    if (Objects.nonNull(mobileId)) {
      bits.replace(7, 8, "1");
    }

    if (Objects.nonNull(mobileIdType)) {
      bits.replace(6, 7, "1");
    }

    if (Objects.nonNull(authentication)) {
      bits.replace(5, 6, "1");
    }

    if (Objects.nonNull(routing)) {
      bits.replace(4, 5, "1");
    }

    if (Objects.nonNull(forwarding)) {
      bits.replace(3, 4, "1");
    }

    if (Objects.nonNull(responseRedirection)) {
      bits.replace(2, 3, "1");
    }

    if (Objects.nonNull(optionExtension)) {
      bits.replace(1, 2, "1");
    }

    return (byte) Integer.parseInt(bits.toString(), 2);
  }

}
