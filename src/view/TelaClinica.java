package view;

import exception.HorarioIndisponivelException;
import interfaces.Prontuario;
import logic.Agenda;
import model.ConsultaPet;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaClinica extends JFrame implements WindowListener, ActionListener {

    protected Dimension dFrame, dLabel, dTextField, dButton, dScroll;
    protected JButton cmdCadastrar, cmdPesquisar, cmdProcessar, cmdOrdenar, cmdReceituario, cmdCancelar, cmdSair;
    protected JTextField txtNumero, txtPet, txtTutor, txtMotivo, txtData, txtHora, txtPrescricao;
    protected JLabel lblNumero, lblPet, lblTutor, lblMotivo, lblData, lblHora, lblPrescricao;
    protected JTextArea txtMsg;
    protected JScrollPane scrollMsg;
    public Agenda agenda;

    public TelaClinica() {
        agenda = new Agenda();
        dFrame = new Dimension(560, 640);
        dLabel = new Dimension(130, 25);
        dTextField = new Dimension(220, 25);
        dButton = new Dimension(150, 28);
        dScroll = new Dimension(500, 200);
        setTitle("Controle de Clínica Veterinária");
        setSize(dFrame);
        setLayout(null);

        lblNumero = new JLabel("Nº da consulta:");
        lblNumero.setSize(dLabel);
        lblNumero.setLocation(25, 20);
        add(lblNumero);
        txtNumero = new JTextField();
        txtNumero.setSize(dTextField);
        txtNumero.setLocation(160, 20);
        add(txtNumero);

        lblPet = new JLabel("Nome do pet:");
        lblPet.setSize(dLabel);
        lblPet.setLocation(25, 50);
        add(lblPet);
        txtPet = new JTextField();
        txtPet.setSize(dTextField);
        txtPet.setLocation(160, 50);
        add(txtPet);

        lblTutor = new JLabel("Nome do tutor:");
        lblTutor.setSize(dLabel);
        lblTutor.setLocation(25, 80);
        add(lblTutor);
        txtTutor = new JTextField();
        txtTutor.setSize(dTextField);
        txtTutor.setLocation(160, 80);
        add(txtTutor);

        lblMotivo = new JLabel("Motivo:");
        lblMotivo.setSize(dLabel);
        lblMotivo.setLocation(25, 110);
        add(lblMotivo);
        txtMotivo = new JTextField();
        txtMotivo.setSize(dTextField);
        txtMotivo.setLocation(160, 110);
        add(txtMotivo);

        lblData = new JLabel("Data (AAAAMMDD):");
        lblData.setSize(dLabel);
        lblData.setLocation(25, 140);
        add(lblData);
        txtData = new JTextField();
        txtData.setSize(dTextField);
        txtData.setLocation(160, 140);
        add(txtData);

        lblHora = new JLabel("Hora (HHMM):");
        lblHora.setSize(dLabel);
        lblHora.setLocation(25, 170);
        add(lblHora);
        txtHora = new JTextField();
        txtHora.setSize(dTextField);
        txtHora.setLocation(160, 170);
        add(txtHora);

        lblPrescricao = new JLabel("Prescrição:");
        lblPrescricao.setSize(dLabel);
        lblPrescricao.setLocation(25, 200);
        add(lblPrescricao);
        txtPrescricao = new JTextField();
        txtPrescricao.setSize(dTextField);
        txtPrescricao.setLocation(160, 200);
        add(txtPrescricao);

        cmdCadastrar = new JButton("Cadastrar");
        cmdCadastrar.setSize(dButton);
        cmdCadastrar.setLocation(25, 250);
        cmdCadastrar.addActionListener(this);
        add(cmdCadastrar);
        cmdPesquisar = new JButton("Pesquisar");
        cmdPesquisar.setSize(dButton);
        cmdPesquisar.setLocation(195, 250);
        cmdPesquisar.addActionListener(this);
        add(cmdPesquisar);
        cmdProcessar = new JButton("Processar Operação");
        cmdProcessar.setSize(dButton);
        cmdProcessar.setLocation(365, 250);
        cmdProcessar.addActionListener(this);
        add(cmdProcessar);

        cmdOrdenar = new JButton("Ordenar");
        cmdOrdenar.setSize(dButton);
        cmdOrdenar.setLocation(25, 285);
        cmdOrdenar.addActionListener(this);
        add(cmdOrdenar);
        cmdReceituario = new JButton("Receituário");
        cmdReceituario.setSize(dButton);
        cmdReceituario.setLocation(195, 285);
        cmdReceituario.addActionListener(this);
        add(cmdReceituario);
        cmdCancelar = new JButton("Cancelar");
        cmdCancelar.setSize(dButton);
        cmdCancelar.setLocation(365, 285);
        cmdCancelar.addActionListener(this);
        add(cmdCancelar);

        cmdSair = new JButton("Sair");
        cmdSair.setSize(dButton);
        cmdSair.setLocation(25, 320);
        cmdSair.addActionListener(this);
        add(cmdSair);

        txtMsg = new JTextArea();
        scrollMsg = new JScrollPane(txtMsg);
        scrollMsg.setSize(dScroll);
        scrollMsg.setLocation(25, 370);
        add(scrollMsg);

        addWindowListener(this);
    }

    private void mostrarErro(String texto) {
        JOptionPane.showMessageDialog(null, texto,
                "Aconteceu um erro", JOptionPane.ERROR_MESSAGE);
    }

    private boolean dataValida(int data) {
        int ano = data / 10000;
        int mes = (data / 100) % 100;
        int dia = data % 100;
        return ano > 0 && mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31;
    }

    private boolean horaValida(int hora) {
        int horas = hora / 100;
        int minutos = hora % 100;
        return hora >= 0 && horas <= 23 && minutos <= 59;
    }

    private void limparCampos() {
        txtNumero.setText("");
        txtPet.setText("");
        txtTutor.setText("");
        txtMotivo.setText("");
        txtData.setText("");
        txtHora.setText("");
        txtPrescricao.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdSair) {
            JOptionPane.showMessageDialog(null, "Finalizado o Programa",
                    "fim", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (e.getSource() == cmdCadastrar) {
            try {
                if (txtPet.getText().isBlank() || txtTutor.getText().isBlank()) {
                    mostrarErro("Informe o nome do pet e o nome do tutor");
                } else {
                    int data = Integer.parseInt(txtData.getText());
                    int hora = Integer.parseInt(txtHora.getText());
                    if (!dataValida(data)) {
                        mostrarErro("Data inválida. Use o formato AAAAMMDD (ex.: 20261125)");
                    } else if (!horaValida(hora)) {
                        mostrarErro("Hora inválida. Use o formato HHMM (ex.: 1430)");
                    } else {
                        txtMsg.append(agenda.agendar(txtPet.getText(), txtTutor.getText(),
                                txtMotivo.getText(), data, hora));
                        txtMsg.append("\n");
                        limparCampos();
                    }
                }
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            } catch (HorarioIndisponivelException ex1) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex1.toString(),
                        "Aconteceu um erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == cmdPesquisar) {
            try {
                ConsultaPet c = agenda.buscar(Integer.parseInt(txtNumero.getText()));
                if (c == null) {
                    mostrarErro("Consulta não encontrada");
                } else {
                    txtMsg.append(c.toString());
                }
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            }
        }
        if (e.getSource() == cmdProcessar) {
            try {
                txtMsg.append(agenda.darBaixa(Integer.parseInt(txtNumero.getText()),
                        txtPrescricao.getText()));
                txtMsg.append("\n");
                txtPrescricao.setText("");
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            }
        }
        if (e.getSource() == cmdOrdenar) {
            try {
                int dia = 0;
                if (!txtData.getText().isBlank()) {
                    dia = Integer.parseInt(txtData.getText());
                }
                if (dia != 0 && !dataValida(dia)) {
                    mostrarErro("Data inválida. Use o formato AAAAMMDD (ex.: 20261125)");
                } else {
                    txtMsg.append("--- Agenda ordenada ---\n");
                    txtMsg.append(agenda.listarOrdenado(dia));
                }
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            }
        }
        if (e.getSource() == cmdReceituario) {
            try {
                ConsultaPet c = agenda.buscar(Integer.parseInt(txtNumero.getText()));
                if (c == null) {
                    mostrarErro("Consulta não encontrada");
                } else {
                    Prontuario p = c; // polimorfismo por interface
                    txtMsg.append(p.emitirReceituarioFormatado("Clínica Veterinária"));
                }
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            }
        }
        if (e.getSource() == cmdCancelar) {
            try {
                txtMsg.append(agenda.cancelar(Integer.parseInt(txtNumero.getText())));
                txtMsg.append("\n");
            } catch (NumberFormatException ex) {
                mostrarErro("Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(null, "Finalizado o Programa",
                "fim", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
