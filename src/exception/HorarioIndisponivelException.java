package exception;

public class HorarioIndisponivelException extends Exception {

    @Override
    public String toString() {
        return "Horário indisponível: já existe uma consulta agendada para esta data e hora";
    }
}
