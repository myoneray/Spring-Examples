package com.javachen.examples.springmvc.petclinic.web.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

public class StringToEntityConverter implements GenericConverter {

    private static final String ID_FIELD = "id";

    private final Class<?> clazz;

    @PersistenceContext
    private EntityManager em;

    public StringToEntityConverter() {
        this.clazz=null;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> types = new HashSet<ConvertiblePair>();
        types.add(new ConvertiblePair(String.class, this.clazz));
        types.add(new ConvertiblePair(this.clazz, String.class));
        return types;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.equals(sourceType.getType())) {
            if (StringUtils.isEmpty((String) source)) {
                return null;
            }
            Long id = Long.parseLong((String) source);
            return this.em.find(this.clazz, id);
        } else if (this.clazz.equals(sourceType.getType())) {
            if (source == null) {
                return "";
            } else {
                return ReflectionUtils.getField(ReflectionUtils.findField(source.getClass(),ID_FIELD), source);
            }
        }
        throw new IllegalArgumentException("Cannot convert " + source + " into a suitable type!");
    }
}
