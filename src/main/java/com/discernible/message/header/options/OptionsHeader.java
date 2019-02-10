package com.discernible.message.header.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.message.ByteField;
import com.discernible.message.Field;
import com.discernible.message.SocketField;
import com.discernible.message.header.options.extension.OptionExtension;

import lombok.Data;

@Data
public class OptionsHeader implements Field {

  private ByteField mobileId;
  private MobileIdTypeField mobileIdType;
  private ByteField authentication;
  private ByteField routing;
  private ForwardingField forwarding;
  private SocketField responseRedirection;
  private OptionExtension optionExtension;

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
