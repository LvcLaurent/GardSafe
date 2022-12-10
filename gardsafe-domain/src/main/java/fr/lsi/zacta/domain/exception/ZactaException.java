package fr.lsi.zacta.domain.exception;

/**
 * Parent exception of the Zacta project
 * 
 * @author Laurent SION
 *
 */
public class ZactaException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -6214086858219086483L;

  private String message;
  private String code;
  private String info;

  /**
   * Zacta generic exception constructor
   * 
   * @param message
   * @param code
   * @param info
   */
  public ZactaException(final String message, final String code, final String info) {
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
   * @param message the message to set
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(final String code) {
    this.code = code;
  }

  /**
   * @return the info
   */
  public String getInfo() {
    return info;
  }

  /**
   * @param info the info to set
   */
  public void setInfo(final String info) {
    this.info = info;
  }


}
