public class JednoPolicko {

    private boolean skryte;
    private int hodnota;
    private Obrazok rub;
    private Obrazok obrazok;

    public JednoPolicko(int x, int y, int hodnota) {
        this.skryte = true;
        this.hodnota = hodnota;
        this.rub = new Obrazok("Obrazky\\rub.png"); // rub pexesa(obal)
        this.rub.nastavSuradnice(x, y);
        this.obrazok = new Obrazok("Obrazky\\0.png"); // jednotlivé obrázky
        this.obrazok.nastavSuradnice(x, y);
    }

    public void zobraz() {
        if (this.skryte) {
            this.rub.zobraz();
            this.obrazok.skry();
        } else {
            this.obrazok.zmenObrazok("Obrazky\\" + this.hodnota + ".png");
            this.rub.skry();
            this.obrazok.zobraz();
        }
    }
    public boolean obsahujeBod(int x, int y) {
        return this.rub.obsahujeBod(x, y);
    }

    public boolean jeZakryte() {
        return this.skryte;
    }

    public void odkry() {
        this.skryte = false;
        this.zobraz();
    }

    public void zakry() {
        this.skryte = true;
        this.zobraz();
    }

    public int dajHodnotu() {
        return this.hodnota;
    }
}
