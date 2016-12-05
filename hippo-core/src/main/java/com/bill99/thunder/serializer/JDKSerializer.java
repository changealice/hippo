package com.bill99.thunder.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author yelong.hou
 * @描述:
 */
public final class JDKSerializer {
    public final static byte[] serialize(Serializable obj) {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = null;
        byte[] bytes = null;
        try {
            objectOut = new ObjectOutputStream(output);
            objectOut.writeObject(obj);
            bytes = output.toByteArray();
        } catch (IOException e) {
        } finally {
            try {
                if (objectOut != null) {
                    objectOut.close();
                }
            } catch (Exception e) {
            } finally {
                objectOut = null;
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
            } finally {
                output = null;
            }

        }
        return bytes;
    }

    @SuppressWarnings("unchecked")
    public final static <T> T deserialize(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        ObjectInputStream objectIn = null;
        Object object = null;
        try {
            objectIn = new ObjectInputStream(input);
            object = objectIn.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (Exception e) {
            } finally {
                objectIn = null;
            }
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
            } finally {
                input = null;
            }
        }
        return (T) object;
    }
}

