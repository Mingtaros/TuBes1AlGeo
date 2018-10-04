import java.util.*;  // needed for Scanner
import java.text.*;
import java.lang.Math.*;
import java.io.*;

/* memanggil Matriks.Java */
public class TuBes {

  public static void main(String args[])
  {
    Scanner scanner = new Scanner( System.in );

    char lagi = 'y';

    while ((lagi == 'y' || lagi == 'Y'))
    {
      int hasil1 = Matriks.menu1();
      if (hasil1 == 1)
      { //Sistem Persamaan Linear
        int hasil2 = Matriks.menu2();
        if (hasil2 == 1)
        { //matriks input
          System.out.print("Masukkan Panjang Baris : "); int m = scanner.nextInt();
          System.out.print("Masukkan Panjang Kolom (augmented) : "); int n = scanner.nextInt();
          Matriks mat = new Matriks(m, n);
          mat.bacam();

          int hasil3 = Matriks.menu3();
          if (hasil3 == 1)
          { //metode gauss
            mat.Gauss();
            System.out.println("Bentuk REF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss mu :");
            mat.solveGauss();
            System.out.println(mat.pers);
            mat.tulisf(true);
          }
          else if (hasil3 == 2)
          { //metode gauss-jordan
            mat.GaussJordan();
            System.out.println("Bentuk RREF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss-Jordan mu :");
            mat.solveGaussJordan();
            System.out.println(mat.pers);
            mat.tulisf(true);
          }
          else if (hasil3 == 3)
          {
            break;
          }
        }
        else if (hasil2 == 2)
        { //matriks input dri file
          Matriks mat = new Matriks(1, 1);
          mat.bacafile();
          mat.tulism();
          int hasil3 = Matriks.menu3();
          if (hasil3 == 1)
          { //metode gauss
            mat.Gauss();
            System.out.println("Bentuk REF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss mu :");
            mat.solveGauss();
            System.out.println(mat.pers);
            mat.tulisf(true);
          }
          else if (hasil3 == 2)
          { //metode gauss-jordan
            mat.GaussJordan();
            System.out.println("Bentuk RREF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss-Jordan mu :");
            mat.solveGaussJordan();
            System.out.println(mat.pers);
            mat.tulisf(true);
          }
          else if (hasil3 == 3)
          {
            break;
          }
        }
        else if (hasil2 == 3)
        {
          break;
        }
      }
      else if (hasil1 == 2)
      { //Interpolasi Polinom
        int hasil2 = Matriks.menu2();
        if (hasil2 == 1)
        {
          System.out.print("Masukkan Banyaknya titik : "); int m = scanner.nextInt();
          Matriks mat = new Matriks(m, 2);
          mat.bacam();
          mat.Interpolasi();
          int hasil3 = Matriks.menu3();
          if (hasil3 == 1)
          {
            mat.Gauss();
            System.out.println("Bentuk REF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss mu :");
            mat.solveGauss();
            System.out.println(mat.pers);
            if (mat.IsNoSol())
            {
              System.out.println("Kurva Invalid! Ada Garis Vertikal pada kurva anda!");
              break;
            }
            System.out.print("Masukkan nilai X : ");
            mat.x = scanner.nextInt();
            mat.solveInterpolasiGauss();
            mat.tulisf(false); //karena yang disimpan akan selalu berbentuk persamaan dan nilai fungsi
          }
          else if (hasil3 == 2)
          {
            mat.GaussJordan();
            System.out.println("Bentuk RREF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss-Jordan mu :");
            mat.solveGaussJordan();
            System.out.println(mat.pers);
            if (mat.IsNoSol())
            {
              System.out.println("Kurva Invalid! Ada Garis Vertikal pada kurva anda!");
              break;
            }
            System.out.print("Masukkan nilai X : ");
            mat.x = scanner.nextInt();
            mat.solveInterpolasiGaussJordan();
            mat.tulisf(false); //karena yang disimpan akan selalu berbentuk persamaan dan nilai fungsi
          }
          else if (hasil3 == 3)
          {
            break;
          }
        }
        else if (hasil2 == 2)
        {
          //apabila input dri file
          Matriks mat = new Matriks(1, 1);
          mat.bacafile();
          mat.Interpolasi();
          mat.tulism();
          int hasil3 = Matriks.menu3();
          if (hasil3 == 1)
          {
            mat.Gauss();
            System.out.println("Bentuk REF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss mu :");
            mat.solveGauss();
            System.out.println(mat.pers);
            if (mat.IsNoSol())
            {
              System.out.println("Kurva Invalid! Ada Garis Vertikal pada kurva anda!");
              break;
            }
            System.out.print("Masukkan nilai X : ");
            mat.x = scanner.nextInt();
            mat.solveInterpolasiGauss();
            mat.tulisf(false); //karena yang disimpan akan selalu berbentuk persamaan dan nilai fungsi
          }
          else if (hasil3 == 2)
          {
            mat.GaussJordan();
            System.out.println("Bentuk RREF Matriksmu :");
            mat.tulism();
            System.out.println("Hasil Solve Gauss-Jordan mu :");
            mat.solveGaussJordan();
            System.out.println(mat.pers);
            if (mat.IsNoSol())
            {
              System.out.println("Kurva Invalid! Ada Garis Vertikal pada kurva anda!");
              break;
            }
            System.out.print("Masukkan nilai X : ");
            mat.x = scanner.nextInt();
            mat.solveInterpolasiGaussJordan();
            mat.tulisf(false); //karena yang disimpan akan selalu berbentuk persamaan dan nilai fungsi
          }
          else if (hasil3 == 3)
          {
            break;
          }
        }
        else if (hasil2 == 3)
        {
          break;
        }
      }
      else if (hasil1 == 3) {
        break;
      }
      System.out.print("Ada Perhitungan yang lain?(Y = Yes, N = No) "); lagi = scanner.next().charAt(0);
      if ((lagi == 'y' || lagi == 'Y'))
      {
        System.out.println("Program dimulai kembali");
      }
    }
    System.out.println("Program Selesai");
  }
}
/* PROGRESS
1. Gauss Jordan
2. Print Augmented format
3. Cara nge-gauss ada di file GaussDoang.java
4. uda ngebuat Matriks.Java
5, sok kalian bikin kelarin interpolasi sama bikin solve gauss sama jordan
KOONTOL INI UDA GK PERNAH DIISI
*/
