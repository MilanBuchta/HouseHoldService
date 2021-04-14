package sk.fri.uniza.core;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Provider
public class DateParameterConverterProvider
        implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType,
                                              final Type genericType,
                                              final Annotation[] annotations) {
        if (LocalDateTime.class.equals(rawType)) {
            final DateParameterConverter dateParameterConverter =
                    new DateParameterConverter();

            for (Annotation annotation : annotations) {
                if (DateTimeFormat.class.equals(
                        annotation.annotationType())) {
                    dateParameterConverter.
                            setCustomDateTimeFormat(
                                    (DateTimeFormat) annotation);
                } else if (DateFormat.class.equals(
                        annotation.annotationType())) {
                    dateParameterConverter.
                            setCustomDateFormat((DateFormat) annotation);
                }
            }
            return (ParamConverter<T>) dateParameterConverter;
        }
        return null;
    }
}
