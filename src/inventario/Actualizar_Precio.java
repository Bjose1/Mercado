/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventario;

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
import inventario.Inventario;


/**
 *
 * @author Usuario
 */
public class Actualizar_Precio extends javax.swing.JFrame {

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

    /**
     * Creates new form Actualizar_Precio
     */
    public Actualizar_Precio() {
        initComponents();
    }

    

    public static void vaciar(File Provedores_N, File Productos_N) {
        try {

            FileWriter ESProductos_N = new FileWriter(Productos_N);
            PrintWriter Productos_registro_N = new PrintWriter(ESProductos_N);

            FileWriter ESProvedores_N = new FileWriter(Provedores_N);
            PrintWriter Provedores_registro_N = new PrintWriter(ESProvedores_N);

            Productos_registro_N.close();
            ESProductos_N.close();
            Provedores_registro_N.close();
            ESProvedores_N.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();  // Agregado para ayudar a depurar el error
        }
    }

    public static void act_pre(File Productos, File Productos_N, File Provedores, File Provedores_N, String nombre_u, String precio_u, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N) {

        String line, linea;
        boolean sw2;
        try {
            FileWriter ESProductos_N = new FileWriter("Productos_N.txt");
            PrintWriter Productos_registro_N = new PrintWriter(Productos_N);

            FileReader FileProd = new FileReader("Productos.txt");
            BufferedReader BufferLecturaProd = new BufferedReader(FileProd);

            while ((line = BufferLecturaProd.readLine()) != null) {
                String[] data = line.split("\t");
                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];

                if (nombre.equals(nombre_u)) {
                    precio_N = precio_u;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    fecha_N = fecha;
                    codigo_N = codigo;
                    stock_N = stock;
                } else {
                    precio_N = precio;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    fecha_N = fecha;
                    codigo_N = codigo;
                    stock_N = stock;
                }
                if (!stock_N.isEmpty() && !fecha_N.isEmpty() && !codigo_N.isEmpty() && !categoria_N.isEmpty() && !precio_N.isEmpty() && !nombre_N.isEmpty()) {
                    Productos_registro_N.println(nombre_N + "\t" + fecha_N + "\t" + codigo_N + "\t" + categoria_N + "\t" + precio_N + "\t" + stock_N);
                }
            }
            BufferLecturaProd.close();
            Productos_registro_N.flush();
            Productos_registro_N.close();
            FileProd.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        try {
            FileWriter ESProductos = new FileWriter("Productos.txt");
            PrintWriter Productos_registro = new PrintWriter(Productos);

            FileReader FileProd_N = new FileReader("Productos_N.txt");
            BufferedReader BufferLecturaProd_N = new BufferedReader(FileProd_N);

            while ((linea = BufferLecturaProd_N.readLine()) != null) {
                String[] data = linea.split("\t");
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
            BufferLecturaProd_N.close();
            Productos_registro.flush();
            Productos_registro.close();
            FileProd_N.close();
            vaciar(Productos_N, Provedores_N);
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

        Nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Precio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        jLabel1.setText("ACTUALIZAR PRECIO");

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel4.setText("Nuevo precio");

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
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(Precio)
                            .addComponent(jButton1))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean sw2;
        String nombre_u = Nombre.getText();
        String precio_u = Precio.getText();
        try {
            Double.parseDouble(precio_u);
            sw2 = true;
        } catch (NumberFormatException e) {
            sw2 = false;
        }
        while (sw2 = false) {
            precio_u = Precio.getText();
        }

        while (Integer.parseInt(precio_u) < 0) {
            precio_u = Precio.getText();
        }

        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        inicializarYProcesarArchivos();

        act_pre(productos, productosN, provedores, provedoresN, nombre_u, precio_u, nombre, fecha, categoria, precio, stock, codigo, nombre_N, fecha_N, categoria_N, precio_N, stock_N, codigo_N);
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
            java.util.logging.Logger.getLogger(Actualizar_Precio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actualizar_Precio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actualizar_Precio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actualizar_Precio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Actualizar_Precio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Precio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
