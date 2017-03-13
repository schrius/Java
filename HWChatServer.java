package MyServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class HWChatServer {
	private ServerSocket listener;
	static boolean finished = false;
	final static int port = 1337;
	
	HWChatServer() throws IOException{
		try{
			listener = new ServerSocket(port); 
			System.out.println("Sever is ready.");
			while(true){
				Socket clients = listener.accept();
	            Thread rc = new Thread(new Client(clients));
	            rc.start();
				}
			}
		catch(IOException e){
			System.out.println(e.getMessage());
        }
		finally{
			if(listener!=null)
				listener.close();
		}
        }
	
	public static void main(String[] args) {
		try{
			 new HWChatServer(); 
			}
		catch(IOException e){
			System.out.println(e.getMessage());
        }
	}
}	


class Client implements Runnable{
	Socket clients;
	boolean quit = false;
	private static List<Client> Clist = new ArrayList<Client>();
	Client(Socket s){
		this.clients = s;
		Clist.add(this);
		System.out.println("New Client connected.");
	}
	public void run() {
		try{
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(clients.getInputStream()));
		    while(!quit){
				String clstring = fromClient.readLine();
				if(clstring == "quit"){
					quit = true;
					clients.close();
					break;
				}
				//System.out.println(clstring);
				Boardcast(clstring);
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		finally{
			disconnect();
		}
	}
    public synchronized void Boardcast(String msg) {
        for (Client c : Clist) {
            try {
    			PrintWriter toClient = new PrintWriter(c.clients.getOutputStream(), true);
                toClient.println(msg);
                toClient.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void disconnect() {
        Clist.remove(this);
    }
}



