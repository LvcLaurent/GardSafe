package fr.lsi.gardsafe.domain.login.service;

import fr.lsi.gardsafe.domain.exception.GardSafeException;
import fr.lsi.gardsafe.domain.login.model.User;

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
   * @throws GardSafeException
   */
  void ajoutUser(final User user) throws GardSafeException;

  /**
   * Find a user in the database
   * 
   * @param user
   * @throws GardSafeException
   */
  User findUser(final User user) throws GardSafeException;

}
