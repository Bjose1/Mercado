/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventario;

import static inventario.Registrar_Recepcion.procesarArchivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Eliminar_Producto extends javax.swing.JFrame {

    private File productos;
    private File provedores;
    private File productosN;
    private File provedoresN;

    public static void procesarArchivo(File Productos, File Provedores, File Productos_N, File Provedores_N) {
        // Verificar que los archivos existen y son accesibles
        if (!Productos.exists() || !Provedores.exists() || !Productos_N.exists() || !Provedores_N.exists()) {
            throw new IllegalArgumentException("Uno o más archivos no existen.");
        }
        System.out.println("Archivos procesados correctamente.");
    }

    /**
     * Creates new form Eliminar_Producto
     */
    public Eliminar_Producto() {
        initComponents();
    }

    public void inicializarYProcesarArchivos() {
        productos = new File("Productos.txt");
        provedores = new File("Provedores.txt");
        productosN = new File("Productos_N.txt");
        provedoresN = new File("Provedores_N.txt");

        // Verifica que los archivos existan
        if (!productos.exists() || !provedores.exists() || !productosN.exists() || !provedoresN.exists()) {
            throw new IllegalStateException("Los archivos necesarios no están presentes.");
        }

        // Llamada para verificar o inicializar archivos, si es necesario
        procesarArchivo(productos, provedores, productosN, provedoresN);
    }

    public static void elim_prod(File Productos, File Productos_N, File Provedores, File Provedores_N, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN, String prod) {
        String seguir = "1";

        String line, linea, linea1, line1, i = "0";

        try {
            FileWriter ESProductos_N = new FileWriter("Productos_N.txt");
            PrintWriter Productos_registro_N = new PrintWriter(Productos_N);

            FileReader FileProd = new FileReader("Productos.txt");
            BufferedReader BufferLecturaProd = new BufferedReader(FileProd);

            FileWriter ESProvedores_N = new FileWriter("Provedores_N.txt");
            PrintWriter Provedores_registro_N = new PrintWriter(Provedores_N);

            FileReader FileProv = new FileReader("Provedores.txt");
            BufferedReader BufferLecturaProv = new BufferedReader(FileProv);

            while ((line = BufferLecturaProd.readLine()) != null) {
                String[] data = line.split("\t");
                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];
                if (!prod.equals(nombre)) {
                    stock_N = stock;
                    fecha_N = fecha;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    precio_N = precio;
                    codigo_N = codigo;
                    if (!stock_N.isEmpty() && !fecha_N.isEmpty() && !codigo_N.isEmpty() && !categoria_N.isEmpty() && !precio_N.isEmpty() && !nombre_N.isEmpty()) {
                        Productos_registro_N.println(nombre_N + "\t" + fecha_N + "\t" + codigo_N + "\t" + categoria_N + "\t" + precio_N + "\t" + stock_N);
                    }
                }

            }
            ESProductos_N.close();
            FileProd.close();
            while ((linea = BufferLecturaProv.readLine()) != null) {
                String[] data = linea.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];
                if (!prod.equals(nombreprod_p)) {
                    fecha_pN = fecha_p;
                    cantsum_pN = cantsum_p;
                    nombreprod_pN = nombreprod_p;
                    nombreprov_pN = nombreprov_p;
                    cedula_pN = cedula_p;
                }
                if (!nombreprov_pN.isEmpty() && !fecha_pN.isEmpty() && !nombreprod_pN.isEmpty() && !cantsum_pN.isEmpty() && !cedula_pN.isEmpty()) {
                    Provedores_registro_N.println(nombreprov_pN + "\t" + fecha_pN + "\t" + nombreprod_pN + "\t" + cantsum_pN + "\t" + cedula_pN);
                }
            }
            BufferLecturaProd.close();
            BufferLecturaProv.close();
            Productos_registro_N.flush();
            Provedores_registro_N.flush();
            Productos_registro_N.close();
            Provedores_registro_N.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        try {

            FileWriter ESProductos = new FileWriter("Productos.txt");
            PrintWriter Productos_registro = new PrintWriter(Productos);

            FileReader FileProd_N = new FileReader("Productos_N.txt");
            BufferedReader BufferLecturaProd_N = new BufferedReader(FileProd_N);

            FileWriter ESProvedores = new FileWriter("Provedores.txt");
            PrintWriter Provedores_registro = new PrintWriter(Provedores);

            FileReader FileProv_N = new FileReader("Provedores_N.txt");
            BufferedReader BufferLecturaProv_N = new BufferedReader(FileProv_N);

            while ((linea1 = BufferLecturaProd_N.readLine()) != null) {
                String[] data = linea1.split("\t");
                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];
                if (!stock.isEmpty() && !fecha.isEmpty() && !codigo.isEmpty() && !categoria.isEmpty() && !precio.isEmpty() && !nombre.isEmpty()) {
                    Productos_registro.println(nombre + "\t" + fecha + "\t" + codigo + "\t" + categoria + "\t" + precio + "\t" + stock);
                }
            }

            while ((line1 = BufferLecturaProv_N.readLine()) != null) {
                String[] data = line1.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];

                if (!nombreprov_p.isEmpty() && !fecha_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty() && !cedula_p.isEmpty()) {
                    Provedores_registro.println(nombreprov_p + "\t" + fecha_p + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                }
            }

            BufferLecturaProd_N.close();
            BufferLecturaProv_N.close();
            Productos_registro.close();
            Provedores_registro.close();
            FileProd_N.close();
            FileProv_N.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        try {
            FileWriter ESProductos_N = new FileWriter("Productos_N.txt");
            FileWriter ESProvedores_N = new FileWriter("Provedores_N.txt");

            ESProductos_N.close();
            ESProvedores_N.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ProductoTXT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ELIMINAR PRODUCTO");

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Producto");

        ProductoTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoTXTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProductoTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(79, 79, 79)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ProductoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProductoTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductoTXTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String prod = ProductoTXT.getText();

        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        inicializarYProcesarArchivos();

        elim_prod(productos, productosN, provedores, provedoresN, nombre, fecha, categoria, precio, stock, codigo, nombreprod_p, nombreprov_p, cantsum_p, fecha_p, cedula_p, nombre_N, fecha_N, categoria_N, precio_N, stock_N, codigo_N, nombreprod_pN, nombreprov_pN, cantsum_pN, fecha_pN, cedula_pN, prod);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ProductoTXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
