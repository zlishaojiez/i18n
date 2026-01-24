package io.github.zlishaojiez.i18n.spring.boot4.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nTestController {

    @GetMapping("locale")
    public String getLocale() {
        return LocaleContextHolder.getLocale().toString();
    }
}
