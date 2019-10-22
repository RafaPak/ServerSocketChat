package Enviavel;

public class Mensagem implements Enviavel
{
    protected String mensagem;
    protected String nome;
    protected String destinatario;

    public Mensagem (String n, String m) throws Exception
    {
        this.nome = n;
        this.mensagem = m;
        this.destinatario = null;
    }
    
    public Mensagem (String n, String m, String r)
    {
        this.nome = n;
        this.mensagem = m;
        this.destinatario = r;
    }

    public String getMensagem()
    {
        return this.mensagem;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public String getDestinatario()
    {
        return this.destinatario;
    }
    
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Mensagem msg = (Mensagem)obj;

        if (!this.mensagem.equals(msg.mensagem))
            return false;

        if (!this.nome.equals(msg.nome))
            return false;

        return true;
    }

    public String toString()
    {
        return this.nome + ": " + this.mensagem;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + this.mensagem.hashCode();
        ret *= 2 + this.nome.hashCode();

        return ret;
    }
}
