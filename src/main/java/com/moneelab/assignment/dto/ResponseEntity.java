package com.moneelab.assignment.dto;

public class ResponseEntity {

    //Http Header
    private String charset = "utf-8";
    private String contentType = "json/application";
    private String location;

    //Http Status
    private int status;

    //Http Body
    private Object body;


    /**
     * Constructor
     */
    public ResponseEntity(int status) {
        this.status = status;
        this.body = null;
    }

    public ResponseEntity(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public ResponseEntity(int status, String location) {
        this.status = status;
        this.location = location;
    }

    /**
     * Getter
     */
    public String getCharset() {
        return charset;
    }

    public String getContentType() {
        return contentType;
    }

    public String getLocation() {
        return location;
    }

    public int getStatus() {
        return status;
    }

    public Object getBody() {
        return body;
    }
}
