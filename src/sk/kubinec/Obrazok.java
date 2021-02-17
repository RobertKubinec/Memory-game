package sk.kubinec;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;

public class Obrazok {

    private boolean jeViditelny;
    private int lavyHornyX;
    private int lavyHornyY;
    private BufferedImage obrazok;

    public Obrazok(String suborSObrazkom) {
        this.obrazok = this.nacitajObrazokZoSuboru(suborSObrazkom);

        this.jeViditelny = false;
        this.lavyHornyX = 100;
        this.lavyHornyY = 100;
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    public void zmenObrazok(String suborSobrazkom) {
        boolean nakresleny = this.jeViditelny;
        this.zmaz();
        this.obrazok = this.nacitajObrazokZoSuboru(suborSobrazkom);

        if (nakresleny) {
            this.nakresli();
        }
    }

    public void zmenPolohu(int stredX, int stredY) {
        boolean nakresleny = this.jeViditelny;
        this.zmaz();
        this.lavyHornyX = stredX - this.sirka() / 2;
        this.lavyHornyY = stredY - this.vyska() / 2;

        if (nakresleny) {
            this.nakresli();
        }
    }

    public void nastavSuradnice(int x, int y) {
        this.lavyHornyX = x;
        this.lavyHornyY = y;
    }

    private BufferedImage nacitajObrazokZoSuboru(String subor) {
        BufferedImage nacitanyOBrazok = null;

        try {
            nacitanyOBrazok = ImageIO.read(new File(subor));
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Subor " + subor + " sa nenasiel.");
        }

        return nacitanyOBrazok;
    }

    private int sirka() {
        return this.obrazok.getWidth();
    }

    private int vyska() {
        return this.obrazok.getHeight();
    }

    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();

            AffineTransform at = new AffineTransform();
            at.translate(this.lavyHornyX, this.lavyHornyY);

            canvas.draw(this, obrazok, at);

            canvas.wait(10);
        }
    }

    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }

    public boolean obsahujeBod(int x, int y) {
        if (x < this.lavyHornyX || x > this.lavyHornyX + this.sirka())
            return false;

        if (y < this.lavyHornyY || y > this.lavyHornyY + this.vyska())
            return false;

        return true;
    }
}
