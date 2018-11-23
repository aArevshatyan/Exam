import java.lang.reflect.*;
import java.util.Collection;
import java.lang.annotation.*;
public class Validator {
    boolean isValid(Object object)  throws IllegalAccessException{
        Field[] declaredFields = object.getClass().getDeclaredFields();
        
        for (Field field : declaredFields) {
            
            field.setAccessible(true);
            
            if (field.isAnnotationPresent(NotNull.class)) {
                if(!field.getType().isPrimitive()){
                    if(field.get(object) == null){
                        return false;
                    }
                }

            }else if (field.isAnnotationPresent(Valid.class)) {
                Valid annotation = field.getAnnotation(Valid.class);
                Object value = field.get(object);
                if (value instanceof Number) {
                    if((((Number)field.get(object))).floatValue() < annotation.minValue() || 
                        (((Number)field.get(object))).floatValue() > annotation.maxValue() ) {
                        return false;
                    }

                }

            }else if (field.isAnnotationPresent(NotEmpty.class)) {
                if (Collection.class.isAssignableFrom(field.getType())){
                    Collection collection = (Collection) field.get(object);
                    if(collection.isEmpty()) {
                        return false;
                    }
                }
            }
            
            field.setAccessible(false);
        }

        return true;
    }
}
