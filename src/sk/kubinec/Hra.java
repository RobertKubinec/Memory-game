package sk.kubinec;

/**
 *
 * @author   «Róbert Kubinec»
 * @version  «2018»
 */
public class Hra
{
    /**
     * Konštruktor objektov triedy src.sk.kubinec.Hra.
     */
    public Hra()
    {
        HraciePole matica = new HraciePole(4, 4); //hracie pole o veľkosti 4x4
        matica.zobraz();
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(matica);
    }
}
