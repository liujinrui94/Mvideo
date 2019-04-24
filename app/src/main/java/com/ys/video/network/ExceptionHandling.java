package com.ys.video.network;


import com.ys.video.network.entity.HttpResult;
import com.ys.video.utils.ToastUtil;

/**
 * Author: liujinrui
 * Date: 2016/5/30 11:53
 * <p/>
 * Description: 异常处理
 */
@SuppressWarnings("unchecked")
final class ExceptionHandling {


    static void operate(final HttpResult result) {
        ToastUtil.showLongToast(result.getResponseMsg());

    }
}
