package fr.lsi.gardsafe.domain.exception;

public class RepositoryException extends GardSafeException {

  /**
   * 
   */
  private static final long serialVersionUID = 4257422499447340643L;

  /**
   * Default constructor
   * 
   * @param message
   * @param code
   * @param info
   */
  public RepositoryException(final String message, final String code, final String info) {
    super(message, code, info);
  }

}
