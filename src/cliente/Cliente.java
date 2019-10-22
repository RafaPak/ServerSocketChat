package cliente;

import java.net.*;

public class Cliente
{    
    public static void main(String[] args)
    {
        try
        {
            Socket s = new Socket("localhost", 12345);

            JanelaEscolhas jE = new JanelaEscolhas(s);
            jE.setVisible(true);
        }
        catch (Exception e)
        {
            System.err.println("N�o foi poss�vel abrir o servidor");
        }
    }
}