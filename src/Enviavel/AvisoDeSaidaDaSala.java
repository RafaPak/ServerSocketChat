package Enviavel;

public class AvisoDeSaidaDaSala implements Enviavel
{
    protected String nome;

    public AvisoDeSaidaDaSala(String n) throws Exception
    {
        if (n == null || n.equals(""))
            throw new Exception("Nome inv�lido");

        this.nome = n;
    }

    public String getNome()
    {
        return this.nome;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + this.nome.hashCode();

        return ret;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!this.getClass().equals(obj.getClass()))
            return false;

        AvisoDeEntradaNaSala a = (AvisoDeEntradaNaSala)obj;

        if (!this.nome.equals(a.nome))
            return false;

        return true;
    }

    public String toString()
    {
        return this.nome + " saiu da sala.";
    }
}
