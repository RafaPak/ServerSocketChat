

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author u18206
 */
import Enviavel.Enviavel;
import java.io.*;
import java.net.*;

public class Usuario
{
    private String nome;
    private Socket conexao;
    private ObjectOutputStream transmissor;
    private ObjectInputStream receptor;
    private Sala sala;

    public Usuario(String nome,
                   Socket conexao,
                   ObjectOutputStream transmissor,
                   ObjectInputStream  receptor,
                   Sala sala) throws Exception
    {
        if (nome == null || nome.equals(""))
            throw new Exception("Nome inválido");

        this.nome = nome;
        this.conexao = conexao;
        this.transmissor = transmissor;
        this.receptor = receptor;
        this.sala = sala;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void envia(Enviavel x) throws Exception
    {
        if (x == null)
            throw new Exception("Conteúdo inexistente");

        try
        {
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }

    public Enviavel recebe() throws Exception
    {
        Enviavel obj = null;

        try
        {
            obj = (Enviavel)receptor.readObject();
        }

        catch(Exception e)
        {
            e.getStackTrace();
        }

        return obj;
    }

    public void fechaTudo() throws Exception
    {
        this.transmissor.close();
        this.receptor.close();
        this.conexao.close();
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Usuario user = (Usuario)obj;

        if (!this.conexao.equals(user.conexao))
            return false;

        if (!this.transmissor.equals(user.transmissor))
            return false;

        if (!this.receptor.equals(user.receptor))
            return false;

        if (!this.nome.equals(user.nome))
            return false;

        if (!this.sala.equals(user.sala))
            return false;

        return true;
    }

    public String toString()
    {
        return "Usuário....: " + this.nome +
               "Conexão....: " + this.conexao.toString() +
               "Transmissor: " + this.transmissor.toString() +
               "Receptor...: " + this.receptor.toString() +
               "Sala.......: " + this.sala.toString();
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + this.nome.hashCode();
        ret *= 2 + this.conexao.hashCode();
        ret *= 2 + this.transmissor.hashCode();
        ret *= 2 + this.receptor.hashCode();
        ret *= 2 + this.sala.hashCode();

        return ret;
    }

    public Usuario(Usuario modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.nome = modelo.nome;
        this.conexao = modelo.conexao;
        this.transmissor = modelo.transmissor;
        this.receptor = modelo.receptor;
        this.sala = modelo.sala;
    }

    public Object clone()
    {
        Usuario ret = null;

        try
        {
            ret = new Usuario(this);
        }
        catch (Exception e)
        {}

        return ret;
    }
}
