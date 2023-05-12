import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        final String filePath = "src/resources/config.properties";
        try (Reader reader = new FileReader(filePath)) {
            Properties properties = new Properties();
            properties.load(reader);
            Injector injector = new Injector(filePath);
            SomeBean someBean = new SomeBean();
            injector.inject(someBean);
            someBean.foo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
