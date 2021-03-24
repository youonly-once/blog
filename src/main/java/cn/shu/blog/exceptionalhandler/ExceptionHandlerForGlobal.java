package cn.shu.blog.exceptionalhandler;

/**
 * 全局异常处理器
 *
 * @author SXS
 * @since 3/24/2021
 */

import cn.shu.blog.beans.ErrorMsg;
import cn.shu.blog.exception.GlobalErrorException;
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
}
