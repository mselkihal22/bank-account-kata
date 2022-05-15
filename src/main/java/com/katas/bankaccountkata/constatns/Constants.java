package com.katas.bankaccountkata.constatns;

import java.time.format.DateTimeFormatter;

public class Constants {


    public static final String AMOUNT = " Amount: ";
    public static final String BALANCE = "Balance: ";
    public static final String DATE = "Date: ";
    public static final String SEPARATOR = " | ";
    public static final String LINE_SEPARATOR = " \n ";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
}
