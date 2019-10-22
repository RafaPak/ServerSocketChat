import java.net.*;

public class Servidor
{
    public static void main (String[] args)
    {
        try
        {
            Salas salas = new Salas();

            ServerSocket pedido = new ServerSocket(12345);
            System.out.println("O servidor est� em funcionamento...");

            for(;;)
            {
                Socket conexao = pedido.accept();
                CuidadoraDeUsuario cuidadora = new CuidadoraDeUsuario(conexao, salas);
                cuidadora.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
