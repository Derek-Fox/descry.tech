package descry.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

public class Log {

    /** Compiling this and the booleans below as "final" will cause the compiler
     * to remove all "if (LogLevel.Info) ..." type statements below the set level. */
    private static int _logLevel = LogLevel.Info;
    private static Logger _logger = new Logger();

    public static boolean ERROR = _logLevel <= LogLevel.Error;
    public static boolean WARN = _logLevel <= LogLevel.Warn;
    public static boolean INFO = _logLevel <= LogLevel.Info;
    public static boolean DEBUG = _logLevel <= LogLevel.Debug;
    public static boolean TRACE = _logLevel <= LogLevel.Trace;

    private Log() { }

    /** Comment out method contents when compiling fixed-level JARs. */
    public static void setLogLevel(int level) {
        _logLevel = level;
        ERROR = (level <= LogLevel.Error);
        WARN = (level <= LogLevel.Warn);
        INFO = (level <= LogLevel.Info);
        DEBUG = (level <= LogLevel.Debug);
        TRACE = (level <= LogLevel.Trace);
    }

    public static int getLogLevel() {
        return _logLevel;
    }

    public static void setLogger(Logger logger) {
        Log._logger = logger;
    }

    //region Error
    public static void error(String message, Throwable ex) {
        if (ERROR) _logger.log(LogLevel.Error, null, message, ex);
    }

    public static void error(String category, String message, Throwable ex) {
        if (ERROR) _logger.log(LogLevel.Error, category, message, ex);
    }

    public static void error(String message) {
        if (ERROR) _logger.log(LogLevel.Error, null, message, null);
    }

    public static void error(String category, String message) {
        if (ERROR) _logger.log(LogLevel.Error, category, message, null);
    }
    //endregion

    //region Warn
    public static void warn(String message, Throwable ex) {
        if (WARN) _logger.log(LogLevel.Warn, null, message, ex);
    }

    public static void warn(String category, String message, Throwable ex) {
        if (WARN) _logger.log(LogLevel.Warn, category, message, ex);
    }

    public static void warn(String message) {
        if (WARN) _logger.log(LogLevel.Warn, null, message, null);
    }

    public static void warn(String category, String message) {
        if (WARN) _logger.log(LogLevel.Warn, category, message, null);
    }
    //endregion

    //region Info
    public static void info(String message, Throwable ex) {
        if (INFO) _logger.log(LogLevel.Info, null, message, ex);
    }

    public static void info(String category, String message, Throwable ex) {
        if (INFO) _logger.log(LogLevel.Info, category, message, ex);
    }

    public static void info(String message) {
        if (INFO) _logger.log(LogLevel.Info, null, message, null);
    }

    public static void info(String category, String message) {
        if (INFO) _logger.log(LogLevel.Info, category, message, null);
    }
    //endregion

    //region Debug
    public static void debug(String message, Throwable ex) {
        if (DEBUG) _logger.log(LogLevel.Debug, null, message, ex);
    }

    public static void debug(String category, String message, Throwable ex) {
        if (DEBUG) _logger.log(LogLevel.Debug, category, message, ex);
    }

    public static void debug(String message) {
        if (DEBUG) _logger.log(LogLevel.Debug, null, message, null);
    }

    public static void debug(String category, String message) {
        if (DEBUG) _logger.log(LogLevel.Debug, category, message, null);
    }
    //endregion

    //region Trace
    public static void trace(String message, Throwable ex) {
        if (TRACE) _logger.log(LogLevel.Trace, null, message, ex);
    }

    public static void trace(String category, String message, Throwable ex) {
        if (TRACE) _logger.log(LogLevel.Trace, category, message, ex);
    }

    public static void trace(String message) {
        if (TRACE) _logger.log(LogLevel.Trace, null, message, null);
    }

    public static void trace(String category, String message) {
        if (TRACE) _logger.log(LogLevel.Trace, category, message, null);
    }
    //endregion

    public static class LogLevel {

        public static final int None = 6;   // No common.common.logging at all.
        public static final int Error = 5;  // Critical errors. The application may no longer work correctly.
        public static final int Warn = 4;   // Important warnings. The application will continue to work correctly.
        public static final int Info = 3;   // Informative messages. Typically used for deployment.
        public static final int Debug = 2;  // Useful during development.
        public static final int Trace = 1;  // A lot of information is logged, so this level is usually only needed when debugging a problem.

        public static String toName(int level) {
            return switch (level) {
                case LogLevel.Error -> "ERROR";
                case LogLevel.Warn -> " WARN";
                case LogLevel.Info -> " INFO";
                case LogLevel.Debug -> "DEBUG";
                case LogLevel.Trace -> "TRACE";
                default -> "";
            };
        }
    }

    // Default implementation logs to System.out
    public static class Logger {

        private final long _firstLogTime = System.currentTimeMillis();

        public void log(int level, String category, String message, Throwable ex) {

            StringBuilder builder = new StringBuilder(256);

            long time = System.currentTimeMillis() - _firstLogTime;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(time);

            if (minutes <= 9) builder.append('0');
            builder.append(minutes);
            builder.append(':');
            if (seconds <= 9) builder.append('0');
            builder.append(seconds);

            builder.append(" ");
            builder.append(LogLevel.toName(level));
            builder.append(": ");

            if (category != null) {
                builder.append("[");
                builder.append(category);
                builder.append("] ");
            }

            builder.append(message);

            if (ex != null) {
                StringWriter writer = new StringWriter(256);
                ex.printStackTrace(new PrintWriter(writer));
                builder.append('\n');
                builder.append(writer.toString().trim());
            }

            System.out.println(builder.toString());
        }
    }

}

