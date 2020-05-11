package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet kategorija koji je definiran nazivom kategorije i
 * artiklima koje spadaju u tu kategoriju
 * 
 * @author deva
 * @version Devcic-6
 */
public class Kategorija<T extends Artikl> extends Entitet {
    private String naziv;
    private List<T> listaArtikala = new ArrayList<T>();

    /**
     * Inicijalizira podatak o nazivu i artiklima kategorije
     * 
     * @param naziv   - podatak o nazivu kategorije
     * @param artikli - podatak o artiklima koji se nalaze u kateogoriji
     */
    public Kategorija(Long id, String naziv, List<T> listaArtikala) {
	super(id);
	this.naziv = naziv;
	this.listaArtikala = listaArtikala;
    }

    public void dodajArtikl(T artikl) {
	listaArtikala.add(artikl);
    }

    public T dohvatiArtikl(Integer indeks) {
	return this.listaArtikala.get(indeks);
    }

    public List<T> dohvatiListuArtikala() {
	return this.listaArtikala;

    }

    public String getNaziv() {
	return naziv;
    }

    public void setNaziv(String naziv) {
	this.naziv = naziv;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Kategorija<?> other = (Kategorija<?>) obj;
	if (naziv == null) {
	    if (other.naziv != null)
		return false;
	} else if (!naziv.equals(other.naziv))
	    return false;
	return true;
    }

}
