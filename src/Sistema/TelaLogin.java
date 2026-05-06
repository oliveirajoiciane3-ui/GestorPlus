package sistema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCriarConta;

    public TelaLogin() {
        setTitle("Gestor+ - Login");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new GridLayout(1, 2));

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBackground(new Color(29, 78, 216));
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.setBorder(new EmptyBorder(120, 60, 120, 60));

        JLabel tituloSistema = new JLabel("GESTOR+");
        tituloSistema.setForeground(Color.WHITE);
        tituloSistema.setFont(new Font("Arial", Font.BOLD, 34));
        tituloSistema.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Sistema de Gestão");
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel frase = new JLabel("Gerencie seu negócio de forma inteligente");
        frase.setForeground(Color.WHITE);
        frase.setFont(new Font("Arial", Font.PLAIN, 15));
        frase.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelEsquerdo.add(Box.createVerticalGlue());
        painelEsquerdo.add(tituloSistema);
        painelEsquerdo.add(Box.createRigidArea(new Dimension(0, 10)));
        painelEsquerdo.add(subtitulo);
        painelEsquerdo.add(Box.createRigidArea(new Dimension(0, 35)));
        painelEsquerdo.add(frase);
        painelEsquerdo.add(Box.createVerticalGlue());

        JPanel painelDireito = new JPanel();
        painelDireito.setBackground(Color.WHITE);
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setBorder(new EmptyBorder(80, 70, 80, 70));

        JLabel tituloLogin = new JLabel("Acesse sua conta");
        tituloLogin.setFont(new Font("Arial", Font.BOLD, 28));
        tituloLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Entre com suas credenciais");
        descricao.setFont(new Font("Arial", Font.PLAIN, 16));
        descricao.setForeground(Color.GRAY);
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));

        campoUsuario = new JTextField();
        campoUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoUsuario.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 16));

        campoSenha = new JPasswordField();
        campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoSenha.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBackground(new Color(37, 99, 235));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFocusPainted(false);
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        botaoCriarConta = new JButton("Criar conta");
        botaoCriarConta.setBackground(new Color(96, 165, 250));
        botaoCriarConta.setForeground(Color.WHITE);
        botaoCriarConta.setFocusPainted(false);
        botaoCriarConta.setFont(new Font("Arial", Font.BOLD, 18));
        botaoCriarConta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        botaoEntrar.addActionListener(e -> fazerLogin());

        botaoCriarConta.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Funcionalidade simulada: criar conta.")
        );

        painelDireito.add(tituloLogin);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 10)));
        painelDireito.add(descricao);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 35)));
        painelDireito.add(lblUsuario);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 5)));
        painelDireito.add(campoUsuario);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 18)));
        painelDireito.add(lblSenha);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 5)));
        painelDireito.add(campoSenha);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 25)));
        painelDireito.add(botaoEntrar);
        painelDireito.add(Box.createRigidArea(new Dimension(0, 15)));
        painelDireito.add(botaoCriarConta);

        painelPrincipal.add(painelEsquerdo);
        painelPrincipal.add(painelDireito);

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void fazerLogin() {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha usuário e senha.");
            return;
        }

        new TelaMenu().setVisible(true);
        dispose();
    }
}