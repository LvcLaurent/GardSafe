package fr.lsi.gardsafe.domain.login.model;

import java.util.regex.Pattern;

public final class UserConstant {

  public static final int SIZE_OF_FIRSTNAME = 3;
  public static final int SIZE_OF_LASTNAME = 3;
  public static final int SIZE_OF_PSEUDO = 3;

  public static final Pattern DATE_PATTERN =
      Pattern.compile("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
  public static final Pattern PHONE_PATTERN =
      Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
  public static final Pattern MAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");

  private UserConstant() {
    super();
  }

}
