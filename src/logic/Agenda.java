package logic;

import exception.HorarioIndisponivelException;
import model.ConsultaPet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Agenda {

    private List<ConsultaPet> lista = new ArrayList<>();
    private int proximoNumero = 1;

    public String agendar(String nomePet, String nomeTutor, String motivo,
                          int data, int hora) throws HorarioIndisponivelException {
        Iterator<ConsultaPet> i = lista.iterator();
        while (i.hasNext()) {
            ConsultaPet c = i.next();
            if (c.getData() == data && c.getHora() == hora) {
                throw new HorarioIndisponivelException();
            }
        }
        ConsultaPet nova = new ConsultaPet(proximoNumero, nomePet, nomeTutor, motivo, data, hora);
        lista.add(nova);
        proximoNumero++;
        return "Consulta " + nova.getNumConsulta() + " agendada com sucesso";
    }

    public ConsultaPet buscar(int numConsulta) {
        ConsultaPet modelo = new ConsultaPet(numConsulta, "", "", "", 0, 0);
        Iterator<ConsultaPet> i = lista.iterator();
        while (i.hasNext()) {
            ConsultaPet c = i.next();
            if (c.equals(modelo)) {
                return c;
            }
        }
        return null;
    }

    public String darBaixa(int numConsulta, String prescricao) {
        ConsultaPet c = buscar(numConsulta);
        if (c == null) {
            return "Consulta não encontrada";
        }
        if (c.isAtendida()) {
            return "Consulta " + numConsulta + " já foi atendida";
        }
        c.setPrescricao(prescricao);
        c.setAtendida(true);
        return "Atendimento da consulta " + numConsulta + " registrado com sucesso";
    }

    public String cancelar(int numConsulta) {
        ConsultaPet c = buscar(numConsulta);
        if (c == null) {
            return "Consulta não encontrada";
        }
        if (c.isAtendida()) {
            return "Não é possível cancelar uma consulta já atendida";
        }
        lista.remove(c);
        return "Consulta " + numConsulta + " cancelada com sucesso";
    }

    public String listarOrdenado(int data) {
        Collections.sort(lista);
        StringBuilder sb = new StringBuilder();
        Iterator<ConsultaPet> i = lista.iterator();
        while (i.hasNext()) {
            ConsultaPet c = i.next();
            if (data == 0 || c.getData() == data) {
                sb.append(c.toString());
            }
        }
        if (sb.length() == 0) {
            return "Nenhuma consulta encontrada\n";
        }
        return sb.toString();
    }
}
