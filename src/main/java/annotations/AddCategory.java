package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddCategory {
    boolean addService() default false;
    boolean promotionAndClickPrice() default false;
    boolean addRequest() default false;
    boolean addRequestPrice() default false;
    int requestQuestionsCount() default 1;
}
