/**
 * 
 */
package com.mazing.commons.server.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <br>
 * 
 * JFan 2015年1月7日 下午2:57:26
 */
@Ignore
public class CompressFilterTest {

    private String url = "http://localhost:8080/xapi/i/compress?abc=123&name=JavaFan&qq=";
    private String body = "name=JFan&qq=47627906&def";

    @Test
    public void gzip() throws Exception {
        HttpPost post = new HttpPost(url);

        // 用gzip方式压缩请求体并赋给request
        ByteArrayInputStream bis = new ByteArrayInputStream(body.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        for (int c = bis.read(); c != -1; c = bis.read()) {
            gos.write(c);
        }
        gos.close();

        InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream(bos.toByteArray()));
        post.setEntity(entity);
        post.addHeader("Content-Encoding", "gzip");

        String text = read(post);
        System.out.println(text);
    }

    @Test
    public void deflate() throws Exception {
        HttpPost post = new HttpPost(url);

        // 用deflate方式压缩请求体并赋给request
        ByteArrayInputStream bis = new ByteArrayInputStream(body.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(bos);
        for (int c = bis.read(); c != -1; c = bis.read()) {
            dos.write(c);
        }
        dos.close();
        InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream(bos.toByteArray()));
        post.setEntity(entity);
        post.addHeader("Content-Encoding", "deflate");

        String text = read(post);
        System.out.println(text);
    }

    private String read(HttpRequestBase method) throws Exception {
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient client = HttpClients.custom().disableContentCompression().build();
            response = client.execute(method);

            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode());

            HttpEntity res = response.getEntity();
            String text = EntityUtils.toString(res);
            return text;
        } catch (Exception e) {
            throw e;
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

}
