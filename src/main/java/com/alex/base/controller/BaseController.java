/**
 * 
 */
package com.alex.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alex.base.vo.BaseResultVo;


/**
 * 项目名称：ichat
 * @description: 基础controller
 * @version V1.0.0
 */
public class BaseController
{
    private static final Log LOGGER = LogFactory.getLog(BaseController.class);

    SerializerFeature[] feature =
    { SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteMapNullValue };


    protected String buildSuccessResultInfo(Object resultData)
    {
//        LOGGER.debug(String.format("enter function, %s", resultData));
        BaseResultVo resultVo = new BaseResultVo();
        resultVo.setResultData(resultData);
        resultVo.setResultMessage("success");
        return JSON.toJSONString(resultVo, feature);
    }


    protected String buildFailedResultInfo(int resultCode, String failedMsg)
    {
        BaseResultVo resultVo = new BaseResultVo(resultCode, failedMsg);
        return JSON.toJSONString(resultVo, feature);
    }

}
