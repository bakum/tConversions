package ua.bmexp.tconversion;


public class tCos extends tNumber {
    public tCos(double w, double a) throws Exception {
        super();
        for (int i = 0; i < getDisc(); i++) {
            this.discrets[i] = Math.pow(w * getH(), i)* Math.cos(pi * i / 2 + a) / factorial(i) ;
        }
    }

    public tCos(double Amplituda, double w, double a) throws Exception {
        super();
        for (int i = 0; i < getDisc(); i++) {
            this.discrets[i] = Amplituda * Math.pow(w * getH(), i) * Math.cos(pi * i / 2 + a)/ factorial(i) ;
        }
    }

}
