package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet korisnika koji je definiran email-om i brojem telefona
 * korisnika
 * 
 * @author deva
 * @version Devcic-6
 */
public abstract class Korisnik extends Entitet {

    protected String email;
    protected String telefon;

    /**
     * Inicijalizira podatak o emailu i broju telefona korisnika
     * 
     * @param email   - podataka o email-u korisnika
     * @param telefon - podataka o broju telefona korisnika
     */
    public Korisnik(Long id, String email, String telefon) {
	super(id);
	this.email = email;
	this.telefon = telefon;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getTelefon() {
	return telefon;
    }

    public void setTelefon(String telefon) {
	this.telefon = telefon;
    }

    /**
     * @return sve klase koje nasljeðuju entitet Korisnik moraju implementirati
     *         vraæanje podatka o kontakt podacima korisnika koje je oglasio oglas
     */
    public abstract String dohvatiKontakt();
}
