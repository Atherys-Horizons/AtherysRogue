package com.atherys.game;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public final class Log {

    private static final Log instance = new Log();
    private List<LoggedMessage> loggedMessages = new ArrayList<>();

    private Log() {
    }

    public static void info(String format, Object... objects) {
        getInstance().log(String.format(format, objects), Severity.INFO);
    }

    public static void error(String format, Object... objects) {
        getInstance().log(String.format(format, objects), Severity.ERROR);
    }

    public static void warn(String format, Object... objects) {
        getInstance().log(String.format(format, objects), Severity.WARN);
    }

    public static void info(String msg) {
        getInstance().log(msg, Severity.INFO);
    }

    public static void error(String msg) {
        getInstance().log(msg, Severity.ERROR);
    }

    public static void warn(String msg) {
        getInstance().log(msg, Severity.WARN);
    }

    public static Log getInstance() {
        return instance;
    }

    public void log(String msg, Severity severity) {
        this.loggedMessages.add(LoggedMessage.of(msg, severity));
    }

    public List<LoggedMessage> getLast(int amount) {
        if (loggedMessages.isEmpty()) return new ArrayList<>();
        List<LoggedMessage> result = new ArrayList<>(amount);
        for (int i = loggedMessages.size() - 1; i >= loggedMessages.size() - amount; i--) {
            if (i < 0) break;
            result.add(loggedMessages.get(i));
        }
        return result;
    }

    public List<LoggedMessage> getLoggedMessages() {
        return loggedMessages;
    }

    public enum Severity {
        INFO(new TextColor.RGB(255, 255, 255), ""),
        WARN(new TextColor.RGB(255, 255, 0), "[WRN]"),
        ERROR(new TextColor.RGB(255, 0, 0), "[ERR]");

        private TextColor textColor;
        private String prefix;

        Severity(TextColor textColor, String prefix) {
            this.textColor = textColor;
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }

        public TextColor getTextColor() {
            return textColor;
        }
    }

    public static class LoggedMessage {

        private Severity severity;
        private String contents;

        public LoggedMessage(String contents, Severity severity) {
            this.severity = severity;
            this.contents = contents;
        }

        public static LoggedMessage of(String contents, Severity severity) {
            return new LoggedMessage(contents, severity);
        }

        public static LoggedMessage info(String contents) {
            return new LoggedMessage(contents, Severity.INFO);
        }

        public static LoggedMessage warn(String contents) {
            return new LoggedMessage(contents, Severity.WARN);
        }

        public static LoggedMessage error(String contents) {
            return new LoggedMessage(contents, Severity.ERROR);
        }

        public Severity getSeverity() {
            return severity;
        }

        public String getMessage() {
            return contents;
        }
    }

}
