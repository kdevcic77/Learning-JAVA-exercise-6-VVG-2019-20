package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;

/**
 * Predstavlja programski dio koda koji služi za kreiranje i objavu oglasa
 * 
 * @author deva
 * @version Devcic-6
 */
public class Glavna {

    private static final String FORMAT_DATUMA = "dd.MM.yyyy.";

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * @param args predstavlja unos sa tipkovnice broja korisnika oglasnika, odabir
     *             tipa korisnika kao i izvršavanje metode kreiranja korisnika
     *             zavisno od odabira; takoðer predstavlja unos broja kategorija
     *             oglasnika, te izvršavanje metode kreiranja kategorije; Nakon
     *             unosa svih traženih podataka, potrebno je odabrati aktivne
     *             artikle koji su za prodaju, a završetkom odabira artikala za
     *             prodaju, trenutno aktivni oglasi se ispisuju na konzolnom ekranu
     *             sa pripadajuæim podacima
     */

    public static void main(String[] args) {

	logger.info("Pokretanje programa");

	Scanner ucitavac = new Scanner(System.in);

	System.out.print("Unesite broj korisnika koje želite unijeti: ");
	int brojKorisnika = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	List<Korisnik> listaKorisnika = new ArrayList<>(brojKorisnika);

	for (int i = 0; i < brojKorisnika; i++) {
	    System.out.println("Unesite tip " + (i + 1) + ". korisnika");
	    int odabraniBrojTipaKorisnika = 0;
	    do {
		odabraniBrojTipaKorisnika = 1;
		System.out.println(odabraniBrojTipaKorisnika + ". Privatni");
		System.out.println((odabraniBrojTipaKorisnika + 1) + ". Poslovni");
		System.out.print("Odabir ->> ");
		odabraniBrojTipaKorisnika = provjeriIntBroj(ucitavac);
		ucitavac.nextLine();
	    } while (odabraniBrojTipaKorisnika != 1 && odabraniBrojTipaKorisnika != 2);

	    if (odabraniBrojTipaKorisnika == 1) {

		listaKorisnika.add(unesiPrivatnogKorisnika(ucitavac, i + 1));
	    }
	    if (odabraniBrojTipaKorisnika == 2) {

		listaKorisnika.add(unesiPoslovnogKorisnika(ucitavac, i + 1));
	    }

	}

	System.out.print(horizontalIsprekidanaLine());

	System.out.println();
	System.out.print("Unesite broj kategorija koje želite unijeti: ");
	int brojKategorija = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	List<Kategorija<?>> listaKategorija = new ArrayList<>();
	for (int k = 0; k < brojKategorija; k++) {
	    listaKategorija.add(unesiKategoriju(ucitavac, k));

	}
	Map<Kategorija<?>, List<Artikl>> mapaArtikalaPoKategoriji = new HashMap<>();

	for (Kategorija<?> kategorija : listaKategorija) {
	    List<Artikl> listaArtikalaKategorije = new ArrayList<>(kategorija.dohvatiListuArtikala());
	    mapaArtikalaPoKategoriji.put(kategorija, listaArtikalaKategorije);
	}

	System.out.print("Unesite broj artikala koji su aktivno na prodaju: ");
	int brojAktivnihOglasa = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	List<Prodaja> listaProdaja = new ArrayList<>();
	for (int i = 0; i < brojAktivnihOglasa; i++) {
	    listaProdaja.add(unesiProdaju(ucitavac, i, listaKorisnika, listaKategorija));
	}

	ucitavac.close();

	System.out.println("Trenutno su oglasi na prodaju:");
	System.out.print(horizontalIsprekidanaLine());
	for (Prodaja prodaja : listaProdaja) {
	    System.out.print("\n" + prodaja.getArtikl().tekstOglasa());
	    LocalDate datumObjave = prodaja.getDatumObjave();
	    String datumObjaveString = datumObjave.format(DateTimeFormatter.ofPattern(FORMAT_DATUMA));
	    System.out.println("\nDatum objave: " + datumObjaveString);
	    System.out.println(prodaja.getKorisnik().dohvatiKontakt());
	    System.out.print(horizontalIsprekidanaLine());
	}
	System.out.println("\nIspis po kategorijama:");
	for (Kategorija<?> kategorija : listaKategorija) {
	    System.out.print(horizontalIsprekidanaLine());
	    System.out.println("\nKategorija: " + kategorija.getNaziv());
	    List<Artikl> listaArtikalaKategorije = new ArrayList<>(kategorija.dohvatiListuArtikala());

//	    izbacivanje korištenja sortiranja uz pomoæ paketa "hr.java.vjezbe.sortiranje" i klase "ArtiklSorter"
//	    Collections.sort(listaArtikalaKategorije, new ArtiklSorter());

//          Lambda izraz sa tipom infomracije parametara klase koje želimo usporediti   
//	    Collections.sort(listaArtikalaKategorije,
//		    (Artikl p1, Artikl p2) -> p1.getNaslov().compareTo(p2.getNaslov()));
//          Lambda izraz sa micanjem tipa informacije koji želimo usporediti

	    Collections.sort(listaArtikalaKategorije, (p1, p2) -> p1.getNaslov().compareTo(p2.getNaslov()));

//	    ZAMJENA FOR PETLJI LISTE LAMBDA IZRAZOM	
//	    for (Artikl artiklKategorije : listaArtikalaKategorije) {
//		System.out.print(horizontalIsprekidanaLine());
//		System.out.println(artiklKategorije.tekstOglasa());
//	    }

	    listaArtikalaKategorije.forEach(s -> {
		System.out.print(horizontalIsprekidanaLine());
		System.out.println(s.tekstOglasa());
	    });
	}
//      ZAMJENA FOR PETLJI MAPE LAMBDA IZRAZIMA
//	System.out.println("\nIspis mape:");
//	for (Kategorija<?> key : mapaArtikalaPoKategoriji.keySet()) {
//	    System.out.print(horizontalIsprekidanaLine());
//	    System.out.println("\nKategorija: " + key.getNaziv());
//	    for (int i = 0; i < mapaArtikalaPoKategoriji.get(key).size(); i++) {
//		System.out.print(horizontalIsprekidanaLine());
//		System.out.println(mapaArtikalaPoKategoriji.get(key).get(i).tekstOglasa());
//	    }
//	}
	System.out.println("\nIspis mape:");
	mapaArtikalaPoKategoriji.keySet().forEach(key -> {
	    System.out.print(horizontalIsprekidanaLine());
	    System.out.println("\nKategorija: " + key.getNaziv());
	    IntStream.range(0, mapaArtikalaPoKategoriji.get(key).size()).forEach(i -> {
		System.out.print(horizontalIsprekidanaLine());
		System.out.println(mapaArtikalaPoKategoriji.get(key).get(i).tekstOglasa());
	    });
	});

    }

