package nordichack.app.cspapi.rest;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@ActiveProfiles(profiles = "test")
public class CspApiIntegrationTest extends AbstractRestIntegrationTest {


    @Mock
    private Appender mockAppender;
    @Captor
    private ArgumentCaptor<LogEvent> captorLoggingEvent;

    private Logger logger;

    @Before
    public void setup() {
        initMocks(this);

        // prepare the appender for log4j
        when(mockAppender.getName()).thenReturn("MockAppender");
        when(mockAppender.isStarted()).thenReturn(true);
        when(mockAppender.isStopped()).thenReturn(false);

     //   logger = (Logger) LogManager.getLogger(LogReportHandler.class);
        logger.addAppender(mockAppender);
        logger.setLevel(Level.INFO);
    }


    @After
    public void tearDown() {
        // the appender we added will sit in the singleton logger forever
        // slowing future things down - so remove it
        logger.removeAppender(mockAppender);
    }

    @Test
    public void testCallReportWithAllArgsLogsResult() throws Exception {

        mockMvc.perform(post("/violation").headers(getContentSize(cspReportJson())).contentType(APPLICATION_JSON).content(cspReportJson())
            .param("app", "mockmvc"))
            .andExpect(status()
                .is2xxSuccessful());

        //then
        verify(mockAppender).append(captorLoggingEvent.capture());

        String mapMessageOut = "csp-app=\"mockmvc\" csp-blockedUri=\"blocked\" csp-columnNumber=\"col\" csp-documentUri=\"doc\" csp-effectedDirective=\"effect\" csp-lineNumber=\"line\" csp-originalPolicy=\"origi\" csp-referrer=\"ref\" csp-sourceFile=\"source\" csp-statusCode=\"stat\" csp-violatedDirective=\"violat\"";

        assertEquals(mapMessageOut, captorLoggingEvent.getValue().getMessage().getFormattedMessage());

    }

    @Test
    public void testCallReportWithAllArgsContentTypeCSPREPORTLogsResult() throws Exception {

        mockMvc.perform(post("/violation").headers(getContentSize(cspReportJson())).contentType("application/csp-report").content(cspReportJson())
            .param("app", "mockmvc"))
            .andExpect(status()
                .is2xxSuccessful());

        //then
        verify(mockAppender).append(captorLoggingEvent.capture());

        String mapMessageOut = "csp-app=\"mockmvc\" csp-blockedUri=\"blocked\" csp-columnNumber=\"col\" csp-documentUri=\"doc\" csp-effectedDirective=\"effect\" csp-lineNumber=\"line\" csp-originalPolicy=\"origi\" csp-referrer=\"ref\" csp-sourceFile=\"source\" csp-statusCode=\"stat\" csp-violatedDirective=\"violat\"";

        assertEquals(mapMessageOut, captorLoggingEvent.getValue().getMessage().getFormattedMessage());

    }

    @Test
    public void testCallReportWithoutParamResultsIn400() throws Exception {

        mockMvc.perform(post("/violation").contentType(APPLICATION_JSON).headers(getContentSize(generateLongString())).content("{}")
            .param("app", "mockmvc"))
            .andExpect(status()
                .is4xxClientError());

        //then
        verify(mockAppender, times(0)).append(captorLoggingEvent.capture());
    }

    @Test
    public void testCallReportWrongJsonResultsIn400() throws Exception {

        mockMvc.perform(post("/violation").headers(getContentSize(generateLongString())).contentType(APPLICATION_JSON).content(""))
            .andExpect(status()
                .is4xxClientError());

        //then
        verify(mockAppender, times(0)).append(captorLoggingEvent.capture());
    }

    @Test
    public void testCallReportWithToMuchDataReturns400() throws Exception {


        mockMvc.perform(post("/violation").contentType(APPLICATION_JSON).headers(getContentSize(generateLongString())).content(generateLongString())
            .param("app", "mockmvc"))
            .andExpect(status()
                .is4xxClientError());
        //then
        verify(mockAppender, times(0)).append(captorLoggingEvent.capture());
    }


    @Test
    public void testCallReportWithNoContenLengthReturns400() throws Exception {

        mockMvc.perform(post("/violation").contentType(APPLICATION_JSON).content(generateLongString())
            .param("app", "mockmvc"))
            .andExpect(status()
                .is4xxClientError());
        //then
        verify(mockAppender, times(0)).append(captorLoggingEvent.capture());
    }


    private static String cspReportJson() {

        return "{ \"csp-report\": " +
            "{\n" +
            "    \"document-uri\": \"doc\", \n" +
            "    \"referrer\": \"ref\", \n" +
            "    \"blocked-uri\": \"blocked\", \n" +
            "    \"violated-directive\": \"violat\", \n" +
            "    \"effective-directive\": \"effect\", \n" +
            "    \"original-policy\": \"origi\", \n" +
            "    \"column-number\": \"col\", \n" +
            "    \"line-number\": \"line\", \n" +
            "    \"source-file\": \"source\", \n" +
            "    \"status-code\": \"stat\" \n" +
            "}" +
            "}"
            ;
    }

    private HttpHeaders getContentSize(String content) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        int byteSize = content.getBytes("UTF-8").length;
        final String s = String.valueOf(byteSize);
        List<String> size = new ArrayList<String>();
        size.add(s);
        httpHeaders.put("content-length", size);
        return httpHeaders;

    }

    private String generateLongString() {

        StringBuilder sb = new StringBuilder(5000);
        for (int i = 0; i < 5000; i++) {
            sb.append("{}");
        }
        return sb.toString();
    }

}