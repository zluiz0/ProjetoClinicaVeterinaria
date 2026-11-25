# Controle de Clínica Veterinária

Trabalho Prático em Dupla — Programação Orientada a Objetos
Prof. Mestre Marcos Roberto de Moraes (Maromo) — Fatec Mogi Mirim

## 1. Identificação da dupla

| Integrante | RA |
|---|---|
| Luiz Miguel Antonio da Silva | 1630482523013 |
| Gabriel Henrique Silva | 1630482523008 |

## 2. Tema

**Tema 02 – Controle de Clínica Veterinária**, desenvolvido em Java 21 com interface gráfica em Swing. Todos os dados ficam em memória (`List<ConsultaPet>` com `ArrayList`), sem banco de dados e sem arquivos.

## 3. Estrutura e explicação das classes

```
src
├── controller
│   └── Principal.java
├── model
│   └── ConsultaPet.java
├── interfaces
│   └── Prontuario.java
├── exception
│   └── HorarioIndisponivelException.java
├── logic
│   └── Agenda.java
└── view
    └── TelaClinica.java
```

| Pacote | Classe | O que faz |
|---|---|---|
| `controller` | `Principal` | Contém o `main`: cria a `TelaClinica` e chama `setVisible(true)`. |
| `model` | `ConsultaPet` | Entidade principal. Guarda número, pet, tutor, motivo, data (`AAAAMMDD`), hora (`HHMM`), prescrição e situação (agendada/atendida). Implementa `Comparable<ConsultaPet>` (ordena por data e depois por hora) e `Prontuario`. Sobrescreve `equals()`, `hashCode()` (ambos pelo número da consulta) e `toString()`. |
| `interfaces` | `Prontuario` | Interface com `emitirReceituario()` (abstrato), `emitirReceituarioFormatado(String)` (`default`), `formatarLayout(...)` (`private`) e `isReceituarioValido(String)` (`static`). |
| `exception` | `HorarioIndisponivelException` | Checked Exception (`extends Exception`), lançada ao agendar em data e hora que já têm consulta. |
| `logic` | `Agenda` | Guarda a `List<ConsultaPet>` e as regras: `agendar`, `buscar`, `darBaixa`, `cancelar` e `listarOrdenado` (usa `Collections.sort`). |
| `view` | `TelaClinica` | `JFrame` que implementa `ActionListener` e `WindowListener`. Tem campos de texto, os botões Cadastrar, Pesquisar, Processar Operação, Ordenar, Receituário, Cancelar e Sair, e uma `JTextArea` com `JScrollPane` para os resultados. |

### Como os requisitos do trabalho foram atendidos

- **Dados em memória:** `List<ConsultaPet> lista = new ArrayList<>()` na classe `Agenda`.
- **Swing e eventos:** `ActionListener` nos botões e `WindowListener` no fechamento da janela.
- **Exceções:** `HorarioIndisponivelException` (customizada) e `NumberFormatException` na conversão dos campos numéricos; as mensagens aparecem com `JOptionPane.showMessageDialog()`.
- **Comparable e ordenação:** `compareTo()` em `ConsultaPet` e `Collections.sort()` em `Agenda`.
- **Interface e polimorfismo:** a tela usa uma referência do tipo `Prontuario` para emitir o receituário.

## 4. Como usar a tela

1. **Cadastrar:** preencha pet, tutor, motivo, data (ex.: `20261125`) e hora (ex.: `1430`). Datas e horas fora do formato são recusadas com mensagem de erro.
2. **Pesquisar:** informe o número da consulta.
3. **Processar Operação:** informe o número da consulta e a prescrição para dar baixa no atendimento.
4. **Receituário:** informe o número de uma consulta já atendida.
5. **Ordenar:** lista a agenda ordenada; se a data estiver preenchida, mostra só as consultas daquele dia.
6. **Cancelar:** informe o número de uma consulta ainda não atendida.
7. **Sair:** encerra o programa.

## 5. Como executar (Java 21)

Na pasta do projeto:

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out controller.Principal
```

Também é possível abrir a pasta do projeto em uma IDE e executar a classe `controller.Principal`.

## 6. Histórico de prompts de IA

### Prompt 1
- **Prompt:** "Me ajude a criar o README.md de um trabalho de faculdade em Java 21 (Programação Orientada a Objetos). O tema é Controle de Clínica Veterinária, com tela em Swing e dados só em memória. Pacotes: controller (Principal), model (ConsultaPet), interfaces (Prontuario), exception (HorarioIndisponivelException), logic (Agenda) e view (TelaClinica). O README precisa ter: identificação da dupla, tema, explicação das classes, como executar e histórico de prompts de IA. Escreva em português e não invente nada."
- **Resposta obtida:** este README.md.
- **Refinamento:** Conferimos todo o material e colocamos os prompts de IA.

### Prompt 2
- **Prompt:** "Revise a tela Swing do meu trabalho (classe `TelaClinica`, Java 21) e corrija e melhore o que for preciso. Use só recursos que aparecem nos materiais da disciplina (POO_07 a POO_10): `JFrame`, `JLabel`, `JTextField`, `JButton`, `JTextArea`, `JScrollPane`, `JOptionPane`, `ActionListener` e `WindowListener`, com `setLayout(null)`. Aponte o que estiver fora desse padrão, valide os campos de data e hora e mantenha o estilo do código do professor. Mostre só o que mudou."
- **Resposta obtida:** a IA revisou a `TelaClinica` e fez três ajustes: (1) removeu `setEditable(false)` da `JTextArea`, por não aparecer nos materiais; (2) criou os métodos `dataValida` (formato `AAAAMMDD`, mês de 1 a 12 e dia de 1 a 31) e `horaValida` (formato `HHMM`, horas de 0 a 23 e minutos de 0 a 59), usando apenas `if` e operações aritméticas; (3) passou a validar data e hora no botão Cadastrar e a data no botão Ordenar, exibindo o erro com `JOptionPane.showMessageDialog()` e `ERROR_MESSAGE`. Antes disso, a tela aceitava valores como `99999999` e hora `2575`.
- **Refinamento:** o código foi compilado em Java 21 e a regra de validação foi testada com valores válidos e inválidos (`20261125` e `1430` aceitos; `99999999`, mês 13 e hora `2575` recusados).
