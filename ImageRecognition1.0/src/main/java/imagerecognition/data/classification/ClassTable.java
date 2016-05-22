/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagerecognition.data.classification;

/**
 *
 * @author Simo
 */
public class ClassTable {
    private String[] classNames;
    public ClassTable(int size) {
        this.classNames = new String[size];
        init();
    }
    
    public String getClassName(int classNumber) {
        return classNames[classNumber];
    }
    
    private void init() {
        for (int i = 0; i < classNames.length; i++) {
            setClassName("Class " + i, i);
        } 
    }
    
    public void setClassName(String className, int classNumber) {
        classNames[classNumber] = className;
    }
    
    public int getNumberOfClasses() {
        return classNames.length;
    }
    
    public int findClassNumber(String className) {
        
        for (int i = 0; i < classNames.length; i++) {
            if (classNames[i].equals(className)) {
                return i;
            }
        }
        
        return -1;
    }
}
