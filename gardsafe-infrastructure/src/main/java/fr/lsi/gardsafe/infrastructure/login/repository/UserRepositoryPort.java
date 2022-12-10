package fr.lsi.gardsafe.infrastructure.login.repository;

import java.util.Optional;

import fr.lsi.gardsafe.domain.exception.GardSafeException;
import fr.lsi.gardsafe.domain.login.model.User;

/**
 * Interface for User Repository
 * 
 * @author Laurent SION
 *
 */
public interface UserRepositoryPort {

  /**
   * Save user
   * 
   * @param user User to save
   * @return saved user
   * @throws ZactaException
   */
  User save(User user) throws GardSafeException;

  /**
   * Find user by pseudo
   * 
   * @param pseudo to find
   * @return user
   */
  Optional<User> findByPseudo(final String pseudo);

  /**
   * Find user by mail
   * 
   * @param mail to find
   * @return user
   */
  Optional<User> findByMail(final String mail);

  /**
   * Find user by id
   * 
   * @param id to find
   * @return user
   */
  User findById(final String id);

}
