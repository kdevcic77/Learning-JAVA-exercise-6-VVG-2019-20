package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet privatnog korisnika koji je definiran imenom, prezimenom,
 * email-om i brojem telefona
 * 
 * @author deva
 * @version Devcic-6
 */
public class PrivatniKorisnik extends Korisnik {

    private String ime;
    private String prezime;

    /**
     * @param ime     - podatak o imenu privatnog korisnika
     * @param prezime - podatak o prezimenu privatnog korisnika
     * @param email   - podatak o email-u privatnog korisnika
     * @param telefon - podatak o broju telefona privatnog korisnika
     */
    public PrivatniKorisnik(Long id, String ime, String prezime, String email, String telefon) {
	super(id, email, telefon);
	this.ime = ime;
	this.prezime = prezime;
    }

    public String getIme() {
	return ime;
    }

    public void setIme(String ime) {
	this.ime = ime;
    }

    public String getPrezime() {
	return prezime;
    }

    public void setPrezime(String prezime) {
	this.prezime = prezime;
    }

    /**
     * Pretvaranje pojedinaènih podataka o imenu, prezimenu, email-u i broju
     * telefona privatnog korisnika u znakovni niz za lakše predstavljanje kontakt
     * podataka privatnog korisnika prilikom objave oglasa
     */
    @Override
    public String dohvatiKontakt() {
	String kontaktPodaciPrivatni = ("Osobni podaci prodavatelja: " + getIme() + " " + getPrezime() + ", mail: "
		+ getEmail() + ", tel: " + getTelefon());
	return kontaktPodaciPrivatni;
    }

}
