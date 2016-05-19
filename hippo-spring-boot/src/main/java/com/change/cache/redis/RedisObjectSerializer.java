package com.change.cache.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午5:46
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {


    static final byte[] EMPTY_ARRAY = new byte[0];
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (null == object) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(object);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return EMPTY_ARRAY;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }
    }
}
