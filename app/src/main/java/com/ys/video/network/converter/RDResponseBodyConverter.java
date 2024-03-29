/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ys.video.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.ys.video.network.entity.HttpResult;
import com.ys.video.network.entity.Params;
import com.ys.video.network.exception.ApiException;
import com.ys.video.utils.AppLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/6 9:35
 * <p/>
 * Description:  JSON response 解析
 */
@SuppressWarnings("unused")
final class RDResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    RDResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string().trim();
        AppLogger.e("请求返回原始数据", response);
        value.close();

        try {
            JSONObject object = new JSONObject(response);
            String code = object.optString(Params.RES_CODE);
            if (code.equals(Params.RES_SUCCEED)){
                StringReader reader = new StringReader(response);
                JsonReader jsonReader = gson.newJsonReader(reader);
                return adapter.read(jsonReader);
            }else {
                throw new ApiException(new Gson().fromJson(response, HttpResult.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            AppLogger.e(e.toString());
            return null;
        }
    }
}
