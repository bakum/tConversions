package ua.bmexp.tconversion;


public class NegativeArgument extends Exception {
    @SuppressWarnings("compatibility:-4646795605116931661")
    private static final long serialVersionUID = 1L;
    int arg;
    public NegativeArgument(int i) {
       arg = i;
    }
    
    public String toString(){
        return "Argument of factorial "+arg+" cannot be negative";
    }
}
