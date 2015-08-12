package ua.bmexp.tconversion;


public class tExp extends tNumber {
    public tExp(double l) throws Exception {
        super();
        for (int i = 0; i < getDiscCount(); i++) {
            this.discrets[i] = Math.pow(l * getH(), i) / factorial(i);
        }
    }
    
    public tExp(double Amplituda, double l) throws Exception {
        super();
        for (int i = 0; i < getDiscCount(); i++) {
            this.discrets[i] = Amplituda* Math.pow(l * getH(), i) / factorial(i);
        }
    }

}
