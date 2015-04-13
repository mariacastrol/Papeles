/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.ConexionMysql;
import Clases.FuncionesGenerales;
import javax.swing.JOptionPane;

/**
 *
 * @author ADOLFO
 */
public class CJDialogAeronavesPlanes extends javax.swing.JDialog {

    /**
     * Creates new form JDialogPersonalVuelo
     */
    public CJDialogAeronavesPlanes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldBuscarMatricula = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botonVolver = new javax.swing.JButton();
        jTextFieldSMatricula = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldSTipo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botonAgregar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        jTextFieldAAMatricula = new javax.swing.JTextField();
        jTextFieldAATipo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAeronavesPlanes = new javax.swing.JTable();

        jMenuItem1.setText("Seleccionar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Actualizar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Eliminar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextFieldBuscarMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldBuscarMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarMatriculaKeyTyped(evt);
            }
        });

        botonBuscar.setText("BUSCAR");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        jLabel19.setText("MATRICULA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldBuscarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonBuscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("AERONAVE"));

        botonVolver.setText("VOLVER A VENTANA PRINCIPAL");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jTextFieldSMatricula.setEditable(false);
        jTextFieldSMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setText("TIPO");

        jTextFieldSTipo.setEditable(false);
        jTextFieldSTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setText("MATRICULA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTextFieldSTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("AGREGAR/ACTUALIZAR AERONAVES"));

        botonAgregar.setText("AGREGAR");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        botonActualizar.setText("ACTUALIZAR");
        botonActualizar.setEnabled(false);
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        jTextFieldAAMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldAAMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAAMatriculaFocusGained(evt);
            }
        });
        jTextFieldAAMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAAMatriculaKeyTyped(evt);
            }
        });

        jTextFieldAATipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldAATipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAATipoFocusGained(evt);
            }
        });
        jTextFieldAATipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAATipoKeyTyped(evt);
            }
        });

        jLabel15.setText("TIPO");

        jLabel20.setText("MATRICULA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAAMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTextFieldAATipo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAgregar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAAMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAATipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregar)
                    .addComponent(botonActualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableAeronavesPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRICULA", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAeronavesPlanes.setComponentPopupMenu(jPopupMenu1);
        jTableAeronavesPlanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAeronavesPlanesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAeronavesPlanes);
        if (jTableAeronavesPlanes.getColumnModel().getColumnCount() > 0) {
            jTableAeronavesPlanes.getColumnModel().getColumn(1).setMinWidth(60);
            jTableAeronavesPlanes.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableAeronavesPlanes.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String campoConsulta = jTextFieldBuscarMatricula.getText();
        if (!ping.mostrarRegistroEspecifico(jTableAeronavesPlanes,nombreTablaMysql,columnasTablaMysql,pK,campoConsulta)) {
            JOptionPane.showMessageDialog(this,ping.getMensajesError(),"ERROR AL CARGAR CONSULTA",JOptionPane.ERROR_MESSAGE);
        } else {
            int filasTabla = jTableAeronavesPlanes.getRowCount();
            if (filasTabla == 0) {
                JOptionPane.showMessageDialog(this,"NO SE HA ENCONTRADO LA MATRICULA","ERROR EN BUSQUEDA",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        if (FuncionesGenerales.estaVacioJTextField(jTextFieldAAMatricula) || FuncionesGenerales.estaVacioJTextField(jTextFieldAATipo)) {
            if (FuncionesGenerales.estaVacioJTextField(jTextFieldAAMatricula)) {
                jTextFieldAAMatricula.setBackground(new java.awt.Color(255,0,0));
            }
            if (FuncionesGenerales.estaVacioJTextField(jTextFieldAATipo)) {
                jTextFieldAATipo.setBackground(new java.awt.Color(255,0,0));
            }
            JOptionPane.showMessageDialog(this,"Ha dejado campos vacios","NO SE HA PODIDO ACTUALIZAR REGISTRO",JOptionPane.ERROR_MESSAGE);
        } else {
            String [] datosActualizados = {jTextFieldAAMatricula.getText(),jTextFieldAATipo.getText()};
            if (!ping.modificarFilaEnTabla(nombreTablaMysql, columnasTablaMysql, datosActualizados, pK,llaveActual)) {
                JOptionPane.showMessageDialog(this,ping.getMensajesError(),"NO SE HA PODIDO ACTUALIZAR REGISTRO",JOptionPane.ERROR_MESSAGE);
            } else {
                if (!ping.mostrarColumnasTablaMysqlSimple(jTableAeronavesPlanes, nombreTablaMysql, columnasTablaMysql)) {
                    JOptionPane.showMessageDialog(this,ping.getMensajesError(),"NO SE HA PODIDO CARGAR LA TABLA",JOptionPane.ERROR_MESSAGE);
                }
            }
            jTextFieldAAMatricula.setText(null);
            jTextFieldAATipo.setText(null);
            botonBuscar.setEnabled(true);
            botonAgregar.setEnabled(true);
            botonVolver.setEnabled(true);
            jMenuItem1.setEnabled(true);
            jMenuItem2.setEnabled(true);
            jMenuItem3.setEnabled(true);
            botonActualizar.setEnabled(false); 
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        if (FuncionesGenerales.estaVacioJTextField(jTextFieldAAMatricula) || FuncionesGenerales.estaVacioJTextField(jTextFieldAATipo)) {
            if (FuncionesGenerales.estaVacioJTextField(jTextFieldAAMatricula)) {
                jTextFieldAAMatricula.setBackground(new java.awt.Color(255,0,0));
            }
            if (FuncionesGenerales.estaVacioJTextField(jTextFieldAATipo)) {
                jTextFieldAATipo.setBackground(new java.awt.Color(255,0,0));
            }
            JOptionPane.showMessageDialog(this,"Ha dejado campos vacios","NO SE HA PODIDO INSERTAR REGISTRO",JOptionPane.ERROR_MESSAGE);
        } else {
            String [] valores = {jTextFieldAAMatricula.getText(),jTextFieldAATipo.getText()};
            String mensajeSiRepiteRegistro = "Ya existe un aeronave con esa matricula";
            if(!ping.insertarFilaEnTabla(nombreTablaMysql,columnasTablaMysql,valores,mensajeSiRepiteRegistro)){
                JOptionPane.showMessageDialog(this,ping.getMensajesError(),"NO SE HA PODIDO INSERTAR REGISTRO",JOptionPane.ERROR_MESSAGE);
            } else {
                if(!ping.mostrarColumnasTablaMysqlSimple(jTableAeronavesPlanes, nombreTablaMysql, columnasTablaMysql)){
                    JOptionPane.showMessageDialog(this,ping.getMensajesError(),"NO SE HA PODIDO CARGAR LA TABLA",JOptionPane.ERROR_MESSAGE);
                }
            }
            jTextFieldAAMatricula.setText(null);
            jTextFieldAATipo.setText(null);
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        funcionVolver();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        funcionSeleccionar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int filaSeleccionada = jTableAeronavesPlanes.getSelectedRow();
        if (filaSeleccionada >= 0) {    
            String celda = jTableAeronavesPlanes.getValueAt(filaSeleccionada,0).toString();
            String celda1 = jTableAeronavesPlanes.getValueAt(filaSeleccionada,1).toString();
            jTextFieldAAMatricula.setText(celda);
            jTextFieldAATipo.setText(celda1);
            llaveActual = celda;
            botonBuscar.setEnabled(false);
            botonAgregar.setEnabled(false);
            botonVolver.setEnabled(false);
            jMenuItem1.setEnabled(false);
            jMenuItem2.setEnabled(false);
            jMenuItem3.setEnabled(false);
            botonActualizar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this,mensajeNoSeleccionado,"SELECCIÓN",JOptionPane.INFORMATION_MESSAGE);
        }
        jTextFieldAAMatricula.setBackground(new java.awt.Color(255,255,255));
        jTextFieldAATipo.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int filaSeleccionada = jTableAeronavesPlanes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String campoBuscar = jTableAeronavesPlanes.getValueAt(filaSeleccionada,0).toString();
            if (!ping.eliminarFilaEnTabla(nombreTablaMysql,pK,campoBuscar)) {
                JOptionPane.showMessageDialog(this,ping.getMensajesError(),"ERROR AL ELIMINAR REGISTRO",JOptionPane.ERROR_MESSAGE);
            } else {
                jTextFieldSMatricula.setText(null);
                jTextFieldSTipo.setText(null);                
                jTextFieldSMatricula.setBackground(new java.awt.Color(212,208,200));
                jTextFieldSTipo.setBackground(new java.awt.Color(212,208,200));               
            }
            if(!ping.mostrarColumnasTablaMysqlSimple(jTableAeronavesPlanes,nombreTablaMysql,columnasTablaMysql)){
                JOptionPane.showMessageDialog(this,ping.getMensajesError(),"NO SE HA PODIDO CARGAR LA TABLA",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,mensajeNoSeleccionado,"SELECCIÓN",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTextFieldBuscarMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMatriculaKeyTyped
        int limite = 7;
        char caracterValidar = evt.getKeyChar();
        if (!((Character.isDigit(caracterValidar) || Character.isAlphabetic(caracterValidar) || Character.isISOControl(caracterValidar)) && jTextFieldBuscarMatricula.getText().length() < limite)){
            getToolkit().beep(); 
            evt.consume();
        } else {
            char caracterMayuscula = Character.toUpperCase(caracterValidar);
            evt.setKeyChar(caracterMayuscula);
        }
    }//GEN-LAST:event_jTextFieldBuscarMatriculaKeyTyped

    private void jTextFieldAAMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAAMatriculaKeyTyped
        int limite = 7;
        char caracterValidar = evt.getKeyChar();
        if (!((Character.isDigit(caracterValidar) || Character.isAlphabetic(caracterValidar) || Character.isISOControl(caracterValidar)) && jTextFieldAAMatricula.getText().length() < limite)){
            getToolkit().beep(); 
            evt.consume();
        } else {
            char caracterMayuscula = Character.toUpperCase(caracterValidar);
            evt.setKeyChar(caracterMayuscula);
        }
    }//GEN-LAST:event_jTextFieldAAMatriculaKeyTyped

    private void jTextFieldAATipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAATipoKeyTyped
        int limite = 4;
        char caracterValidar = evt.getKeyChar();
        if (!((Character.isDigit(caracterValidar) || Character.isAlphabetic(caracterValidar) || Character.isISOControl(caracterValidar)) && jTextFieldAATipo.getText().length() < limite)){
            getToolkit().beep(); 
            evt.consume();
        } else {
            char caracterMayuscula = Character.toUpperCase(caracterValidar);
            evt.setKeyChar(caracterMayuscula);
        }
    }//GEN-LAST:event_jTextFieldAATipoKeyTyped

    private void jTextFieldAAMatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAAMatriculaFocusGained
        jTextFieldAAMatricula.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jTextFieldAAMatriculaFocusGained

    private void jTextFieldAATipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAATipoFocusGained
        jTextFieldAATipo.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jTextFieldAATipoFocusGained

    private void jTableAeronavesPlanesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAeronavesPlanesMouseClicked
        if (evt.getClickCount() == 2) {
            funcionSeleccionar();
        } else if (evt.getClickCount() == 3) {
            funcionVolver();
        }
    }//GEN-LAST:event_jTableAeronavesPlanesMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CJDialogAeronavesPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CJDialogAeronavesPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CJDialogAeronavesPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CJDialogAeronavesPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CJDialogAeronavesPlanes dialog = new CJDialogAeronavesPlanes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAeronavesPlanes;
    private javax.swing.JTextField jTextFieldAAMatricula;
    private javax.swing.JTextField jTextFieldAATipo;
    private javax.swing.JTextField jTextFieldBuscarMatricula;
    private javax.swing.JTextField jTextFieldSMatricula;
    private javax.swing.JTextField jTextFieldSTipo;
    // End of variables declaration//GEN-END:variables
    
    private final ConexionMysql ping = new ConexionMysql();
    private String llaveActual;
    private String [] columnasTablaMysql;
    private String nombreTablaMysql;
    private String pK;
    private final String mensajeNoSeleccionado = "Primero de clic izquierdo sobre el elemento deseado de la tabla";
    
    public void setDatosConexion (String sv, String us, String pw, String dB, String [] cTM, String nTM, String pK) {
        if (ping.conectarBD(sv,us,pw,dB)) {
            columnasTablaMysql = cTM;
            nombreTablaMysql = nTM;
            this.pK = pK;
            if(!ping.mostrarColumnasTablaMysqlSimple(jTableAeronavesPlanes, nombreTablaMysql, columnasTablaMysql)){
                JOptionPane.showMessageDialog(this, ping.getMensajesError(),"NO SE HA PODIDO CARGAR LA INFORMACIÓN DE LA TABLA",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, ping.getMensajesError(),"NO SE HA PODIDO CONECTAR A LA BASE",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void funcionSeleccionar() {
        int filaSeleccionada = jTableAeronavesPlanes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String celda = jTableAeronavesPlanes.getValueAt(filaSeleccionada,0).toString();
            String celda1 = jTableAeronavesPlanes.getValueAt(filaSeleccionada,1).toString();
            jTextFieldSMatricula.setText(celda);
            jTextFieldSTipo.setText(celda1);       
            jTextFieldSMatricula.setBackground(new java.awt.Color(153,255,153));
            jTextFieldSTipo.setBackground(new java.awt.Color(153,255,153));          
        } else {
            JOptionPane.showMessageDialog(this,mensajeNoSeleccionado,"SELECCIÓN",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void funcionVolver() {
        if (FuncionesGenerales.estaVacioJTextField(jTextFieldSMatricula)) {
            jTextFieldSMatricula.setBackground(new java.awt.Color(255,0,0));
            jTextFieldSTipo.setBackground(new java.awt.Color(255,0,0));
        } else {
            AJFrameVentanaCapturas.jTextField4.setText(jTextFieldSMatricula.getText());
            AJFrameVentanaCapturas.jTextField5.setText(jTextFieldSTipo.getText());
            AJFrameVentanaCapturas.jTextField4.setBackground(new java.awt.Color(153,255,153));
            AJFrameVentanaCapturas.jTextField5.setBackground(new java.awt.Color(153,255,153));
            this.dispose();  
        }
    }
}
