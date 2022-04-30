package homework;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class PannelloGrafico
{	private JFrame frame;
	private JTextField[][] matriceA;
	private JTextField[][] matriceB;
	private JButton[] bottoni;
	private JLabel messaggio;
	private String titoloFinestra;
	private int numeroRigheA;
	private int numeroColonneA;
	private int numeroRigheB;
	private int numeroColonneB;
	private int numeroBottoni;

	/* INIZIO PARTE DA PERSONALIZZARE */
	private JTextField campotesto1, campotesto2;
	Font f = new Font("helvetica", Font.BOLD, 20); //modifico il font della matrice Risultato con la scrittura Helvetica
	Font a = new Font("Times new roman", Font.ITALIC, 40); //modifico il font dei campotesti con la scrittura Times New Roman
	Font b = new Font("Helvetica",Font.BOLD, 25); //modifico il font del messaggio con la scrittura Helvetica
	/* FINE PARTE DA PERSONALIZZARE */

	public PannelloGrafico()
	{	/* INIZIO PARTE DA PERSONALIZZARE */
		titoloFinestra = "Operazioni matrice"; //Do il titolo alla matrice
		numeroRigheA = 6; //Imposto come numero di righe 6
		numeroColonneA = 6; //Imposto come numero di colonne 6
		numeroRigheB = 6; //Imposto come numero di righe della matrice risultato 6
		numeroColonneB = 6;//Imposto come numero di colonne della matrice risultato 6
		numeroBottoni = 5; //Imposto le operazioni da fare (4) + il tasto "Pulizia"
		/* FINE PARTE DA PERSONALIZZARE */

		inizializza();
	}

	private void bottonePremuto(int numeroBottone)
	{	/* INIZIO PARTE DA PERSONALIZZARE */
		setMessaggio("Premuto il bottone "+numeroBottone); //Ogni bottone mi stampa questo messaggio
		bloccaMatriceB(); //rendo la matrice B non mutabile
		Razionale [][] m= convertiInMatriceRazionale(getMatriceA()); //convertimi la matrice A in razionale
		Razionale [][] m2= convertiInMatriceRazionale(getMatriceB()); //convertimi la matrice B in razionale
		if(numeroBottone== 1) { 
			int x= getRiga(campotesto1); //Inserisci il numero della prima riga da prendere 
			int y= getRiga(campotesto2); //Inserisci il numero della seconda riga da prendere
			addizione(m,m2,x,y);}
		if(numeroBottone == 2) {
			int a= getRiga(campotesto1);
			int b= getRiga(campotesto2);
			sottrazione(m,m2,a,b);}
		if(numeroBottone== 3) {
			int a= getRiga(campotesto1);
			int b= getRiga(campotesto2);
			moltiplicazione(m,m2,a,b);}
		if(numeroBottone==4) {
			int a= getRiga(campotesto1);
			int b= getRiga(campotesto2);
			scambiaRighe(m,m2,a,b);
		}
		if(numeroBottone== 5) {
			pulizia(m,m2);}
		}
		
		/* FINE PARTE DA PERSONALIZZARE */
	

	private Razionale[][] convertiInMatriceRazionale(String[][] m) {
		int nR= m.length; //Si prende il numero delle righe
		int nC= m[0].length; //Si prende il numero delle colonne
		Razionale [][] matrice= new Razionale [nR][nC]; //inizializza una nuova matrice di lunghezza nR*nC
		for(int i=0; i<nR; i++) {
			for(int j=0; j<nC; j++) {
				if(!m[i][j].equals("")) { //se non è un elemento vuoto
					matrice[i][j]= Razionale.parseRaz(m[i][j]);} //trasformami, con il metodo parceRaz, l'elemento contenuto in un numero razionale
				else {
					matrice[i][j]= new Razionale(0);}}} //altrimenti mettimi un nuovo razionale inizializzato a 0
		return matrice;}

	public static void main(String[] args)
	{	PannelloGrafico p = new PannelloGrafico();
		/* INIZIO PARTE DA PERSONALIZZARE */
		p.setEtichettaBottone(1, "ADDIZIONE"); 
		p.setEtichettaBottone(2, "SOTTRAZIONE");
		p.setEtichettaBottone(3, "MOLTIPLICAZIONE");
		p.setEtichettaBottone(4, "SCAMBIA RIGHE");
		p.setEtichettaBottone(5, "PULIZIA");
		/* FINE PARTE DA PERSONALIZZARE */
	} 
	
	
	
	
	public void addizione(Razionale[][] m, Razionale[][]m2, int a, int b){
		setMessaggio("Operazione scelta: ADDIZIONE");
		Razionale r1=null, r2=null;
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[i].length;j++) {
				r1=m[a][j];
				r2=m[b][j];
				if(m!=null && m2!=null) {
					m2[a][j]= Razionale.somma(r1, r2);
					m2[b][j]= r2;}
				if(a==b) {
					setMessaggio("Hai scelto la stessa riga, l'operazione non è consentita");}
				m2[i][j]= m[i][j];
				}
		}
		String[][] risultato= convertiDaMatriceIntera(m2); //converte la matrice in string
		setMatriceB(risultato); 
		setMatriceA(risultato);}
	
	public void sottrazione(Razionale [][]m, Razionale [][]m2, int a, int b) {
		setMessaggio("Operazione scelta: SOTTRAZIONE");
		Razionale r1= null;
		Razionale r2= null;
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[i].length;j++) {
				r1=m[a][j];
				r2=m[b][j];
				if(m!=null && m2!=null) {
					m2[a][j]= Razionale.differenza(r1,r2);
					m2[b][j]= r2;}
				if(a==b) {
					setMessaggio("Hai scelto la stessa riga, l'operazione non è consentita");}
				m2[i][j]= m[i][j];
				}
		}
		String[][] risultato= convertiDaMatriceIntera(m2);
		setMatriceB(risultato);
		setMatriceA(risultato);}
	
	public void moltiplicazione(Razionale [][] m, Razionale[][] m2, int a, int b) {
		setMessaggio("Operazione scelta: MOLTIPLICAZIONE");
		Razionale r1= null;
		Razionale r2= null;
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				r1=m[a][j];
				r2= m[b][j];
				if(m != null && m2 != null) {
					m2[a][j]= Razionale.prodotto(r1,r2);
					m2[b][j]= r2;}
				if(a==b) {
					setMessaggio("Hai inserito la stessa riga, perciò non è possibile eseguire il prodotto.");
					}
				
				m2[i][j]=m[i][j];
			}
			String[][] risultato= convertiDaMatriceIntera(m2);
			setMatriceB(risultato);
			setMatriceA(risultato);}	
		}
	public void pulizia(Razionale [][] m, Razionale [][] m2) {
		setMessaggio("Operazione Pulizia");
		String[][] risultato1= convertiDaMatriceIntera(m);
		String[][] risultato2= convertiDaMatriceIntera(m2);
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				risultato1[i][j]="" ;
				risultato2[i][j]="" ;}}
		setMatriceB(risultato1);
		setMatriceA(risultato2);
			}
	public void scambiaRighe(Razionale [][] m, Razionale [][] m2, int a, int b) {
		setMessaggio("Operazione Scelta: SCAMBIA RIGHE");
		Razionale r1= null;
		Razionale r2= null;
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				r1= m[a][j];
				r2= m[b][j];
				if(m != null && m2 != null ) {
					m2[a][j]= r2;
					m2[b][j]= r1;}
				if(a==b) {
					setMessaggio("E' la stessa riga, cambia");}
				m2[i][j]= m[i][j];}}
		String[][] risultato= convertiDaMatriceIntera(m2);
		setMatriceB(risultato);
		setMatriceA(risultato);}
	
	public int getRiga(JTextField a) {
		String x = a.getText(); //Sto prendendo il contenuto del riquadro
		int n=0;
		if(x.isEmpty()) 
			setMessaggio("NON è STATO INSERITO NESSUN NUMERO");
			setMessaggio(x);
		if(x.matches("[\\+\\-]?\\d+")) { //qui controllo se il valore inserito è un valore accettato.
			n = Integer.parseInt(x);
			if(n<-1) setMessaggio("NUMERO TROPPO PICCOLO");
			if(n>7) setMessaggio("NUMERO TROPPO GRANDE");
		}
		return n;
		
		}
	

	private void setMessaggio(String m)
	{	messaggio.setText(m);
	}

	private void setEtichettaBottone(int numeroBottone, String etichetta)
	{	bottoni[numeroBottone-1].setText(etichetta);
	}

	private String[][] getMatriceA()
	{	String[][] ret = new String[numeroRigheA][numeroColonneA];
		for(int i=0;i<numeroRigheA;i++)
			for(int j=0;j<numeroColonneA;j++)
				ret[i][j] = matriceA[i][j].getText();
		return ret;
	}

	private String[][] getMatriceB()
	{	String[][] ret = new String[numeroRigheB][numeroColonneB];
		for(int i=0;i<numeroRigheB;i++)
			for(int j=0;j<numeroColonneB;j++)
				ret[i][j] = matriceB[i][j].getText();
		return ret;
	}

	private void setMatriceA(String[][] A)
	{	for(int i=0;i<numeroRigheA;i++)
			for(int j=0;j<numeroColonneA;j++)
				matriceA[i][j].setText(A[i][j]);
	}

	private void setMatriceB(String[][] B)
	{	for(int i=0;i<numeroRigheB;i++)
			for(int j=0;j<numeroColonneB;j++)
				matriceB[i][j].setText(B[i][j]);
	}
	
	private void bloccaMatriceA()
	{	for(int i=0;i<numeroRigheA;i++)
			for(int j=0;j<numeroColonneA;j++)
				matriceA[i][j].setEditable(false);;
	}

	private void sbloccaMatriceA()
	{	for(int i=0;i<numeroRigheA;i++)
			for(int j=0;j<numeroColonneA;j++)
				matriceA[i][j].setEditable(true);;
	}
	
	private void bloccaMatriceB()
	{	for(int i=0;i<numeroRigheB;i++)
			for(int j=0;j<numeroColonneB;j++)
				matriceB[i][j].setEditable(false);;
	}

	private void sbloccaMatriceB()
	{	for(int i=0;i<numeroRigheB;i++)
			for(int j=0;j<numeroColonneB;j++)
				matriceB[i][j].setEditable(true);;
	}
	
	private int[][] convertiInMatriceIntera(String[][] m)
	{	int nRighe=m.length;
		int nColonne=m[0].length;
		int[][] ret = new int[nRighe][nColonne];
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++)
				if(m[i][j].equals(""))
					ret[i][j]=0;
				else
					ret[i][j]=Integer.parseInt(m[i][j]);
		return ret;
	}

	private String[][] convertiDaMatriceIntera(Razionale [][] m) //ho ritenuto opportuno modificare il seguente metodo in base alle mie esigenze
	{	int nRighe=m.length;
		int nColonne=m[0].length;
		String[][] ret = new String[nRighe][nColonne];
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++)
				ret[i][j]=""+m[i][j];
		return ret;
	}

	private void inizializza()
	{	frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try
		{	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{	System.out.println(e);
			System.exit(0);
		}
		
		//INIZIO PARTE MODIFICATA
		campotesto1 =  new JTextField(); //il riquadro dove inserire la prima riga
		frame.getContentPane().add(campotesto1); //aggiungo il riquadro al pannello
		campotesto1.setFont(a); //cambio lo stile di scrittura
		campotesto1.setVisible(true); //lo rendo visibile
		campotesto1.setBounds(10, 300, 100, 100); //sto posizionando il pannello
		
		campotesto2 =  new JTextField();
		campotesto2.setFont(a);
		frame.getContentPane().add(campotesto2);
		campotesto2.setVisible(true);
		campotesto2.setBounds(110, 300, 100, 100);
		
		//FINE PARTE MODIFICATA
		
		frame.setVisible(true);
		frame.setBounds(100, 100, 480, 360+30*numeroBottoni);
		frame.setTitle(titoloFinestra);

		messaggio = new JLabel("");
		messaggio.setFont(f);
		frame.getContentPane().add(messaggio);
		messaggio.setBounds(170, 270, 600, 30);
		messaggio.setHorizontalAlignment(JLabel.CENTER);

		bottoni=new JButton[numeroBottoni];
		ActionListener listener = new PressioneBottoni();
		for(int i=0;i<numeroBottoni;i++)
		{	JButton bottone=new JButton();
			bottone.setBounds(240, 300+30*i, 300, 30);
			bottoni[i]=bottone;
			bottone.addActionListener(listener);
			frame.getContentPane().add(bottone);
		}

		matriceA = new JTextField[numeroRigheA][numeroColonneA];
		for(int i=0;i<numeroRigheA;i++)
			for(int j=0;j<numeroColonneA;j++)
			{	JTextField campoTesto = new JTextField("");
				frame.getContentPane().add(campoTesto);
				campoTesto.setBounds(50+45*j, 60+30*i, 45, 30);
				campoTesto.setHorizontalAlignment(JTextField.CENTER);
				matriceA[i][j] = campoTesto;
			}
		for(int i=0;i<numeroRigheA;i++)
		{	JLabel numeroRigaA = new JLabel(""+i);
			frame.getContentPane().add(numeroRigaA);
			numeroRigaA.setBounds(30, 60+30*i, 45, 30);
		}
		for(int j=0;j<numeroColonneA;j++)
		{	JLabel numeroColonnaA = new JLabel(""+j);
			frame.getContentPane().add(numeroColonnaA);
			numeroColonnaA.setBounds(60+45*j, 40, 45, 30);
		}

		if(numeroRigheB != 0 || numeroColonneB != 0)
		{	matriceB = new JTextField[numeroRigheB][numeroColonneB];
			for(int i=0;i<numeroRigheB;i++)
				for(int j=0;j<numeroColonneB;j++)
				{	JTextField campoTesto = new JTextField("");
					campoTesto.setFont(f);
					frame.getContentPane().add(campoTesto);
					campoTesto.setBounds(430+45*j, 60+30*i, 45, 30);
					campoTesto.setHorizontalAlignment(JTextField.CENTER);
					matriceB[i][j] = campoTesto;
				}
			for(int i=0;i<numeroRigheB;i++)
			{	JLabel numeroRigaB = new JLabel(""+i);
				frame.getContentPane().add(numeroRigaB);
				numeroRigaB.setBounds(410, 60+30*i, 45, 30);
			}
			for(int j=0;j<numeroColonneB;j++)
			{	JLabel numeroColonnaB = new JLabel(""+j);
				frame.getContentPane().add(numeroColonnaB);
				numeroColonnaB.setBounds(440+45*j, 40, 45, 30);
			}
		}
	}

	private class PressioneBottoni implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	int numeroBottonePremuto = -1;
			for(int i=0;i<numeroBottoni;i++)
				if(e.getSource()==bottoni[i])
					numeroBottonePremuto = i + 1;
			bottonePremuto(numeroBottonePremuto);
		}
	}
}


