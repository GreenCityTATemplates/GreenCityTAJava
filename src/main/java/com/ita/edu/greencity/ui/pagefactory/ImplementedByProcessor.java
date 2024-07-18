package com.ita.edu.greencity.ui.pagefactory;


import com.ita.edu.greencity.ui.elements.base_element.Element;
import com.ita.edu.greencity.ui.elements.base_element.ImplementedBy;

public final class ImplementedByProcessor {
    private ImplementedByProcessor() {
    }

    public static <T> Class<?> getWrapperClass(Class<T> iface) {
        if (iface.isAnnotationPresent(ImplementedBy.class)) {
            ImplementedBy annotation = iface.getAnnotation(ImplementedBy.class);
            Class<?> clazz = annotation.value();
            if (Element.class.isAssignableFrom(clazz)) {
                return annotation.value();
            }
        }
        throw new UnsupportedOperationException("Apply @ImplementedBy interface to your Interface " +
                iface.getCanonicalName() + " if you want to extend ");
    }

}