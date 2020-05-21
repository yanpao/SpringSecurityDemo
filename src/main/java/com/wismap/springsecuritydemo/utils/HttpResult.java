package com.wismap.springsecuritydemo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class HttpResult <T> implements Serializable  {

    private Boolean success;
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private HttpResult() {
        this.code = 200;
        this.success = true;
    }

    private HttpResult(T obj) {
        this.code = 200;
        this.data = obj;
        this.success = true;
    }

    private HttpResult(ResultCodeEnum resultCode) {
        this.success = false;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public static<T> HttpResult<T> success(){
        return new HttpResult();
    }

    public static<T> HttpResult<T> success(T data){
        return new HttpResult<T>(data);
    }

    public static<T> HttpResult<T> failure(ResultCodeEnum resultCode){
        return  new HttpResult<T>(resultCode);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public HttpResult setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }

}
