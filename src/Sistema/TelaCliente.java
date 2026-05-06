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

public class TelaCliente extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private DefaultTableModel modeloTabela;

    public TelaCliente() {
        setTitle("Gestor+ - Clientes");
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

        JLabel titulo = new JLabel("Cadastro de Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(29, 78, 216));
        tituloPanel.add(titulo, BorderLayout.WEST);

        JPanel formulario = new JPanel(new GridLayout(7, 1, 10, 10));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Novo Cliente"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        campoNome = new JTextField();
        campoCpf = new JTextField();
        campoTelefone = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar Cliente");
        btnAdicionar.setBackground(new Color(37, 99, 235));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));

        btnAdicionar.addActionListener(e -> adicionarCliente());

        formulario.add(new JLabel("Nome:"));
        formulario.add(campoNome);
        formulario.add(new JLabel("CPF:"));
        formulario.add(campoCpf);
        formulario.add(new JLabel("Telefone:"));
        formulario.add(campoTelefone);
        formulario.add(btnAdicionar);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Telefone");

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Clientes Cadastrados"));

        JPanel topo = new JPanel(new BorderLayout(0, 15));
        topo.setBackground(new Color(239, 246, 255));
        topo.add(tituloPanel, BorderLayout.NORTH);
        topo.add(formulario, BorderLayout.CENTER);

        conteudo.add(topo, BorderLayout.NORTH);
        conteudo.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(conteudo, BorderLayout.CENTER);

        add(painelPrincipal);

        carregarClientes();
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
        JButton btnProdutos = criarBotao("Produtos");
        JButton btnPedidos = criarBotao("Pedidos");
        JButton btnSair = criarBotao("Sair");

        btnMenu.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            dispose();
        });

        btnProdutos.addActionListener(e -> {
            new TelaProdutos().setVisible(true);
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
        menu.add(btnProdutos);
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

    private void adicionarCliente() {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String telefone = campoTelefone.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Cliente cliente = new Cliente(nome, cpf, telefone);
        ClienteDAO dao = new ClienteDAO();
        dao.inserir(cliente);

        JOptionPane.showMessageDialog(this, "Cliente salvo no banco com sucesso!");

        campoNome.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");

        carregarClientes();
    }

    private void carregarClientes() {
        modeloTabela.setRowCount(0);

        ClienteDAO dao = new ClienteDAO();

        for (Cliente cliente : dao.listar()) {
            modeloTabela.addRow(new Object[]{
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone()
            });
        }
    }
}