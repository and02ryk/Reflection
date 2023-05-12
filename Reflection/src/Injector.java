import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class Injector {

    public Injector(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        properties.load(inputStream);
    }

    public <T> T inject(T object) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> type = field.getType();
                Object impl = Class.forName(properties.getProperty(type.getName())).newInstance();
                field.setAccessible(true);
                field.set(object, impl);
            }
        }
        return object;
    }

    private static List<Field> getFieldsWithAnnotation(Object obj, Class<? extends Annotation> annotation) {
        List<Field> result = new ArrayList<Field>();
        Class<?> clas = obj.getClass();

        while (clas != null)
        {
            for (Field field : clas.getDeclaredFields())
                if (field.isAnnotationPresent(annotation))
                    result.add(field);

            clas = clas.getSuperclass();
        }

        return result;
    }
}
