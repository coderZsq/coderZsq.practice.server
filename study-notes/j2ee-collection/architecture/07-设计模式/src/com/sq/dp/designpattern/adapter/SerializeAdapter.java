package com.sq.dp.designpattern.adapter;

/**
 * 序列化适配器
 */
public class SerializeAdapter extends ProtoBuf implements Serializer {
    // private ProtoBuf protoBuf;
    //
    // public SerializeAdapter(ProtoBuf protoBuf) {
    //     this.protoBuf = protoBuf;
    // }

    @Override
    public byte[] serialize(String msg) {
        return super.serialize(msg);
    }

    @Override
    public String deserialize(byte[] bytes) {
        return super.deserialize(bytes);
    }
}
