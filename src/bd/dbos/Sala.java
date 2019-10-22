package bd.dbos;

public class Sala implements Cloneable
{
    private int    codigo;
    private String nome;
    private int    limite;

    public void setCod(int cod) throws Exception
    {
        if (cod <= 0)
            throw new Exception("C�digo inv�lido");

        this.codigo = cod;
    }

    public int getCod()
    {
        return this.codigo;
    }

    public void setNome(String n) throws Exception
    {
        if (n == null || n.equals(""))
            throw new Exception("Nome não fornecido");

        this.nome = n;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void setLimite(int lim) throws Exception
    {
        if (lim <= 0)
        	throw new Exception("Limite inv�lido");

        this.limite = lim;
    }

    public int getLimite()
    {
        return this.limite;
    }

    public Sala (int cod, String n, int lim) throws Exception
    {
        this.setCod   (cod);
        this.setNome  (n);
        this.setLimite(lim);
    }

    public String toString ()
    {
        String ret = "";

        ret += "Codigo: " + this.codigo + "\n";
        ret += "Nome..: " + this.nome   + "\n";
        ret += "Limite: " + this.limite;

        return ret;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Sala))
            return false;

        Sala sala = (Sala)obj;

        if (this.codigo != sala.codigo)
            return false;

        if (this.nome.equals(sala.nome))
            return false;

        if (this.limite != sala.limite)
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + new Integer(this.codigo).hashCode();
        ret *= 2 + this.nome.hashCode();
        ret *= 2 + new Integer(this.limite).hashCode();

        return ret;
    }

    public Sala (Sala modelo) throws Exception
    {
        this.codigo = modelo.codigo;
        this.nome   = modelo.nome;
        this.limite = modelo.limite;
    }

    public Object clone ()
    {
        Sala ret = null;

        try
        {
            ret = new Sala(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}