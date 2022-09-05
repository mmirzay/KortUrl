package com.project.my.exception;

import com.project.my.out.ActionResult;
import com.project.my.out.ErrorModel;
import com.project.my.util.BindingFailureTranslatorUtil;
import com.project.my.util.DbExceptionTranslatorUtil;
import com.project.my.util.MessageTranslatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({InternalException.class})
    @ResponseStatus(HttpStatus.OK)
    public ActionResult<String> handlingInternalException(InternalException ex) {
        log.error("Internal Error occurred : [{}]", ex.getMessage());
        return ActionResult.<String>builder()
                .message(ex.getMessage())
                .success(Boolean.FALSE)
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.OK)
    public ActionResult<String> handlingNotFoundException(NotFoundException ex) {
        log.error("result not found : [{}]", ex.getMessage());
        return ActionResult.<String>builder()
                .message(ex.getMessage())
                .success(Boolean.FALSE)
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({PSQLException.class})
    @ResponseStatus(HttpStatus.OK)
    public ActionResult<String> handlingDbException(PSQLException ex) {
        log.error("db exception thrown : [{}]", ex.getMessage());
        return ActionResult.<String>builder()
                .message(DbExceptionTranslatorUtil.findCause(ex))
                .success(Boolean.FALSE)
                .build();
    }



    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleBindingException(BindException ex) {
        log.error("received invalid parameter [{}] ", ex.getMessage());
        return ErrorModel.builder()
                .errorReasons(BindingFailureTranslatorUtil.buildErrorReasonInCaseOfBindingFailure(ex.getBindingResult()))
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel jsonFormatIsIncorrectException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return ErrorModel.builder()
                .errorReason(MessageTranslatorUtil.getText("exception.handler.json.format"))
                .build();
    }


}
