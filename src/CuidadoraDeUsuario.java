import Enviavel.PedidoParaSairDaSala;
import Enviavel.AvisoDeSaidaDaSala;
import Enviavel.AvisoDeEntradaNaSala;
import Enviavel.Mensagem;
import Enviavel.Enviavel;
import Enviavel.PedidoParaMensagemPrivada;
import Enviavel.PermissaoParaMensagemPrivada;
import java.io.*;
import java.net.*;

public class CuidadoraDeUsuario extends Thread
{
    private Socket  conexao;
    private Salas   salas;
    private Usuario usuario;

    public CuidadoraDeUsuario (Socket conexao, Salas salas) throws Exception
    {
        if (conexao == null)
            throw new Exception("Conexão inválida");

        if (salas == null)
            throw new Exception("Salas inválidas");

        this.conexao = conexao;
        this.salas = salas;
    }

    public void run()
    {
        // procurar em salas a sala com o nome desejado
        // interagir com o usr via OOS e OIS ate descobrir o nome que ele deseja usar, eventualmente, informando nome invalido ou ja usado
        // instanciar o Usuario, fornecendo, conexao, OOS, OIS, nome e sala
        // fazer varias vezes this.usuario.envia(new AvisoDeEntradaDaSala(i)), onde i é o nome de algum usuario que ja estava na sala
        // fazer varias vezes i.envia(new AvisoDeEntradaDaSala(usuario.getNome()), onde i é o nome de algum usuario que ja estava na sala
        // incluir o usuario na sala
        try
        {
            // declarar e instanciar OOS e OIS
            ObjectOutputStream saida = new ObjectOutputStream(this.conexao.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(this.conexao.getInputStream());

            // interagir com o usr via OOS e OIS ate descobrir o nome da sala em que ele deseja entrar, eventualmente, informando sala cheia
            saida.writeObject(this.salas.getSalasDisponiveis());
            saida.flush();
            
            String nick;
            String nomeSala;
            Sala salaEscolhida = null;

            for (;;)
            {
                nomeSala = (String)entrada.readObject();
                
                if (this.salas.getSala(nomeSala).isCheia())
                {
                    saida.writeBoolean(false);
                    saida.flush();
                    entrada.readObject();
                    continue;
                }
                salaEscolhida = this.salas.getSala(nomeSala);
                saida.writeBoolean(true);
                
                nick = (String)entrada.readObject();

                if (salaEscolhida.existe(nick))
                {
                    saida.writeBoolean(false);
                    saida.flush();
                    continue;
                }
                saida.writeBoolean(true);
                
                String[] listaU = new String[salaEscolhida.getQtd()];
                
                int i = 0;
                for (Usuario u : salaEscolhida.getUsuarios())
                {
                    listaU[i++] = u.getNome();
                }
                
                saida.writeObject(listaU);
                saida.flush();
                
                this.usuario = new Usuario(nick, this.conexao, saida, entrada, salaEscolhida);
                salaEscolhida.addUser(this.usuario);

                for (Usuario s : salaEscolhida.getUsuarios())
                {
                    if (!s.equals(this.usuario))
                        s.envia(new AvisoDeEntradaNaSala(usuario.getNome()));
                }

                Enviavel recebido = null;
                do
                {
                    // receber mensagens, avisos de entrada na e de saida da sala
                    // se for mensagem, pega nela o destinatario, acha o destinatario na sala e manda para ele a mensagem
                    recebido = this.usuario.recebe();

                    if (recebido != null)
                    {
                        if (recebido instanceof Mensagem)
                        {
                            recebido = new Mensagem(this.usuario.getNome(),
                                                   ((Mensagem)recebido).getMensagem(),
                                                   ((Mensagem)recebido).getDestinatario());
                            Mensagem m = (Mensagem)recebido;
                            if (m.getDestinatario() != null)
                            {
                                Usuario destinatario = salaEscolhida.getUsuario(m.getDestinatario());
                                destinatario.envia(m);
                                continue;
                            }
                        }
                        
                        if (recebido instanceof PedidoParaMensagemPrivada)
                        {
                            PedidoParaMensagemPrivada p = 
                                    new PedidoParaMensagemPrivada(
                                            this.usuario.getNome(),
                                            ((PedidoParaMensagemPrivada)recebido).getDestinatario());
                            Usuario aEnviar = salaEscolhida.getUsuario(((PedidoParaMensagemPrivada)recebido).getDestinatario());
                            aEnviar.envia(p);
                            
                            continue;
                        }
                        
                        if (recebido instanceof PermissaoParaMensagemPrivada)
                        {
                            PermissaoParaMensagemPrivada p = 
                                    new PermissaoParaMensagemPrivada(
                                            this.usuario.getNome(),
                                            ((PermissaoParaMensagemPrivada)recebido).getDestinatario());
                            Usuario aEnviar = salaEscolhida.getUsuario(((PermissaoParaMensagemPrivada)recebido).getDestinatario());
                            aEnviar.envia(p);
                            
                            continue;
                        }
                        
                        if (recebido instanceof AvisoDeEntradaNaSala)
                            recebido = (AvisoDeEntradaNaSala)recebido;
                        
                        if (recebido instanceof AvisoDeSaidaDaSala)
                            recebido = (AvisoDeSaidaDaSala)recebido;
                        
                        if (recebido instanceof PedidoParaSairDaSala)
                            recebido = (PedidoParaSairDaSala)recebido;
                        
                        for (Usuario u: salaEscolhida.getUsuarios())
                            if (!u.equals(this.usuario))
                                u.envia(recebido);
                    }
                    else
                        break;
                }
                while (!(recebido instanceof PedidoParaSairDaSala));

                // remover this.usuario da sala
                // mandar new AvisoDeSaidaDaSala(this.usuario.getNome()) para todos da sala
                for (Usuario u : salaEscolhida.getUsuarios())
                    if (!u.equals(this.usuario))
                        u.envia(new AvisoDeSaidaDaSala(this.usuario.getNome()));
                        
                System.out.println("O usuario " + this.usuario.getNome() + " saiu do servidor.");
                this.usuario.fechaTudo();
                salaEscolhida.excluirUser(this.usuario);
                break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return "Usuários: " + usuario.toString() +
               "Salas...: " + salas.toString() +
               "Conexões: " + conexao.toString();
    }

    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        CuidadoraDeUsuario cdu = (CuidadoraDeUsuario)obj;

        if (!this.conexao.equals(cdu.conexao))
            return false;

        if (!this.salas.equals(cdu.salas))
            return false;

        if (!this.usuario.equals(cdu.usuario))
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + this.conexao.hashCode();
        ret *= 2 + this.salas.hashCode();
        ret *= 2 + this.usuario.hashCode();

        return ret;
    }
}
