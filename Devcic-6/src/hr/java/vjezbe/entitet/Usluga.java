package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet Usluga koji je definiran naslovom usluge, opisom usluge i
 * cijenom usluge
 * 
 * @author deva
 * @version Devcic-6
 */
public class Usluga extends Artikl {

    /**
     * Inicijalizira entitet Usluga koji je definiran naslovom, opisom, stanjem i cijenom
     * usluge
     * 
     * @param naslov - podatak o naslovu usluge
     * @param opis   - podatak o opisu usluge
     * @param stanje - podatak o stanju
     * @param cijena - podatak o cijeni usluge
     */
    public Usluga(Long id, String naslov, String opis, Stanje stanje, BigDecimal cijena) {
	super(id, naslov, opis, stanje, cijena);
    }

    /**
     * Pretvaranje pojedinaènih podataka o naslovu, opisu i cijeni usluge u znakovni
     * niz za lakše predstavljanje oglasa usluge
     */
    @Override
    public String tekstOglasa() {
	String tekstOglasa = ("\nNaslov usluge: " + getNaslov() + "\nOpis usluge: " + getOpis() + "\nCijena usluge: "
		+ getCijena());
	return tekstOglasa;
    }

}
