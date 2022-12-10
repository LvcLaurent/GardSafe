package fr.lsi.gardsafe.infrastructure.login.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.lsi.gardsafe.domain.login.model.User;

public interface UserJpaRepository extends JpaRepository<User, String> {

  // simple query
  Optional<User> findByPseudo(String pseudo);

  // simple query
  Optional<User> findByMail(String mail);

  @Override
  User getOne(String id);

}