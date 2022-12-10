#Author: Laurent SION
@tag
Feature: Test sur un utilisateur
  Ici nous allons essayer de tester tout ce qu on à besoin sur un utilisateur

  @tag1
  Scenario Outline: Creation d un utilisateur avec l ensemble ou non des informations
    Given je veux creer un utilisateur avec pour "<nom>" "<prenom>" né le "<dateNaissance>" avec le "<pseudo>"
    And habitant au "<adresse>" "<codePostal>" "<ville>"
    And avec pour numero de tel "<numeroTel>" et adresse mail "<adresseMail>"
    When je creer cet utilisateur
    Then je verifie sont age <age> de "2022-03-25" et les autres informations
    And avec son nom en majuscule et "<prenomFormat>"

    Examples: 
      | nom      | prenom       | prenomFormat | pseudo | dateNaissance | adresse    | codePostal | Ville  | numeroTel  | adresseMail        | age |
      | sion     | Laurent      | Laurent      | pseudo | 1988-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  33 |
      | sion     | Laurent      | Laurent      | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |
      | sion     | laurent      | Laurent      | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |
      | sion     | laurent-bill | Laurent-Bill | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |
      | SION     | l-bill       | L-Bill       | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |
      | sion     | laurent-b    | Laurent-B    | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |
      | trigault | laurent-b    | Laurent-B    | pseudo | 1987-05-04    | 9 rue Noël |      01015 | Londre | 0912121212 | adresse@domain.int |  34 |

  @tag2
  Scenario:
    Given On veux creer un utilisateur avec une date vide
    And et un utilisateur avec un date null
    Then On verifie que les deux utilisateur reste null 
    
  @tag3
  Scenario:
    Given On veux creer un utilisateur avec une nom vide
    And et un utilisateur avec un nom null
    And et un utilisateur avec un prenom null
    And et un utilisateur avec un prenom vide
    And et un utilisateur avec un pseudo vide
    And et un utilisateur avec un pseudo null
    And et un utilisateur avec un mail null
    And et un utilisateur avec un mail vide
    And et un utilisateur avec un mail au mauvais format
    And et un utilisateur avec un numero de tel au mauvais format
    Then On verifie que les dix utilisateurs reste null 