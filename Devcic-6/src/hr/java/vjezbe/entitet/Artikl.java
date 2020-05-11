package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet artikla koji je definiran naslovom artikla, opisom
 * artikla i njegovom cijenom
 * 
 * @author deva
 * @version Devcic-6
 */
public abstract class Artikl extends Entitet {
    private String naslov;
    private String opis;
    private BigDecimal cijena;
    private Stanje stanje;

    /**
     * Inicijalizira podatak o naslovu, opisu i cijeni artikla
     * 
     * @param naslov - podatak o naslovu artikla
     * @param opis   - podatak o opisu artikla
     * @param cijena - podatak o cijeni artikla
     * @param id
     */
    public Artikl(Long id, String naslov, String opis, Stanje stanje, BigDecimal cijena) {
	super(id);
	this.naslov = naslov;
	this.opis = opis;
	this.stanje = stanje;
	this.cijena = cijena;
    }

    public String getNaslov() {
	return naslov;
    }

    public void setNaslov(String naslov) {
	this.naslov = naslov;
    }

    public String getOpis() {
	return opis;
    }

    public void setOpis(String opis) {
	this.opis = opis;
    }

    public BigDecimal getCijena() {
	return cijena;
    }

    public void setCijena(BigDecimal cijena) {
	this.cijena = cijena;
    }

    public Stanje getStanje() {
	return stanje;
    }

    public void setStanje(Stanje stanje) {
	this.stanje = stanje;
    }

    /**
     * @return sve klase koje nasljeðuju entitet Artikl moraju implementirati
     *         vraæanje podatka teksta oglasa
     */
    public abstract String tekstOglasa();

    /**
     * Ukoliko su dva objekta ista, njihov hash code mora biti isti
     *
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cijena == null) ? 0 : cijena.hashCode());
	result = prime * result + ((naslov == null) ? 0 : naslov.hashCode());
	result = prime * result + ((opis == null) ? 0 : opis.hashCode());
	result = prime * result + ((stanje == null) ? 0 : stanje.hashCode());
	return result;
    }

    /**
     * Vraæa istinu jedino kada dve reference pokazuju na isti objekt
     *
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Artikl other = (Artikl) obj;
	if (cijena == null) {
	    if (other.cijena != null)
		return false;
	} else if (!cijena.equals(other.cijena))
	    return false;
	if (naslov == null) {
	    if (other.naslov != null)
		return false;
	} else if (!naslov.equals(other.naslov))
	    return false;
	if (opis == null) {
	    if (other.opis != null)
		return false;
	} else if (!opis.equals(other.opis))
	    return false;
	if (stanje != other.stanje)
	    return false;
	return true;
    }

}
