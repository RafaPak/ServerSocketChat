package Enviavel;

public class PermissaoParaMensagemPrivada implements Enviavel
{
    protected String remetente;
    protected String destinatario;
    
    public PermissaoParaMensagemPrivada(String r, String d)
    {
        this.remetente = r;
        this.destinatario = d;
    }
    
    public String getRemetente()
    {
        return remetente;
    }
    
    public String getDestinatario()
    {
        return destinatario;
    }
    
    public int hashCode()
    {
        int ret = 777;
        
        ret *= 2 + this.remetente.hashCode();
        ret *= 2 + this.destinatario.hashCode();
        
        return ret;
    }
    
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (this.getClass() != obj.getClass())
            return false;
        
        PedidoParaMensagemPrivada p = (PedidoParaMensagemPrivada)obj;
        
        if (!this.destinatario.equals(p.destinatario))
            return false;
        
        if (!this.remetente.equals(p.remetente))
            return false;
        
        return true;
    }
    
    public String toString()
    {
        return "Remetente:    " + this.remetente +
               "Destinatário: " + this.destinatario;
    }
}
