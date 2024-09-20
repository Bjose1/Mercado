/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventario;

import static inventario.Eliminar_Producto.procesarArchivo;
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
public class A_E_Registro_Proveedor extends javax.swing.JFrame {

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

    public static void elim_prov(File Productos, File Productos_N, File Provedores, File Provedores_N, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN, String prov) {

        String line, linea, linea1, line1, nombre_u = "", i = "0";
        try {
            FileWriter ESProductos_N = new FileWriter("Productos_N.txt");
            PrintWriter Productos_registro_N = new PrintWriter(Productos_N);

            FileReader FileProd = new FileReader("Productos.txt");
            BufferedReader BufferLecturaProd = new BufferedReader(FileProd);

            FileWriter ESProvedores_N = new FileWriter("Provedores_N.txt");
            PrintWriter Provedores_registro_N = new PrintWriter(Provedores_N);

            FileReader FileProv = new FileReader("Provedores.txt");
            BufferedReader BufferLecturaProv = new BufferedReader(FileProv);

            while ((line = BufferLecturaProv.readLine()) != null) {
                String[] data = line.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];
                if (!prov.equals(nombreprov_p)) {
                    fecha_pN = fecha_p;
                    cantsum_pN = cantsum_p;
                    nombreprod_pN = nombreprod_p;
                    nombreprov_pN = nombreprov_p;
                    cedula_pN = cedula_p;
                    if (!nombreprov_pN.isEmpty() && !fecha_pN.isEmpty() && !nombreprod_pN.isEmpty() && !cantsum_pN.isEmpty() && !cedula_pN.isEmpty()) {
                        Provedores_registro_N.println(nombreprov_pN + "\t" + fecha_pN + "\t" + nombreprod_pN + "\t" + cantsum_pN + "\t" + cedula_pN);
                    }
                } else {
                    nombre_u = nombreprod_p;
                }

            }
            while ((linea = BufferLecturaProd.readLine()) != null) {
                String[] data = linea.split("\t");
                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];
                if (!nombre_u.equals(nombre)) {
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

            vaciar(Productos_N, Provedores_N);
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }

    }

    public static void agregar_prov(File Productos, File Productos_N, File Provedores, File Provedores_N, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN, String N, String Cc, String PS, String CS, String f) {

        String line, linea;
        boolean sw2;
        try {

            FileWriter ESProvedores_N = new FileWriter("Provedores_N.txt");
            PrintWriter Provedores_registro_N = new PrintWriter(Provedores_N);

            FileReader FileProv = new FileReader("Provedores.txt");
            BufferedReader BufferLecturaProv = new BufferedReader(FileProv);

            while ((line = BufferLecturaProv.readLine()) != null) {

                String[] data = line.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];
                fecha_pN = fecha_p;
                cantsum_pN = cantsum_p;
                nombreprod_pN = nombreprod_p;
                nombreprov_pN = nombreprov_p;
                cedula_pN = cedula_p;

                if (!nombreprov_pN.isEmpty() && !fecha_pN.isEmpty() && !nombreprod_pN.isEmpty() && !cantsum_pN.isEmpty() && !cedula_pN.isEmpty()) {
                    Provedores_registro_N.println(nombreprov_pN + "\t" + fecha_pN + "\t" + nombreprod_pN + "\t" + cantsum_pN + "\t" + cedula_pN);
                }

            }

            nombreprov_pN = N;
            cedula_pN = Cc;
            nombreprod_pN = PS;
            fecha_pN = f;
            cantsum_pN = CS;

            if (!nombreprov_pN.isEmpty() && !fecha_pN.isEmpty() && !nombreprod_pN.isEmpty() && !cantsum_pN.isEmpty() && !cedula_pN.isEmpty()) {
                Provedores_registro_N.println(nombreprov_pN + "\t" + fecha_pN + "\t" + nombreprod_pN + "\t" + cantsum_pN + "\t" + cedula_pN);
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
            PrintWriter Provedores_registro = new PrintWriter(Provedores);

            FileReader FileProv_N = new FileReader("Provedores_N.txt");
            BufferedReader BufferLecturaProv_N = new BufferedReader(FileProv_N);

            while ((linea = BufferLecturaProv_N.readLine()) != null) {

                String[] data = linea.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];

                if (!nombreprov_p.isEmpty() && !fecha_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty() && !cedula_p.isEmpty()) {
                    Provedores_registro.println(nombreprov_p + "\t" + fecha_p + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                }

            }
            BufferLecturaProv_N.close();
            Provedores_registro.close();
            FileProv_N.close();

            vaciar(Productos_N, Provedores_N);
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }

    }

