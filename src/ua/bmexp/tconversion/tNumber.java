package ua.bmexp.tconversion;


public class tNumber {
    private static double H = 0.002;
    private static int disc = 12;

    protected final double pi = Math.PI;
    protected double[] discrets;

    private void initialize() {
        discrets = new double[disc];
        for (int i = 0; i < disc; i++) {
            discrets[i] = 0;
        }
    }

    public tNumber() {
        super();
        initialize();
    }

    public tNumber(double[] StartConditions) throws Exception {
        super();
        initialize();
        for (int i = 0; i < StartConditions.length; i++) {
            discrets[i] = (Math.pow(H, i) / factorial(i)) * StartConditions[i];
        }
    }

    public static double Gamma(double z) {
        double tmp1 = Math.sqrt(2 * Math.PI / z);
        double tmp2 = z + 1.0 / (12 * z - 1.0 / (10 * z));
        tmp2 = Math.pow(z / Math.E, z); // ooops; thanks hj
        tmp2 = Math.pow(tmp2 / Math.E, z);
        return tmp1 * tmp2;
    }

    public static long factorial(int f) throws Exception {
        if (f < 0) throw new NegativeArgument(f);            
        return ((f == 0) ? 1 : f * factorial(f - 1));
    }

    public tNumber add(tNumber x) {
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] + x.discrets[i];
        }
        return res;
    }

    public tNumber add(double x) {
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] + x;
        }
        return res;
    }

    public tNumber sub(tNumber x) {
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] - x.discrets[i];
        }
        return res;
    }

    public tNumber sub(double x) {
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] - x;
        }
        return res;
    }

    public tNumber mult(double x) {
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] * x;
        }
        return res;
    }

    public tNumber mult(tNumber x) {
        tNumber res = new tNumber();
        for (int k = 0; k < disc; k++) {
            for (int l = 0; l <= k; l++) {
                res.discrets[k] += discrets[l] * x.discrets[k - l];
            }
        }
        return res;
    }

    public tNumber div(double x) throws Exception {
        if (x == 0) throw new ZeroDiv();  
        tNumber res = new tNumber();
        for (int i = 0; i < disc; i++) {
            res.discrets[i] = discrets[i] / x;
        }
        return res;
    }
    
    public tNumber div(tNumber x) throws Exception {
        if (x.discrets[0] == 0)
            throw new ZeroDiv();
        tNumber res = new tNumber();
        double[] dis = new double[disc];
        for (int k = 0; k < disc; k++) {
            for (int l = 0; l <= k - 1; l++) {
                dis[k] += res.discrets[l] * x.discrets[k - l];
            }
            res.discrets[k] = (discrets[k] - dis[k]) / x.discrets[0];
        }
        return res;
    }
    
   public tNumber diff(int i) throws Exception {
        if (i < 0) throw new IllegalArgumentException("Differential cannot be negative");
        tNumber res = new tNumber();
        for(int k = 0; k < disc-i; k++){
            res.set(k, factorial(k+i)*this.discrets[k+i]/(factorial(k)*Math.pow(H, i)));
        }
        return res;
    }
   
   public tNumber integral(double StartCondition) throws Exception {
       tNumber res = new tNumber();
       res.set(0,StartCondition);
       for(int k = 1; k < disc; k++){
           res.set(k, this.discrets[k-1]*H/k);
       }
       return res;
   }

    public double getOriginal(double t) {
        if (t == 0)
            return discrets[0];
        double res = 0;
        for (int i = 0; i < disc; i++) {
            res += Math.pow(t / H, i) * discrets[i];
        }
        return res;
    }

    public void printNumber() {
        for (int i = 0; i < disc; i++) {
            System.out.println("X[" + i + "] = " + discrets[i]);
        }
        System.out.println();
    }
    
    public void printOriginal() {
        for(double t = 0; t <= 5*H/2; t += H/10) {
        System.out.println("x("+String.format( "%.7f", t )+") = "+getOriginal(t));
        }
        System.out.println();
    }

    public double getPi() {
        return pi;
    }

    public static void setH(double H) {
        tNumber.H = H;
    }

    public static double getH() {
        return H;
    }

    public static void setDisc(int disc) {
        tNumber.disc = disc;
    }

    public static int getDisc() {
        return disc;
    }

    public void set(int i, double s) {
        this.discrets[i] = s;
    }

    public double get(int i) {
        return discrets[i];
    }
}
