package com.project.my.controller;

import com.project.my.in.CreateShortUrlInDto;
import com.project.my.out.ActionResult;
import com.project.my.service.interfaces.KortUrlService;
import com.project.my.util.MessageTranslatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
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

    @GetMapping("/{shortUrl}")
    public ModelAndView createShortUrl(@PathVariable String shortUrl) {
        return new ModelAndView(String.format("redirect:%s", urlService.getLongUrl(shortUrl)));
    }

}
