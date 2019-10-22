package bd;

import bd.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://regulus:1433;databasename=BD18206",
            "BD18206", "InfinityYasuo777");
        }
        catch (Exception erro)
        {
            System.err.println("Problemas de conexao com o Banco de Dados");
            System.exit(0);
        }

        COMANDO = comando;
    }
}