package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * * Predstavlja suèelje Nekretnina kako bi klase koje se ne nasljeðuju
 * implementirale zajednièku skupinu metoda
 * 
 * @author deva
 * @version Devcic-6
 */
public interface Nekretnina {

    /**
     * Vraæa izraèunati iznos poreza na temelju unešene cijene nekretnine, a ukoliko
     * se unese preniska cijena nekretnine baca se iznimka
     * 
     * @param cijenaNekretnine - podatak o cijeni nekretnine
     * @return vraæa izraèunati iznos poreza
     */
    default public BigDecimal izracunajPorez(BigDecimal cijenaNekretnine) {
	BigDecimal iznosPoreza = new BigDecimal(0);
	BigDecimal minimalnaCijenaNekretnine = new BigDecimal(10000);
	if (cijenaNekretnine.compareTo(minimalnaCijenaNekretnine) < 0) {
	    throw new CijenaJePreniskaException();
	}
	iznosPoreza = (cijenaNekretnine.multiply(new BigDecimal(3)).divide(new BigDecimal(100), RoundingMode.HALF_UP));
	return iznosPoreza;

    }
}
