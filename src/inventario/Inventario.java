/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventario;

import inventario.Llenar_Archivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Inventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Registro registro = new Registro();
        registro.setVisible(true);

        File Productos = new File("Productos.txt");
        Productos.createNewFile();
        File Provedores = new File("Provedores.txt");
        Provedores.createNewFile();
        File Productos_N = new File("Productos_N.txt");
        Productos_N.createNewFile();
        File Provedores_N = new File("Provedores_N.txt");
        Provedores_N.createNewFile();

        String nombre_u = "", categoria_u = "", precio_u = "", stock_u = "", codigo_u = "", nombreprod_pu = "", nombreprov_pu = "", cantsum_pu = "", fecha_pu = "", cedula_pu = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "", nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";
        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "", nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";

        Scanner sc = new Scanner(System.in);
        System.out.println("Fecha actual en formato AAAAMMDD(AÑO MES DIA):");
        String fecha_u = sc.nextLine();
        while (Integer.parseInt(fecha_u) < 20240101) {
            System.out.println("Fecha actual(AAAAMMDD):");
            fecha_u = sc.nextLine();
        }

        Llenar_Archivo.llenar_archivo(Provedores, Productos, Productos_N, Provedores_N, sc, fecha_u);

        
        

    }

    
}