    /**
     * Kreira novi objekt prodaje artikla na temelju odabira korisnika, kategorije
     * oglasa kao i pripadajuæeg artikla kategorije
     * 
     * @param ucitavac        omoguæava korisnièki unos, odnosno predstavlja Scanner
     *                        objekt u obliku varijable koji u ovom sluèaju
     *                        omoguæava korisnièki unos putem tipkovnice
     * @param i               omoguæava prijenos cijelog broja kao parametra
     *                        trenutnog indeksa polja u koji se sprema kreirani
     *                        objekt prodaje
     * @param listaKorisnika  podaci o kreiranim korisnicimaa
     * @param listaKategorija podaci o kreiranim kategorijama sa pripadajuæim
     *                        artiklima
     * @return vraæa novi objekt prodaje odn. oglas
     */
    private static Prodaja unesiProdaju(Scanner ucitavac, int i, List<Korisnik> listaKorisnika,
	    List<Kategorija<?>> listaKategorija) {
	Integer redniBrojKorisnika = 0;
	System.out.println("Odaberite korisnika: ");
	int j = 0;
	do {
	    for (Korisnik korisnik : listaKorisnika) {
		System.out.println((j + 1) + ". " + korisnik.dohvatiKontakt());
		j++;
	    }

	    System.out.print("Odabir korisnika >> ");
	    redniBrojKorisnika = provjeriIntBroj(ucitavac);
	    ucitavac.nextLine();
	} while (redniBrojKorisnika < 1 || redniBrojKorisnika > listaKorisnika.size());

	Korisnik odabraniKorisnik = listaKorisnika.get(redniBrojKorisnika - 1);

	Integer redniBrojKategorije = 0;
	System.out.println("Odaberite kategoriju: ");
	j = 0;
	for (Kategorija<?> kategorija : listaKategorija) {
	    System.out.println((j + 1) + ". " + kategorija.getNaziv());
	    j++;
	}
	System.out.print("Odabir kategorije >> ");
	redniBrojKategorije = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	Kategorija<?> odabranaKategorija = listaKategorija.get(redniBrojKategorije - 1);
	List<Artikl> listaArtikalaKategorije = new ArrayList<>(odabranaKategorija.dohvatiListuArtikala());
	System.out.println("Odaberite artikl:");
	Integer redniBrojArtikla = 0;
	j = 0;
	for (Artikl artikl : listaArtikalaKategorije) {
	    System.out.println((j + 1) + ". " + artikl.getNaslov());
	    j++;
	}
	System.out.print("Odabir artikla >> ");
	redniBrojArtikla = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	Artikl odabraniArtikl = listaArtikalaKategorije.get(redniBrojArtikla - 1);
	LocalDate datumObjave = LocalDate.now();
	return new Prodaja(null, odabraniArtikl, odabraniKorisnik, datumObjave);
    }

