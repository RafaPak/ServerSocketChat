package cliente;

import java.io.*;
import javax.swing.*;

import Enviavel.AvisoDeEntradaNaSala;
import Enviavel.AvisoDeSaidaDaSala;
import Enviavel.PedidoParaSairDaSala;
import Enviavel.Mensagem;
import Enviavel.PedidoParaMensagemPrivada;
import Enviavel.PermissaoParaMensagemPrivada;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Janela extends javax.swing.JFrame
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DefaultListModel<String> m;
    private String usuarioAReceber;

    public Janela (ObjectOutputStream oos, ObjectInputStream ois, String s) throws Exception
    {      
        this.oos = oos;
        this.ois = ois;

        this.initComponents();
        
        jLabel1.setText("Chat Geral - " + s);
        
        txtMensagens.append("Você entrou na sala.\n");
        txtMensagem.addActionListener(action);
        lstUsuarios.addMouseListener(clickLista);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMensagem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUsuarios = new javax.swing.JList<>();
        btnSair = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensagens = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMsgsPrivada = new javax.swing.JTextArea();
        lbPrivate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensagemActionPerformed(evt);
            }
        });

        lstUsuarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstUsuarios);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtMensagens.setEditable(false);
        txtMensagens.setColumns(20);
        txtMensagens.setLineWrap(true);
        txtMensagens.setRows(5);
        jScrollPane2.setViewportView(txtMensagens);

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 64, 0));
        jLabel1.setText("Chat Geral");

        txtMsgsPrivada.setEditable(false);
        txtMsgsPrivada.setColumns(20);
        txtMsgsPrivada.setRows(5);
        jScrollPane3.setViewportView(txtMsgsPrivada);

        lbPrivate.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        lbPrivate.setForeground(new java.awt.Color(0, 64, 0));
        lbPrivate.setText("Chat Privado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(lbPrivate))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnviar)
                            .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPrivate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try
        {
            if (txtMensagem.getText().trim().equals("") || txtMensagem.getText() == null)
                return;
            
            Mensagem aEnviar = new Mensagem("EU", txtMensagem.getText());
            
            oos.writeObject(aEnviar);
            oos.flush();
            
            txtMensagens.append("Você: " + txtMensagem.getText() + "\n");
            txtMensagem.setText("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try
        {
            String[] s = (String[])ois.readObject();

            m = new DefaultListModel<>();
            for(String st : s)
            {
                m.addElement(st);
            }
            lstUsuarios.setModel(m);
            t.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }//GEN-LAST:event_formWindowOpened

    private void txtMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMensagemActionPerformed
        btnEnviar.doClick();
    }//GEN-LAST:event_txtMensagemActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try
        {
            oos.writeObject(new PedidoParaSairDaSala("Eu"));
            oos.flush();
            
            fim = true;
            
            oos.close();
            ois.close();
            
            this.setVisible(false);
            this.dispose();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    ArrayList<String> usuariosMensagemPrivada = new ArrayList<>();
    
    MouseAdapter clickLista = (new MouseAdapter() {
        public void mouseClicked(MouseEvent evt)
        {
            JList list = (JList)evt.getSource();
            
            if (list.getModel().getSize() == 0)
                return;
            
            if (evt.getClickCount() == 2)
            {
                // Double-click detectado
                int index = list.locationToIndex(evt.getPoint());

                list.setSelectedIndex(index);
                usuarioAReceber = (String)list.getSelectedValue();

                if (!usuariosMensagemPrivada.contains(usuarioAReceber))
                {
                    try
                    {
                        PedidoParaMensagemPrivada p = new PedidoParaMensagemPrivada("Eu", usuarioAReceber);
                        oos.writeObject(p);
                        oos.flush();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    JOptionPane.showMessageDialog(null, "Enviado pedido para conversar com esta pessoa");
                    
                    return;
                }
                
                String mensagemAEnviar = JOptionPane.showInputDialog("Digite a mensagem particular a ser enviada para " + usuarioAReceber);;

                if (mensagemAEnviar == null || mensagemAEnviar.trim().equals(""))
                    return;
                
                try
                {
                    Mensagem aEnviar = new Mensagem("Eu", mensagemAEnviar, usuarioAReceber);
                    oos.writeObject(aEnviar);
                    oos.flush();
                    txtMsgsPrivada.append("Você para " + usuarioAReceber + ": " + mensagemAEnviar + "\n");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    });
    
    Action action = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            btnEnviar.doClick();
        }
    };
    
    boolean fim = false;
    Thread t = new Thread() {
    	public void run()
    	{
            while (!fim)
            {
                try
                {
                    Object r = ois.readObject();
                    
                    if (r instanceof AvisoDeEntradaNaSala)
                    {
                        m.addElement(((AvisoDeEntradaNaSala)r).getNome());
                        txtMensagens.append(r.toString() + "\n");
                    }
                    
                    if (r instanceof Mensagem)
                    {
                        Mensagem m = (Mensagem)r;
                        if (m.getDestinatario() != null)
                            txtMsgsPrivada.append(((Mensagem)r).getNome() + " para você: " + ((Mensagem)r).getMensagem() + "\n");
                        else
                            txtMensagens.append(((Mensagem)r).getNome() + ": " + ((Mensagem)r).getMensagem() + "\n");
                    }
                    
                    if (r instanceof AvisoDeSaidaDaSala)
                    {
                        m.removeElement(((AvisoDeSaidaDaSala)r).getNome());
                        txtMensagens.append(r.toString() + "\n");
                        txtMsgsPrivada.append(r.toString() + "\n");
                    }
                    
                    if (r instanceof PermissaoParaMensagemPrivada)
                    {
                        usuariosMensagemPrivada.add(((PermissaoParaMensagemPrivada)r).getRemetente());
                    }
                    
                    if (r instanceof PedidoParaMensagemPrivada)
                    {
                        int i = JOptionPane.showConfirmDialog(null, 
                                "Deseja receber mensagens privadas de " + 
                                ((PedidoParaMensagemPrivada)r).getRemetente() + "?",
                                "Confirmar", JOptionPane.YES_NO_OPTION);
                        
                        if (i == JOptionPane.YES_OPTION )
                        {
                            PermissaoParaMensagemPrivada p = new PermissaoParaMensagemPrivada("eu",
                                                        ((PedidoParaMensagemPrivada)r).getRemetente());
                            oos.writeObject(p);
                            oos.flush();
                        }
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                    System.out.println("A conexao foi fechada.");
                }
            }
    	}
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbPrivate;
    private javax.swing.JList<String> lstUsuarios;
    private javax.swing.JTextField txtMensagem;
    private javax.swing.JTextArea txtMensagens;
    private javax.swing.JTextArea txtMsgsPrivada;
    // End of variables declaration//GEN-END:variables
}
