

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author u18206
 */
import java.util.ArrayList;

public class Salas
{
    private ArrayList<Sala> salas;

    public Salas() throws Exception
    {
        salas = new ArrayList<>();
        for(bd.dbos.Sala s : bd.daos.Salas.getSalas())
            salas.add(new Sala(s.getCod(), s.getNome(), s.getLimite()));
    }

    public Sala getSala(String s) throws Exception
    {
        Sala sala = null;

        if (s == null || s.equals(""))
            throw new Exception("Procura de sala inválida");

        for (Sala sl : this.salas)
            if (sl.getNome().equals(s))
                sala = sl;

        if (sala == null)
            throw new Exception("Essa sala não existe");

        return sala;
    }

    public String[] getSalasDisponiveis()
    {
        ArrayList<String> s = new ArrayList<>();

        for (Sala sala : salas)
            s.add(sala.getNome());

        return (String[]) s.toArray(new String[0]);
    }

    public String toString()
    {
        String ret = "";

        if (this.salas.isEmpty())
            ret += "Não existe nenhuma sala";

        for (int i = 0; i < this.salas.size(); i++)
            ret += "Salas: " + this.salas.get(i).getNome() + "\n";

        return ret;
    }

    public int hashCode()
    {
        int ret = 777;

        ret *= 2 + this.salas.hashCode();

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

        Salas sl = (Salas)obj;

        for (int i = 0; i < this.salas.size(); i++)
            if (!sl.salas.contains(this.salas.get(i)))
                return false;

        return true;
    }
}