    /**
     * Dodjeljivanje stanja artikla na temelju odabira korisnika iz enumeracije
     * stanje
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @return vraæa odabrano stanje artikla
     */
    private static Stanje unesiStanje(Scanner ucitavac) {
	for (int i = 0; i < Stanje.values().length; i++) {
	    System.out.println((i + 1) + ". " + Stanje.values()[i]);
	}
	int odabraniRedniBrojStanja = 0;
	while (true) {
	    System.out.print("Odabir stanja artikla: ");
	    odabraniRedniBrojStanja = provjeriIntBroj(ucitavac);
	    if ((odabraniRedniBrojStanja >= 1) && (odabraniRedniBrojStanja < Stanje.values().length)) {
		return Stanje.values()[odabraniRedniBrojStanja - 1];
	    } else {
		System.out.println("Neispravan unos!");
	    }
	}
    }

    /**
     * Kreira novi objekt stan na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vraæa novo kreirani objekt stan sa podacima o naslovu, opisu,
     *         kvadraturi i cijeni stana
     */
    private static Stan unesiStan(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa stana: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa stana: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite kvadraturu " + i + ". oglasa stana: ");
	int kvadratura = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	Stanje stanje = unesiStanje(ucitavac);
	System.out.print("Unesite cijenu " + i + ". oglasa stana: ");
	BigDecimal cijena = provjeriBigDecimalBroj(ucitavac);
	ucitavac.nextLine();
	return new Stan(naslov, opis, kvadratura, stanje, cijena);

    }

    /**
     * Kreira novi objekt usluga na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vraæa novo kreirani objekt usluge sa podacima o naslovu, opisu i
     *         cijeni usluge
     */
    private static Usluga unesiUslugu(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa usluge: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa usluge: ");
	String opis = ucitavac.nextLine();
	Stanje stanje = unesiStanje(ucitavac);
	System.out.print("Unesite cijenu " + i + ". oglasa usluge: ");
	BigDecimal cijena = provjeriBigDecimalBroj(ucitavac);
	ucitavac.nextLine();
	return new Usluga(naslov, opis, stanje, cijena);

    }

    /**
     * Kreira novi objekt automobila na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vraæa novo kreirani objekt automobila sa podacima o naslovu, opisu,
     *         snazi izraženoj u konjskim snagama kao i cijenom automobila
     */
    private static Automobil unesiAutomobil(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa automobila: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa automobila: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite snagu (u ks) " + i + ". oglasa automobila: ");
	BigDecimal snagaKs = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	Stanje stanje = unesiStanje(ucitavac);
	System.out.print("Unesite cijenu " + i + ". oglasa automobila: ");
	BigDecimal cijena = provjeriBigDecimalBroj(ucitavac);
	ucitavac.nextLine();
	return new Automobil(naslov, opis, snagaKs, stanje, cijena);
    }

