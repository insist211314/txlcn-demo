package org.txlcn.demo.serviceb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version:
 * @describe 集中的异常处理Handler
 */
@ControllerAdvice
@ResponseBody
public class CollectivelyExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CollectivelyExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public Object handler(Exception ex) {
        logger.error("系统异常!", ex);
        return "error";
    }

}
