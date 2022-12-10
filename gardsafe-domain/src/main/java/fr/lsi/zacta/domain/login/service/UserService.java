package fr.lsi.zacta.domain.login.service;

import fr.lsi.zacta.domain.exception.ZactaException;
import fr.lsi.zacta.domain.login.model.User;

/**
 * Interface for User and Login Service
 * 
 * @author Laurent SION
 *
 */
public interface UserService {

  /**
   * Add a user in the application
   * 
   * @param user
   * @throws ZactaException
   */
  void ajoutUser(final User user) throws ZactaException;

  /**
   * Find a user in the database
   * 
   * @param user
   * @throws ZactaException
   */
  User findUser(final User user) throws ZactaException;

}
