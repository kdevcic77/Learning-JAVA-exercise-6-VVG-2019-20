package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Artikl;

/**
 * Entitet ArtiklSorter implementira su�elje Comparator na na�in da implementira
 * metodu compare koja uspore�uje i sortira artikle prema naslovu. Ukoliko
 * artikli imaju isti naslov, radi se usporedba prema opisu artikla
 * 
 * @author deva
 * @version Devcic-6
 */
public class ArtiklSorter implements Comparator<Artikl> {

    /**
     * @param objekt artikl1 se uspore�uje sa objektom artikl2
     * @return Ako su dva objekta ista, vra�a se kao rezultat 0. U tom slu�aju su
     *         objekti isti po naslovu, pa se objekti uspore�uju po opisu abecedeno.
     *         Ako objekt artikl1 dolazi prije objekta artikl2 abecedno, vra�a se
     *         negativni broj 1 kao rezultat (nema zamjene mjesta objekata). Ako
     *         objekt artikl1 dolazi poslije objekta artikl2 abecedno, vra�a se
     *         pozitivni broj 1 kao rezultat, te tada objekti mjenjaju mjesta
     *         (artikl2 dolazi prije artikl1)
     */
    @Override
    public int compare(Artikl artikl1, Artikl artikl2) {
	int rezultat = artikl1.getNaslov().compareTo(artikl2.getNaslov());
	if (rezultat == 0) {
	    rezultat = artikl1.getOpis().compareTo(artikl2.getOpis());
	}
	return rezultat;
    }

}
