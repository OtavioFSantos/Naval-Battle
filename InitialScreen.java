import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InitialScreen extends JFrame implements ActionListener{

	//componentes básicos da janela
    private JPanel panel = new JPanel();

    private JButton aleatorio = new JButton("Random");
    private JButton define = new JButton("Set");
    private JButton ranking = new JButton("Ranking");

    private JLabel hello = new JLabel("");
    private JLabel decision = new JLabel("Choose game");

	private String player;

    public InitialScreen(String name){
        super("BattleShip");

		player = name;

		//adiciona as configuracoes basicas da janela
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 240);
        
        panel.setBorder(null);
		panel.setLayout(null);
        setContentPane(panel);

		//configuracoes basicas dos componentes da janela
        hello.setFont(new Font("Arial", Font.BOLD, 18));
		hello.setBounds(200, 30, 120, 29);
		hello.setText("Hello, "+player+"!");
		panel.add(hello);

		decision.setFont(new Font("Arial", Font.PLAIN, 14));
		decision.setBounds(200, 50, 105, 100);
		panel.add(decision);
		
		aleatorio.setBounds(195, 148, 111, 36);
		aleatorio.setFont(new Font("Arial", Font.PLAIN, 12));		
		aleatorio.addActionListener(this);		
		panel.add(aleatorio);

		ranking.setFont(new Font("Arial", Font.PLAIN, 12));
		ranking.setBounds(30, 148, 111, 36);
		ranking.setFocusable(false);
		ranking.addActionListener(this);
		panel.add(ranking);
	
		define.setBounds(355, 148, 111, 36);
		define.setFont(new Font("Arial", Font.PLAIN, 12));
		define.setFocusable(false);		
		define.addActionListener(this);		
		panel.add(define);
		
		setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent event){

		//verifica se o player clicou em "aleatorio"
		if(event.getSource() == aleatorio){					
			RandomBoard board = new RandomBoard(player);
			board.setVisible(true);			
		}

		//verifica se o player clicou em "definido"
		if(event.getSource() == define){					
			SetBoard board = new SetBoard(player);
			board.setVisible(true);						
		}

		//verifica se o player clicou em "ranking" e mostra o ranking com os 15 melhores times
		if (event.getSource() == ranking){
			Ranking ranking = new Ranking();
			ranking.setVisible(true);					
		}
	} 
}