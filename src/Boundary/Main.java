/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import Control.Auth;
import Control.CertaintyFactor;
import Control.JsonHandler;
import Control.MathFx;
import Entity.ButtonColumn;
import Entity.ButtonColumn2;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.json.simple.JSONObject;

/**
 *
 * @author Azhary Arliansyah
 */
public class Main extends javax.swing.JFrame {

    private DefaultCellEditor defaultCellEditor;
    private List<JComboBox> comboboxList;
    private CertaintyFactor cf;
    private List<String> symptoms;
    private List<String> diseases;
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        if (!Auth.isAuthenticated()) {
            this.logoutButton.setText("Login");
            this.TabbedPane.remove(2);
            this.TabbedPane.remove(2);
        }
        
        this.cf = new CertaintyFactor();
        this.symptoms = this.cf.getSymptoms();
        
        this.SymptomsTable.setRowHeight(this.SymptomsTable.getRowHeight() + 3);
        
        DefaultTableModel model = (DefaultTableModel)
                this.SymptomsTable.getModel();
        model.setRowCount(this.symptoms.size());
        model.setColumnCount(2);
        
        TableColumn columns = SymptomsTable.getColumnModel().getColumn(1);
        this.comboboxList = new ArrayList<>();
        JSONObject weights = 
                JsonHandler.readJsonObjectFile("config/weights.json");
        Set<String> keys = weights.keySet();
        JComboBox combobox = new JComboBox(keys.toArray());
        
        for (int i = 0; i < this.symptoms.size(); i++) {
            JComboBox rowCombobox = new JComboBox(keys.toArray());
            this.comboboxList.add(rowCombobox);
        }
        
        this.defaultCellEditor = new DefaultCellEditor(combobox);
        columns.setCellEditor(this.defaultCellEditor);
        columns.setCellRenderer(new CheckBoxCellRenderer(this.comboboxList, 
                this.symptoms));
        SymptomsTable.repaint();
        
        this.symptomsListTable.setRowHeight(this.symptomsListTable.getRowHeight() + 3);
        DefaultTableModel listModel = (DefaultTableModel)
                this.symptomsListTable.getModel();
        listModel.setRowCount(this.symptoms.size());
        listModel.setColumnCount(2);
        
        for (int i = 0; i < this.symptoms.size(); i++) {
            listModel.setValueAt(this.symptoms.get(i), i, 0);
            listModel.setValueAt("Remove", i, 1);
        }
        
