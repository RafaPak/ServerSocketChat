package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import java.util.ArrayList;

public class Salas
{
    public static bd.dbos.Sala[] getSalas () throws Exception
    {
        ArrayList<bd.dbos.Sala> salas = new ArrayList<>();
        
        try
        {
            String sql = "SELECT * FROM SALAS ";

            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet r = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            while(r.next()) {
                salas.add(new bd.dbos.Sala(r.getInt("CodSala"), r.getString("Nome"), r.getInt("Maximo")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return (bd.dbos.Sala[]) salas.toArray(new bd.dbos.Sala[0]);
    }
}