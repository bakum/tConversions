package ua.bmexp.tconversion;


public class ZeroDiv  extends Exception{
    @SuppressWarnings("compatibility:1262139188102538006")
    private static final long serialVersionUID = 1L;


    public ZeroDiv() {
        super();
    }
    
    public String toString(){
        return "Argument cannot be zero";
    }
}
