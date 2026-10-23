package queuedesk.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;

public class JacksonConfig {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {//this will execute once class is loaded
        MAPPER.registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }
    //if we want the object mapper then we get from here
    private JacksonConfig() {}
}
