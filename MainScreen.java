import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener{

	//componentes básicos da janela
    private JPanel panel = new JPanel();
    private JTextField nameField = new JTextField(20);

    InitialScreen initialScreen;

    private JButton play = new JButton("Play");

    private JLabel welcome = new JLabel("Welcome!");
    private JLabel user = new JLabel("Insert name:");

    public MainScreen(){
        super("Welcome!");

		//adiciona as configuracoes basicas da janela
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 230);
        
        panel.setBorder(null);
		panel.setLayout(null);
        setContentPane(panel);

		//configuracoes basicas dos componentes da janela
        nameField.setBounds(40, 101, 223, 29);
        panel.add(nameField);

        welcome.setFont(new Font("Arial", Font.BOLD, 18));
		welcome.setBounds(100, 30, 120, 29);
		panel.add(welcome);

		user.setFont(new Font("Arial", Font.PLAIN, 14));
		user.setBounds(40, 76, 105, 14);
		panel.add(user);
		
		play.setBounds(95, 158, 111, 36);
		play.setFont(new Font("Arial", Font.PLAIN, 12));		
		play.addActionListener(this);		
		panel.add(play);
		
		setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent event){

		//verifica se o player clicou em "play" e se o campo do name esta preenchido
		if(event.getSource() == play){
			String player = nameField.getText();

			if(player.equals("")){
				JOptionPane.showMessageDialog(null, "Insert a valid name!");
			}
			//se estiver tudo certo, sera gerada uma tela para selecao de modo de jogo
			if(!(player.equals(""))){					
				InitialScreen initialScreen = new InitialScreen(player);
				initialScreen.setVisible(true);
                nameField.setText("");					
			}
		}
    }
}