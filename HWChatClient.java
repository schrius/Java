package MyServer;

import javax.swing.*;
import javax.swing.JTextArea;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HWChatClient {

	private JFrame frame;
	private JTextArea InputArea;
	private JTextArea OutArea;
	private JButton SendButton;
	private Socket server;
	static boolean finished = false;

	public static void main(String[] args) throws IOException {
		HWChatClient window = new HWChatClient();
		window.frame.setVisible(true);
	}

	public HWChatClient() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
		server = new Socket("127.0.0.1", 1337);
	    try {
			PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);
			
			frame = new JFrame();
			frame.setBounds(100, 100, 520, 480);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			InputArea = new JTextArea();
			InputArea.setBounds(0, 0, 504, 270);
			frame.getContentPane().add(InputArea);
			
		    OutArea = new JTextArea();
			OutArea.setBounds(0, 281, 504, 118);
			frame.getContentPane().add(OutArea);
			
			SendButton = new JButton("Send");
			SendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					toServer.println(OutArea.getText());
					OutArea.setText("");
				}
			});
			SendButton.setBounds(413, 399, 91, 31);
			frame.getContentPane().add(SendButton);
			
			new Receiver(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Receiver implements Runnable {
		Socket Rserver;
		Receiver(Socket s){
			this.Rserver = s;
			new Thread(this).start();
		}
		public void run(){
			try{
			BufferedReader fServer = new BufferedReader(new InputStreamReader(Rserver.getInputStream()));
			while(!finished){
			String inString = fServer.readLine();
			InputArea.append("Sever: " + inString + "\n");
			}
			}
			catch(IOException e){
				System.err.println(e.getMessage());
			}
		}
	}
	protected void finalize() throws IOException{
		server.close();
	}
}


