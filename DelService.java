package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DelService {

    private List<Deltager> deltagere = new ArrayList<>();

    public DelService() {
        leggTilStartDeltagere();
    }

    public boolean addDeltager(Deltager deltager, BindingResult result) {
        boolean valid = true;

        if (!isValidFornavn(deltager.getFornavn())) {
            result.rejectValue("fornavn", "invalid.fornavn", "Ugyldig fornavn");
            valid = false;
        }
        if (!isValidEtternavn(deltager.getEtternavn())) {
            result.rejectValue("etternavn", "invalid.etternavn", "Ugyldig etternavn");
            valid = false;
        }
        
        if (!isValidMobil(deltager.getMobil())) {
            result.rejectValue("mobil", "invalid.mobil", "Ugyldig mobilnummer");
            valid = false;
        }
        
        if (!isValidPassord(deltager.getPassord())) {
            result.rejectValue("passord", "invalid.passord", "Passord må være minst 8 tegn");
            valid = false;
        }

        if (!deltager.getPassord().equals(deltager.getRepetertPassord())) {
            result.rejectValue("repetertPassord", "mismatch.repetertPassord", "Passordene er ikke like");
            valid = false;
        }
        
        if (!isValidKjonn(deltager.getKjonn())) {
            result.rejectValue("kjonn", "invalid.kjonn", "Kjønn må velges");
            valid = false;
        }
        
        for (Deltager d : deltagere) {
            if (d.getMobil().equals(deltager.getMobil())) {
                result.rejectValue("mobil", "duplicate.mobil", "Mobilnummeret er allerede registrert");
                valid = false;
                break;
            }
        }
        
        if (valid) {
            deltagere.add(deltager);
        }
        return valid;
    }

    public List<Deltager> getDeltagere() {
        return deltagere.stream()
            .sorted(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn))
            .collect(Collectors.toList());
    }


    private void leggTilStartDeltagere() {
        deltagere.add(new Deltager("12345678", "passord123", "Sara", "Myking", "Kvinne"));
        deltagere.add(new Deltager("87654321", "passord2", "Bob", "Doe", "Mann"));
        deltagere.add(new Deltager("12345672", "pass0rd1", "Elena", "Jakobsen", "Kvinne"));
        deltagere.add(new Deltager("51238123", "passoRd6", "Chlas", "Smith-Smithsen", "Mann"));
        deltagere.add(new Deltager("56781123", "passld564", "John Pelle", "Ohlson", "Mann"));
    }
    
    private boolean isValidFornavn(String fornavn) {
        return Pattern.matches("^[A-ZÆØÅ][a-zæøå\\-\\s]{1,19}$", fornavn);
    }

    private boolean isValidEtternavn(String etternavn) {
        return Pattern.matches("^[A-ZÆØÅ][a-zæøå\\-]{1,19}$", etternavn);
    }

    private boolean isValidMobil(String mobil) {
        return Pattern.matches("\\d{8}", mobil);
    }

    private boolean isValidPassord(String passord) {
        return passord.length() >= 8;
    }

    private boolean isValidKjonn(String kjonn) {
        return kjonn != null && !kjonn.isEmpty();
    }
}