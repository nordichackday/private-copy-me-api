package nordichack.pcmapi.app;

import nordichack.pcmapi.model.LiveStreamer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan({"nordichack.pcmapi"})
@EnableAsync
public class PcmApiContext {

 @Bean
   public LiveStreamer liveStreamer() {
       return new LiveStreamer();
    }
}
