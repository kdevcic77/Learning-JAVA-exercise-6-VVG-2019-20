package hr.java.vjezbe.iznimke;

/**
 * Predstavlja entitet NemoguceOdreditiGrupuOsiguranjaException koji oznaèava
 * oznaèenu iznimku u sluèaju kada se ne može odraditi cijena osiguranja
 * 
 * @author deva
 * @version Devcic-6
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

    private static final long serialVersionUID = -2291535264076831445L;

    /**
     * Kreira objekt znakovnog niza sa porukom korisniku da se izvoðenje programa ne
     * odvija normalno
     */
    public NemoguceOdreditiGrupuOsiguranjaException() {
	super("Pogreška prilikom odreðivanja cijene osiguranja!");
    }

    /**
     * Kreira objekt sa porukom o pogrešci
     * 
     * @param poruka - podaci poruke o pogrešci
     */
    public NemoguceOdreditiGrupuOsiguranjaException(String poruka) {
	super(poruka);
    }

    /**
     * Kreira objekt sa porukum o pogrešci kao i uzrocima pogreške
     * 
     * @param poruka - podaci poruke o pogrešci
     * @param uzrok  - opisuje uzrok pogreške
     */
    public NemoguceOdreditiGrupuOsiguranjaException(String poruka, Throwable uzrok) {
	super(poruka, uzrok);
    }

    /**
     * Kreira objekt sa uzrocima pogreške
     * 
     * @param uzrok - podaci o uzrocima pogreške
     */
    public NemoguceOdreditiGrupuOsiguranjaException(Throwable uzrok) {
	super(uzrok);
    }
}
