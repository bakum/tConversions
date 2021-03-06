package ua.bmexp.test;


import java.awt.Color;

import ua.bmexp.stdout.StdDraw;
import ua.bmexp.tconversion.*;


public class Conversion {

    public static void main(String[] args) throws Exception {
        /* int N = 100;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-N*N, N*N);
        StdDraw.setPenRadius(.008);

        for (int i=1;i<=N;i++){
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*Math.log(i));
        } */

        /* double R = 10, L = 0.01, e = 100;
        double[] startConditions = { 0.0 };

        tNumber.setH(L / R);

        tNumber I = new tNumber(startConditions);
        tCos E = new tCos(e, 2 * Math.PI * 300, 0);
        for (int k = 0; k < tNumber.getDisc() - 1; k++) {
            I.set(k + 1, ((E.get(k) - R * I.get(k)) / (k + 1)) * tNumber.getH() / L);
        }
        System.out.println("Tau = " + 4 * L / R);
        E.printNumber();
        E.printOriginal();
        I.printNumber();
        I.printOriginal();

        StdDraw.setXscale(0, 5 * tNumber.getH() / 2);
        StdDraw.setYscale(-5, 5);
        StdDraw.setPenRadius(.008);

        for (double t = 0; t <= 5 * tNumber.getH() / 2; t += tNumber.getH() / 10) {
            StdDraw.point(t, I.getOriginal(t));
           // StdDraw.point(t, E.getOriginal(t));
            StdDraw.point(t, 0);
        } 
        
        Complex u = Complex.valueOf(100, 50);
        Complex r = Complex.valueOf(34, 0);
        System.out.println(u.toString());
        System.out.println(r.toString());*/
        
        tMatrix tm = new tMatrix(1,2);
        double R = 100, L = 0.01, C = 0.00001, e = 100, Uc = 100;
        double [] startConditions = {e/R, (-e-Uc)/L};
        tNumber.setH(L/R);
        tNumber.setDiscCount(30);
        tExp F = new tExp(1, -4*R / L);
        //tNumber F = (new tTeta(100)).sub(Exp);

        

        double H = tNumber.getH();
        int d = tNumber.getDiscCount();

        tNumber I = new tNumber(startConditions);
        tTeta E = new tTeta(e);
        //tNumber c = E.diff(1);
        for(int k = 0; k < d-2; k++){
            I.set(k+2, (-(I.get(k)/C)-((k+1)*I.get(k+1)*R/H))*Math.pow(H, 2)/(L*(k+1)*(k+2)));
        }
        //c.printNumber();
        E.printNumber();
        E.printOriginal();
        I.printNumber();
        System.out.println("i(t)");
        I.printOriginal();
        System.out.println("Ur(t)");
        I.mult(R).printOriginal();
        System.out.println("Ul(t)");
        I.diff(1).mult(L).printOriginal();
        System.out.println("Uc(t)");
        I.integral(Uc*C).div(C).printOriginal();
        
        StdDraw.setXscale(0, 2*tNumber.getH());
        StdDraw.setYscale(-1, 1);
        StdDraw.setPenRadius(.006);
        StdDraw.setPenColor(Color.BLUE);
        
        for (double t = 0; t <= 5 * tNumber.getH(); t += tNumber.getH() / 100) {
            StdDraw.point(t, F.getOriginal(t));
           StdDraw.point(t, I.getOriginal(t));
            StdDraw.point(t, 0);
        } 


    }
}
