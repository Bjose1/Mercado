/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventario;

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
public class Reponer_Stock extends javax.swing.JFrame {

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
     * Creates new form Reponer_Stock
     */
    public Reponer_Stock() {
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

    public static void renov_stock(File Productos, File Productos_N, File Provedores, File Provedores_N, String umbral, String cantsum_pu, String fecha_u, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN) {

        String linea, linea1, line1, nombre_u, fecha_un = "";/*La fecha de ultima entrega sera 2 semas despues de la actual*/
        int sw = 1;
        try {

            FileWriter ESProductos_N = new FileWriter("Productos_N.txt");
            PrintWriter Productos_registro_N = new PrintWriter(Productos_N);

            FileReader FileProd = new FileReader("Productos.txt");
            BufferedReader BufferLecturaProd = new BufferedReader(FileProd);

            while ((linea = BufferLecturaProd.readLine()) != null) {
                String[] data = linea.split("\t");
                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];
                if (Integer.parseInt(stock) < Integer.parseInt(umbral)) {
                    int stockValue_p3 = Integer.parseInt(fecha);
                    int stockValue_p1 = Integer.parseInt(fecha_u);
                    stockValue_p3 = stockValue_p1 + 100;
                    fecha_N = String.valueOf(stockValue_p3);
                    fecha_un = fecha_N;

                    int stockValue_p4 = Integer.parseInt(stock);
                    stockValue_p4 = stockValue_p4 + Integer.parseInt(cantsum_pu);
                    stock_N = String.valueOf(stockValue_p4);
                    nombre_u = nombre;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    precio_N = precio;
                    codigo_N = codigo;

                    try {
                        String line = null;
                        FileWriter ESProvedores_N = new FileWriter("Provedores_N.txt");
                        PrintWriter Provedores_registro_N = new PrintWriter(Provedores_N);

                        FileReader FileProv = new FileReader("Provedores.txt");
                        BufferedReader BufferLecturaProv = new BufferedReader(FileProv);
                        while ((line = BufferLecturaProv.readLine()) != null) {

                            String[] data1 = line.split("\t");
                            nombreprov_p = data1[0];
                            cantsum_p = data1[3];
                            fecha_p = data1[1];
                            nombreprod_p = data1[2];
                            cedula_p = data1[4];
                            if (nombre_u.equals(nombreprod_p)) {
                                fecha_pN = fecha_un;
                                cantsum_pN = cantsum_pu;
                                nombreprod_pN = nombreprod_p;
                                nombreprov_pN = nombreprov_p;
                                cedula_pN = cedula_p;
                            } else {
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

                        BufferLecturaProv.close();
                        Provedores_registro_N.flush();
                        Provedores_registro_N.close();

                    } catch (IOException e) {
                        System.out.println("Ocurrió un error.");
                        e.printStackTrace();
                    }
                    try {

                        FileWriter ESProvedores = new FileWriter("Provedores.txt");
                        PrintWriter Provedores_registro = new PrintWriter(ESProvedores);

                        FileReader FileProv_N = new FileReader("Provedores_N.txt");
                        BufferedReader BufferLecturaProv_N = new BufferedReader(FileProv_N);

                        while ((linea1 = BufferLecturaProv_N.readLine()) != null) {
                            String[] data1 = linea1.split("\t");
                            nombreprov_p = data1[0];
                            cantsum_p = data1[3];
                            fecha_p = data1[1];
                            nombreprod_p = data1[2];
                            cedula_p = data1[4];
                            if (!nombreprov_p.isEmpty() && !fecha_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty() && !cedula_p.isEmpty()) {
                                Provedores_registro.println(nombreprov_p + "\t" + fecha_p + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                            }

                        }

                        BufferLecturaProv_N.close();
                        Provedores_registro.close();
                        FileProv_N.close();

                    } catch (IOException e) {
                        System.out.println("Ocurrió un error.");
                        e.printStackTrace();
                    }
                } else {
                    nombre_N = nombre;
                    categoria_N = categoria;
                    precio_N = precio;
                    codigo_N = codigo;
                    fecha_N = fecha;
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
            PrintWriter Productos_registro = new PrintWriter(ESProductos);

            FileReader FileProd_N = new FileReader("Productos_N.txt");
            BufferedReader BufferLecturaProd_N = new BufferedReader(FileProd_N);

            while ((line1 = BufferLecturaProd_N.readLine()) != null) {
                String[] data = line1.split("\t");
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

        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Umbral = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Fecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REPONER STOCK");

        jButton5.setText("Reponer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Reponer Stock");

        jLabel5.setText("Cantidad");

        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });

        jLabel6.setText("Umbral");

        Umbral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UmbralActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha");

        Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaActionPerformed(evt);
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
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Fecha)
                            .addComponent(Umbral)
                            .addComponent(Cantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Umbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel4))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        boolean sw;
        
        int dia, mes, año;
        String fecha_u;
        do{
            fecha_u = Fecha.getText();
            dia = Integer.parseInt(fecha_u)%100;
            mes = Integer.parseInt(fecha_u)%10000/100;
            año = Integer.parseInt(fecha_u)/10000;
        }while(año<2024 || año>2100 || mes < 1 || mes > 12 || dia < 1 || dia > 31);
        String umbral = Umbral.getText();
        try {
            Double.parseDouble(umbral);
            sw = true;
        } catch (NumberFormatException e) {
            sw = false;
        }
        while (sw = false) {
            umbral = Umbral.getText();
        }
        while (Integer.parseInt(umbral) < 0) {
            umbral = Umbral.getText();
        }
        
        
        String cantsum_pu = Cantidad.getText();
        try {
            Double.parseDouble(cantsum_pu);
            sw = true;
        } catch (NumberFormatException e) {
            sw = false;
        }
        while (sw = false) {
            cantsum_pu = Cantidad.getText();
        }
        while (Integer.parseInt(cantsum_pu) < 0) {
            cantsum_pu = Cantidad.getText();
        }

        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        inicializarYProcesarArchivos();

        renov_stock(productos, productosN, provedores, provedoresN, umbral, cantsum_pu, fecha_u, nombre, fecha, categoria, precio, stock, codigo, nombreprod_p, nombreprov_p, cantsum_p, fecha_p, cedula_p, nombre_N, fecha_N, categoria_N, precio_N, stock_N, codigo_N, nombreprod_pN, nombreprov_pN, cantsum_pN, fecha_pN, cedula_pN);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed

    private void UmbralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UmbralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmbralActionPerformed

    private void FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaActionPerformed

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
            java.util.logging.Logger.getLogger(Reponer_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reponer_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reponer_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reponer_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reponer_Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Fecha;
    private javax.swing.JTextField Umbral;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
