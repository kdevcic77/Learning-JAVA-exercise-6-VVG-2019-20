package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Predstavlja entitet stan koji je definiran naslovom , opisom, kvadraturom, stanjem i
 * cijenom
 * 
 * @author deva
 * @version Devcic-6
 */
public class Stan extends Artikl implements Nekretnina {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(Stan.class);

    private int kvadratura;

    /**
     * @param naslov     podatak o naslovu stana koji se prodaje
     * @param opis       podatak opisa stan
     * @param kvadratura podatak o kvadraturi stana
     * @param stanje     podatak o stanju nekretnine
     * @param cijena     podatak o cijeni stana
     */
    public Stan(Long id, String naslov, String opis, int kvadratura, Stanje stanje, BigDecimal cijena) {
	super(id, naslov, opis, stanje, cijena);
	this.kvadratura = kvadratura;
    }

    public int getKvadratura() {
	return kvadratura;
    }

    public void setKvadratura(int kvadratura) {
	this.kvadratura = kvadratura;
    }

    /**
     * Pretvaranje pojedinaènih podataka o naslovu, opisu, kvadraturi, stanju, izraèunatom
     * porezu i cijeni stana u znakovni niz za lakše predstavljanje oglasa
     * automobila; u sluèaju premale cijene nekretnine, lovi se neoznaèena iznimka
     */
    @Override
    public String tekstOglasa() {
	String izracunatPorez = "";
	try {
	    izracunatPorez = ("" + izracunajPorez(getCijena()));
	} catch (CijenaJePreniskaException e) {
	    izracunatPorez = ("Cijena ne smije biti manja od 10000kn");
	    logger.error(e.getMessage(), e);
	}
	String tekstOglasa = ("\nNaslov nekretnine: " + getNaslov() + "\nOpis nekretnine: " + getOpis()
		+ "\nKvadratura Nekretnine: " + getKvadratura() + "\nStanje nekretnine: " + getStanje()
		+ "\nPorez na nekretnine: " + izracunatPorez + "\nCijena nekretnine " + getCijena());
	return tekstOglasa;
    }

}
