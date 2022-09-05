package com.project.my.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
public class BindingFailureTranslatorUtil {


    private BindingFailureTranslatorUtil() {
    }

    public static List<String> buildErrorReasonInCaseOfBindingFailure(BindingResult bindingResult) {
        if (log.isDebugEnabled())
            log.debug("binding failure with reason [{}]", bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        return bindingResult.getFieldErrors().stream()
                .map(br -> {
                    List<Object> args = Arrays.stream(Objects.requireNonNull(br.getArguments())).collect(Collectors.toList());
                    args.remove(0);
                    return MessageTranslatorUtil.getText(br.getDefaultMessage(), args.toArray(new Object[0]));
                })
                .collect(Collectors.toList());
    }

}