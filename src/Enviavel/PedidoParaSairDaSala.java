package Enviavel;

public class PedidoParaSairDaSala implements Enviavel
{
    protected String nome;
    
    public PedidoParaSairDaSala(String n) throws Exception
    {
        if (n == null || n.equals(""))
            throw new Exception("Nome inválido");
        
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
    
    public String toString()
    {
        return this.nome + " deseja sair.";
    }
    
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!this.getClass().equals(obj.getClass()))
            return false;

        PedidoParaSairDaSala p = (PedidoParaSairDaSala)obj;

        if (!this.nome.equals(p.nome))
            return false;

        return true;
    }
}
