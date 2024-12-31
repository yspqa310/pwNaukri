package utilities;
import org.apache.log4j.Logger;

public class genericMethods extends myBrowser {
    public static Logger LOGGER = Logger.getLogger(genericMethods.class);




//    below methods are for the Logging purpose
    public static void writeLogInfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    public void writeLoginfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    public static void writeLogWarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    public void writeLogwarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    public static void writeLogDebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    public void writeLogdebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    public static void writeLogError(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }

    public void writeLogerror(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }
}
