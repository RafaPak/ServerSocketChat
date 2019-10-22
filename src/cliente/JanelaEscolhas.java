package cliente;

import javax.swing.DefaultComboBoxModel;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class JanelaEscolhas extends javax.swing.JFrame {

    private Socket conexao;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public JanelaEscolhas(Socket s) throws Exception
    {
        initComponents();
        
        this.conexao = s;
        oos = new ObjectOutputStream(conexao.getOutputStream());
        ois = new ObjectInputStream(conexao.getInputStream());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbSala = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        cbxSalas = new javax.swing.JComboBox<>();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlPrincipal.setBorder(new javax.swing.border.MatteBorder(null));

        lbNome.setText("Nome: ");

        lbSala.setText("Salas: ");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNome)
                            .addComponent(lbSala))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNome)
                            .addComponent(cbxSalas, 0, 135, Short.MAX_VALUE)))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnEntrar)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSala))
                .addGap(18, 18, 18)
                .addComponent(btnEntrar)
                .addGap(21, 21, 21))
        );

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 64, 0));
        jLabel1.setText("Chat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel1)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try
        {
            String[] s = (String[])ois.readObject();

            DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
            for (String st : s)
            {
                m.addElement(st);
            }
            cbxSalas.setModel(m);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        if (txtNome.getText() == null || txtNome.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Nome inválido");
            return;
        }

        try
        {
            oos.writeObject(cbxSalas.getModel().getSelectedItem());
            oos.writeObject(txtNome.getText());
            oos.flush();

            if (!ois.readBoolean())
            {
                JOptionPane.showMessageDialog(null, "Sala está cheia");
                return;
            }

            if (!ois.readBoolean())
            {
                JOptionPane.showMessageDialog(null, "Este nome já está em uso");
                return;
            }

            Janela j = new Janela(oos, ois, (String)cbxSalas.getSelectedItem());
            j.setVisible(true);
            
            this.setVisible(false);
            this.dispose();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JComboBox<String> cbxSalas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSala;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
