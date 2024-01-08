package tokdebeleza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe Pedido para representar um pedido
class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dataPedido;
    private float valor;
    private int quantidade;

    public Pedido(String dataPedido, float valor, int quantidade) {
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public float getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

// Classe Caixa para representar um caixa
class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;

    private float fundoCaixa;
    private float cartao;
    private float pix;
    private float dinheiro;
    private float saida;
    private String dataCaixa;

    public Caixa(float fundoCaixa, float cartao, float pix, float dinheiro, float saida, String dataCaixa) {
        this.fundoCaixa = fundoCaixa;
        this.cartao = cartao;
        this.pix = pix;
        this.dinheiro = dinheiro;
        this.saida = saida;
        this.dataCaixa = dataCaixa;
    }

    public float getFundoCaixa() {
        return fundoCaixa;
    }

    public float getCartao() {
        return cartao;
    }

    public float getPix() {
        return pix;
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public float getSaida() {
        return saida;
    }

    public String getDataCaixa() {
        return dataCaixa;
    }
}

public class TokDeBelezaSystem extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Pedido> listaPedidos; // Lista para armazenar os pedidos
    private List<Caixa> listaCaixas; // Lista para armazenar os caixas

    public TokDeBelezaSystem() {
        super("Sistema de Pedidos e Caixa da Tok de Beleza");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

     // Título
        JLabel titleLabel = new JLabel("Seja bem-vindo ao sistema de pedidos e caixa da Tok de Beleza");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // Set text color to white

        // Create a JPanel with a purple background
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(155, 89, 182)); // Purple color
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center align the components

        // Add the titleLabel to the topPanel
        topPanel.add(titleLabel);

        // Add the topPanel to the JFrame
        add(topPanel, BorderLayout.NORTH);
     
     // Botões
     // Create a panel to hold the button panel
     JPanel centerPanel = new JPanel(new GridBagLayout());
     add(centerPanel, BorderLayout.CENTER);

     // Create the button panel
     JPanel buttonPanel = new JPanel(new GridBagLayout());

     // Create the buttons with smaller size
     Font buttonFont = new Font("Arial", Font.BOLD, 18); // Adjust the font size as needed
     Dimension buttonSize = new Dimension(300, 50); // Adjust the button size as needed
  // Crie uma cor personalizada com os valores RGB fornecidos
     Color buttonBackground = new Color(155, 89, 182);

     // Crie os botões com o tamanho e a fonte desejados
     JButton addPedidoButton = new JButton("Adicionar Pedido");
     addPedidoButton.setFont(buttonFont);
     addPedidoButton.setPreferredSize(buttonSize);
     addPedidoButton.setBackground(buttonBackground);
     addPedidoButton.setForeground(Color.WHITE);

     JButton consultarPedidosButton = new JButton("Consultar Pedidos");
     consultarPedidosButton.setFont(buttonFont);
     consultarPedidosButton.setPreferredSize(buttonSize);
     consultarPedidosButton.setBackground(buttonBackground);
     consultarPedidosButton.setForeground(Color.WHITE);

     JButton caixaDoDiaButton = new JButton("Caixa do Dia");
     caixaDoDiaButton.setFont(buttonFont);
     caixaDoDiaButton.setPreferredSize(buttonSize);
     caixaDoDiaButton.setBackground(buttonBackground);
     caixaDoDiaButton.setForeground(Color.WHITE);

     JButton consultarCaixasButton = new JButton("Consultar Caixas");
     consultarCaixasButton.setFont(buttonFont);
     consultarCaixasButton.setPreferredSize(buttonSize);
     consultarCaixasButton.setBackground(buttonBackground);
     consultarCaixasButton.setForeground(Color.WHITE);


     // Create GridBagConstraints for buttonPanel
     GridBagConstraints buttonConstraints = new GridBagConstraints();
     buttonConstraints.gridx = 0;
     buttonConstraints.gridy = GridBagConstraints.RELATIVE;
     buttonConstraints.weightx = 1.0; // Allow buttons to expand horizontally
     buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
     buttonConstraints.anchor = GridBagConstraints.CENTER;
     buttonConstraints.insets = new Insets(5, 0, 5, 0); // Add spacing between buttons

     buttonPanel.add(addPedidoButton, buttonConstraints);
     buttonPanel.add(consultarPedidosButton, buttonConstraints);
     buttonPanel.add(caixaDoDiaButton, buttonConstraints);
     buttonPanel.add(consultarCaixasButton, buttonConstraints);

     // Add the button panel to the center panel
     centerPanel.add(buttonPanel);

     // Center the main panel within the JFrame
     JPanel mainPanel = new JPanel(new BorderLayout());
     mainPanel.add(centerPanel, BorderLayout.CENTER);
     add(mainPanel, BorderLayout.CENTER);



        // Ações dos botões
        addPedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTabelaPedido();
            }
        });

        consultarPedidosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPedidos();
            }
        });

        caixaDoDiaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTabelaCaixa();
            }
        });

        consultarCaixasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarCaixas();
            }
        });

        // Inicializar as listas de pedidos e caixas
        listaPedidos = new ArrayList<>();
        listaCaixas = new ArrayList<>();

        setVisible(true);
    }

    private void abrirTabelaPedido() {
        JFrame tabelaPedidoFrame = new JFrame("Adicionar Pedido");
        tabelaPedidoFrame.setSize(400, 400);
        tabelaPedidoFrame.setLayout(new GridLayout(0, 1));

        JLabel dataPedidoLabel = new JLabel("Data do Pedido:");
        JTextField dataPedidoField = new JTextField();

        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField();

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JTextField quantidadeField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar");

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dataPedido = dataPedidoField.getText();
                float valor = Float.parseFloat(valorField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());

                Pedido pedido = new Pedido(dataPedido, valor, quantidade);
                listaPedidos.add(pedido);

                // Salvar os pedidos em um arquivo
                salvarPedidos();

                JOptionPane.showMessageDialog(null, "Pedido adicionado");
                tabelaPedidoFrame.dispose();
            }
        });
     // Personalizar o botão adicionarButton
        adicionarButton.setBackground(new Color(155, 89, 182)); // Fundo roxo
        adicionarButton.setForeground(Color.WHITE); // Texto branco
        
        tabelaPedidoFrame.add(dataPedidoLabel);
        tabelaPedidoFrame.add(dataPedidoField);
        tabelaPedidoFrame.add(valorLabel);
        tabelaPedidoFrame.add(valorField);
        tabelaPedidoFrame.add(quantidadeLabel);
        tabelaPedidoFrame.add(quantidadeField);
        tabelaPedidoFrame.add(adicionarButton);

        tabelaPedidoFrame.setVisible(true);
    }

    private void consultarPedidos() {
        String mes = JOptionPane.showInputDialog("Digite o mês para consultar os pedidos:");

        // Lógica para consultar os pedidos do mês especificado
        List<Pedido> pedidosMes = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            // Extrair o mês do pedido
            String mesPedido = pedido.getDataPedido().split("/")[1];
            if (mesPedido.equals(mes)) {
                pedidosMes.add(pedido);
            }
        }

        // Exibir os pedidos do mês
        StringBuilder mensagem = new StringBuilder("Pedidos do mês " + mes + ":\n");
        for (Pedido pedido : pedidosMes) {
            mensagem.append("Data: ").append(pedido.getDataPedido())
                    .append(", Valor: ").append(pedido.getValor())
                    .append(", Quantidade: ").append(pedido.getQuantidade())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private void salvarPedidos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("pedidos.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaPedidos);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarPedidos() {
        try (FileInputStream fileIn = new FileInputStream("pedidos.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Object obj = objectIn.readObject();
            if (obj instanceof List<?>) {
                List<?> tempList = (List<?>) obj;
                for (Object item : tempList) {
                    if (item instanceof Pedido) {
                        listaPedidos.add((Pedido) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void abrirTabelaCaixa() {
        JFrame tabelaCaixaFrame = new JFrame("Adicionar Caixa");
        tabelaCaixaFrame.setSize(400, 400);
        tabelaCaixaFrame.setLayout(new GridLayout(0, 1));

        JLabel fundoCaixaLabel = new JLabel("Fundo de Caixa:");
        JTextField fundoCaixaField = new JTextField();

        JLabel cartaoLabel = new JLabel("Cartão:");
        JTextField cartaoField = new JTextField();

        JLabel pixLabel = new JLabel("PIX:");
        JTextField pixField = new JTextField();

        JLabel dinheiroLabel = new JLabel("Dinheiro:");
        JTextField dinheiroField = new JTextField();

        JLabel saidaLabel = new JLabel("Saída:");
        JTextField saidaField = new JTextField();

        JLabel dataCaixaLabel = new JLabel("Data do Caixa:");
        JTextField dataCaixaField = new JTextField();

        JButton adicionarButton = new JButton("Adicionar");

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float fundoCaixa = Float.parseFloat(fundoCaixaField.getText());
                float cartao = Float.parseFloat(cartaoField.getText());
                float pix = Float.parseFloat(pixField.getText());
                float dinheiro = Float.parseFloat(dinheiroField.getText());
                float saida = Float.parseFloat(saidaField.getText());
                String dataCaixa = dataCaixaField.getText();

                Caixa caixa = new Caixa(fundoCaixa, cartao, pix, dinheiro, saida, dataCaixa);
                listaCaixas.add(caixa);

                // Salvar os caixas em um arquivo
                salvarCaixas();

                JOptionPane.showMessageDialog(null, "Caixa adicionado");
                tabelaCaixaFrame.dispose();
            }
        });
        
     // Personalizar o botão adicionarButton
        adicionarButton.setBackground(new Color(155, 89, 182)); // Fundo roxo
        adicionarButton.setForeground(Color.WHITE); // Texto branco

        tabelaCaixaFrame.add(fundoCaixaLabel);
        tabelaCaixaFrame.add(fundoCaixaField);
        tabelaCaixaFrame.add(cartaoLabel);
        tabelaCaixaFrame.add(cartaoField);
        tabelaCaixaFrame.add(pixLabel);
        tabelaCaixaFrame.add(pixField);
        tabelaCaixaFrame.add(dinheiroLabel);
        tabelaCaixaFrame.add(dinheiroField);
        tabelaCaixaFrame.add(saidaLabel);
        tabelaCaixaFrame.add(saidaField);
        tabelaCaixaFrame.add(dataCaixaLabel);
        tabelaCaixaFrame.add(dataCaixaField);
        tabelaCaixaFrame.add(adicionarButton);

        tabelaCaixaFrame.setVisible(true);
    }

    private void consultarCaixas() {
        String mes = JOptionPane.showInputDialog("Digite o mês para consultar os caixas:");

        // Lógica para consultar os caixas do mês especificado
        List<Caixa> caixasMes = new ArrayList<>();
        for (Caixa caixa : listaCaixas) {
            // Extrair o mês do caixa
            String mesCaixa = caixa.getDataCaixa().split("/")[1];
            if (mesCaixa.equals(mes)) {
                caixasMes.add(caixa);
            }
        }

        // Exibir os caixas do mês
        StringBuilder mensagem = new StringBuilder("Caixas do mês " + mes + ":\n");
        for (Caixa caixa : caixasMes) {
            mensagem.append("Data: ").append(caixa.getDataCaixa())
                    .append(", Fundo de Caixa: ").append(caixa.getFundoCaixa())
                    .append(", Cartão: ").append(caixa.getCartao())
                    .append(", PIX: ").append(caixa.getPix())
                    .append(", Dinheiro: ").append(caixa.getDinheiro())
                    .append(", Saída: ").append(caixa.getSaida())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private void salvarCaixas() {
        try {
            FileOutputStream fileOut = new FileOutputStream("caixas.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaCaixas);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCaixas() {
        try (FileInputStream fileIn = new FileInputStream("caixas.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Object obj = objectIn.readObject();
            if (obj instanceof List<?>) {
                List<?> tempList = (List<?>) obj;
                for (Object item : tempList) {
                    if (item instanceof Caixa) {
                        listaCaixas.add((Caixa) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TokDeBelezaSystem sistema = new TokDeBelezaSystem();
        sistema.carregarPedidos();
        sistema.carregarCaixas();
    }
}
