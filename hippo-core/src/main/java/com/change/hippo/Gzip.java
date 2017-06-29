package com.change.hippo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * User: change.long
 * Date: 16/6/26
 * Time: 下午10:34
 */
public class Gzip {

    public static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gzip = null;
        try {
            bos = new ByteArrayOutputStream(data.length());
            gzip = new GZIPOutputStream(bos);
            gzip.write(data.getBytes());
            gzip.finish();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != gzip) {
                gzip.close();
            }
            if (null != bos) {
                bos.close();
            }
        }
        return null;
    }

    public static String decompress(byte[] compressed) throws IOException {
        BufferedReader br = null;
        GZIPInputStream gis = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(compressed);
            gis = new GZIPInputStream(bis);
            br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                br.close();
            }
            if (null != gis) {
                gis.close();
            }
            if (null != bis) {
                bis.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String certBuf = "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";

        System.out.println(certBuf.getBytes().length);

        byte[] compressByteArray = Gzip.compress(certBuf);

        System.out.println(compressByteArray.length);

        String deCompressString = Gzip.decompress(compressByteArray);
        System.out.println(deCompressString);

        System.out.println(deCompressString.equals(certBuf));
    }
}
