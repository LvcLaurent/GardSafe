package fr.lsi.zacta.domain.login.model;

public final class ErrorConstant {

  public static final String MESSAGE_USER_ERREUR = "Paramètre manquant pour la création du USER";

  public static final String INFO_LASTNAME_ERREUR = "Le nom est obligatoire et de longueur 3";
  public static final String CODE_LASTNAME_ERREUR = "err_func_001";

  public static final String INFO_FIRSTNAME_ERREUR = "Le prenom est obligatoire et de longueur 3";
  public static final String CODE_FIRSTNAME_ERREUR = "err_func_002";

  public static final String INFO_PSEUDO_ERREUR = "Le pseudonyme est obligatoire et de longueur 3";
  public static final String CODE_PSEUDO_ERREUR = "err_func_003";

  public static final String INFO_MAIL_ERREUR = "Le mail est obligatoire au format a@a";
  public static final String CODE_MAIL_ERREUR = "err_func_004";

  public static final String INFO_BIRTHDATE_ERREUR =
      "La date de naissance est obligatoire au format AAAA-MM-DD";
  public static final String CODE_BIRTHDATE_ERREUR = "err_func_005";

  public static final String INFO_PHONE_ERREUR =
      "La numéro de téléphone ne possède pas 10 chiffres";
  public static final String CODE_PHONE_ERREUR = "err_func_006";

  private ErrorConstant() {
    super();
  }



}
