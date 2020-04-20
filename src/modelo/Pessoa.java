
package modelo;


public class Pessoa {
    
    int idpessoa;
    String nome;
    String telefone;
    String email;

    public Pessoa() {
    }

    public Pessoa(int idpessoa, String nome, String telefone, String email) {
        this.idpessoa = idpessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    
}
