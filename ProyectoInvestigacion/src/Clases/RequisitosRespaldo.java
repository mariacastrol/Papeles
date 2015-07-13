/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author ADOLFO
 */
public class RequisitosRespaldo extends SwingWorker<Void, String> {
    Icon bien = new ImageIcon(getClass().getResource("/Necesarios/OKIcon.png"));
    
    private final JButton need_jButton;
    private final JFrame need_ventana;
    private final JTextField need_caja;
    private final JComboBox need_combo;
    private final String need_con;
    private boolean perfecto;
    File file;
    
    public RequisitosRespaldo(JButton boton, JFrame ventanaPadre, JTextField caja, JComboBox combo, String con) {
        need_jButton = boton;
        need_ventana = ventanaPadre;
        need_caja = caja;
        need_combo = combo;
        need_con = con;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        file = new File(need_caja.getText());
        String path = file.getAbsolutePath()+ ".sql";
        perfecto = true;
        if (need_combo.getSelectedItem().toString().startsWith("C")) {
            try {
                int c = JOptionPane.showConfirmDialog(need_ventana,"¿Desea crear una copia de seguridad en la siguiente ruta? \n" + path,"Mensaje de Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if (c == JOptionPane.YES_OPTION) {
                    publish("false","TRABAJANDO...");
                    String backup = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump --opt -u " + "root" + " -p" + need_con + " " + "BASEAEROPUERTO" + "  -r" + path;
                    Runtime rt = Runtime.getRuntime();
                    rt.exec(backup);
                    perfecto = true;
                }    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(need_ventana,"SE HA GENERADO UN ERROR AL GENERAR LA COPIA\n" + ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
                perfecto = false;
            }
        } else {
            path = file.getAbsolutePath();
            int c = JOptionPane.showConfirmDialog(need_ventana, "¿Desea restaurar esta base de datos?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (c == JOptionPane.YES_OPTION) {
                publish("false","TRABAJANDO...");
                CMysql ping = new CMysql();
                if (ping.conectarSV("localhost","root",SONS.getC())) {
                    if (!ping.ejecutarModificacionStatement("DROP DATABASE IF EXISTS BASEAEROPUERTO;")) {
                        JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL ELIMINAR BASE DE DATOS",JOptionPane.ERROR_MESSAGE);
                        perfecto = false;
                    } else {
                        if (!ping.ejecutarModificacionStatement("CREATE DATABASE BASEAEROPUERTO DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci;")) {
                            JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL CREAR BASE DE DATOS",JOptionPane.ERROR_MESSAGE);
                            perfecto = false;
                        } else {
                            try {
                                String backup = "cmd /C cd C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin & mysql -u root -p" + need_con + " BASEAEROPUERTO <" + path;
                                Process runtimeProcess = Runtime.getRuntime().exec(backup);
                                int processComplete = runtimeProcess.waitFor();
                                if (processComplete == 0) {
                                    perfecto = true;
                                } else {
                                    JOptionPane.showMessageDialog(need_ventana,"ERROR AL RESTAURAR BASE DE DATOS","",JOptionPane.ERROR_MESSAGE);
                                    perfecto = false;
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(need_ventana,"SE HA GENERADO UN ERROR AL RESTAURAR LA BASE DE DATOS\n" + ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
                                perfecto = false;
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"CONEXIÓN FALLIDA",JOptionPane.ERROR_MESSAGE);
                    perfecto = false;
                }
            }    
        }
        return null;
    }
    
    @Override
    protected void process(List<String> chunks) {
        need_jButton.setEnabled(Boolean.valueOf(chunks.get(0)));
        need_jButton.setText(chunks.get(1));
    }

    @Override
    protected void done() {
        if (perfecto) {
            if (need_combo.getSelectedItem().toString().startsWith("C")) {
                JOptionPane.showMessageDialog(need_ventana,"SE HA CREADO EXITOSAMENTE UNA COPIA DE LA BASE DE DATOS EN:\n" + file.getPath(),"",JOptionPane.INFORMATION_MESSAGE,bien);
            } else {
                JOptionPane.showMessageDialog(need_ventana,"SE HA RESTAURADO EXITOSAMENTE LA BASE DE DATOS","",JOptionPane.INFORMATION_MESSAGE,bien);
            }
            need_jButton.setText("HECHO");
            need_jButton.setEnabled(false);
        } else {
            need_jButton.setText("INTENTAR NUEVAMENTE");
            need_jButton.setEnabled(true);
        }   
    }
    
}
