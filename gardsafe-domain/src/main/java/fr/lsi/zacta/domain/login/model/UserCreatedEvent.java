package fr.lsi.zacta.domain.login.model;

import fr.lsi.zacta.domain.common.event.Event;

/**
 * Event occured when a User was created
 * 
 * @author Laurent SION
 *
 */
public final class UserCreatedEvent extends Event {

  private final String lastName;
  private final String firstName;
  private final String mail;
  private final String pseudo;

  /**
   * 
   * @param builder
   */
  private UserCreatedEvent(final Builder builder) {
    lastName = builder.lastName;
    firstName = builder.firstName;
    pseudo = builder.pseudo;
    mail = builder.mail;
  }

  /**
   * builder static
   */
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String lastName;
    private String firstName;
    private String mail;
    private String pseudo;

    public Builder lastName(final String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder firstName(final String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder mail(final String mail) {
      this.mail = mail;
      return this;
    }

    public Builder pseudo(final String pseudo) {
      this.pseudo = pseudo;
      return this;
    }

    public UserCreatedEvent build() {
      return new UserCreatedEvent(this);
    }
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return the mail
   */
  public String getMail() {
    return mail;
  }

  /**
   * @return the pseudo
   */
  public String getPseudo() {
    return pseudo;
  }

}
