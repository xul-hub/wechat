package com.mybatis.bdtj.login.util;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.JavaType;

public abstract class GZipUtil {
    
    public static final byte[] unGzip(byte[] data) throws IOException {
        GZIPInputStream zin = new GZIPInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            data = new byte[10240];
            int len;
            while ((len = zin.read(data)) != -1) {
                out.write(data, 0, len);
            }
            return out.toByteArray();
        } finally {
            zin.close();
            out.close();
        }
    }
    
    public static final byte[] gzip(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream zout = new GZIPOutputStream(out);
        zout.write(data);
        zout.close();
        return out.toByteArray();
    }
    
    public static final byte[] gzipString(String str) throws IOException {
        byte[] data = str.getBytes("UTF-8");
        return gzip(data);
    }
    
    public static final String unGzipString(byte[] data) throws IOException {
        data = unGzip(data);
        return new String(data, "UTF-8");
    }
    
    public static final <T> T readObj(byte[] data, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
        GZIPInputStream zin = new GZIPInputStream(new ByteArrayInputStream(data));
        try {
            return JacksonUtil.readObj(zin, valueType);
        } finally {
            zin.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> T readObj(byte[] data, JavaType valueType) throws JsonParseException, JsonMappingException, IOException {
        GZIPInputStream zin = new GZIPInputStream(new ByteArrayInputStream(data));
        try {
            return (T)JacksonUtil.readObj(zin, valueType);
        } finally {
            zin.close();
        }
    }
}
