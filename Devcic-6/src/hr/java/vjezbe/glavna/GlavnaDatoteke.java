package hr.java.vjezbe.glavna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

public class GlavnaDatoteke {

    private static final String FORMAT_DATUMA = "dd.MM.yyyy.";
    private static final String FILE_KORISNICI = "dat/korisnici.txt";
    private static final String FILE_ARTIKLI = "dat/artikli.txt";
    private static final String FILE_KATEGORIJE = "dat/kategorije.txt";

    private static final int BROJ_LINIJA_KATEGORIJE = 3;

    public static void main(String[] args) {

	List<Korisnik> listaKorisnika = new ArrayList<>();
	List<Artikl> listaArtikala = new ArrayList<>();
	List<Kategorija<?>> listaKategorija = new ArrayList<>();
	List<String> listaProdaja = new ArrayList<>();

	System.out.println("Uèitavanje korisnika...");
	listaKorisnika = dohvatiKorisnike();
	System.out.println("Uèitavanje artikala...");
	listaArtikala = dohvatiArtikle();
	System.out.println("Uèitavanje kategorija...");
	listaKategorija = dohvatiKategorije(listaArtikala);
	System.out.println("Uèitavanje prodaja...");
	listaProdaja = dohvatiProdaje();

//	listaKategorija.stream().forEach(System.out::println);

//	for (String string : listaKorisnika) {
//	    System.out.println(string);
//	}
//	listaArtikala.stream().forEach(System.out::println);

	Map<Long, Korisnik> mapaKorisnika = new HashMap<>();
	Map<Long, Artikl> mapaArtikala = new HashMap<>();
	Map<Long, Kategorija<?>> mapaKategorija = new HashMap<>();
	Map<Long, Prodaja> mapaProdaja = new HashMap<>();

	mapaKorisnika = kreiranjeObjekataKorisnika(listaKorisnika);
//	mapaArtikala = kreiranjeObjekataArtikala(listaArtikala);
	mapaKategorija = kreiranjeObjekataKategorija(listaKategorija);
	mapaProdaja = kreiranjeObjekataProdaja(listaProdaja);

    }

    private static Map<Long, Prodaja> kreiranjeObjekataProdaja(List<String> listaProdaja) {
	Map<Long, Prodaja> mapaProdaja = new HashMap<>();

	return mapaProdaja;
    }

    private static List<String> dohvatiProdaje() {
	List<String> listaProdaja = new ArrayList<>();

	return listaProdaja;
    }

    private static Map<Long, Kategorija<?>> kreiranjeObjekataKategorija(List<Kategorija<?>> listaKategorija) {
	Map<Long, Kategorija<?>> mapaKategorija = new HashMap<>();

	return mapaKategorija;
    }