        Action delete = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // extend to nothing
            }
            
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(this.symptomsListTable, delete, 1);
        
        this.setDiseaseListTable();
    }
    
    public void setDiseaseListTable() {
        this.cf.expertCertaintyFactors();
        this.diseases = new ArrayList<>(this.cf.getDiseases().keySet());
        this.diseaseListTable.setRowHeight(this.diseaseListTable.getRowHeight() + 3);
        DefaultTableModel diseaseModel = (DefaultTableModel)
                this.diseaseListTable.getModel();
        diseaseModel.setRowCount(this.diseases.size());
        diseaseModel.setColumnCount(2);
        
        for (int i = 0; i < this.diseases.size(); i++) {
            diseaseModel.setValueAt(this.diseases.get(i), i, 0);
            diseaseModel.setValueAt("Remove", i, 1);
        }
        
        Action delete2 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // extend to nothing
            }
            
        };
        
        ButtonColumn2 buttonColumn2 = 
                new ButtonColumn2(this.diseaseListTable, delete2, 1);
    }
    
    class CheckBoxCellRenderer implements TableCellRenderer {
        List<JComboBox> comboboxList;
        List<String> symptoms;
        
        public CheckBoxCellRenderer(List<JComboBox> comboboxList, 
                List<String> symptoms) {
            this.comboboxList = comboboxList;
            this.symptoms = symptoms;
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, 
                Object value, boolean isSelected, boolean hasFocus, 
                int row, int column) {
            jtable.setValueAt(this.symptoms.get(row), row, 0);
            this.comboboxList.get(row).setSelectedItem(value);
            return this.comboboxList.get(row);
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

        TabbedPane = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        startDiagnoseButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SymptomsTable = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        resultLabel = new javax.swing.JLabel();
        jToolBar3 = new javax.swing.JToolBar();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        symptomsListTable = new javax.swing.JTable();
        symptomsMenu = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        diseaseListTable = new javax.swing.JTable();
        diseaseMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        startDiagnoseButton.setText("Start Diagnose");
        startDiagnoseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDiagnoseButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDiagnoseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(startDiagnoseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel1);

        TabbedPane.addTab("Home", jToolBar1);

        jToolBar2.setRollover(true);

        SymptomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Gejala", "CF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(SymptomsTable);

        jToggleButton1.setText("Diagnose");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnoseButton(evt);
            }
        });

        resultLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1)
                        .addGap(76, 76, 76))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addGap(18, 18, 18)
                .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar2.add(jPanel2);

        TabbedPane.addTab("Diagnose", jToolBar2);

        jToolBar3.setRollover(true);

        symptomsListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nama Gejala", "-"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(symptomsListTable);

        symptomsMenu.setText("Tambah Gejala Baru");
        symptomsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                symptomsMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(symptomsMenu)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(symptomsMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jToolBar3.add(jPanel3);

        TabbedPane.addTab("Menu Gejala", jToolBar3);

        jToolBar4.setRollover(true);

        diseaseListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nama Penyakit", "-"
            }
        ));
        jScrollPane3.setViewportView(diseaseListTable);

        diseaseMenu.setText("Tambah Penyakit Baru");
        diseaseMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseaseMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(diseaseMenu)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(diseaseMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar4.add(jPanel4);

        TabbedPane.addTab("Menu Penyakit", jToolBar4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void diagnoseButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnoseButton
        
        Map<String, Double> userCertaintyFactor = new HashMap<>();
        Map<String, Double> certaintyWeight = this.cf.getCertaintyWeight();
        
        for (int i = 0; i < this.comboboxList.size(); i++) {
            Object objWeight = this.comboboxList.get(i)
                    .getSelectedItem();
            double weight = 0.0;
            if (objWeight != null) {
                String strWeight = String.valueOf(objWeight);
                weight = certaintyWeight.get(strWeight);
            }
            userCertaintyFactor.put(this.symptoms.get(i), weight);
        }
        
        Map<String, Double> diseaseCertaintyFactor = 
                this.cf.calculateDiseaseCertaintyFactor(userCertaintyFactor);
        List<Map.Entry<String, Double>> rank = 
                MathFx.sortMapDouble(diseaseCertaintyFactor, "DESC");
        
        StringBuilder resultLabelText = 
                new StringBuilder("<html>Anda didiagnosa menderita penyakit:<br/>");
        for (int i = 0; i < rank.size(); i++) {
            if (i >= 2) {
                break;
            }
            
            resultLabelText.append("- ")
                    .append(rank.get(i).getKey())
                    .append(" dengan tingkat keyakinan ")
                    .append(rank.get(i).getValue() * 100)
                    .append("%<br/>");
        }
        resultLabelText.append("</html>");
        this.resultLabel.setText(resultLabelText.toString());
    }//GEN-LAST:event_diagnoseButton

    private void startDiagnoseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDiagnoseButtonActionPerformed
        // TODO add your handling code here:
        this.TabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_startDiagnoseButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Auth.clearSession();
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void symptomsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_symptomsMenuActionPerformed
        new SymptomsMenu().setVisible(true);
    }//GEN-LAST:event_symptomsMenuActionPerformed

    private void diseaseMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseaseMenuActionPerformed
        DiseaseMenu diseaseMenu = new DiseaseMenu();
        diseaseMenu.setVisible(true);
        diseaseMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                setDiseaseListTable();
            }
        });
    }//GEN-LAST:event_diseaseMenuActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable SymptomsTable;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JTable diseaseListTable;
    private javax.swing.JButton diseaseMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JButton startDiagnoseButton;
    private javax.swing.JTable symptomsListTable;
    private javax.swing.JButton symptomsMenu;
    // End of variables declaration//GEN-END:variables
}
