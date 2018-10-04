import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class Matriks
{
  public double [][] Isi;
  private int bar, kol;
  Scanner scanner = new Scanner(System.in);
  public String pers, persI;
  public double hasil;
  public int x;

  public Matriks(int m, int n)
  //I.S. m dan n terdefinisi
  //F.S. terbentuk matriks dengan jumlah baris m dan jumlah kolom n
  {
    this.bar = m;
    this.kol = n;
    Isi = new double[m][n];
    pers = new String("");
    persI = new String("");
    this.hasil=0;
  }

  public void bacam()
  //I.S. ukuran Isi terdefinisi
  //F.S. Matriks Isi memiliki isi sesuai input user
  {
    for(int i=0; i<this.bar; i++)
    {
          for(int j=0; j<this.kol; j++)
          {
            Isi[i][j]=scanner.nextInt();
          }
    }
    System.out.print("\n");
  }

  public void tulism()
  //I.S. Isi terdefinisi
  //F.S. menampilkan Matriks Isi dengan format Augmented Matriks
  {
    for (int k = 0; k <this.bar; k++)
    {
      for (int l = 0; l < this.kol; l++)
      {
        Isi[k][l] += 0; //menghindari keberadaaan -0.00
      }
    }
    for(int i=0; i<this.bar; i++)
    {
          for(int j=0; j<this.kol; j++)
          {
              if (j == (this.kol-1))
              {
                  System.out.printf("| %.2f", Isi[i][j]);
              }
              else if ((Isi[i][j] != -0))
              {
                System.out.printf("%.2f ", Isi[i][j]);
              } else {
                System.out.printf("%.2f ", 0.00);
              }
          }
    System.out.println();
    }
  }

  public void kkalibaris(int i, double x)
  //I.S. Matriks Isi dan skalar terdefinsisi
  //F.S. Salah satu baris matriks Isi dikalikan dengan skalar
  {
    for(int j=0; j<this.kol; j++)
    {
      (Isi[i][j])=(Isi[i][j])*x;
    }
  }

  public void kurangbaris(int i1, int i2, double x)
  //I.S. matriks Isi, nomor baris, dan skalar terdefinisi
  //F.S. salah satu baris pada matriks dikurangi dengan skalar*baris lain
  {
    for(int j=0; j<this.kol; j++)
    {
          (Isi[i1][j])=(Isi[i1][j])-(x*Isi[i2][j]);
    }
  }

  public void susunkali()
  //I.S. Isi terdefinisi
  //F.S. membuat semua pivot point menjadi 1
  {
    for(int i=0; i<this.bar; i++)
    {
      if (pivotpoint(i) != -999)
      {
        kkalibaris(i, (1/(Isi[i][pivotpoint(i)])));
      }
    }
  }

  public void hapusbaris(double mx[][], int n,int i)
  //I.S. Isi terdefinsisi
  //F.S. menghapus(mengganti semua elemen dengan 0) dalam satu baris
  {
    for (int j=0; j<n; j++)
    {
        mx[i][j]=0;
    }
  }

  public int pivotpoint(int i)//parameter i adalah baris yang pivotpoint nya dicari
  {
    int j = 0; boolean found = false;
    while ((j < this.kol-1) && !(found)) //cuma dicek sampe kol-2 karena this.kol-1 isinya jawaban augmented
    {
      if (Isi[i][j] != 0)
      {
        found = true;
      } else {
        j++;
      }
    }

    if (found)
    {
      return j;
    } else {
      return -999; //nanti klo dia -999 maka dia no/inf solution
    }
  }

  public void tukarbaris(int i1, int i2)
  {
    for (int j = 0; j < this.kol; j++)
    {
      double temp = Isi[i1][j];
      Isi[i1][j] = Isi[i2][j];
      Isi[i2][j] = temp;
    }
  }

  public void susunmatrix()
  {
    int[] rank = new int[this.bar];
    int x=0;
    double[][] tx = new double[this.bar][this.kol];

    //inisialisasi tx
    for (int j = 0; j < this.kol; j++)
    {
      for (int i = 0; i < this.bar; i++)
      {
        tx[i][j] = Isi[i][j];
      }
    }
    //hapus baris yg perlu dihapus
    for (int j = 0; j < this.kol; j++)
    {
      for (int i = 0; i < this.bar; i++)
      {
        if (tx[i][j] != 0)
        {
            rank[x] = i;
            x += 1;
            hapusbaris(tx, this.kol, i);
        }
      }
    }

    int limit = x-1;
    double[][] nx = new double[this.bar][this.kol];
    //inisialisasi nx dengan 0
    for (int i = 0; i < this.bar; i++)
    {
      for (int j = 0; j < this.kol; j++)
      {
        nx[i][j] = 0;
      }
    }

    int y=0; //iterator
    while(y<=limit)
    {
        for (int j=0; j<this.kol; j++)
        {
            nx[y][j] = Isi[(rank[y])][j];
        }
        y++;
    }
    //memasukkan elemen nx ke this matrix
    for (int j=0; j < this.kol; j++)
    {
        for (int i = 0; i < this.bar; i++)
        {
            Isi[i][j]=nx[i][j];
        }
    }
  }
  public boolean IsiBarNol(int i) //baris ke-i
  /* mengembalikan true jika seluruh elemen isi baris i bernilai 0 (kecuali elemen hasil)
     mengembalikan false jika tidak
  */
  {
    boolean semua = true;
    int j = 0;
    while ((j < this.kol-1) && semua)
    {
      if (Isi[i][j] != 0)
      {
        semua = false;
      } else {
        j++;
      }
    }
    return semua;
  }
  public void Gauss()
  //I.S. Isi terdefinsisi
  //F.S. menghasilkan matriks yang sudah menjadi Row Echelon Form
  {
    int n = (bar<kol)? bar:kol;
    for(int i=0; i<n; i++)
    {
      susunmatrix();
      susunkali();

      if (!this.IsiBarNol(i))
      {
        for (int a = i+1; a<this.bar; a++)
        {
          if (!this.IsiBarNol(a))
          {
            double x = (-1) * Isi[a][pivotpoint(i)] / Isi[i][pivotpoint(i)];
            for (int b = 0; b < this.kol; b++)
            {
              Isi[a][b] += (x * Isi[i][b]);
            }
          }
          Isi[a][pivotpoint(i)] = 0;
        }
      }
    }

    for (int c = 0; c < this.bar; c++)
    {
      if (!this.IsiBarNol(c))
      {
        this.kkalibaris(c, (1/Isi[c][this.pivotpoint(c)]));
      }
    }
  }
  public void GaussJordan()
  //I.S. Isi terdefinsisi
  //F.S. terbentuk matriks yang sudah menjadi bentuk Gauss-Jordan
  {
    Gauss();
    int n = (bar<kol)? bar:kol;
    for (int i = n-1; i > 0; i--)
    {
      susunmatrix();
      susunkali();

      if(!this.IsiBarNol(i))
      {
        for (int j = i-1; j >= 0; j--)
        {
          if (!this.IsiBarNol(j))
          {
            double x = (-1) * Isi[j][this.pivotpoint(i)];
            for (int b = 0; b < this.kol; b++)
            {
              Isi[j][b] += (x * Isi[i][b]);
            }
          }
        }
      }
    }
  }

  public void Interpolasi()
  //I.S. banyaknya titik terdefinsisi
  //F.S. membentuk sebuah matriks interpolasi dan memberikan solusi.
  //Lalu membuat persamaan polinom dan menyelesaikan fungsinya
  {
    double[][] mat = new double[this.bar][this.bar+1];

    for (int i = 0; i < this.bar; i++)
    {
      for(int j = 0; j < this.bar+1 ; j++)
      {
        if (j == 0)
        {
          mat[i][j] = 1;
        }
        else if (j == this.bar)
        {
          mat[i][j] = Isi[i][1];
        } else {
          mat[i][j] = Math.pow(Isi[i][0] , j);
        }
      }
    }
    this.kol = this.bar + 1;
    this.Isi = new double[this.bar][this.kol];//deklarasi baru
    for (int i = 0; i < this.bar; i++)
    {
      for(int j = 0; j < this.kol; j++)
      {
        Isi[i][j] = mat[i][j];
      }
    }
  }

  public boolean IsBarParametrik(int i)
  /* true = baris ini parametrik
     false = baris biasa
  */
  {
    boolean a = false;
    int j = 0;
    while ((j < this.kol-1) && !a)
    {
      if ((j != pivotpoint(i)) && Isi[i][j] != 0)
      {
        a = true;
      }
    }
    return a;
  }
  public void solveGauss(){
          //I.S. Isi terdefinisi dan dalam bentu row echelon tereduksi atas
          //F.S. Terbentuk persamaan dari matriks
          Gauss();
          String persT="";
          DecimalFormat df2 = new DecimalFormat("#.##");
          int i=bar-1;
          int j;
          int a=0;
          int n=kol-2;
          String Tpers="";
          char symbol = 's';
          String[] def = new String[kol-1];
          while(i>=0){
              j=pivotpoint(i);
              if (j!=-999){
                  if(j < n){
                      while(j < n){
                          def[n]=Character.toString(symbol);//menambah parameter
                          symbol++;
                          if (symbol=='x'){
                              symbol='a';
                          }
                          n--;
                      }
                      int k=kol-1;
                      if(Isi[i][k]!=0 && Isi[i][k]!=-0){
                          Tpers=Tpers + df2.format(Isi[i][kol-1]);
                      }
                      k--;
                      while (k>j) {
                          if(Isi[i][k]!=1 && Isi[i][k]>0){
                              Tpers=Tpers + "-" + df2.format(Isi[i][k]) + "(" + def[k] + ")";
                          }
                          else if(Isi[i][k]==1){
                              Tpers=Tpers + "-" + "(" + def[k] + ")";
                          }
                          else if(Isi[i][k]<0){
                              Tpers=Tpers + "+" + df2.format(Isi[i][k]*-1) + "(" + def[k] + ")";
                          }
                          k--;
                      }
                      def[j]=Tpers;
                      n--;
                  }
                  else{
                      int k=kol-1;
                      if(Isi[i][k]!=0 && Isi[i][k]!=-0){
                          Tpers=Tpers + df2.format(Isi[i][kol-1]);
                      }
                      k--;
                      while (k>j) {
                          if(Isi[i][k]!=1 && Isi[i][k]>0){
                              Tpers=Tpers + "-" + df2.format(Isi[i][k]) + "(" + def[k] + ")";
                          }
                          else if(Isi[i][k]==1){
                              Tpers=Tpers + "-" + "(" + def[k] + ")";
                          }
                          else if(Isi[i][k]<0){
                              Tpers=Tpers + "+" + df2.format(Isi[i][k]*-1) + "(" + def[k] + ")";
                          }
                          k--;
                      }
                      def[j]=Tpers;
                      n--;
                  }
              }
              Tpers="";
              i--;
          }
          for(int b=0;b<kol-1;b++){
              pers=pers+"X"+(b+1)+" = "+def[b]+"\n";
          }
      }

  public void solveGauss2()
  // klo kepepet pake yg ini aj sumpah dripada gk kelar WKWKWKWKWK
  {
    solveGaussJordan();
  }
  public void solveGaussJordan()
  //prekondisi pemanggilan : matriks sudah berbentuk gauss jordan
  //UDAH BISA PARAMETRIK LOH KEREN GAK GW (VINSEN)
    /* I.S. Isi terdefinisi dan dalam bentuk row echelon
       F.S. Terbentuk persamaan dari matriks row echelon
    */
  {
      GaussJordan();
      String[] hasil = new String[this.kol-1];
      // inisialisasi hasil[k]
      for (int k = 0; k < this.kol-1; k++)
      {
        hasil[k] = String.format("X%d =", k+1);
      }
      for (int i = 0; i < this.bar; i++)
      {
        if (!IsiBarNol(i))
        {
          if (Isi[i][this.kol-1] != 0)
          {
            hasil[pivotpoint(i)] += String.format(" %.2f", Isi[i][this.kol-1]);
            for (int j = 0; j < this.kol-1; j++)
            {
              if ((Isi[i][j] != 0) && j != pivotpoint(i)) //maka dia pasti variabel bebas
              {
                if (Isi[i][j] > 0)
                {
                  hasil[pivotpoint(i)] += String.format(" -%.2f u%d", Isi[i][j], j+1);
                } else /*Isi[i][j] < 0 */ {
                  hasil[pivotpoint(i)] += String.format(" +%.2f u%d", (-1) * Isi[i][j], j+1);
                }
              }
            }
          } else {
            //apabila dia 0, dan tidak ada variabel bebas, maka print 0
            boolean bebas = false;
            int iter = 0;
            while ((!bebas) && (iter < this.kol-1))
            {
              if ((Isi[i][iter] != 0) && iter != pivotpoint(i))
              {
                bebas = true;
              } else {
                iter += 1;
              }
            }

            if (!bebas) //apabila tidak ada variabel bebas
            {
              hasil[pivotpoint(i)] += " 0.00";
            }
            for (int j = 0; j < this.kol-1; j++)
            {
              if ((Isi[i][j] != 0) && j != pivotpoint(i)) //maka dia pasti variabel bebas
              {
                if (Isi[i][j] > 0)
                {
                  hasil[pivotpoint(i)] += String.format(" -%.2f u%d", Isi[i][j], j+1);
                } else /*Isi[i][j] < 0 */ {
                  hasil[pivotpoint(i)] += String.format(" +%.2f u%d", Isi[i][j], j+1);
                }
              }
            }
          }
        }
      }
      //mencari variabel bebas sambil meletakkannya di pers
      for (int k = 0; k < this.kol-1; k++)
      {
        if (hasil[k].charAt(hasil[k].length()-1) == '=')
        {
          hasil[k] += String.format(" u%d", k+1);
        }
        pers += hasil[k] + "; \n";
      }
  }
  public void solveInterpolasi()
    //prekondisi pemanggilan : matriks harus memiliki solusi dan non-parametrik
    //I.S. Isi terdefinisi dan dalam bentuk row echelon
    //F.S. Terbentuk persamaan interpolasi dari matriks row echelon
  {
        boolean first = true;
        this.hasil = 0;
        tulism();
        int n = (bar < kol) ? bar : kol;
        persI = "f(X) = ";
        for (int i = 0 ; i < this.bar ; i++)
        {
          if (Isi[i][this.kol-1] != 0)
          {
            if (i == 0 || first == true)
            {
              if (i == 0){
                persI = persI + String.format("(%.2f)", Isi[i][this.kol-1]);
              }
              else
              {
                persI = persI + String.format("(%.2f)X^" + (i), Isi[i][this.kol-1]);
              }
              first = false;
            }
            else
            {
              persI = persI + String.format(" + (%.2f)X^" + (i), Isi[i][this.kol-1]);
            }
            this.hasil = this.hasil + (Math.pow(3 , i)*Isi[i][this.kol-1]);
          }
        }
        System.out.println(this.hasil);
  }
  public void solveInterpolasiGauss()
  //I.S. matriks sudah di Gauss. Prekondisi : harus valid(tidak ada no solution)
  //F.S. terbentuk persamaan interpolasi dan terhitung nilai fungsi sesuai masukan pengguna
  {
    boolean first = true;
    int i,j;
    this.hasil = 0;
    double[] nilai = new double[this.bar];
    tulism();
    persI = "f(X) = ";
    for (i = this.bar ; i >0 ; i--)
    {
      nilai[i-1] = Isi[i-1][this.kol-1];
      for (j = i; j<this.kol-1; j++)
      {
        nilai[i-1] = nilai[i-1] - (Isi[i-1][j]*nilai[j]);
      }
      System.out.println(nilai[i-1]);
    }
    for(i = 0 ; i<this.bar ; i++)
    {
      if (nilai[i] != 0)
      {
        if (i == 0 || first == true)
        {
          if (i == 0){
            persI = persI + String.format("(%.2f)", nilai[i]);
          }
          else
          {
            persI = persI + String.format("(%.2f)X^" + (i), nilai[i]);
          }
          first = false;
        }
        else
        {
          persI = persI + String.format(" + (%.2f)X^" + (i), nilai[i]);
        }
        this.hasil = this.hasil + (Math.pow(2.7 , i)*nilai[i]);

        System.out.println(persI);
      }
    }
    System.out.println(this.hasil);
  }
  public void bacafile()
  //Membaca file yang berisi matriks dan mengisikan ke bentuk matriks
  {
    try
    {
      int Nbar = -1;
      int Nkol = -1;
      ArrayList<ArrayList<Double>> baca = new ArrayList<ArrayList<Double>>();
      String namfil = new String();
      System.out.print("Masukkan Nama File(diakhiri .txt) : ");
      namfil = scanner.nextLine(); //menginput nama file
      System.out.println();
      File f = new File(namfil);
      Scanner sb = new Scanner(f);
      while (sb.hasNextLine())
      {
          Nbar++;
          baca.add(new ArrayList<Double>());
          String sebaris = sb.nextLine();
          Scanner sa = new Scanner(sebaris);
          while (sa.hasNextDouble())
          {
            Double angka = sa.nextDouble();
            baca.get(Nbar).add(angka);
          }
      }
      if (Nbar == -1)
      {
        System.out.println("File Kosong");
      }
      else
      {
        Nkol = baca.get(0).size();
        this.Isi = new double[baca.size()][baca.get(0).size()];
        for (int i = 0; i <= Nbar ; i++){
          for (int j = 0; j < Nkol ; j++){
              Isi[i][j] = baca.get(i).get(j);
          }
        }
        this.bar = Nbar + 1;
        this.kol = Nkol;
      }
    } catch (Exception e) {
            System.out.println("Error : " + e);
    }
  }
  public void tulisf(boolean SPL)
  //I.S. jenis jawaban sudah terdefinsisi
  //F.S. membentuk file dengan isi sesuai dengan jawaban yang sudah didapat.
  {
    char simpan = 'y';
    //do
    //{
      System.out.print("Apakah hasil ingin disimpan ke dalam file?(y/n)\n");
      simpan = scanner.next().charAt(0);
    //} while (simpan != 'y' || simpan != 'n' || simpan != 'Y' || simpan != 'N');
    if ((simpan == 'y') || (simpan == 'Y'))
    {
      System.out.print("Masukkan Nama File(diakhiri .txt) : ");
      String namafile = scanner.next();
      try
      {
        File f = new File(namafile);
        if (!(f.exists()))
        {
          f.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(namafile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if (SPL)
        { //gauss atau gauss jordan
          printWriter.print(pers);
          System.out.printf("Hasil Tersimpan pada file %s!\n", namafile);
        }
        else
        {
          // interpolasi
          solveInterpolasi();
          printWriter.print(persI);
          printWriter.printf("f(%d) = %.2f",x,this.hasil);
          System.out.printf("Hasil Tersimpan! pada file %s\n", namafile);
        }
        printWriter.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public boolean nosol()
  {
    int i = this.bar-1;
    int j = 0; boolean iya = false;
    boolean IsNoSol = false;
    while ((j <= this.kol-1) && !(iya))
    {
      if (j != this.kol-1)
      {
        if (Isi[i][j] == 0)
        {
          j++;
        } else {
          iya = true;
        }
      } else { //j == this.kol-1
        if (Isi[i][j] != 0)
        {
          IsNoSol = true;
        }
      }
    }
    return IsNoSol;
  }
  public static int menu1()
  //menuliskan menu awal dan membaca masukan menu
  {
    System.out.println("\nMENU 1\n1. Sistem Persamaan Linier\n2. Interpolasi Polinom\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }

  public static int menu2()
  {
    System.out.println("\nMENU 2\n1. Matriks Input\n2. Matriks dari File\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }

  public static int menu3()
  {
    System.out.println("\nMENU 3\n1. Gauss\n2. Gauss Jordan\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }
}
