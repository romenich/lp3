/*
 * Copyright (C) 2023 Romenik
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycompany.infox.telas;

import java.sql.*;
import com.mycompany.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 * Jframe interno para a criação da tela de usuários
 *
 * @author Romenik
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limparDados() {
        txtNomeUsuario.setText(null);
        txtFoneUsuario.setText(null);
        txtLoginUsuario.setText(null);
        txtSenhaUsuario.setText(null);

    }

    /**
     * Método para consultar usuários
     */
    private void consultar() {
        String sql = "select * from tabusuarios where iduser=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUsuario.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomeUsuario.setText(rs.getString(2));
                txtFoneUsuario.setText(rs.getString(3));
                txtLoginUsuario.setText(rs.getString(4));
                txtSenhaUsuario.setText(rs.getString(5));
                cboPerfilUsuario.setSelectedItem(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado.");
                limparDados();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para adicionar usuários
     */
    private void adicionar() {
        String sql = "insert into tabusuarios (iduser, usuario, telefone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUsuario.getText());
            pst.setString(2, txtNomeUsuario.getText());
            pst.setString(3, txtFoneUsuario.getText());
            pst.setString(4, txtLoginUsuario.getText());
            pst.setString(5, txtSenhaUsuario.getText());
            pst.setString(6, cboPerfilUsuario.getSelectedItem().toString());

            if ((txtIdUsuario.getText().isEmpty()) || (txtNomeUsuario.getText().isEmpty()) || (txtLoginUsuario.getText().isEmpty()) || (txtSenhaUsuario.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso.");
                    limparDados();
                    txtIdUsuario.setText(null);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para alterar dados do usuário
     */
    private void alterar() {
        String sql = "update tabusuarios set usuario=?, telefone=?, login=?, senha=?, perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeUsuario.getText());
            pst.setString(2, txtFoneUsuario.getText());
            pst.setString(3, txtLoginUsuario.getText());
            pst.setString(4, txtSenhaUsuario.getText());
            pst.setString(5, cboPerfilUsuario.getSelectedItem().toString());
            pst.setString(6, txtIdUsuario.getText());
            if ((txtIdUsuario.getText().isEmpty()) || (txtNomeUsuario.getText().isEmpty()) || (txtLoginUsuario.getText().isEmpty()) || (txtSenhaUsuario.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso.");
                    limparDados();
                    txtIdUsuario.setText(null);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável pela remoção de usuários
     */
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tabusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdUsuario.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    limparDados();
                    txtIdUsuario.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
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

        idUsuario = new javax.swing.JLabel();
        nomeUsuario = new javax.swing.JLabel();
        loginUsuario = new javax.swing.JLabel();
        senhaUsuario = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtNomeUsuario = new javax.swing.JTextField();
        txtFoneUsuario = new javax.swing.JTextField();
        txtSenhaUsuario = new javax.swing.JTextField();
        cboPerfilUsuario = new javax.swing.JComboBox<>();
        foneUsuario = new javax.swing.JLabel();
        txtLoginUsuario = new javax.swing.JTextField();
        bntCreateUser = new javax.swing.JButton();
        btnReadUser = new javax.swing.JButton();
        btnUpdateUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setVisible(true);

        idUsuario.setText("Id*:");

        nomeUsuario.setText("Nome*:");

        loginUsuario.setText("Login*:");

        senhaUsuario.setText("Senha*:");

        jLabel5.setText("Perfil*:");

        cboPerfilUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        foneUsuario.setText("Fone:");

        bntCreateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/createIcone.png"))); // NOI18N
        bntCreateUser.setToolTipText("Adicionar ");
        bntCreateUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCreateUser.setPreferredSize(new java.awt.Dimension(80, 80));
        bntCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCreateUserActionPerformed(evt);
            }
        });

        btnReadUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/readIcone.png"))); // NOI18N
        btnReadUser.setToolTipText("Consultar ");
        btnReadUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReadUser.setPreferredSize(new java.awt.Dimension(80, 80));
        btnReadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadUserActionPerformed(evt);
            }
        });

        btnUpdateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/updateIcone.png"))); // NOI18N
        btnUpdateUser.setToolTipText("Alterar");
        btnUpdateUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateUser.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/deleteIcone.png"))); // NOI18N
        btnDeleteUser.setToolTipText("Remover");
        btnDeleteUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteUser.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        jLabel1.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(senhaUsuario)
                        .addGap(515, 515, 515)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginUsuario)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoginUsuario)
                            .addComponent(cboPerfilUsuario, 0, 68, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idUsuario)
                        .addGap(29, 29, 29)
                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomeUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(foneUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFoneUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(bntCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnReadUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bntCreateUser, btnDeleteUser, btnReadUser, btnUpdateUser});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idUsuario)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeUsuario)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginUsuario)
                    .addComponent(txtFoneUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneUsuario)
                    .addComponent(txtLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboPerfilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(senhaUsuario))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReadUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        setBounds(0, 0, 800, 486);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadUserActionPerformed
        consultar();
    }//GEN-LAST:event_btnReadUserActionPerformed

    private void bntCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCreateUserActionPerformed
        adicionar();
    }//GEN-LAST:event_bntCreateUserActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        alterar();
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        remover();
    }//GEN-LAST:event_btnDeleteUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCreateUser;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnReadUser;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JComboBox<String> cboPerfilUsuario;
    private javax.swing.JLabel foneUsuario;
    private javax.swing.JLabel idUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel loginUsuario;
    private javax.swing.JLabel nomeUsuario;
    private javax.swing.JLabel senhaUsuario;
    private javax.swing.JTextField txtFoneUsuario;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtLoginUsuario;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtSenhaUsuario;
    // End of variables declaration//GEN-END:variables
}
