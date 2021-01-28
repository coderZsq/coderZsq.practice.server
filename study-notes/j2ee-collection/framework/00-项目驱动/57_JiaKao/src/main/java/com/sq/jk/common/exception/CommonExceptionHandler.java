package com.sq.jk.common.exception;

import com.sq.jk.common.util.JsonVos;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.vo.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public JsonVo handle(Throwable t) {
        log.error("handle", t);

        // 一些可以直接处理的异常
        if (t instanceof CommonException) {
            return handle((CommonException) t);
        } else if (t instanceof BindException) {
            return handle((BindException) t);
        } else if (t instanceof ConstraintViolationException) {
            return handle((ConstraintViolationException) t);
        } else if (t instanceof AuthorizationException) {
            return JsonVos.error(CodeMsg.NO_PERMISSION);
        }

        // 处理cause异常 (导致产生t的异常)
        Throwable cause = t.getCause();
        if (cause != null) {
            return handle(cause);
        }

        // 其他异常 (没有cause的异常)
        return JsonVos.error();
    }

    private JsonVo handle(CommonException ce) {
        return JsonVos.error(ce.getCode(), ce.getMessage());
    }

    private JsonVo handle(BindException be) {
        List<ObjectError> errors = be.getBindingResult().getAllErrors();
        // 函数式编程的方式: stream
        List<String> defaultMsgs = Streams.map(errors, ObjectError::getDefaultMessage);
        String msg = StringUtils.collectionToDelimitedString(defaultMsgs, ", ");
        return JsonVos.error(msg);
    }

    private JsonVo handle(ConstraintViolationException cve) {
        List<String> msgs = Streams.map(cve.getConstraintViolations(), ConstraintViolation::getMessage);
        String msg = StringUtils.collectionToDelimitedString(msgs, ", ");
        return JsonVos.error(msg);
    }

    // @ExceptionHandler(Throwable.class)
    // public void handle(Throwable t,
    //                    HttpServletRequest request,
    //                    HttpServletResponse response) throws Exception {
    //     // 设置返回数据的格式
    //     response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    //     response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    //     // response.setContentType("application/json; charset=UTF-8");
    //     response.setStatus(400);
    //     response.getWriter().write(Rs.error(t).jsonString());
    //     // Debugs.run(t::printStackTrace);
    //     log.error(null, t);
    // }
}
