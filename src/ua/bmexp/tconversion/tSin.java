package ua.bmexp.tconversion;


public class tSin extends tNumber {
    public tSin(double w, double a) throws Exception {
        super();
        for (int i = 0; i < getDisc(); i++) {
            this.discrets[i] = Math.pow(w * getH(), i) * Math.sin(pi * i / 2 + a)/ factorial(i) ;
        }
    }

    public tSin(double Amplituda, double w, double a) throws Exception {
        super();
        for (int i = 0; i < getDisc(); i++) {
            this.discrets[i] = Amplituda * Math.pow(w * getH(), i) * Math.sin(pi * i / 2 + a)/ factorial(i) ;
        }
    }

}
