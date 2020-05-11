package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Predstavlja entitet Prodaja koji je definiran artiklom koji se prodaje,
 * korisnikom koji prodaje artikl i datumom objave prodaje artikla
 * 
 * @author deva
 * @version Devcic-6
 */
public class Prodaja extends Entitet {
    private Artikl artikl;
    private Korisnik korisnik;
    private LocalDate datumObjave;

    /**
     * @param artikl      - podatak o artiklu koji se prodaje
     * @param korisnik    - podatak o korisniku koji prodaje artikl
     * @param datumObjave - podatak o datumu objave prodaje artikla
     */
    public Prodaja(Long id, Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
	super(id);
	this.artikl = artikl;
	this.korisnik = korisnik;
	this.datumObjave = datumObjave;
    }

    public Artikl getArtikl() {
	return artikl;
    }

    public void setArtikl(Artikl artikl) {
	this.artikl = artikl;
    }

    public Korisnik getKorisnik() {
	return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
	this.korisnik = korisnik;
    }

    public LocalDate getDatumObjave() {
	return datumObjave;
    }

    public void setDatumObjave(LocalDate datumObjave) {
	this.datumObjave = datumObjave;
    }

}
