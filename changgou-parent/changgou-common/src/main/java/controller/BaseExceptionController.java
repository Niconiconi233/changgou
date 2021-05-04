package controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-04-07 21:34
 **/
@ControllerAdvice
public class BaseExceptionController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }


}
