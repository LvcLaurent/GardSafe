package fr.lsi.gardsafe.domain.exception;

/**
 * Parent exception of the GardSafe project
 * 
 * @author Laurent SION
 *
 */
public class GardSafeException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -6214086858219086483L;

  private final String message;
  private final String code;
  private final String info;

  /**
   * GardSafe generic exception constructor
   * 
   * @param message
   * @param code
   * @param info
   */
  public GardSafeException(final String message, final String code, final String info) {
    this.message = message;
    this.code = code;
    this.info = info;
  }

  /**
   * @return the message
   */
  @Override
  public String getMessage() {
    return message;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }


  /**
   * @return the info
   */
  public String getInfo() {
    return info;
  }


}
