package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    Conexao conectar = new Conexao();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Pessoa> dados = new ArrayList<>();
        String sql = "select * from pessoa";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setIdpessoa(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setTelefone(rs.getString(3));
                p.setEmail(rs.getString(4));
                dados.add(p);
            }
        } catch (Exception e) {
        }

        return dados;
    }
    
    public int inserir(Pessoa p){
        String sql ="insert into pessoa (nome, telefone, email) values (?,?,?)";
        
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setString(3, p.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        
        return 1;
    }
    
    public int editar(Pessoa p){
        int r=0;
        String sql ="update pessoa set nome=?, telefone=?, email=? where idpessoa=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getIdpessoa());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        
        return r;
    }
    
    public void excluir(int id){
        String sql ="delete from pessoa where idpessoa="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
