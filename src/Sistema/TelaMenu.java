package sistema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Gestor+ - Menu Principal");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel menuLateral = new JPanel();
        menuLateral.setPreferredSize(new Dimension(220, 650));
        menuLateral.setBackground(new Color(29, 78, 216));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBorder(new EmptyBorder(20, 15, 20, 15));

        JLabel logo = new JLabel("GESTOR+");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnClientes = criarBotaoMenu("Clientes");
        JButton btnProdutos = criarBotaoMenu("Produtos");
        JButton btnPedidos = criarBotaoMenu("Pedidos");
        JButton btnRelatorios = criarBotaoMenu("Relatórios");
        JButton btnSair = criarBotaoMenu("Sair");

       btnClientes.addActionListener(e -> {
    new TelaCliente().setVisible(true);
    dispose();
});

        btnPedidos.addActionListener(e -> {
    new TelaPedidos().setVisible(true);
    dispose();
});

       btnProdutos.addActionListener(e -> {
    new TelaProdutos().setVisible(true);
    dispose();
});

        btnRelatorios.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Relatórios simulados sem banco de dados.")
        );

        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        menuLateral.add(logo);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 40)));
        menuLateral.add(btnClientes);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnProdutos);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnPedidos);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnRelatorios);
        menuLateral.add(Box.createVerticalGlue());
        menuLateral.add(btnSair);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(239, 246, 255));

        JLabel boasVindas = new JLabel("Bem-vinda ao Gestor+");
        boasVindas.setFont(new Font("Arial", Font.BOLD, 28));
        boasVindas.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("Escolha uma opção no menu lateral para continuar.");
        texto.setFont(new Font("Arial", Font.PLAIN, 18));
        texto.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelTexto = new JPanel();
        painelTexto.setBackground(new Color(239, 246, 255));
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
        painelTexto.add(Box.createVerticalGlue());
        boasVindas.setAlignmentX(Component.CENTER_ALIGNMENT);
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTexto.add(boasVindas);
        painelTexto.add(Box.createRigidArea(new Dimension(0, 15)));
        painelTexto.add(texto);
        painelTexto.add(Box.createVerticalGlue());

        centro.add(painelTexto, BorderLayout.CENTER);

        painelPrincipal.add(menuLateral, BorderLayout.WEST);
        painelPrincipal.add(centro, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setMaximumSize(new Dimension(180, 40));
        botao.setBackground(new Color(37, 99, 235));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        return botao;
    }
}
