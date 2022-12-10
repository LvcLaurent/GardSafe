package fr.lsi.zacta.domain.exception;

/**
 * exception for login service
 * 
 * @author Laurent SION
 *
 */
public class LoginException extends ZactaException {

  /**
   * 
   */
  private static final long serialVersionUID = -8268484020567444014L;



  /**
   * Default constructor
   * 
   * @param message
   * @param code
   * @param info
   */
  public LoginException(final String message, final String code, final String info) {
    super(message, code, info);
  }

}
