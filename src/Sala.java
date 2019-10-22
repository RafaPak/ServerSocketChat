

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author u18206
 */
import java.util.ArrayList;

public class Sala
{
    protected int codSala;
    protected String nomeSala;
    protected int limiteSala;
    protected ArrayList<Usuario> usuarios;
    protected int qtd;

    public Sala(int c, String n, int l) throws Exception
    {
        this.usuarios = new ArrayList<>();
        this.setCodSala(c);
        this.setNome(n);
        this.setLimite(l);
    }

    public void setCodSala(int c) throws Exception
    {
        if (c <= 0)
            throw new Exception("Código inválido para sala");

        this.codSala = c;
    }

    public void setNome(String n) throws Exception
    {
        if (n == null || n.equals(""))
            throw new Exception("Nome inválido");

        this.nomeSala = n;
    }

    public void setLimite(int l) throws Exception
    {
        if (l <= 0)
            throw new Exception("Limite inválido");

        this.limiteSala = l;
    }

    public int getCodSala()
    {
        return this.codSala;
    }

    public String getNome()
    {
        return this.nomeSala;
    }

    public int getLimite()
    {
        return this.limiteSala;
    }

    public Usuario getUsuario(String n) throws Exception
    {
        if (n == null || n.equals(""))
            throw new Exception("Nome de usuário nulo");

        for (int i = 0; i < this.qtd; i++)
            if (this.usuarios.get(i).getNome().trim().equals(n.trim()))
                return usuarios.get(i);
        
        throw new Exception("Usuário não encontrado");
    }

    public ArrayList<Usuario> getUsuarios()
    {
        ArrayList<Usuario> lista = new ArrayList<Usuario>(this.usuarios.size());

        for (Usuario u : this.usuarios)
            lista.add((Usuario)u.clone());

        return lista;
    }
    
    public int getQtd()
    {
        return this.qtd;
    }

    public boolean isCheia()
    {
        return this.qtd == this.limiteSala;
    }

    public boolean isVazia()
    {
        return this.qtd == 0;
    }

    public void addUser(Usuario usuario) throws Exception
    {
        if (!this.isCheia())
        {
            this.usuarios.add(usuario);
            this.qtd++;
        }
        else throw new Exception("A sala está cheia");
    }

    public void excluirUser(Usuario usuario) throws Exception
    {
        if (this.qtd == 0)
            throw new Exception("Não tem usuários na lista");

        if (!(usuarios.contains(usuario)))
            throw new Exception("Este usuário não existe");

        this.usuarios.remove(usuario);
        this.qtd--;
    }

    public boolean existe(String nome)
    {
        for (int i = 0; i < this.qtd; i++)
            if (this.usuarios.get(i).getNome().trim().equals(nome.trim()))
                return true;

        return false;
    }

    public String toString()
    {
        return "Nome da sala.......: " + this.nomeSala + "\n" +
               "Código da sala.....: " + this.codSala  + "\n" +
               "Lugares disponíveis: " + (this.limiteSala - this.qtd);
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Sala sala = (Sala)obj;

        if (this.codSala != sala.codSala)
            return false;

        if (this.nomeSala.equals(sala.nomeSala))
            return false;

        if (this.limiteSala != sala.limiteSala)
            return false;

        if (!this.usuarios.equals(sala.usuarios))
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + new Integer(this.codSala).hashCode();
        ret *= 2 + this.nomeSala.hashCode();
        ret *= 2 + new Integer(this.limiteSala).hashCode();
        ret *= 2 + new Integer(this.qtd).hashCode();
        ret *= 2 + this.usuarios.hashCode();

        return ret;
    }
}
