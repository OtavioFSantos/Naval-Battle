import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Ranking extends JFrame implements ActionListener{
	
	//atributo e objetos necessarios
	private JPanel panel = new JPanel();
	private JButton returnButton = new JButton("Return");
	private JLabel title = new JLabel("Ranking");
    private String finalRank = "";
	private TextArea textField = new TextArea();

	//construtor do ranking
	public Ranking(){
		super("Ranking");
	
		//configuracoes basicas da janela
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 380);
		setLocationRelativeTo(null);
		panel.setLayout(null);
		setContentPane(panel);

		//posicionamento dos botoes e textos na janela
		returnButton.setBounds(100, 310, 95, 31);
		returnButton.addActionListener(this);
		
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setBounds(105, 11, 170, 29);
			
		textField.setBounds(50, 46, 200, 250);

        panel.add(returnButton);
        panel.add(title);	
		panel.add(textField);

		//lista que ira conter o content do arquivo lido
        ArrayList<String> content = new ArrayList<String>();

		//dependendo do caso, abre ou cria um arquivo "ranking.txt"
        try(BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))){
            String line;

			//coloca cada line no atributo "line", e depois passa a line para dentro da lista de content
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

			//ordena a lista de acordo com os melhores times
            Collections.sort(content);

			//concatena somente os 15 melhores times no atributo "finalRank"
            for(int i = 0; i < content.size(); i++){
                if(i >= 15){
                    break;
                }
                finalRank = finalRank.concat(content.get(i)+"\n");
            }

			//mostra os times na janela, dentro do "textField"
            textField.setText(finalRank);
        }catch(Exception ex){
            //evita qualquer aviso que seja gerado
        }
	}

	//metodo para verificar se o botao "return" foi pressionado
	public void actionPerformed(ActionEvent event) {

		//se foi, ele fecha a janela de ranking
		if (event.getSource() == returnButton){
			this.dispose();
		}
	}
}