    /**
     * Kreira novi objekt kategorije na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vraæa novo kreirani objekt kategorije sa podacima o nazivu, brojem
     *         artikala koji spadaju u tu kategoriju, tipu artikla kao i samim
     *         artiklima koji spadaju u tu kategoriju
     */
    private static Kategorija<?> unesiKategoriju(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + (i + 1) + ". kategorije: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");
	int brojArtikalaKategorije = provjeriIntBroj(ucitavac);
	ucitavac.nextLine();
	List<Artikl> listaArtikalaKategorije = new ArrayList<>();
	for (int j = 0; j < brojArtikalaKategorije; j++) {
	    System.out.println("Unesite tip " + (j + 1) + ". artikla");
	    int k = 0;
	    do {
		k = 1;
		System.out.println(k + ". Usluga");
		System.out.println((k + 1) + ". Automobil");
		System.out.println((k + 2) + ". Stan");
		System.out.print("Odabir ->> ");
		k = provjeriIntBroj(ucitavac);
		ucitavac.nextLine();
	    } while (k != 1 && k != 2 && k != 3);
	    if (k == 1) {
		listaArtikalaKategorije.add(unesiUslugu(ucitavac, j + 1));
	    }
	    if (k == 2) {
		listaArtikalaKategorije.add(unesiAutomobil(ucitavac, j + 1));
	    }
	    if (k == 3) {
		listaArtikalaKategorije.add(unesiStan(ucitavac, j + 1));
	    }
	}
	return new Kategorija<>(naziv, listaArtikalaKategorije);
    }

    /**
     * Kreira novi objekt privatnog korisnika na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt
     * @return vraæa novo kreiranog privatnog korisnika sa podacima o imenu,
     *         prezimenu, email-u i broju telefona
     */

    private static PrivatniKorisnik unesiPrivatnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite ime " + i + ". osobe: ");
	String ime = ucitavac.nextLine();
	ime = ime.substring(0, 1).toUpperCase() + ime.substring(1).toLowerCase();
	System.out.print("Unesite prezime " + i + ". osobe: ");
	String prezime = ucitavac.nextLine();
	prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1).toLowerCase();
	System.out.print("Unesite email " + i + ". osobe: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". osobe: ");
	String telefon = ucitavac.nextLine();
	return new PrivatniKorisnik(ime, prezime, email, telefon);
    }

    /**
     * Kreira novi objekt poslovnog korisnika na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @param i        omoguæava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt prodaje
     * @return vraæa novo kreiranog poslovnog korisnika sa podacima o nazivu,
     *         email-u, web stranici i broju telefona
     */

    private static PoslovniKorisnik unesiPoslovnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + i + ". tvrtke: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite e-Mail " + i + ". tvrtke: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite web " + i + ". tvrtke: ");
	String web = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". tvrtke: ");
	String telefon = ucitavac.nextLine();
	return new PoslovniKorisnik(naziv, email, web, telefon);
    }

    /**
     * Provjerava da li je unešen cijeli broj, te vraæa taj broj u sluèaju dobrog
     * unosa. U sluèaju da nije unešen cijeli broj, prisiljava korisnika na ponovni
     * unos
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @return vraæa validirani cijeli broj nakon dobrog unosa korisnika
     */
    private static int provjeriIntBroj(Scanner ucitavac) {
	boolean nastaviPetlju = false;
	int cijeliBroj = 0;
	do {
	    try {
		cijeliBroj = ucitavac.nextInt();
		nastaviPetlju = false;
	    } catch (InputMismatchException e) {
		logger.info("Pogreška prilikom unosa int tipa podatka");
		System.out.println("Morate unijeti cjelobrojnu vrijednost!");
		System.out.print("Unesite ponovno broj: ");
		ucitavac.nextLine();
		nastaviPetlju = true;
	    }
	} while (nastaviPetlju);
	return cijeliBroj;
    }

    /**
     * Provjerava da li je unešen decimalni broj, te vraæa taj broj u sluèaju dobrog
     * unosa. U sluèaju da nije unešen decimalni broj, prisiljava korisnika na
     * ponovni unos
     * 
     * @param ucitavac omoguæava korisnièki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom sluèaju omoguæava korisnièki
     *                 unos putem tipkovnice
     * @return vraæa validirani decimalni broj nakon dobrog unosa korisnika
     */
    private static BigDecimal provjeriBigDecimalBroj(Scanner ucitavac) {
	boolean nastaviPetlju = false;
	BigDecimal decimalniiBroj = new BigDecimal(0);
	do {
	    try {
		decimalniiBroj = ucitavac.nextBigDecimal();
		nastaviPetlju = false;
	    } catch (InputMismatchException e) {
		logger.info("Pogreška prilikom unosa BigDecimal tipa podatka");
		System.out.println("Morate unijeti decimalnu vrijednost!");
		System.out.print("Unesite ponovno cijeli broj: ");
		ucitavac.nextLine();
		nastaviPetlju = true;
	    }
	} while (nastaviPetlju);
	return decimalniiBroj;
    }

    /**
     * @return vraæa horizontalnu iscrtanu liniju za odjeljivanje bitnih dijelova
     *         kod ispisa rezultata
     */
    private static String horizontalIsprekidanaLine() {
	String isprekidanaLinija = "";
	for (int j = 0; j < 100; j++) {
	    isprekidanaLinija += "-";
	}
	return isprekidanaLinija;
    }
}