    /**
     * Creates new form A_E_Registro_Proveedor
     */
    public A_E_Registro_Proveedor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        NombreE = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        CC = new javax.swing.JTextField();
        Prod = new javax.swing.JTextField();
        Cant = new javax.swing.JTextField();
        Fecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel8.setText("Mostrar Productos");

        jButton2.setText("Mostrar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreEActionPerformed(evt);
            }
        });
        getContentPane().add(NombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 71, 151, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("A/E PROVEEDOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 130, -1));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 355, 80, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 74, -1, -1));

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 111, 82, -1));

        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 152, -1, -1));

        jLabel4.setText("Cédula Jurídica");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 189, -1, -1));

        jLabel5.setText("Productos Suministrados");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 229, 132, -1));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        getContentPane().add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 152, 151, -1));
        getContentPane().add(CC, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 186, 151, -1));

        Prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdActionPerformed(evt);
            }
        });
        getContentPane().add(Prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 226, 151, -1));
        getContentPane().add(Cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 266, 151, -1));

        Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaActionPerformed(evt);
            }
        });
        getContentPane().add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 306, 151, -1));

        jLabel6.setText("Cantidad Suministrada ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 269, -1, -1));

        jLabel7.setText("Fecha Última Entrega");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 309, 112, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreEActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean sw2;
        String N = Nombre.getText();
        String Cc = CC.getText();
        while (Cc.length() > 10) {
            Cc = CC.getText();
        }
        String PS = Prod.getText();
        String CS = Cant.getText();
        try {
            Double.parseDouble(CS);
            sw2 = true;
        } catch (NumberFormatException e) {
            sw2 = false;
        }
        while (sw2 = false) {
            CS = Cant.getText();
        }
        while (Integer.parseInt(CS) <= 0) {
            CS = Cant.getText();
        }
        
        int dia, mes, año;
        String f;
        do{
            f = Fecha.getText();
            dia = Integer.parseInt(f)%100;
            mes = Integer.parseInt(f)%10000/100;
            año = Integer.parseInt(f)/10000;
        }while(año<2024 || año>2100 || mes < 1 || mes > 12 || dia < 1 || dia > 31);
        
        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        inicializarYProcesarArchivos();
        agregar_prov(productos, productosN, provedores, provedoresN, nombreprod_p, nombreprov_p, cantsum_p, fecha_p, cedula_p, nombreprod_pN, nombreprov_pN, cantsum_p, fecha_pN, cedula_pN, N, Cc, PS, CS, f);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void ProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdActionPerformed

    private void FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
            String prov = NombreE.getText();
       
            
        
        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        inicializarYProcesarArchivos();

        elim_prov(productos, productosN, provedores, provedoresN, nombre, fecha, categoria, precio, stock, codigo, nombreprod_p, nombreprov_p, cantsum_p, fecha_p, cedula_p, nombre_N, fecha_N, categoria_N, precio_N, stock_N, codigo_N, nombreprod_pN, nombreprov_pN, cantsum_pN, fecha_pN, cedula_pN, prov);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(A_E_Registro_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A_E_Registro_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A_E_Registro_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A_E_Registro_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_E_Registro_Proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CC;
    private javax.swing.JTextField Cant;
    private javax.swing.JTextField Fecha;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField NombreE;
    private javax.swing.JTextField Prod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
