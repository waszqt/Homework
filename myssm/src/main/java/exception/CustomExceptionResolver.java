package exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomExceptionResolver implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        //输出异常
        ex.printStackTrace();


        //统一异常处理代码
        //针对系统自定义的CustomException异常，就可以直接从一场中获取一场信息，将异常处理在错误页面展示
        //异常信息
        String message=null;
        CustomException customException=null;
        //如果ex是系统自定义的异常，我们就直接取出异常信息
        if (ex instanceof CustomException)
        {
            customException= (CustomException) ex;
        }else {
            customException=new CustomException("未知错误");
        }

        //错误信息
        message=customException.getMessage();

        request.setAttribute("message",message);


        try {
            //转向到错误页面
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
