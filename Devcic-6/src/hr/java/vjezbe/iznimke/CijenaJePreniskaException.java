package hr.java.vjezbe.iznimke;

/**
 * Predstavlja entitet CijenaJePreniskaException koji oznaèava neoznaèenu
 * iznimku u sluèaju kada je unešena cijena stana preniska
 * 
 * @author deva
 * @version Devcic-6
 */
public class CijenaJePreniskaException extends RuntimeException {

    private static final long serialVersionUID = 2555341954833609776L;

    /**
     * Kreira objekt znakovnog niza sa porukom korisniku da se izvoðenje programa ne
     * odvija normalno
     */
    public CijenaJePreniskaException() {
	super("Pogreška prilikom odreðivanja iznosa poreza!");
    }

    /**
     * Kreira objekt sa porukom o pogrešci
     * 
     * @param poruka - podaci poruke o pogrešci
     */
    public CijenaJePreniskaException(String poruka) {
	super(poruka);
    }

    /**
     * Kreira objekt sa porukum o pogrešci kao i uzrocima pogreške
     * 
     * @param poruka - podaci poruke o pogrešci
     * @param uzrok  - opisuje uzrok pogreške
     */
    public CijenaJePreniskaException(String poruka, Throwable uzrok) {
	super(poruka, uzrok);
    }

    /**
     * Kreira objekt sa uzrocima pogreške
     * 
     * @param uzrok - podaci o uzrocima pogreške
     */
    public CijenaJePreniskaException(Throwable uzrok) {
	super(uzrok);
    }
}
