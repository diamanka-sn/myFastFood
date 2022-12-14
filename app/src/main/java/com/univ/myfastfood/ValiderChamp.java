package com.univ.myfastfood;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValiderChamp {
    private Pattern modele;
    private Matcher matcher;

    private static final String EMAIL_MODELE = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NOM_MODELE = "[a-z A-Z]{2,20}";
    private static final String PASSWORD_MODELE = "^(?=.*\\d).{4,8}$";
    private static final String TELEPHONE_MODELE = "^7[0,5,6,7,8][0-9]{7}";

    public boolean validerMail(final String mail) {
        modele = Pattern.compile(EMAIL_MODELE);
        matcher = modele.matcher(mail);

        return matcher.matches();
    }

    public boolean verifNom(final String prenom) {
        modele = Pattern.compile(NOM_MODELE);
        matcher = modele.matcher(prenom);
        return matcher.matches();
    }

    public boolean validerMotPasse(final String passe) {
        modele = Pattern.compile(PASSWORD_MODELE);
        matcher = modele.matcher(passe);
        return matcher.matches();
    }

    public boolean validerTelephone(final String passe) {
        modele = Pattern.compile(TELEPHONE_MODELE);
        matcher = modele.matcher(passe);
        return matcher.matches();
    }
}
