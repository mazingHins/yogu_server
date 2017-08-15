package com.yogu.commons.utils;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;

/**
 * 包装 http 响应 <br>
 * 
 * JFan 2015年2月2日 下午2:36:42
 */
public class HttpResult {

    private boolean reqOK;
    private String response;

    private int statusCode;
    private String reasonPhrase;
    private String protocolVersion;

    public HttpResult(StatusLine status, String response) {
        this(status.getStatusCode(), status.getReasonPhrase(), status.getProtocolVersion().toString(), response);
    }

    public HttpResult(int statusCode, String reasonPhrase, String protocolVersion, String response) {
        this.response = response;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.protocolVersion = protocolVersion;

        reqOK = HttpStatus.SC_OK == statusCode;
    }

    /**
     * @return statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return reasonPhrase
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * @return protocolVersion
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @return reqOK
     */
    public boolean isReqOK() {
        return reqOK;
    }

}
