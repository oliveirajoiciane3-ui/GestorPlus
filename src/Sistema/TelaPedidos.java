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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaPedidos extends JFrame {

    private JComboBox<String> comboCliente;
    private JComboBox<String> comboProduto;
    private JTextField campoTotal;
    private DefaultTableModel modeloTabela;

    public TelaPedidos() {
        setTitle("Gestor+ - Pedidos");
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

        JLabel titulo = new JLabel("Registro de Pedidos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(29, 78, 216));
        tituloPanel.add(titulo, BorderLayout.WEST);

        JPanel formulario = new JPanel(new GridLayout(7, 1, 10, 10));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Novo Pedido"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        comboCliente = new JComboBox<>(new String[]{
            "João Silva",
            "Maria Santos",
            "Pedro Costa"
        });

        comboProduto = new JComboBox<>(new String[]{
            "Notebook Dell - 3500",
            "Mouse Logitech - 120",
            "Teclado Mecânico - 450"
        });

        campoTotal = new JTextField();
        campoTotal.setEditable(false);

        JButton btnCalcular = new JButton("Calcular Total");
        btnCalcular.setBackground(new Color(59, 130, 246));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setBackground(new Color(37, 99, 235));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCalcular.addActionListener(e -> calcularTotal());
        btnFinalizar.addActionListener(e -> finalizarPedido());

        formulario.add(new JLabel("Cliente:"));
        formulario.add(comboCliente);
        formulario.add(new JLabel("Produto:"));
        formulario.add(comboProduto);
        formulario.add(new JLabel("Total:"));
        formulario.add(campoTotal);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnFinalizar);
        formulario.add(painelBotoes);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Cliente");
        modeloTabela.addColumn("Produto");
        modeloTabela.addColumn("Total");

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Pedidos Registrados"));

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
        JButton btnProdutos = criarBotao("Produtos");
        JButton btnSair = criarBotao("Sair");

        btnMenu.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            dispose();
        });

        btnClientes.addActionListener(e -> {
            new TelaCliente().setVisible(true);
            dispose();
        });

        btnProdutos.addActionListener(e -> {
            new TelaProdutos().setVisible(true);
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
        menu.add(btnProdutos);
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

    private void calcularTotal() {
        String produto = comboProduto.getSelectedItem().toString();

        if (produto.contains("3500")) {
            campoTotal.setText("R$ 3500,00");
        } else if (produto.contains("120")) {
            campoTotal.setText("R$ 120,00");
        } else if (produto.contains("450")) {
            campoTotal.setText("R$ 450,00");
        }
    }

    private void finalizarPedido() {
        String cliente = comboCliente.getSelectedItem().toString();
        String produto = comboProduto.getSelectedItem().toString();
        String total = campoTotal.getText().trim();

        if (total.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Calcule o total antes de finalizar.");
            return;
        }

        modeloTabela.addRow(new Object[]{cliente, produto, total});
        campoTotal.setText("");

        JOptionPane.showMessageDialog(this, "Pedido finalizado com sucesso.");
    }
}