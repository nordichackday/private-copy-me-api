package nordichack.pcmapi.rest;

import nordichack.pcmapi.app.LiveStreamerHandler;
import nordichack.pcmapi.model.LiveStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan({
    "nordichack.pcmapi"
})

public class CspApiWebContext extends WebMvcConfigurerAdapter {


    @Bean
    public DownloadRestService downloadRestService() {
        return new DownloadRestService();
    }


    @Bean
    public LiveStreamerHandler liveStreamerHandler() {
        return new LiveStreamerHandler();
    }
}
