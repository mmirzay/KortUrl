package com.project.my.controller;

import com.project.my.in.CreateShortUrlInDto;
import com.project.my.out.ActionResult;
import com.project.my.service.interfaces.KortUrlService;
import com.project.my.util.MessageTranslatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/short-url")
public class KortUrlController {

    private final KortUrlService urlService;

    @PostMapping("/create")
    public ActionResult<String> createShortUrl(@RequestBody @Valid CreateShortUrlInDto dto) {
        return ActionResult.<String>builder()
                .success(Boolean.TRUE)
                .data(urlService.createShortUrl(dto))
                .message(MessageTranslatorUtil.getText("controller.url.create.short.url.message.success"))
                .build();
    }

}
