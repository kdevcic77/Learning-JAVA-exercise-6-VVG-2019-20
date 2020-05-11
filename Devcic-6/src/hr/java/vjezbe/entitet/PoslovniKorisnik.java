package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet poslovnog korisnika koji je definiran nazivom, email-om,
 * brojem telefona i web stranicom
 * 
 * @author deva
 * @version Devcic-6
 */
public class PoslovniKorisnik extends Korisnik {
    private String naziv;
    private String web;

    /**
     * Inicijalizira poslovnog korisnika koji je definiran nazivom, web adresom,
     * email-om brojem telefona
     * 
     * @param naziv   - podatak o nazivu poslovnog korisnika
     * @param web     - podatak o web stranici poslovnog korisnika
     * @param email   - podatak o email-u poslovnog korisnika
     * @param telefon - podatak o broju telefona poslovnog korisnika
     */
    public PoslovniKorisnik(Long id, String naziv, String web, String email, String telefon) {
	super(id, email, telefon);
	this.naziv = naziv;
	this.web = web;
    }

    public String getNaziv() {
	return naziv;
    }

    public void setNaziv(String naziv) {
	this.naziv = naziv;
    }

    public String getWeb() {
	return web;
    }

    public void setWeb(String web) {
	this.web = web;
    }

    /**
     * Pretvaranje pojedinaènih podataka o nazivu, email-u, broju telefona i web
     * stranica poslovnog korisnika u znakovni niz za lakše predstavljanje kontakt
     * podataka poslovnog korisnika prilikom objave oglasa
     */
    @Override
    public String dohvatiKontakt() {
	String kontaktPodaciPoslovni = ("Naziv tvrtke: " + getNaziv() + ", mail: " + getEmail() + ", tel: "
		+ getTelefon() + ", web: " + getWeb());
	return kontaktPodaciPoslovni;
    }

}
