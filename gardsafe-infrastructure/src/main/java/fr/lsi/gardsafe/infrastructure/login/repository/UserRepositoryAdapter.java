package fr.lsi.gardsafe.infrastructure.login.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import fr.lsi.gardsafe.domain.common.event.Event;
import fr.lsi.gardsafe.domain.exception.GardSafeException;
import fr.lsi.gardsafe.domain.exception.RepositoryException;
import fr.lsi.gardsafe.domain.login.model.User;
import fr.lsi.gardsafe.domain.repository.RepositoryConstant;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserJpaRepository userJpaRepository;

  private final ApplicationEventPublisher publisher;

  /**
   * Constructor for bean injection
   */
  public UserRepositoryAdapter(final UserJpaRepository userJpaRepository,
      final ApplicationEventPublisher publisher) {
    this.userJpaRepository = userJpaRepository;
    this.publisher = publisher;
  }

  @Override
  public User save(final User user) throws GardSafeException {

    if (user == null) {
      throw new RepositoryException(RepositoryConstant.MESSAGE_REPOSITORY_ERREUR,
          RepositoryConstant.CODE_REPOSITORY_ERREUR, RepositoryConstant.INFO_REPOSITORY_ERREUR);
    }

    final List<Event> events = user.getEvents();
    events.forEach(publisher::publishEvent);
    user.cleanEvents();

    return userJpaRepository.save(user);
  }

  @Override
  public Optional<User> findByPseudo(final String pseudo) {
    return userJpaRepository.findByPseudo(pseudo);
  }

  @Override
  public Optional<User> findByMail(final String mail) {
    return userJpaRepository.findByMail(mail);
  }

  @Override
  public User findById(final String id) {
    return userJpaRepository.getOne(id);
  }

}
