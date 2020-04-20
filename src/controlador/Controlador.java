/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.xml.internal.txw2.TXW;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Pessoa;
import modelo.PessoaDAO;
import view.frmPessoa;

/**
 *
 * @author Consorte
 */
public class Controlador implements ActionListener {

    PessoaDAO dao = new PessoaDAO();
    Pessoa p = new Pessoa();
    frmPessoa vista = new frmPessoa();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(frmPessoa v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOK.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.tabela);
    }

    public void inserir() {
        String nom = vista.txtNome.getText();
        String tel = vista.txtTelefone.getText();
        String ema = vista.txtEmail.getText();
        p.setNome(nom);
        p.setTelefone(tel);
        p.setEmail(ema);
        int r = dao.inserir(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Pessoa Inserido com Sucesso");
        } else {
            JOptionPane.showMessageDialog(vista, "Erro");
        }
    }

    public void listar(JTable tabela) {
        modelo = (DefaultTableModel) tabela.getModel();
        List<Pessoa> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdpessoa();
            object[1] = lista.get(i).getNome();
            object[2] = lista.get(i).getTelefone();
            object[3] = lista.get(i).getEmail();
            modelo.addRow(object);
        }
        vista.tabela.setModel(modelo);
    }

    void Editar() {
        int id = Integer.parseInt(vista.txtCodigo.getText());
        String nom = vista.txtNome.getText();
        String tel = vista.txtTelefone.getText();
        String ema = vista.txtEmail.getText();
        p.setIdpessoa(id);
        p.setNome(nom);
        p.setTelefone(tel);
        p.setEmail(ema);
        int r = dao.editar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Pessoa Alterado com Sucesso");
        } else {
            JOptionPane.showMessageDialog(vista, "Erro");
        }
    }

    void limparTabela() {
        for (int i = 0; i < vista.tabela.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            listar(vista.tabela);
        }
        if (e.getSource() == vista.btnGuardar) {
            inserir();
            limparTabela();
            listar(vista.tabela);
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabela.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Deve selecionar uma fila");
            } else {
                int id = Integer.parseInt((String) vista.tabela.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabela.getValueAt(fila, 1).toString();
                String tel = (String) vista.tabela.getValueAt(fila, 2).toString();
                String ema = (String) vista.tabela.getValueAt(fila, 3).toString();
                vista.txtCodigo.setText("" + id);
                vista.txtNome.setText(nom);
                vista.txtTelefone.setText(tel);
                vista.txtEmail.setText(ema);
            }
        }
        if (e.getSource() == vista.btnOK) {
            Editar();
            limparTabela();
            listar(vista.tabela);
        }
        if (e.getSource() == vista.btnEliminar) {
            int fila = vista.tabela.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Deve selecionar uma Pessoa");
            } else {
                int id = Integer.parseInt((String) vista.tabela.getValueAt(fila, 0).toString());
                dao.excluir(id);
                JOptionPane.showMessageDialog(vista, "Dados excluídos com Sucesso");
            }
            limparTabela();
            listar(vista.tabela);
        }
    }

}
