package com.wismap.springsecuritydemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseController {

    public static final Logger logger = LogManager.getLogger(BaseController.class.getName());

    /**
     * @return
     */
    public String renderError() {
        JSONObject object = new JSONObject();
        object.put("data", "");
        object.put("code", -1);
        object.put("msg", "操作失败！");
        return object.toJSONString();
    }
    /**
     * ajax失败
     *
     * @param msg 失败的消息
     * @return {Object}
     */
    public String renderError(String msg) {
        JSONObject object = new JSONObject();
        object.put("data", "");
        object.put("code", -1);
        object.put("msg", msg);
        return object.toJSONString();
    }

    /**
     * @param msg
     * @param code
     * @return
     */
    public String renderError(Object msg,int code) {
        JSONObject object = new JSONObject();
        object.put("data", "");
        object.put("code", code);
        object.put("msg", msg);
        return object.toJSONString();
    }

    /**
     * ajax成功
     *
     * @return {Object}
     */
    public String renderSuccess() {
        JSONObject object = new JSONObject();
        object.put("data", "");
        object.put("code", 200);
        object.put("msg", "操作成功！");
        return object.toJSONString();
    }

    /**
     * @param data
     * @return
     */
    public String renderSuccess(Object data) {
        JSONObject object = new JSONObject();
        object.put("data", data);
        object.put("code", 200);
        object.put("msg", "操作成功！");
        return object.toJSONString();
    }
}
