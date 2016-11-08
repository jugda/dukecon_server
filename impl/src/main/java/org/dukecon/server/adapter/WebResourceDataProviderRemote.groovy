package org.dukecon.server.adapter

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import org.dukecon.model.Conference

import java.nio.charset.StandardCharsets

/**
 * @author Falk Sippach, falk@jug-da.de, @sippsack
 */
@Slf4j
@TypeChecked
class WebResourceDataProviderRemote {

    final ConferenceDataExtractor dataExtractor
    final InputStream backupUri
    volatile boolean backupActive = false
    Exception staleException

    WebResourceDataProviderRemote(ConferenceDataExtractor dataExtractor, InputStream backupUri) {
        this.dataExtractor = dataExtractor
        this.backupUri = backupUri
    }

    Conference getConference() {
        dataExtractor.conference
    }

//    /* JsonOutput and JsonSlurper will encode/parse UTF characters as \\u anyway,
//   but to be sure choosing UTF-8 here.
// */
//    private final static String BACKUP_CHARSET = StandardCharsets.UTF_8.toString();
//
//    @TypeChecked(TypeCheckingMode.SKIP)
//    @HystrixCommand(groupKey = "doag", commandKey = "readConferenceData", fallbackMethod = "readConferenceDataFallback")
//    public Conference readConferenceData() {
//        try {
//            log.info("Rereading data from '{}'", talksUri)
//            def data = getInputData(talksUri);
//            def rawJson = new JsonSlurper().parse(data, "ISO-8859-1")
//            Conference conference = createConference(rawJson.hits.hits._source)
//            try {
//                File backupFile = new File(backup);
//                backupFile.write(JsonOutput.toJson(rawJson), BACKUP_CHARSET);
//            } catch (IOException e) {
//                log.warn("unable to write backup file '{}'", backup, e);
//            }
//            backupActive = false;
//            staleException = null;
//            return conference;
//        } catch (RuntimeException e) {
//            log.error("unable to read data", e);
//            staleException = e;
//            throw e;
//        }
//    }
//
//    @TypeChecked(TypeCheckingMode.SKIP)
//    public Conference readConferenceDataFallback() {
//        try {
//            log.info("Rereading JSON data from backup '{}'", backup)
//            def rawJson = new JsonSlurper().parse(new File(backup).newInputStream(), BACKUP_CHARSET)
//            Conference conference = createConference(rawJson.hits.hits._source)
//            backupActive = true;
//            return conference;
//        } catch (RuntimeException e) {
//            log.error("unable to read backup", e);
//            throw e;
//        }
//    }
//
//
//    private Object getInputData(String uri) {
//        uri.startsWith("resource:") ? readResource(uri) : readJavalandFile(uri)
//    }
//
//    private InputStream readResource(String uri) {
//        log.info("Reading JSON data from '{}'", uri)
//        String[] resourceParts = uri.split(":")
//        InputStream stream = this.getClass().getResourceAsStream(resourceParts[1])
//        if (stream == null) {
//            throw new IOException("file '${uri}' not found")
//        }
//        return stream;
//    }
//
//    private URL readJavalandFile(String uri) {
//        log.info("Reading JSON data from remote '{}'", uri)
//        return new URL(uri)
//    }

}
