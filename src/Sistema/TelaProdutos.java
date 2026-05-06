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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaProdutos extends JFrame {

    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoEstoque;
    private DefaultTableModel modeloTabela;

    public TelaProdutos() {
        setTitle("Gestor+ - Produtos");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(criarMenuLateral(), BorderLayout.WEST);

        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(new Color(239, 246, 255));
        conteudo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.setBackground(new Color(239, 246, 255));

        JLabel titulo = new JLabel("Cadastro de Produtos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(29, 78, 216));
        tituloPanel.add(titulo, BorderLayout.WEST);

        JPanel formulario = new JPanel(new GridLayout(7, 1, 10, 10));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Novo Produto"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        campoNome = new JTextField();
        campoPreco = new JTextField();
        campoEstoque = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar Produto");
        btnAdicionar.setBackground(new Color(37, 99, 235));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));

        btnAdicionar.addActionListener(e -> adicionarProduto());

        formulario.add(new JLabel("Nome:"));
        formulario.add(campoNome);
        formulario.add(new JLabel("Preço:"));
        formulario.add(campoPreco);
        formulario.add(new JLabel("Estoque:"));
        formulario.add(campoEstoque);
        formulario.add(btnAdicionar);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Preço");
        modeloTabela.addColumn("Estoque");

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Produtos Cadastrados"));

        JPanel topo = new JPanel(new BorderLayout(0, 15));
        topo.setBackground(new Color(239, 246, 255));
        topo.add(tituloPanel, BorderLayout.NORTH);
        topo.add(formulario, BorderLayout.CENTER);

        conteudo.add(topo, BorderLayout.NORTH);
        conteudo.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(conteudo, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    private JPanel criarMenuLateral() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(220, 650));
        menu.setBackground(new Color(29, 78, 216));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(new EmptyBorder(20, 15, 20, 15));

        JLabel logo = new JLabel("GESTOR+");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnMenu = criarBotao("Menu");
        JButton btnClientes = criarBotao("Clientes");
        JButton btnPedidos = criarBotao("Pedidos");
        JButton btnSair = criarBotao("Sair");

        btnMenu.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            dispose();
        });

        btnClientes.addActionListener(e -> {
            new TelaCliente().setVisible(true);
            dispose();
        });

        btnPedidos.addActionListener(e -> {
    new TelaPedidos().setVisible(true);
    dispose();
});
        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        menu.add(logo);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(btnMenu);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnClientes);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(btnPedidos);
        menu.add(Box.createVerticalGlue());
        menu.add(btnSair);

        return menu;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setMaximumSize(new Dimension(180, 40));
        botao.setBackground(new Color(37, 99, 235));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        return botao;
    }

    private void adicionarProduto() {
        String nome = campoNome.getText().trim();
        String preco = campoPreco.getText().trim();
        String estoque = campoEstoque.getText().trim();

        if (nome.isEmpty() || preco.isEmpty() || estoque.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        modeloTabela.addRow(new Object[]{nome, preco, estoque});

        campoNome.setText("");
        campoPreco.setText("");
        campoEstoque.setText("");

        JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso.");
    }
}