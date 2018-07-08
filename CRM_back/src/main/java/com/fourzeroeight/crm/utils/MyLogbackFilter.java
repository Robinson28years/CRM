package com.fourzeroeight.crm.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class MyLogbackFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
       if(iLoggingEvent.getLoggerName().equals("upload")){
            return FilterReply.ACCEPT;
       }else {
           return FilterReply.DENY;
       }
    }
}
