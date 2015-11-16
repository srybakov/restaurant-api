package com.srybakov.restaurant.controller;

import com.srybakov.restaurant.domain.webmodel.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Controller
@RequestMapping(value = "/")
public class BaseController {

    protected static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    protected void logDebug(String message, Object... params){
        if (LOG.isDebugEnabled()){
            LOG.debug(String.format(message, params));
        }
    }

    protected static ResponseJson createResponse(int code, String message){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(code);
        responseJson.setMessage(message);
        return responseJson;
    }
}
