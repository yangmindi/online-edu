package com.guli.edu.handler;

import com.guli.common.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 统一异常处理类
     * 2.对特定的异常进行处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody //向页面输出
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("出现了异常");
    }

    //2.对特定的异常进行处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("0不能做除数，出现了异常");
    }

    //3.自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMessgae()).code(e.getCode());
    }
}
