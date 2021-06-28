import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomBoard extends JFrame implements ActionListener{

    //create all needed components
    JPanel gridPlayer = new JPanel();
    JPanel gridPc = new JPanel();
    JPanel panel = new JPanel();
    Container container;

    JLabel playerLabel = new JLabel("Player");
	JLabel pc = new JLabel("Computer");
    JLabel stopwatch = new JLabel("");

    JButton[][] tablePlayer = new JButton[10][10];
    JButton[][] tablePc = new JButton[10][10];
	JButton hint = new JButton("Hint");
	JButton singleShot = new JButton("Single");
    JButton commonShot = new JButton("Common");
    JButton cascade = new JButton("Cascade");
    JButton star = new JButton("Star");
    JButton newGame = new JButton("New Game");
    JButton reset = new JButton("Reset");
    JButton exit = new JButton("Exit");

    //cria alguns atributos que irao auxiliar
    int arrayPlayer[][] = new int[10][10];
    int arrayPc[][] = new int[10][10];
    int arrayPlayerAux[][] = new int[10][10];
    int arrayPcAux[][] = new int[10][10];
    int countHint = 0;
    int auxC;
    int auxS;
    int auxN;
    int auxP;
    String selected = "";
    String auxVer;
    String auxHor;
    String name;
    String msg;
    String msg2;

    //cria objetos que vao ser essenciais
    DistributeVehiclesPlayer distVehPlayer = new DistributeVehiclesPlayer();
    DistributeVehiclesPc distVehPc = new DistributeVehiclesPc();
	Player player = new Player();

    //construtor do board aleatorio
    public RandomBoard(String newName){
        super("BattleShip Random");

        //configuracoes basicas da janela
		setSize(1100, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        container = getContentPane();
        container.setLayout(null);

        //cria a base do board do player e do computador
        gridPlayer.setBounds(3, 100, 497, 325);
		gridPlayer.setLayout(new GridLayout(10, 10, 2, 2));
        gridPc.setBounds(599, 100, 497, 325);
		gridPc.setLayout(new GridLayout(10, 10, 2, 2));

        //pega o name do player passado por parametro
        player.setName(newName);
        name = newName;

        //preenche ambos boards com uma imagem de ocean
        for(int line = 0; line < 10; line++){
			for(int column = 0; column < 10; column++){
				tablePlayer[line][column] = new JButton(line+"-"+column, new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                tablePlayer[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                tablePlayer[line][column].setMargin( new Insets(10, 10, 10, 10) );
				tablePlayer[line][column].addActionListener(this);
                tablePlayer[line][column].setForeground(Color.WHITE);
				gridPlayer.add(tablePlayer[line][column]);
                tablePc[line][column] = new JButton(line+"-"+column, new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                tablePc[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                tablePc[line][column].setMargin( new Insets(10, 10, 10, 10) );
				tablePc[line][column].addActionListener(this);
                tablePc[line][column].setForeground(Color.WHITE);
				gridPc.add(tablePc[line][column]);
			}
		}

        //posiciona os elementos na janela
        exit.setBounds(7, 14, 120, 35);
		exit.addActionListener(this);

		newGame.setBounds(127, 14, 120, 35);
		newGame.addActionListener(this);

        reset.setBounds(247, 14, 120, 35);
		reset.addActionListener(this);

        hint.setBounds(367, 14, 120, 35);
		hint.addActionListener(this);

        singleShot.setBounds(487, 14, 120, 35);
		singleShot.addActionListener(this);

        commonShot.setBounds(607, 14, 120, 35);
		commonShot.addActionListener(this);

        cascade.setBounds(727, 14, 120, 35);
		cascade.addActionListener(this);

        star.setBounds(847, 14, 120, 35);
		star.addActionListener(this);

        playerLabel.setFont(new Font("Arial", Font.BOLD, 15));
		playerLabel.setBounds(12, 78, 120, 18);

        pc.setFont(new Font("Arial", Font.BOLD, 15));
		pc.setBounds(607, 78, 120, 18);

        stopwatch.setFont(new Font("Arial", Font.BOLD, 25));
		stopwatch.setBounds(1000, 10, 120, 50);

        container.add(gridPlayer);
		container.add(gridPc);
        container.add(exit);
        container.add(newGame);
        container.add(reset);
        container.add(hint);
        container.add(singleShot);
        container.add(commonShot);
        container.add(cascade);
        container.add(star);
        container.add(playerLabel);
        container.add(pc);
        container.add(stopwatch);

        //distribui os vehicles aleatoriamente em ambos boards
        distVehPlayer.distribui(arrayPlayer, tablePlayer);
        distVehPc.distribui(arrayPc, tablePc);

        //copia os valuees dos arrayes para outros dois arrayes, eles serao usados caso o jogo seja reiniciado
        for(int line = 0; line < 10; line++){
            for(int column = 0; column < 10; column++){
                arrayPlayerAux[line][column] = arrayPlayer[line][column];
                arrayPcAux[line][column] = arrayPc[line][column];
            }
        }

        //inicializa o stopwatch
        player.setInitialTime(System.currentTimeMillis());
    }

    public void actionPerformed(ActionEvent event){

        //atualiza o stopwatch a cada acao do player
        player.setFinalTime(System.currentTimeMillis());
        stopwatch.setText(""+player.getSetTime());

        //verifica qual opcao o player clicou
		if(event.getSource() == exit){
			this.dispose();
		}
        if(event.getSource() == singleShot){
			selected = "single";
		}
        if(event.getSource() == commonShot){
			selected = "common";
		}
        if(event.getSource() == cascade){
			selected = "cascade";
		}
        if(event.getSource() == star){
			selected = "star";
		}
        if(event.getSource() == hint){
			selected = "hint";
		}

        //verifica se foi clicado dentro da tabela do computador, e qual opcao estava selecionada quando isso ocorreu
		for(int line = 0; line < 10; line++){
			for(int column = 0; column < 10; column++){
                if(event.getSource() == tablePc[line][column] && selected != ""){

                    //se foi selecionada a hint
                    if(selected == "hint"){
                        if(countHint < 3){
                            auxVer = "There are no vertical vehicles. ";
                            for(int i = 0; i < 10; i++){
                                if(arrayPc[i][column] > 0){
                                    auxVer = "There are vertical vehicles. ";
                                }
                            }
                            auxHor = "There are no horizontal vehicles.";
                            for(int i = 0; i < 10; i++){
                                if(arrayPc[line][i] > 0){
                                    auxHor = "There are horizontal vehicles.";
                                }
                            }
                            msg2 = auxVer + auxHor;
                            msg = "Line: "+line+" Column: "+column+"\n"+msg2;
                            JOptionPane.showMessageDialog(null, msg);
                            countHint++;
                            //reseta a opcao selecionada
                            selected = "";
                        }else if(countHint >= 3){
                            //desabilita o botao de hint quando ele for usado 3 vezes
                            hint.setEnabled(false);
                        }

                    //verifica quando foi clicado no board do computador com uma opcao de disparo selecionada
                    }else if(selected != ""){

                        //realiza o disparo do player
                        distVehPlayer.tiroPlayer(selected, arrayPc, tablePc, line, column, arrayPlayer, singleShot);

                        //realiza o disparo do computador
                        distVehPc.tiroPc(arrayPlayer, tablePlayer, arrayPc, singleShot, commonShot, cascade, star, player);
                        
                        //reseta a opcao selecionada
                        selected = "";
                    }
                }
            }
        }

        //gera um new board aleatorio se a opcao for clicada
        if(event.getSource() == newGame){
			this.dispose();
			RandomBoard board = new RandomBoard(name);
			board.setVisible(true);
			JOptionPane.showMessageDialog(this, "New Game!");
		}

        //reinicia o board aleatorio se a opcao for clicada, com as mesmas posicoes da ultima geracao aleatoria
        if(event.getSource() == reset){

            //atributos necessarios para saber se as images dos vehicles ja foram postas 
            auxC = 0;
            auxS = 0;
            auxN = 0;
            auxP = 0;

            //reseta o atributo responsavel por monitorar se o disparo single pode ser usado
            distVehPc.setAuxSingle(3);
            distVehPlayer.setAuxSingle(3);

            //reseta as hints e ativa todos os botoes de tiro novamente
            countHint = 0;
            singleShot.setEnabled(true);
            commonShot.setEnabled(true);
            cascade.setEnabled(true);
            star.setEnabled(true);

            //repeticao para restaurar o board
            for(int line = 0; line < 10; line++){
                for(int column = 0; column < 10; column++){

                    //os arrayes auxiliares sao usados para passar os valuees originais aos arrayes utilizados
                    arrayPlayer[line][column] = arrayPlayerAux[line][column];
                    tablePlayer[line][column].setEnabled(true);
                    arrayPc[line][column] = arrayPcAux[line][column];
                    tablePc[line][column].setEnabled(true);

                    //restaura todo o board do computador com a imagem de ocean
                    tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                    tablePc[line][column].setText(line+"-"+column);
                    tablePc[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                    tablePc[line][column].setMargin( new Insets(10, 10, 10, 10) );
                    tablePc[line][column].addActionListener(this);
                    tablePc[line][column].setForeground(Color.WHITE);

                    //restaura o aircraft do player
                    if(arrayPlayer[line][column] == 1 && auxC == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft2.png")));
                        auxC = 1;
                    }

                    //restaura o submarine do player
                    if(arrayPlayer[line][column] == 2 && auxS == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine2.png")));
                        auxS = 1;
                    }

                    //restaura o navio de escolta do player
                    if(arrayPlayer[line][column] == 3 && auxN == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip2.png")));
                        tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip3.png")));
                        auxN = 1;
                    }

                    //restaura o porta aviao do player
                    if(arrayPlayer[line][column] == 4 && auxP == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier2.png")));
                        tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier3.png")));
                        tablePlayer[line][column+3].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier4.png")));
                        auxP = 1;
                    }
                }
            }

            //reinicia o stopwatch
            player.setInitialTime(System.currentTimeMillis());

            //emite mensagem na tela mostrando que o jogo foi reiniciado
			JOptionPane.showMessageDialog(this, "Restarted!");
		}
    }
}