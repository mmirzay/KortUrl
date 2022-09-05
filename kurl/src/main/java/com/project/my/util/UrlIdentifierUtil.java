package com.project.my.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.project.my.exception.InternalException;
import org.apache.commons.validator.routines.UrlValidator;

import java.nio.charset.StandardCharsets;

public class UrlIdentifierUtil {
    private final static UrlValidator VALIDATOR = new UrlValidator();
    private final static HashFunction HASH_FUNCTION = Hashing.murmur3_32_fixed();

    public static String identifierOf(String url) {
        validateUrl(url);
        return HASH_FUNCTION
                .hashString(url, StandardCharsets.UTF_8)
                .toString();
    }

    private static void validateUrl(String url) {
        if (!VALIDATOR.isValid(url))
            throw new InternalException(MessageTranslatorUtil.getText("utility.url.identifier.invalid.url"));
    }
}
