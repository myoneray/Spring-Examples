package com.javachen.examples.springmvc.petclinic.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.NumberUtils;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 10:54.
 */
public class StringToIntConverter  implements Converter<String, Integer> {
    public Integer convert(String source) {
        if (source.length() == 0) {
            return null;
        }
        try {
            Integer.parseInt(source);
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
        return NumberUtils.parseNumber(source, Integer.class);
    }
}
