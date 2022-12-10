package fr.lsi.gardsafe.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import fr.lsi.gardsafe.TestConfig;
import fr.lsi.gardsafe.domain.exception.GardSafeException;
import fr.lsi.gardsafe.domain.exception.LoginException;
import fr.lsi.gardsafe.domain.login.model.User;
import fr.lsi.gardsafe.infrastructure.login.repository.UserRepositoryPort;

/**
 * Junit test for repository
 * 
 * @author Laurent SION
 *
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@DataJpaTest
class UserRepositoryAdapterTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepositoryPort userRepository;

  @Test
  void testFindByLoginExistingShouldSuccess() throws LoginException {

    // Given
    final User user = User.builder().birthDate("2000-04-05").lastName("nom").firstName("prenom")
        .pseudo("pseudo").mail("mail@mail.com").build();
    entityManager.persistAndFlush(user);

    // When
    final Optional<User> found = userRepository.findByPseudo("pseudo");

    // Then
    assertThat(found).as("recherche de l'utilisateur existant via le pseudo")
        .overridingErrorMessage("l'utilisateur n'a pas été trouvé").isPresent();
  }

  @Test
  void testFindByLoginExistingUnknownShoulfFail() {
    final Optional<User> found = userRepository.findByPseudo("pseudo");
    assertThat(found).as("recherche de l'utilisateur non existant via le pseudo")
        .overridingErrorMessage("l'utilisateur a été trouvé").isNotPresent();
  }

  @Test
  void testCreateAndFindShouldSuccess() throws GardSafeException {

    // Given
    final User user = User.builder().birthDate("2000-04-06").lastName("nom2").firstName("prenom2")
        .pseudo("pseudo2").mail("mail2@mail.com").build();
    final User created = userRepository.save(user);

    // When
    final User found = userRepository.findById(created.getId());

    // Then
    assertThat(found).as("recherche de l'utilisateur existant via l'id")
        .overridingErrorMessage("l'utilisateur n'a pas été trouvé").isNotNull();
  }

  @Test
  void testFindNull() throws GardSafeException {

    // Given
    final User user = User.builder().birthDate("2000-04-06").lastName("nom2").firstName("prenom2")
        .pseudo("pseudo2").mail("mail2@mail.com").build();
    userRepository.save(user);

    // When
    final Optional<User> found = userRepository.findByPseudo("pseudo");

    // Then
    assertThat(found).as("recherche de l'utilisateur non existant via le pseudo")
        .overridingErrorMessage("l'utilisateur a été trouvé").isNotPresent();

  }

  @Test
  void testSaveNull() {
    try {
      final User user = null;
      userRepository.save(user);
      assertFalse("On ne devrai pas arrivé dans cette partie du code", Boolean.TRUE);
    } catch (GardSafeException e) {
      assertTrue("Il doit y avoir d'exception", Boolean.TRUE);
    }
  }

}
