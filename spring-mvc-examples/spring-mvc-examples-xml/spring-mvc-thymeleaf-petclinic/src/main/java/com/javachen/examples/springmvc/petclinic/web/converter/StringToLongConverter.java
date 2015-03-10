package com.javachen.examples.springmvc.petclinic.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.NumberUtils;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 10:55.
 */
public class StringToLongConverter implements Converter<String, Long> {

    public Long convert(String source) {
        if (source.length() == 0) {
            return null;
        }
        try {
            Long.parseLong(source);
        } catch (Exception e) {
            return Long.MAX_VALUE;
        }
        return NumberUtils.parseNumber(source, Long.class);
    }
}