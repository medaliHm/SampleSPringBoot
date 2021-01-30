package com.sample.credentials.common.config.logger;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public abstract class NormalLogger {



    /** Constant : DEFAULT_FIELD_SEPARATOR. */
    public static final String DEFAULT_FIELD_SEPARATOR = "|";

    /** Constant : BUSINESS_LOG. */
    public static final NormalLogger BUSINESS_LOG = new NormalLogger("business")
    {
    };

    /** Constant : API_IO_LOG. */
    public static final NormalLogger API_IO_LOG = new NormalLogger("api-io", Level.DEBUG)
    {
    };

    /** Constant : API_AUDIT_LOG. */
    public static final NormalLogger API_AUDIT_LOG = new NormalLogger("api-audit",Level.INFO)
    {
    };

    /** Constant : EXCEPTIONS_LOG. */
    public static final NormalLogger EXCEPTIONS_LOG = new NormalLogger("exceptions", Level.ERROR)
    {
    };

    /** logger. */
    private Logger logger;

    /** field separator. */
    private String fieldSeparator;

    /** level. */
    private Level level;


    /**
     * Instanciation de normal logger.
     *
     * @param name
     */
    public NormalLogger(String name)
    {

        // En cas besoin de plusieurs logger pour chaque type (fichier logback.xml a mettre à jour)
        logger =(Logger)  LoggerFactory.getLogger(name);
        //logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        this.fieldSeparator = DEFAULT_FIELD_SEPARATOR;
        this.level = Level.INFO;
    }

    /**
     * Instanciation de normal logger.
     *
     * @param name
     * @param level
     */
    public NormalLogger(String name, Level level)
    {
        this(name);
        this.level = level;
    }

    /**
     * Instanciation de normal logger.
     *
     * @param name DOCUMENTEZ_MOI
     * @param level DOCUMENTEZ_MOI
     * @param fieldSeparator DOCUMENTEZ_MOI
     */
    public NormalLogger(String name, Level level, String fieldSeparator)
    {
        this(name);
        this.level = level;
        this.fieldSeparator = fieldSeparator;
    }

    /**
     * methode Log.
     *
     * @param message
     */
    public void log(String message)
    {
        if (isLevelDebug())
        {
            logger.debug(message);
        }

        if (isLevelError())
        {
            logger.error(message);
        }


        if (isLevelInfo())
        {
            logger.info(message);
        }

        if (isLevelTrace())
        {
            logger.trace(message);
        }

        if (isLevelWarn())
        {
            logger.warn(message);
        }
    }

    /**
     * methode Log.
     *
     * @param messages
     */
    public void log(String... messages)
    {

        StringBuilder sbuilder = new StringBuilder();
        for (String msg : messages)
        {
            sbuilder.append(msg).append(fieldSeparator);
        }

        log(sbuilder.toString());
    }

    /**
     * methode Builds the message exception.
     *
     * @param message
     * @param exception
     * @return string
     */
    private String buildMessageException(String message, Throwable exception)
    {
        Throwable cause = exception;

        while (cause.getCause() != null)
        {
            cause = cause.getCause();
        }

        return new StringBuilder(cause.getMessage() == null ? "" : cause.getMessage()).append(fieldSeparator)
                .append(message == null ? "" : message).append(fieldSeparator).toString();

    }

    /**
     * methode Log.
     *
     * @param message
     * @param exception
     */
    public void log(String message, Throwable exception)
    {
        if (isLevelDebug())
        {
            logger.debug(buildMessageException(message, exception), exception);
        }

        if (isLevelError())
        {
            logger.error(buildMessageException(message, exception), exception);
        }



        if (isLevelInfo())
        {
            logger.info(buildMessageException(message, exception), exception);
        }

        if (isLevelTrace())
        {
            logger.trace(buildMessageException(message, exception), exception);
        }

        if (isLevelWarn())
        {
            logger.warn(buildMessageException(message, exception), exception);
        }
    }

    /**
     * methode Log.
     *
     * @param exception
     */
    public void log(Throwable exception)
    {
        log(buildMessageException(null, exception), exception);
    }

    /**
     * Verifie si debug enabled.
     *
     * @return true, si c'est debug enabled
     */
    public boolean isDebugEnabled()
    {
        return this.level.levelInt == Level.DEBUG.levelInt;
    }

    /**
     * Accesseur de l attribut level.
     *
     * @return level
     */
    public Level getLevel()
    {
        return level;
    }

    /**
     * Verifie si level warn.
     *
     * @return true, si c'est level warn
     */
    private boolean isLevelWarn()
    {
        return logger.isWarnEnabled() && level.equals(Level.WARN);
    }

    /**
     * Verifie si level trace.
     *
     * @return true, si c'est level trace
     */
    private boolean isLevelTrace()
    {
        return logger.isTraceEnabled() && level.equals(Level.TRACE);
    }

    /**
     * Verifie si level info.
     *
     * @return true, si c'est level info
     */
    private boolean isLevelInfo()
    {
        return logger.isInfoEnabled() && level.equals(Level.INFO);
    }



    /**
     * Verifie si level error.
     *
     * @return true, si c'est level error
     */
    private boolean isLevelError()
    {
        return logger.isErrorEnabled() && level.equals(Level.ERROR);
    }

    /**
     * Verifie si level debug.
     *
     * @return true, si c'est level debug
     */
    private boolean isLevelDebug()
    {
        return logger.isDebugEnabled() && level.equals(Level.DEBUG);
    }



}
