package com.sq.jk.common.exception;

import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.vo.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public JsonVo handle(Throwable t) {
        log.error("handle", t);
        return JsonVos.error(t);
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