    private static <T> List<Kategorija<?>> dohvatiKategorije(List<Artikl> listaArtikala) {
	List<String> stringListaKategorija = new ArrayList<>();
	List<Kategorija<?>> listaKategorija = new ArrayList<>();

	Long id = null;
	String naziv = null;
	

	try (BufferedReader bufferedCitac = new BufferedReader(new FileReader(FILE_KATEGORIJE))) {
	    while (true) {
		String linija = bufferedCitac.readLine();
		if (linija == null) {
		    break;
		}
		stringListaKategorija.add(linija);
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < stringListaKategorija.size(); i++) {
	    List<Artikl> listaArtikalaKategorije = new ArrayList<>();
	    String linija = stringListaKategorija.get(i);
	    switch (i % BROJ_LINIJA_KATEGORIJE) {
	    case 0:
		id = Long.parseLong(linija);
		break;
	    case 1:
		naziv = linija;
		break;
	    case 2: {
		String[] stringVrijednosti = linija.split(" ");
		int[] idArtikala = new int[stringVrijednosti.length];
		for (int j = 0; j < stringVrijednosti.length; j++) {
		    idArtikala[j] = Integer.parseInt(stringVrijednosti[j]);

		}
		for (int idArtikl : idArtikala) {
		    listaArtikalaKategorije.add(listaArtikala.get(idArtikl-1));
		}

		listaKategorija.add(new Kategorija(id, naziv, listaArtikalaKategorije));
	    }
		break;
	    }

	}
	return listaKategorija;
    }

    private static Map<Long, Artikl> kreiranjeObjekataArtikala(List<String> listaArtikala) {
	Map<Long, Artikl> mapaArtikala = new HashMap<>();

	return mapaArtikala;
    }

    private static List<Artikl> dohvatiArtikle() {

	List<String> stringListaArtikala = new ArrayList<>();
	List<Artikl> listaArtikala = new ArrayList<>();

	Long idUsluge = null;
	String naslovUsluge = null;
	String opisUsluge = null;
	Stanje stanjeUsluge = null;
	BigDecimal cijenaUsluge = null;

	Long idAutomobila = null;
	String naslovAutomobila = null;
	String opisAutomobila = null;
	BigDecimal snagaKsAutomobila = null;
	Stanje stanjeAutomobila = null;
	BigDecimal cijenaAutomobila = null;

	Long idStana = null;
	String naslovStana = null;
	String opisStana = null;
	int kvadratura = 0;
	Stanje stanjeStana = null;
	BigDecimal cijenaStana = null;

	try (Stream<String> stream = Files.lines(new File(FILE_ARTIKLI).toPath(), Charset.forName("ISO-8859-2"))) {
	    stringListaArtikala = stream.collect(Collectors.toList());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	int x = 0;
	for (int j = x; j < stringListaArtikala.size(); j++) {
	    if (x < stringListaArtikala.size()) {
		Integer tipArtikla = Integer.parseInt(stringListaArtikala.get(0 + x));
		if (tipArtikla == 1) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idUsluge = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovUsluge = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisUsluge = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    stanjeUsluge = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 5) {
			    cijenaUsluge = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Usluga(idUsluge, naslovUsluge, opisUsluge, stanjeUsluge, cijenaUsluge));
		    x += 6;
		} else if (tipArtikla == 2) {
		    for (int i = x; i < x + 7; i++) {
			if (i == x + 1) {
			    idAutomobila = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovAutomobila = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisAutomobila = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    snagaKsAutomobila = new BigDecimal(stringListaArtikala.get(i));
			} else if (i == x + 5) {
			    stanjeAutomobila = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 6) {
			    cijenaAutomobila = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Automobil(idAutomobila, naslovAutomobila, opisAutomobila, snagaKsAutomobila,
			    stanjeAutomobila, cijenaAutomobila));
		    x += 7;
		} else if (tipArtikla == 3) {
		    for (int i = x; i < x + 7; i++) {
			if (i == x + 1) {
			    idStana = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovStana = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisStana = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    kvadratura = Integer.parseInt(stringListaArtikala.get(i));
			} else if (i == x + 5) {
			    stanjeStana = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 6) {
			    cijenaStana = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Stan(idStana, naslovStana, opisStana, kvadratura, stanjeStana, cijenaStana));
		    x += 7;
		}
	    } else {
		break;
	    }
	}
	return listaArtikala;

    }

    private static Map<Long, Korisnik> kreiranjeObjekataKorisnika(List<Korisnik> listaKorisnika) {
	Map<Long, Korisnik> mapaKorisnika = new HashMap<>();

	return mapaKorisnika;
    }

    private static List<Korisnik> dohvatiKorisnike() {
	List<String> stringListaKorisnika = new ArrayList<>();
	List<Korisnik> listaKorisnika = new ArrayList<>();

	Long idPrivatniKorisnik = null;
	String imePrivatniKorisnik = null;
	String prezimePrivatniKorisnik = null;
	String emailPrivatniKorisnik = null;
	String telefonPrivatniKorisnik = null;

	Long idPoslovniKorisnik = null;
	String nazivPoslovniKorisnik = null;
	String webPoslovniKorisnik = null;
	String emailPoslovniKorisnik = null;
	String telefonPoslovniKorisnik = null;

	try (BufferedReader bufferedCitac = new BufferedReader(new FileReader(FILE_KORISNICI));) {
//	    FileReader citac = new FileReader(FILE_PRIVATNI_KORISNICI);
//	    BufferedReader bufferedCitac = new BufferedReader(citac);
	    String linija;
	    while ((linija = bufferedCitac.readLine()) != null) {
		stringListaKorisnika.add(linija);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	int x = 0;
	for (int j = x; j < stringListaKorisnika.size(); j++) {
	    if (x < stringListaKorisnika.size()) {
		Integer tipKorisnika = Integer.parseInt(stringListaKorisnika.get(0 + x));
		if (tipKorisnika == 1) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idPrivatniKorisnik = Long.parseLong(stringListaKorisnika.get(i));
			} else if (i == x + 2) {
			    imePrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 3) {
			    prezimePrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 4) {
			    emailPrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 5) {
			    telefonPrivatniKorisnik = stringListaKorisnika.get(i);
			}
		    }
		    listaKorisnika.add(new PrivatniKorisnik(idPrivatniKorisnik, imePrivatniKorisnik,
			    prezimePrivatniKorisnik, emailPrivatniKorisnik, telefonPrivatniKorisnik));
		    x += 6;
		} else if (tipKorisnika == 2) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idPoslovniKorisnik = Long.parseLong(stringListaKorisnika.get(i));
			} else if (i == x + 2) {
			    nazivPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 3) {
			    webPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 4) {
			    emailPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 5) {
			    telefonPoslovniKorisnik = stringListaKorisnika.get(i);
			}
		    }
		    listaKorisnika.add(new PoslovniKorisnik(idPoslovniKorisnik, nazivPoslovniKorisnik,
			    webPoslovniKorisnik, emailPoslovniKorisnik, telefonPoslovniKorisnik));
		    x += 6;
		}

	    } else {
		break;
	    }
	}
	return listaKorisnika;
    }
}
