package model;

import interfaces.Prontuario;

public class ConsultaPet implements Comparable<ConsultaPet>, Prontuario {

    private int numConsulta;
    private String nomePet;
    private String nomeTutor;
    private String motivo;
    private int data;
    private int hora;
    private String prescricao;
    private boolean atendida;

    public ConsultaPet(int numConsulta, String nomePet, String nomeTutor,
                       String motivo, int data, int hora) {
        this.numConsulta = numConsulta;
        this.nomePet = nomePet;
        this.nomeTutor = nomeTutor;
        this.motivo = motivo;
        this.data = data;
        this.hora = hora;
        this.prescricao = "";
        this.atendida = false;
    }

    public int getNumConsulta() {
        return numConsulta;
    }

    public void setNumConsulta(int numConsulta) {
        this.numConsulta = numConsulta;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeTutor() {
        return nomeTutor;
    }

    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    @Override
    public int hashCode() {
        return numConsulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ConsultaPet c) {
            return this.numConsulta == c.numConsulta;
        }
        return false;
    }

    @Override
    public int compareTo(ConsultaPet o) {
        if (this.data < o.data) {
            return -1;
        }
        if (this.data > o.data) {
            return 1;
        }
        if (this.hora < o.hora) {
            return -1;
        }
        if (this.hora > o.hora) {
            return 1;
        }
        return 0;
    }

    @Override
    public String emitirReceituario() {
        if (!atendida) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Paciente: ").append(nomePet).append("\n");
        sb.append("Tutor: ").append(nomeTutor).append("\n");
        sb.append("Prescrição: ").append(prescricao);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consulta nº: ").append(numConsulta).append("\n");
        sb.append("Pet: ").append(nomePet).append("\n");
        sb.append("Tutor: ").append(nomeTutor).append("\n");
        sb.append("Motivo: ").append(motivo).append("\n");
        sb.append("Data: ").append(data).append("  Hora: ").append(hora).append("\n");
        sb.append("Situação: ").append(atendida ? "Atendida" : "Agendada").append("\n\n");
        return sb.toString();
    }
}
