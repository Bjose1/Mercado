/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventario;

import inventario.Inventario;
import inventario.Procesos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Registrar_Recepcion extends javax.swing.JFrame {

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

    public Registrar_Recepcion() {
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

    public void nuevo_lote1(File Productos, File Productos_N, File Provedores, File Provedores_N, String fecha_u, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN, String nombre_u, int cant) {

        boolean actualizar = false;

        String line, linea, linea1, line1;
        try {

            PrintWriter Productos_registro_N;
            BufferedReader BufferLecturaProv;
            PrintWriter Provedores_registro_N;
            try ( BufferedReader BufferLecturaProd = new BufferedReader(new FileReader("Productos.txt"))) {
                System.out.println("entro");
                Productos_registro_N = new PrintWriter(new FileWriter("Productos_N.txt"));
                BufferLecturaProv = new BufferedReader(new FileReader(Provedores));
                Provedores_registro_N = new PrintWriter(new FileWriter(Provedores_N));
                while ((linea = BufferLecturaProd.readLine()) != null) {
                    // Suponiendo que los datos se separan por tabuladores
                    String[] data = linea.split("\t");

                    nombre = data[0];
                    stock = data[5];
                    fecha = data[1];
                    codigo = data[2];
                    categoria = data[3];
                    precio = data[4];

                    if (nombre.equals(nombre_u)) {
                        stock_N = stock;
                        fecha_N = fecha;
                        nombre_N = nombre;
                        categoria_N = categoria;
                        precio_N = precio;
                        codigo_N = codigo;
                    } else {
                        int stockValue = Integer.parseInt(stock) + cant;
                        stock_N = String.valueOf(stockValue);
                        fecha_N = fecha_u;
                        nombre_N = nombre;
                        categoria_N = categoria;
                        precio_N = precio;
                        codigo_N = codigo;

                    }

                    // Escribir la línea actualizada en el archivo de destino
                    if (!stock_N.isEmpty() && !fecha_N.isEmpty() && !codigo_N.isEmpty() && !categoria_N.isEmpty() && !precio_N.isEmpty() && !nombre_N.isEmpty()) {
                        System.out.println("entrooooooooooooooooo");
                        Productos_registro_N.println(nombre_N + "\t" + fecha_N + "\t" + codigo_N + "\t" + categoria_N + "\t" + precio_N + "\t" + stock_N);
                    }
                    Productos_registro_N.flush();
                }
                while ((line = BufferLecturaProv.readLine()) != null) {
                    String[] data = line.split("\t");
                    nombreprov_p = data[0];
                    cantsum_p = data[3];
                    fecha_p = data[1];
                    nombreprod_p = data[2];
                    cedula_p = data[4];
                    if (nombreprod_p == nombre_u) {

                        cantsum_pN = String.valueOf(cant);
                        fecha_pN = fecha_u;
                        nombreprod_pN = nombreprod_p;
                        nombreprov_pN = nombreprov_p;
                        cedula_pN = cedula_p;
                    } else {
                        fecha_pN = fecha_u;
                        cantsum_pN = cantsum_p;
                        nombreprod_pN = nombreprod_p;
                        nombreprov_pN = nombreprov_p;
                        cedula_pN = cedula_p;
                    }

                    if (!nombreprov_pN.isEmpty() && !fecha_pN.isEmpty() && !nombreprod_pN.isEmpty() && !cantsum_pN.isEmpty() && !cedula_pN.isEmpty()) {
                        Provedores_registro_N.println(nombreprov_pN + "\t" + fecha_pN + "\t" + nombreprod_pN + "\t" + cantsum_pN + "\t" + cedula_pN);
                    }
                    Provedores_registro_N.flush();

                }
            }
            BufferLecturaProv.close();
            Productos_registro_N.flush();
            Provedores_registro_N.flush();
            Productos_registro_N.close();
            Provedores_registro_N.close();
            actualizar = true;

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        if (actualizar) {
            try {
                FileWriter ESProductos = new FileWriter("Productos.txt");
                PrintWriter Productos_registro = new PrintWriter(ESProductos);

                FileReader FileProd_N = new FileReader("Productos_N.txt");
                BufferedReader BufferLecturaProd_N = new BufferedReader(FileProd_N);

                FileWriter ESProvedores = new FileWriter("Provedores.txt");
                PrintWriter Provedores_registro = new PrintWriter(ESProvedores);

                FileReader FileProv_N = new FileReader("Provedores_N.txt");
                BufferedReader BufferLecturaProv_N = new BufferedReader(FileProv_N);

                while ((linea1 = BufferLecturaProd_N.readLine()) != null) {
                    String[] data = linea1.split("\t");

                    nombre_N = data[0];
                    stock_N = data[5];
                    fecha_N = data[1];
                    codigo_N = data[2];
                    categoria_N = data[3];
                    precio_N = data[4];
                    fecha = fecha_N;
                    stock = stock_N;
                    nombre = nombre_N;
                    categoria = categoria_N;
                    precio = precio_N;
                    codigo = codigo_N;

                    if (!stock.isEmpty() && !fecha.isEmpty() && !codigo.isEmpty() && !categoria.isEmpty() && !precio.isEmpty() && !nombre.isEmpty()) {
                        Productos_registro.println(nombre + "\t" + fecha + "\t" + codigo + "\t" + categoria + "\t" + precio + "\t" + stock);
                    }
                }
                Productos_registro.flush(); // Asegura que todo se escriba en el archivo

                while ((line1 = BufferLecturaProv_N.readLine()) != null) {
                    String[] data = line1.split("\t");
                    nombreprov_pN = data[0];
                    cantsum_pN = data[3];
                    fecha_pN = data[1];
                    nombreprod_pN = data[2];
                    cedula_pN = data[4];
                    fecha_p = fecha_pN;
                    cantsum_p = cantsum_pN;
                    nombreprod_p = nombreprod_pN;
                    nombreprov_p = nombreprov_pN;
                    cedula_p = cedula_pN;

                    if (!nombreprov_p.isEmpty() && !fecha_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty() && !cedula_p.isEmpty()) {
                        Provedores_registro.println(nombreprov_p + "\t" + fecha_p + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                    }
                }
                Provedores_registro.flush(); // Asegura que todo se escriba en el archivo

                // Cerrar los recursos
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

    public static void nuevo_lote(File Productos, File Productos_N, File Provedores, File Provedores_N, String fecha_u, String nombre, String fecha, String categoria, String precio, String stock, String codigo, String nombreprod_p, String nombreprov_p, String cantsum_p, String fecha_p, String cedula_p, String nombre_N, String fecha_N, String categoria_N, String precio_N, String stock_N, String codigo_N, String nombreprod_pN, String nombreprov_pN, String cantsum_pN, String fecha_pN, String cedula_pN, String nombre_u, int cant) {
        String i = "0";
        boolean actualizar = false;

        String line, linea, linea1, line1;
        try {

            BufferedReader BufferLecturaProd = new BufferedReader(new FileReader(Productos));
            PrintWriter Productos_registro_N = new PrintWriter(new FileWriter(Productos_N));

            BufferedReader BufferLecturaProv = new BufferedReader(new FileReader(Provedores));
            PrintWriter Provedores_registro_N = new PrintWriter(new FileWriter(Provedores_N));

            while ((linea = BufferLecturaProd.readLine()) != null) {
                // Suponiendo que los datos se separan por tabuladores
                String[] data = linea.split("\t");

                nombre = data[0];
                stock = data[5];
                fecha = data[1];
                codigo = data[2];
                categoria = data[3];
                precio = data[4];

                if (nombre.equals(nombre_u)) {
                    int stockValue = Integer.parseInt(stock) + cant;
                    stock_N = String.valueOf(stockValue);
                    fecha_N = fecha_u;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    precio_N = precio;
                    codigo_N = codigo;

                } else {
                    stock_N = stock;
                    fecha_N = fecha;
                    nombre_N = nombre;
                    categoria_N = categoria;
                    precio_N = precio;
                    codigo_N = codigo;
                }

                // Escribir la línea actualizada en el archivo de destino
                if (!stock_N.isEmpty() && !fecha_N.isEmpty() && !codigo_N.isEmpty() && !categoria_N.isEmpty() && !precio_N.isEmpty() && !nombre_N.isEmpty()) {
                    Productos_registro_N.println(nombre_N + "\t" + fecha_N + "\t" + codigo_N + "\t" + categoria_N + "\t" + precio_N + "\t" + stock_N);
                }
            }

            while ((line = BufferLecturaProv.readLine()) != null) {
                String[] data = line.split("\t");
                nombreprov_p = data[0];
                cantsum_p = data[3];
                fecha_p = data[1];
                nombreprod_p = data[2];
                cedula_p = data[4];
                if (nombreprod_p == nombre_u) {

                    cantsum_pN = String.valueOf(cant);
                    fecha_pN = fecha_u;
                    nombreprod_pN = nombreprod_p;
                    nombreprov_pN = nombreprov_p;
                    cedula_pN = cedula_p;
                } else {
                    fecha_pN = fecha_u;
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
            actualizar = true;

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        if (actualizar) {
            try {
                FileWriter ESProductos = new FileWriter("Productos.txt");
                PrintWriter Productos_registro = new PrintWriter(ESProductos);

                FileReader FileProd_N = new FileReader("Productos_N.txt");
                BufferedReader BufferLecturaProd_N = new BufferedReader(FileProd_N);

                FileWriter ESProvedores = new FileWriter("Provedores.txt");
                PrintWriter Provedores_registro = new PrintWriter(ESProvedores);

                FileReader FileProv_N = new FileReader("Provedores_N.txt");
                BufferedReader BufferLecturaProv_N = new BufferedReader(FileProv_N);

                while ((linea1 = BufferLecturaProd_N.readLine()) != null) {
                    String[] data = linea1.split("\t");

                    nombre_N = data[0];
                    stock_N = data[5];
                    fecha_N = data[1];
                    codigo_N = data[2];
                    categoria_N = data[3];
                    precio_N = data[4];
                    fecha = fecha_N;
                    stock = stock_N;
                    nombre = nombre_N;
                    categoria = categoria_N;
                    precio = precio_N;
                    codigo = codigo_N;

                    if (!stock.isEmpty() && !fecha.isEmpty() && !codigo.isEmpty() && !categoria.isEmpty() && !precio.isEmpty() && !nombre.isEmpty()) {
                        Productos_registro.println(nombre + "\t" + fecha + "\t" + codigo + "\t" + categoria + "\t" + precio + "\t" + stock);
                    }
                }
                Productos_registro.flush(); // Asegura que todo se escriba en el archivo

                while ((line1 = BufferLecturaProv_N.readLine()) != null) {
                    String[] data = line1.split("\t");
                    nombreprov_pN = data[0];
                    cantsum_pN = data[3];
                    fecha_pN = data[1];
                    nombreprod_pN = data[2];
                    cedula_pN = data[4];
                    fecha_p = fecha_pN;
                    cantsum_p = cantsum_pN;
                    nombreprod_p = nombreprod_pN;
                    nombreprov_p = nombreprov_pN;
                    cedula_p = cedula_pN;

                    if (!nombreprov_p.isEmpty() && !fecha_p.isEmpty() && !nombreprod_p.isEmpty() && !cantsum_p.isEmpty() && !cedula_p.isEmpty()) {
                        Provedores_registro.println(nombreprov_p + "\t" + fecha_p + "\t" + nombreprod_p + "\t" + cantsum_p + "\t" + cedula_p);
                    }
                }
                Provedores_registro.flush(); // Asegura que todo se escriba en el archivo

                // Cerrar los recursos
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REGISTRAR RECEPCION");

        jLabel2.setText("Nombre");

        jLabel3.setText("Cantidad Recibida");

        jLabel4.setText("Última Reposición");

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Obtener datos de los campos de texto
        String nombre_u = Nombre.getText();  // Obtener el nombre del JTextField
        int cant = Integer.parseInt(jTextField2.getText());  // Obtener la cantidad
        while (cant < 0) {
            cant = Integer.parseInt(jTextField2.getText());
        }
        int dia, mes, año;
        String fecha_u;
        do{
            fecha_u = jTextField3.getText(); // Obtener la fecha
            dia = Integer.parseInt(fecha_u)%100;
            mes = Integer.parseInt(fecha_u)%10000/100;
            año = Integer.parseInt(fecha_u)/10000;
        }while(año<2024 || año>2100 || mes < 1 || mes > 12 || dia < 1 || dia > 31);
        // Inicializar las demás variables a valores vacíos o predeterminados
        String nombre = "", fecha = "", categoria = "", precio = "", stock = "", codigo = "";
        String nombreprod_p = "", nombreprov_p = "", cantsum_p = "", fecha_p = "", cedula_p = "";
        String nombre_N = "", fecha_N = "", categoria_N = "", precio_N = "", stock_N = "", codigo_N = "";
        String nombreprod_pN = "", nombreprov_pN = "", cantsum_pN = "", fecha_pN = "", cedula_pN = "";

        System.out.println(nombre_u);
        System.out.println(cant);
        System.out.println(fecha_u);

        inicializarYProcesarArchivos();
        // Llamar a nuevo_lote con los argumentos
        nuevo_lote(productos, productosN, provedores, provedoresN, fecha_u, nombre, fecha, categoria, precio, stock, codigo, nombreprod_p, nombreprov_p, cantsum_p, fecha_p, cedula_p, nombre_N, fecha_N, categoria_N, precio_N, stock_N, codigo_N, nombreprod_pN, nombreprov_pN, cantsum_pN, fecha_pN, cedula_pN, nombre_u, cant);
        System.out.println("YA");

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
            java.util.logging.Logger.getLogger(Registrar_Recepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_Recepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_Recepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_Recepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_Recepcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
