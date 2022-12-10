package fr.lsi.gardsafe.domain.login.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import fr.lsi.gardsafe.domain.common.entity.AbstractBaseEntity;
import fr.lsi.gardsafe.domain.common.event.Event;
import fr.lsi.gardsafe.domain.common.event.EventAware;
import fr.lsi.gardsafe.domain.exception.LoginException;

/**
 * Class of User (Entity)
 * 
 * @author Laurent SION
 *
 */
public final class User extends AbstractBaseEntity implements EventAware {


  /**
   * 
   */
  private static final long serialVersionUID = -3898421724312729334L;

  private String lastName;
  private String firstName;
  private String pseudo;
  private LocalDate birthDate;
  private String adress;
  private String postalCode;
  private String phone;
  private String mail;
  private String city;
  private LocalDate createUserDate;
  private LocalDate lastUserDate;
  private transient List<Event> events = new ArrayList<>();

  /**
   * Constructor for JPA
   */
  protected User() {
    super();
  }

  private User(final Builder builder) {
    lastName = builder.lastName;
    firstName = builder.firstName;
    pseudo = builder.pseudo;
    birthDate = builder.birthDate;
    adress = builder.adress;
    postalCode = builder.postalCode;
    phone = builder.phone;
    mail = builder.mail;
    city = builder.city;
    createUserDate = builder.createUserDate;
    events.add(UserCreatedEvent.builder().firstName(firstName).lastName(lastName).mail(mail)
        .pseudo(pseudo).build());
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder pattern to ensure User Object is immutable
   */
  public static class Builder {
    private String lastName;
    private String firstName;
    private String pseudo;
    private LocalDate birthDate;
    private String adress;
    private String postalCode;
    private String phone;
    private String mail;
    private String city;
    private LocalDate createUserDate;

    public Builder() {
      super();
      this.createUserDate = LocalDate.now();
    }

    public Builder lastName(final String lastName) throws LoginException {
      if ((lastName == null) || lastName.isEmpty()
          || (lastName.length() < UserConstant.SIZE_OF_LASTNAME)) {
        throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR,
            ErrorConstant.CODE_LASTNAME_ERREUR, ErrorConstant.INFO_LASTNAME_ERREUR);
      }
      this.lastName = lastName.toUpperCase(Locale.FRENCH);
      return this;
    }

    public Builder firstName(final String firstName) throws LoginException {
      if ((firstName == null) || firstName.isEmpty()
          || (firstName.length() < UserConstant.SIZE_OF_FIRSTNAME)) {
        throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR,
            ErrorConstant.CODE_FIRSTNAME_ERREUR, ErrorConstant.INFO_FIRSTNAME_ERREUR);
      }
      String[] tab = firstName.split("-");
      StringBuilder sb = new StringBuilder();
      sb.append("");
      for (String string : tab) {
        if (!"".equals(sb.toString())) {
          sb.append("-");
        }
        sb.append(string.substring(0, 1).toUpperCase(Locale.FRENCH));
        sb.append(string.substring(1));
      }
      this.firstName = sb.toString();
      return this;
    }

    public Builder pseudo(final String pseudo) throws LoginException {
      if ((pseudo == null) || pseudo.isEmpty() || (pseudo.length() < UserConstant.SIZE_OF_PSEUDO)) {
        throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR,
            ErrorConstant.CODE_PSEUDO_ERREUR, ErrorConstant.INFO_PSEUDO_ERREUR);
      }
      this.pseudo = pseudo;
      return this;
    }

    public Builder birthDate(final String birthDate) throws LoginException {
      if ((birthDate == null) || birthDate.isEmpty()
          || !UserConstant.DATE_PATTERN.matcher(birthDate).matches()) {
        throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR,
            ErrorConstant.CODE_BIRTHDATE_ERREUR, ErrorConstant.INFO_BIRTHDATE_ERREUR);
      }
      this.birthDate = LocalDate.parse(birthDate);
      return this;
    }

    public Builder adress(final String adress) {
      this.adress = adress;
      return this;
    }

    public Builder postalCode(final String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder phone(final String phone) throws LoginException {
      String phoneSave = "";
      if ((phone != null) && !phone.isEmpty()) {
        phoneSave = phone.replace(" ", "");
        if (!UserConstant.PHONE_PATTERN.matcher(phoneSave).matches()) {
          throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR,
              ErrorConstant.CODE_PHONE_ERREUR, ErrorConstant.INFO_PHONE_ERREUR);
        }
      }
      this.phone = phoneSave;
      return this;
    }

    public Builder mail(final String mail) throws LoginException {
      if ((mail == null) || mail.isEmpty() || !UserConstant.MAIL_PATTERN.matcher(mail).matches()) {
        throw new LoginException(ErrorConstant.MESSAGE_USER_ERREUR, ErrorConstant.CODE_MAIL_ERREUR,
            ErrorConstant.INFO_MAIL_ERREUR);
      }
      this.mail = mail;
      return this;
    }

    public Builder city(final String city) {
      this.city = city;
      return this;
    }

    public User build() {
      return new User(this);
    }

  }

  @Override
  public void cleanEvents() {
    events.clear();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(getId())
        .append(firstName).append(lastName).append(birthDate).append(pseudo).append(adress)
        .append(postalCode).append(phone).append(mail).append(createUserDate).append(lastUserDate)
        .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getPseudo()).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    final User other = (User) obj;
    return new EqualsBuilder().append(id, other.id).append(pseudo, other.pseudo).isEquals();
  }



  /**
   * returns the age of the user
   * 
   * @return
   */
  public int getAge(final LocalDate date) {
    return Period.between(birthDate, date).getYears();
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the pseudo
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * @param pseudo the pseudo to set
   */
  public void setPseudo(final String pseudo) {
    this.pseudo = pseudo;
  }

  /**
   * @return the birthDate
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * @param birthDate the birthDate to set
   */
  public void setBirthDate(final LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * @return the adress
   */
  public String getAdress() {
    return adress;
  }

  /**
   * @param adress the adress to set
   */
  public void setAdress(final String adress) {
    this.adress = adress;
  }

  /**
   * @return the postalCode
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(final String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone the phone to set
   */
  public void setPhone(final String phone) {
    this.phone = phone;
  }

  /**
   * @return the mail
   */
  public String getMail() {
    return mail;
  }

  /**
   * @param mail the mail to set
   */
  public void setMail(final String mail) {
    this.mail = mail;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(final String city) {
    this.city = city;
  }

  /**
   * @return the createUserDate
   */
  public LocalDate getCreateUserDate() {
    return createUserDate;
  }

  /**
   * @param createUserDate the createUserDate to set
   */
  public void setCreateUserDate(final LocalDate createUserDate) {
    this.createUserDate = createUserDate;
  }

  /**
   * @return the lUserDate
   */
  public LocalDate getlastUserDate() {
    return lastUserDate;
  }

  /**
   * @param lUserDate the lUserDate to set
   */
  public void setlUserDate(final LocalDate lastUserDate) {
    this.lastUserDate = lastUserDate;
  }

  /**
   * @return the lastUserDate
   */
  public LocalDate getLastUserDate() {
    return lastUserDate;
  }

  /**
   * @param lastUserDate the lastUserDate to set
   */
  public void setLastUserDate(final LocalDate lastUserDate) {
    this.lastUserDate = lastUserDate;
  }

  /**
   * @return the events
   */
  @Override
  public List<Event> getEvents() {
    return events;
  }

  /**
   * @param events the events to set
   */
  public void setEvents(final List<Event> events) {
    this.events.addAll(events);
  }

}
