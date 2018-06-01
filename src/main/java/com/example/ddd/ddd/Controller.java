package com.example.ddd.ddd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ddd.model.DataModel;
import com.example.ddd.model.ResponseModel;
import com.example.ddd.res.RetModel;


/**
 * Controller
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:57 2018/4/26
 */
public class Controller {
    /**
     * 响应
     * @param code
     * @param context
     * @param data
     * @return
     */
    protected static String response(int code, String context, Object data) {
        DataModel dataModel = new DataModel();
        dataModel.setCode(code);
        dataModel.setContext(context);
        dataModel.setData(data);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setRet(RetModel.RET_SUCCESS);
        responseModel.setMsg(RetModel.MSG_SUCCESS);
        responseModel.setData(dataModel);
        return JSON.toJSONString(responseModel);
    }
}
