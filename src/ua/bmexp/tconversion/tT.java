package ua.bmexp.tconversion;


public class tT extends tNumber{
    public tT() {
        super();
        this.set(1, this.getH());
    }
    
    public tT(double Amplituda) {
        super();
        this.set(1, this.getH() * Amplituda);
    }
}
