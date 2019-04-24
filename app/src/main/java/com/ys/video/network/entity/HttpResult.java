package com.ys.video.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/5 15:23
 * <p/>
 * Description: 网络返回消息，最外层解析实体
 */
@SuppressWarnings("unused")
public class HttpResult<T> {
    /**
     * 错误码
     */
    @SerializedName(Params.RES_CODE)
    private String responseCode;
    /**
     * 错误信息
     */
    @SerializedName(Params.RES_MSG)
    private String responseMsg;
    /**
     * 消息响应的主体
     */
    @SerializedName(Params.RES_DATA)
    private T info;



    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", info=" + info +
                '}';
    }
}
