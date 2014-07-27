import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

appender("FILE", FileAppender) {
    file = "logs/app.log"
    append = false
    encoder(PatternLayoutEncoder) {
        pattern = "%date{yyyy/MM/dd HH:mm:ss:SSS} %level %logger - %msg%n"
    }
}

appender("STDOUT", ConsoleAppender) {
    target = "System.out"
    encoder(PatternLayoutEncoder) {
        pattern = "%date{yyyy/MM/dd HH:mm:ss:SSS} %.5level - %logger{0}.%.20method %msg%n"
    }
}

root(INFO, ["STDOUT", "FILE"])