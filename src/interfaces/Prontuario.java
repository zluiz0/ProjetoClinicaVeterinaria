package interfaces;

public interface Prontuario {

    String emitirReceituario();

    default String emitirReceituarioFormatado(String clinica) {
        String receituario = emitirReceituario();
        if (!Prontuario.isReceituarioValido(receituario)) {
            return "Receituário indisponível: a consulta ainda não foi atendida\n";
        }
        return formatarLayout(clinica, receituario);
    }

    private String formatarLayout(String titulo, String corpo) {
        return """
               ========================================
               [%s]
               %s
               ========================================
               """.formatted(titulo.toUpperCase(), corpo);
    }

    static boolean isReceituarioValido(String receituario) {
        return receituario != null && !receituario.isBlank();
    }
}
