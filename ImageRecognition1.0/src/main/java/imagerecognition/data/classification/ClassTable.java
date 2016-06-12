
package imagerecognition.data.classification;

/**
 *
 * ClassTable on luokka, joka sisältää luokkien ihmisluettavat nimet.
 */
public class ClassTable {
    private String[] classNames;
    
    /**
     * Konstruktori ClassTable-luokalle.
     * @param size taulun koko
     */
    public ClassTable(int size) {
        this.classNames = new String[size];
        init();
    }
    /**
     * Konstruktori luo luokkataulun nimien perusteella.
     * @param names nimet
     */
    public ClassTable(String[] names) {
        this.classNames = names;
    }
    
 
    /**
     * Metodi palauttaa luokkanumeroa vastaavan nimen.
     * @param classNumber luokkanumero
     * @return luokan nimi
     */
    public String getClassName(int classNumber) {
        return classNames[classNumber];
    }
    
    private void init() {
        for (int i = 0; i < classNames.length; i++) {
            setClassName("Class " + i, i);
        } 
    }
    
    /**
     * Metodi asettaa luokkanumeroa vastaavan luokan nimeksi parametrina
     * annetun nimen.
     * @param className luokkanimi
     * @param classNumber luokkanumero
     */
    public void setClassName(String className, int classNumber) {
        classNames[classNumber] = className;
    }
    
    /**
     * 
     * @return taulukon koko  
     */
    public int getNumberOfClasses() {
        return classNames.length;
    }
    
    
    
    /**
     * Metodi palauttaa nimeä vastaavan luokan luokkanumeron tai -1 jos luokkaa
     * ei löydy.
     * @param className luokkanimi
     * @return luokkanumero
     */
    public int findClassNumber(String className) {
        
        for (int i = 0; i < classNames.length; i++) {
            if (classNames[i].equals(className)) {
                return i;
            }
        }
        
        return -1;
    }

    public String[] getClassNames() {
        return classNames;
    }
    
    
}
