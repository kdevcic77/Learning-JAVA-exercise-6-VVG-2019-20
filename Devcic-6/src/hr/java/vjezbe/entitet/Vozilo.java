package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja suèelje vozila kako bi klase koje se ne nasljeðuju implementirale
 * zajednièku skupinu metoda
 * 
 * @author deva
 * @version Devcic-6
 */
public interface Vozilo {

    /**
     * @param snagaKs - predstavlja unos snage vozila u konjskim satima
     * @return - vraæa izraèunatu snagu vozila u kilovatima (kW), jer su konjske
     *         snage u biti nezakonita jedinica
     */
    default public BigDecimal izracunajKilovate(BigDecimal snagaKs) {
	BigDecimal snagaKw = new BigDecimal(0);
	snagaKw = snagaKs.multiply(new BigDecimal(0.735499));
	return snagaKw;
    }

    /**
     * @return svi entiteti koji koriste suèelje Vozilo moraju implementirati
     *         izraèunavanje grupe osiguranja putem vraæanja cjelobrojne vrijednosti
     *         grupe osiguranja u koje vozilo spada
     * @throws NemoguceOdreditiGrupuOsiguranjaException - u sluèaju prevelikog broja
     *                                                  kilivata, baca se greška da
     *                                                  nije moguæe odrediti grupu
     *                                                  osiguranja
     */
    public Integer izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;

    /**
     * @return vraæa odreðeni iznos osiguranja za vozilo koje je potrebno platiti na
     *         temelju toga u koju grupu osiguranja vozilo pripada
     * @throws NemoguceOdreditiGrupuOsiguranjaException - u sluèaju prevelikog broja
     *                                                  kilivata, baca se greška da
     *                                                  nije moguæe odrediti grupu
     *                                                  osiguranja
     */
    default public BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {

	BigDecimal iznosOsiguranja = new BigDecimal(0);

	switch (izracunajGrupuOsiguranja()) {
	// 5 grupa osiguranja
	case 0:
	    iznosOsiguranja = new BigDecimal(300);
	    break;
	case 1:
	    iznosOsiguranja = new BigDecimal(600);
	    break;
	case 2:
	    iznosOsiguranja = new BigDecimal(900);
	    break;
	case 3:
	    iznosOsiguranja = new BigDecimal(1200);
	    break;
	case 4:
	    iznosOsiguranja = new BigDecimal(1500);
	    break;
	}
	return iznosOsiguranja;

    }

}
