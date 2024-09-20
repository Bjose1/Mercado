 /* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 *
 * @author Usuario
 */
public class Llenar_Archivo {
    
    
    public static void llenar_archivo(File Provedores, File Productos, File Productos_N, File Provedores_N, Scanner sc, String fecha_u) {
        String seguir = "1", i = "0";

        try {
            // Abrir archivos para escritura
            FileWriter ESProductos = new FileWriter(Productos, true);
            PrintWriter Productos_registro = new PrintWriter(ESProductos);

            FileWriter ESProvedores = new FileWriter(Provedores, true);
            PrintWriter Provedores_registro = new PrintWriter(ESProvedores);

            // Recopilar datos para productos
            System.out.println("Datos del producto ");
            while (seguir.equals("1")) {
                System.out.println("Nombre Producto:");
                String nombre = sc.nextLine();
                
                System.out.println("Código:");
                String codigo = sc.nextLine();
                System.out.println("Valor:");
                String precio = sc.nextLine();
                System.out.println("Stock:");
                String stock = sc.nextLine();
                System.out.println("Categoría:");
                String categoria = sc.nextLine();

                if (!stock.isEmpty() && !precio.isEmpty() && !codigo.isEmpty() && !categoria.isEmpty() && !nombre.isEmpty()) {
                    Productos_registro.println(nombre + "\t" + fecha_u + "\t" + codigo + "\t" + categoria + "\t" + precio + "\t" + stock);
                }

                System.out.println("¿Desea seguir? 1 para Sí y 2 para No");
                seguir = sc.nextLine();
                while (!seguir.equals("1") && !seguir.equals("2")) {
                    System.out.println("Opción inválida. Por favor, ingrese 1 o 2:");
                    seguir = sc.nextLine();
                }
            }

            // Cerrar recursos de productos
            Productos_registro.close();
            ESProductos.close();

            // Resetear el valor de 'seguir' para el siguiente bucle
            seguir = "1";
            System.out.println("Datos del provedor");
            // Recopilar datos para proveedores
            while (seguir.equals("1")) {
                System.out.println("");
                System.out.println("Nombre Producto:");
                String nombreprod_p = sc.nextLine();
                if (nombreprod_p.isEmpty()) {
                    break;
                }
                System.out.println("Nombre Proveedor:");
                String nombreprov_p = sc.nextLine();
                System.out.println("Cédula:");
                String cedula_p = sc.nextLine();
                System.out.println("Stock:");
                String cantsum_p = sc.nextLine();

                if (!nombreprov_p.isEmpty() && !cedula_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty()) {
                    Provedores_registro.println(nombreprov_p + "\t" + fecha_u + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                }

                System.out.println("¿Desea seguir? 1 para Sí y 2 para No");
                seguir = sc.nextLine();
                while (!seguir.equals("1") && !seguir.equals("2")) {
                    System.out.println("Opción inválida. Por favor, ingrese 1 o 2:");
                    seguir = sc.nextLine();
                }
            }

            // Cerrar recursos de proveedores
            Provedores_registro.close();
            ESProvedores.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();  // Agregado para ayudar a depurar el error
        }
    } 
}
