package testUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static String getGlobalValues(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis= new FileInputStream("src/test/resources/global.properties");

        prop.load(fis);
        return prop.getProperty(key);
    }


    public static String getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }


}
