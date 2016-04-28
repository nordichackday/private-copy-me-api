package nordichack.pcmapi.app;

import nordichack.pcmapi.model.LiveStreamer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"nordichack.pcmapi"})
public class PcmApiContext {

 @Bean
   public LiveStreamer liveStreamer() {
       return new LiveStreamer();
    }
}
