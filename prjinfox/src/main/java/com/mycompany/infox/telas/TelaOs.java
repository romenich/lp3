/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.infox.telas;

import java.sql.*;
import com.mycompany.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author reydemonio
 */
public class TelaOs extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    // a linha abaixo cria uma variábvel para armazenar um texto de acordo com o radio button selecionato
    private String tipo;

    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limparDados() {
        txtIdCliente.setText(null);
        txtEquipamentoOS.setText(null);
        txtDefeitoOS.setText(null);
        txtServicoOs.setText(null);
        txtTecnicoOs.setText(null);
        txtValorOs.setText(null);
    }

    private void pesquisarCliente() {
        String sql = "select idcliente as Id, nomecliente as Nome, telefonecliente as Telefone from tabclientes where nomecliente like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisarCliente.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCampos() {
        int setar = tblClientes.getSelectedRow();
        txtIdCliente.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }

    // método para emitir uma ordem de serviço
    private void emitirOs() {
        String sql = "insert into tabos (tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcliente) values(?, ?, ?, ? ,? ,? ,?, ? )";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOsSituacao.getSelectedItem().toString());
            pst.setString(3, txtEquipamentoOS.getText());
            pst.setString(4, txtDefeitoOS.getText());
            pst.setString(5, txtServicoOs.getText());
            pst.setString(6, txtTecnicoOs.getText());
            //.replace substitui a "," pelo "."
            pst.setString(7, txtValorOs.getText().replace(",", "."));
            pst.setString(8, txtIdCliente.getText());

            //validação dos campos obrigatórios
            if ((txtIdCliente.getText().isEmpty()) || (txtEquipamentoOS.getText().isEmpty()) || (txtDefeitoOS.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço emitida com sucesso!");
                    limparDados();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // método para pesquisar uma ordem de serviço
    private void pesquisarOs() {
        // a linha abaixo cria uma caixa de entrada do tipo JOpiton pane
        String numOs = JOptionPane.showInputDialog("Número da Ordem de Serviço: ");
        String sql = "select * from tabos where os = " + numOs;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                //setando os radio buttons
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    rbtOS.setSelected(true);
                    tipo = "OS";
                } else {
                    rbtOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }
                cboOsSituacao.setSelectedItem(rs.getString(4));
                txtEquipamentoOS.setText(rs.getString(5));
                txtDefeitoOS.setText(rs.getString(6));
                txtServicoOs.setText(rs.getString(7));
                txtTecnicoOs.setText(rs.getString(8));
                txtValorOs.setText(rs.getString(9));
                txtIdCliente.setText(rs.getString(10));
                //evitando problemas
                btnAdicionarOs.setEnabled(false);
                txtPesquisarCliente.setEnabled(false);
                tblClientes.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Ordem de serviço não cadastrada!");
            }
        } catch (java.sql.SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Ordem de serviço inválida!");
            //System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOrcamento = new javax.swing.JRadioButton();
        rbtOS = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtPesquisarCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEquipamentoOS = new javax.swing.JTextField();
        txtDefeitoOS = new javax.swing.JTextField();
        txtServicoOs = new javax.swing.JTextField();
        txtTecnicoOs = new javax.swing.JTextField();
        txtValorOs = new javax.swing.JTextField();
        btnAdicionarOs = new javax.swing.JButton();
        btnPesquisarOs = new javax.swing.JButton();
        btnAlterarOs = new javax.swing.JButton();
        btnExcluirOs = new javax.swing.JButton();
        btnImprimirOs = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("OS");
        setPreferredSize(new java.awt.Dimension(800, 486));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS: ");

        jLabel2.setText("Data:");

        txtOS.setEditable(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N

        buttonGroup1.add(rbtOrcamento);
        rbtOrcamento.setText("Orçamento");
        rbtOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOS);
        rbtOS.setText("Ordem de serviço");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtOrcamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtOS)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtOS)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrcamento)
                    .addComponent(rbtOS))
                .addContainerGap())
        );

        jLabel3.setText("Situação:");

        cboOsSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando peças", "Abandonado pelo cliente", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtPesquisarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarClienteKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/pesquisar2.png"))); // NOI18N

        jLabel5.setText("Id*:");

        txtIdCliente.setEditable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(txtPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setText("Equipamento*:");

        jLabel7.setText("Defeito*:");

        jLabel8.setText("Serviço:");

        jLabel9.setText("Técnico:");

        jLabel10.setText("Valor Total:");

        txtValorOs.setText("0");

        btnAdicionarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/addComputerIcon.png"))); // NOI18N
        btnAdicionarOs.setToolTipText("Adicionar OS");
        btnAdicionarOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarOsActionPerformed(evt);
            }
        });

        btnPesquisarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/searchComputerIcon.png"))); // NOI18N
        btnPesquisarOs.setToolTipText("Pesquisar OS");
        btnPesquisarOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisarOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPesquisarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarOsActionPerformed(evt);
            }
        });

        btnAlterarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/updateComputerIcon.png"))); // NOI18N
        btnAlterarOs.setToolTipText("Alterar OS");
        btnAlterarOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterarOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnExcluirOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/deleteComputerIcon.png"))); // NOI18N
        btnExcluirOs.setToolTipText("Deletar OS");
        btnExcluirOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnImprimirOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/printerIcon.png"))); // NOI18N
        btnImprimirOs.setToolTipText("Imprimir OS");
        btnImprimirOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboOsSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(412, 412, 412))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTecnicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtServicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDefeitoOS, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEquipamentoOS, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)))
                        .addComponent(jLabel10)
                        .addGap(32, 32, 32)
                        .addComponent(txtValorOs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionarOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisarOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterarOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimirOs)
                .addGap(252, 252, 252))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboOsSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEquipamentoOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDefeitoOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtServicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTecnicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValorOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterarOs)
                    .addComponent(btnPesquisarOs)
                    .addComponent(btnExcluirOs)
                    .addComponent(btnImprimirOs)
                    .addComponent(btnAdicionarOs))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setBounds(0, 0, 800, 486);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarOsActionPerformed
        // chamando o método pesquisar Os 
        pesquisarOs();
    }//GEN-LAST:event_btnPesquisarOsActionPerformed

    private void txtPesquisarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarClienteKeyReleased
        // chamando o método pesquisar cliente
        pesquisarCliente();
    }//GEN-LAST:event_txtPesquisarClienteKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // chamando o método setar campos
        setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void rbtOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcamentoActionPerformed
        // atribuindo um texto a variável tipo se o radio button estiver selecionado
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOrcamentoActionPerformed

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        // atribuindo um texto a variável tipo se o radio button selecionado
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o form marcar o radio button orçamento
        rbtOrcamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAdicionarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarOsActionPerformed
        // chamar o método para emitir ordem de serviços
        emitirOs();
    }//GEN-LAST:event_btnAdicionarOsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarOs;
    private javax.swing.JButton btnAlterarOs;
    private javax.swing.JButton btnExcluirOs;
    private javax.swing.JButton btnImprimirOs;
    private javax.swing.JButton btnPesquisarOs;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtOS;
    private javax.swing.JRadioButton rbtOrcamento;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeitoOS;
    private javax.swing.JTextField txtEquipamentoOS;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtPesquisarCliente;
    private javax.swing.JTextField txtServicoOs;
    private javax.swing.JTextField txtTecnicoOs;
    private javax.swing.JTextField txtValorOs;
    // End of variables declaration//GEN-END:variables
}
