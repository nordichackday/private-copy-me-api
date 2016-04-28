package nordichack.pcmapi.rest;

import nordichack.pcmapi.app.PcmApiContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import static org.slf4j.bridge.SLF4JBridgeHandler.install;
import static org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger;

@SpringBootApplication
@Import({PcmApiWebContext.class, PcmApiContext.class})
public class PcmWebApplication  {


    public static void main(String... args) {
        redirectJulToSlf4j();
        SpringApplication.run(PcmWebApplication.class);
    }

    private static void redirectJulToSlf4j() {
        removeHandlersForRootLogger();
        install();
    }

}
