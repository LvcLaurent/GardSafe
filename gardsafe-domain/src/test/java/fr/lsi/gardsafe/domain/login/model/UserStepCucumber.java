package fr.lsi.gardsafe.domain.login.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;

import fr.lsi.gardsafe.domain.exception.LoginException;
import fr.lsi.gardsafe.domain.login.model.ErrorConstant;
import fr.lsi.gardsafe.domain.login.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStepCucumber {

  User user;
  User userDateVide, userDateNull;
  User userLastNameVide, userLastNameNull;
  User userFirstNameVide, userFirstNameNull;
  User userMailNull, userMailVide, userMailFormat;
  User userPseudoVide, userPseudoNull;
  User userPhoneFormat;


  private String lastName, firstName, adress, city, phone, mail, stringBirthDate, postalCode,
      pseudonyme;
  private LocalDate birthDate;
  private LocalDate today = LocalDate.now();

  @Given("je veux creer un utilisateur avec pour {string} {string} né le {string} avec le {string}")
  public void je_veux_creer_un_utilisateur_avec_pour_né_le(final String lastName,
      final String firstName, final String birthDate, final String pseudo) throws Throwable {
    this.lastName = lastName;
    this.firstName = firstName;
    this.stringBirthDate = birthDate;
    this.pseudonyme = pseudo;
    if (birthDate.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")) {
      this.birthDate = LocalDate.parse(this.stringBirthDate);
    }
  }

  @Given("habitant au {string} {string} {string}")
  public void habitant_au(final String adress, final String postalCode, final String city)
      throws Throwable {
    this.adress = adress;
    this.postalCode = postalCode;
    this.city = city;
  }

  @Given("avec pour numero de tel {string} et adresse mail {string}")
  public void avec_pour_numero_de_tel_et_adresse_mail(final String phone, final String mail)
      throws Throwable {
    this.phone = phone;
    this.mail = mail;
  }

  @When("je creer cet utilisateur")
  public void je_creer_cet_utilisateur() throws Throwable {
    user = User.builder().lastName(lastName).firstName(firstName).mail(mail).pseudo(pseudonyme)
        .adress(adress).city(city).postalCode(postalCode).birthDate(stringBirthDate).phone(phone)
        .build();
  }

  @Then("je verifie sont age {int} de {string} et les autres informations")
  public void je_verifie_sont_et_les_autres_informations(final int age, final String date)
      throws Throwable {
    LocalDate ControlDate = LocalDate.parse(date);
    assertEquals("l'age de l'utilisateur est correct", age, user.getAge(ControlDate));
  }

  @Then("avec son nom en majuscule et {string}")
  public void avec_son_nom_en_majuscule_et(final String prenomFormat) throws Throwable {
    assertEquals("le nom de famille est en majuscule", lastName.toUpperCase(), user.getLastName());
    assertEquals("le prenom est au bon format", prenomFormat, user.getFirstName());
    assertEquals("le mail correspond", mail, user.getMail());
    assertEquals("le numéro de téléphone correspond", phone, user.getPhone());
    assertEquals("la ville correspond", city, user.getCity());
    assertEquals("l'adresse correspond", adress, user.getAdress());
    assertEquals("le code postal correspond", postalCode, user.getPostalCode());
    assertEquals("la date de naissance est au bon format est correspond", birthDate,
        user.getBirthDate());
    assertEquals("La date de création correspond à la date du jour", today.getDayOfYear(),
        user.getCreateUserDate().getDayOfYear());
  }

  @Given("On veux creer un utilisateur avec une date vide")
  public void on_veux_creer_un_utilisateur_avec_une_date_vide() throws Throwable {
    try {
      userDateVide = User.builder().lastName("lastName").firstName("firstName").mail("aze@aze")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode").birthDate("")
          .phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_BIRTHDATE_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_BIRTHDATE_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un date null")
  public void et_un_utilisateur_avec_un_date_null() throws Throwable {
    try {
      userDateNull = User.builder().lastName("lastName").firstName("firstName").mail("aze@aze")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate(null).phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_BIRTHDATE_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_BIRTHDATE_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Then("On verifie que les deux utilisateur reste null")
  public void on_verifie_que_les_deux_utilisateur_reste_null() throws Throwable {
    assertNull("Création d'un utilisateur avec une date Null", userDateNull);
    assertNull("Création d'un utilisateur avec une date Vide", userDateVide);
  }

  @Given("On veux creer un utilisateur avec une nom vide")
  public void on_veux_creer_un_utilisateur_avec_une_nom_vide() {
    try {
      userLastNameVide = User.builder().lastName("").firstName("firstName").mail("mail")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_LASTNAME_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_LASTNAME_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un nom null")
  public void et_un_utilisateur_avec_un_nom_null() {
    try {
      userLastNameNull = User.builder().lastName(null).firstName("firstName").mail("mail")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_LASTNAME_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_LASTNAME_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un prenom null")
  public void et_un_utilisateur_avec_un_prenom_null() {
    try {
      userFirstNameNull = User.builder().lastName("lastName").firstName(null).mail("mail")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_FIRSTNAME_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_FIRSTNAME_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un prenom vide")
  public void et_un_utilisateur_avec_un_prenom_vide() {
    try {
      userFirstNameVide = User.builder().lastName("lastName").firstName("").mail("mail")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_FIRSTNAME_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_FIRSTNAME_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un mail null")
  public void et_un_utilisateur_avec_un_mail_null() {
    try {
      userMailNull = User.builder().lastName("lastName").firstName("firstName").mail(null)
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_MAIL_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_MAIL_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un mail vide")
  public void et_un_utilisateur_avec_un_mail_vide() {
    try {
      userMailVide = User.builder().lastName("lastName").firstName("firstName").mail("")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_MAIL_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_MAIL_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un mail au mauvais format")
  public void et_un_utilisateur_avec_un_mail_au_mauvais_format() {
    try {
      userMailVide = User.builder().lastName("lastName").firstName("firstName").mail("mail.test")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_MAIL_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_MAIL_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un pseudo vide")
  public void et_un_utilisateur_avec_un_pseudo_vide() {
    try {
      userPseudoVide = User.builder().lastName("lastName").firstName("firstName").mail("sdf@sdft")
          .pseudo("").adress("adress").city("city").postalCode("postalCode").birthDate("1988-04-05")
          .phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_PSEUDO_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_PSEUDO_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un pseudo null")
  public void et_un_utilisateur_avec_un_pseudo_null() {
    try {
      userPseudoVide = User.builder().lastName("lastName").firstName("firstName").mail("sdf@sdft")
          .pseudo(null).adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("0231649785").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_PSEUDO_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_PSEUDO_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Given("et un utilisateur avec un numero de tel au mauvais format")
  public void et_un_utilisateur_avec_un_numero_de_tel_au_mauvais_format() {

    try {
      userPhoneFormat = User.builder().lastName("lastName").firstName("firstName").mail("sdf@sdft")
          .pseudo("pseudonyme").adress("adress").city("city").postalCode("postalCode")
          .birthDate("1988-04-05").phone("phone").build();
    } catch (Exception ex) {
      assertTrue(ex instanceof LoginException, "On attend une exception LoginException");
      LoginException otherEx = (LoginException) ex;
      assertEquals("le code attendu est celui de la classe des constantes",
          ErrorConstant.CODE_PHONE_ERREUR, otherEx.getCode());
      assertEquals("l'info attendu est celle de la classe des constantes",
          ErrorConstant.INFO_PHONE_ERREUR, otherEx.getInfo());
      assertEquals("le message attendu est celle de la classe des constantes",
          ErrorConstant.MESSAGE_USER_ERREUR, otherEx.getMessage());
    }
  }

  @Then("On verifie que les dix utilisateurs reste null")
  public void on_verifie_que_les_dix_utilisateurs_reste_null() {
    assertNull("Création d'un utilisateur avec un nom Null", userLastNameNull);
    assertNull("Création d'un utilisateur avec un nom Vide", userLastNameVide);
    assertNull("Création d'un utilisateur avec un prenom Null", userFirstNameNull);
    assertNull("Création d'un utilisateur avec un prenom Vide", userFirstNameVide);
    assertNull("Création d'un utilisateur avec un mail au mauvais format", userMailFormat);
    assertNull("Création d'un utilisateur avec un mail null", userMailNull);
    assertNull("Création d'un utilisateur avec un mail Vide", userMailVide);
    assertNull("Création d'un utilisateur avec un pseudo Vide", userPseudoVide);
    assertNull("Création d'un utilisateur avec un pseudo null", userPseudoNull);
    assertNull("Création d'un utilisateur avec un numéro de téléphone au mauvais format",
        userPhoneFormat);
  }


}
