package testUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SpecBuilder {

    public static RequestSpecification requestSpec;

    public static RequestSpecification requestSpecification() throws IOException {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .addHeader("Accept","application/json")
                    .addHeader("Accept-Encoding","gzip, deflate, br")
                    .addHeader("Connection","keep-alive")
                    .build();
            return requestSpec;
    }

    public static RequestSpecification requestSpecificationWithMultipart() throws IOException {

            PrintStream log = new PrintStream(new FileOutputStream("loggingMultipart.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.MULTIPART)
                    .addHeader("Accept","application/json")
                    .build();
            return requestSpec;
    }
}
