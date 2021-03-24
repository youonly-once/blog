package cn.shu.blog.exceptionalhandler;

/**
 * 全局异常处理器
 *
 * @author SXS
 * @since 3/24/2021
 */

import cn.shu.blog.beans.ErrorMsg;
import cn.shu.blog.exception.GlobalErrorException;
import cn.shu.blog.exception.UserException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerForGlobal {

    /**
     * 全局异常处理，出现错误请求转发到404页面
     * @param e 异常
     * @return 404页面
     */
    @ExceptionHandler(value = GlobalErrorException.class)
    private ModelAndView exceptionHander(GlobalErrorException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/WEB-INF/jsp/404.jsp");
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setDesc(e.getMessage());
        errorMsg.setCode(400);
        modelAndView.addObject("msg",errorMsg);
        return modelAndView;
    }
    /**
     * 全局异常处理，出现错误请求转发到404页面
     * @param e 异常
     * @return 404页面
     */
    @ExceptionHandler(value = Exception.class)
    private ModelAndView exceptionHander(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/WEB-INF/jsp/404.jsp");
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setDesc(e.getMessage());
        errorMsg.setCode(400);
        modelAndView.addObject("msg",errorMsg);
        return modelAndView;
    }

    /**
     * 字段校验异常处理，
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(value = BindException.class)
    private String exceptionHandler(BindException e){
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError allError : e.getAllErrors()) {
            stringBuilder.append(allError.getDefaultMessage()).append("\n");
        }
        return stringBuilder.toString();
    }
    /**
     * 全局异常处理，出现错误请求转发到404页面
     * @param e 异常
     * @return 404页面
     */
    @ExceptionHandler(value = UserException.class)
    private String exceptionHander(UserException e){
        return e.getMessage();
    }
}
