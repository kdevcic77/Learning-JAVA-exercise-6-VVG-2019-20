package hr.java.vjezbe.entitet;

/**
 * @author deva
 * @version Devcic-6
 */
public enum Stanje {
    novo, izvrsno, rabljeno, neispravno;
    
    public static Stanje fromInteger(int broj) {
        switch(broj) {
        case 1:
            return novo;
        case 2:
           return izvrsno;
        case 3:
            return rabljeno;
        case 4:
           return neispravno;
        }
        return null;
    }
}
