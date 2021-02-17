package sk.kubinec; /**
 * časť kódu prevzatá z cvičenia
 */
import java.util.Random;//import balíčka, náhodné generovanie
/**
 * @author «Róbert Kubinec»
 * @version «2018»
 */
public class HraciePole {
    private int aktualnaHodnota = 0;
    private JednoPolicko[][] matica;
    private JednoPolicko posledneOdkryte[];
    private boolean dveOdkryte;
    private int pocetNajdenych;

    public HraciePole(int m, int n) {
        Random random = new Random(); //náhodné rozmiestnenie obrázkov v hracom poli
        int pocitadla[] = new int[8];

        this.matica = new JednoPolicko[m][];
        for (int i = 0; i < this.matica.length; i++) {
            this.matica[i] = new JednoPolicko[n];
            for (int j = 0; j < this.matica[i].length; j++) {
                int nahodne = random.nextInt(8);//zoberie náhodné číslo, ktoré je priradené obrázku, od 0-7
                while (pocitadla[nahodne] == 2) {
                    nahodne = random.nextInt(8);
                }
                pocitadla[nahodne]++;
                JednoPolicko policko = new JednoPolicko(5 + (j * 175), 5 + (i * 175), nahodne);//priradenie obr. do policka
                this.matica[i][j] = policko;
            }
        }
        this.posledneOdkryte = new JednoPolicko[2];
        this.dveOdkryte = false;
        this.pocetNajdenych = 0;
    }
    public void zobraz() {
        for (int i = 0; i < this.matica.length; i++) {
            for (int j = 0; j < this.matica[i].length; j++)
                this.matica[i][j].zobraz();
        }
    }
    /**
     * Metóda, ktorá zaručí, že ak klikneme na 2 obrázky, ktoré sú rôzne, tak
     * sa skryjú a ak sú rovnaké, tak ostanú otvorené
     */
    public void vyberSuradnice(int x, int y) {
        JednoPolicko najdene = null;
        for (int i = 0; i < this.matica.length; i++) {
            for (int j = 0; j < this.matica[i].length; j++) {
                if (this.matica[i][j].jeZakryte()) {
                    if (this.matica[i][j].obsahujeBod(x, y)) {
                        najdene = this.matica[i][j];
                        break;
                    }
                }
            }
        }
        //zisťujeme, či sú odkryté práve 2 políčka
        if (dveOdkryte) {
            for (int i = 0; i < 2; i++) {
                this.posledneOdkryte[i].zakry();
                this.posledneOdkryte[i] = null;
            }
            dveOdkryte = false;
        }
        // keď odkryjeme 2 políčka, zisťujeme, čí sú rovnaké
        // ak odkryjemene 2 políčka, ktoré sú rovnaké, tak ostanú otvorené
        if (najdene != null) {
            najdene.odkry();
            if (this.posledneOdkryte[0] != null) {
                if (this.posledneOdkryte[0].dajHodnotu() != najdene.dajHodnotu()) {
                    this.dveOdkryte = true;
                    this.posledneOdkryte[1] = najdene;
                } else {
                    this.posledneOdkryte[0] = null;
                    this.pocetNajdenych++;
                    if (this.pocetNajdenych == 8) {
                        System.out.println("KONIEC HRY!");
                    }
                }
            } else {
                this.posledneOdkryte[0] = najdene;
            }
        }
    }
}

