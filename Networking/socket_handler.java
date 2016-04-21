import java.io.*;
import java.net.*;
import java.util.*;

public class socket_handler implements Runnable 
{
	public String my_ip_address = "10.192.45.162";

	public DatagramSocket socket = null;

	//Enter HashMap here

	public HashMap<String,indiv_connection_handler> connect_list;

	public String choice;

	public socket_handler(String input_choice) throws SocketException
	{
		this.socket = new DatagramSocket(9876);
		this.connect_list = new HashMap<String,indiv_connection_handler>();
		this.choice = input_choice;
	}

	public void run()
	{
		try
		{
			if (choice.equals("2"))
			{
				String ip_address = System.console().readLine("Enter the IP Address: ");

				if (this.connect_list.get(ip_address) == null)
		        {
		        	this.connect_list.put(ip_address,new indiv_connection_handler(ip_address));
		        }

		        this.connect_list.get(ip_address).send_message("Connection-Request;"+/*InetAddress.getLocalHost().getHostAddress()*/my_ip_address+"");
			}

			new Thread(new message_sender_thread(this)).start();

			while(true)
			{
				byte[] incomingData = new byte[1024];

				DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
	            	socket.receive(incomingPacket);
	            String response = new String(incomingPacket.getData(),incomingPacket.getOffset(),incomingPacket.getLength(),"UTF-8");
	            String[] decode = response.split(";");

	            if (decode[0].equals("Connection-Request"))
	            {
	                // Code to handle new user
	                this.new_user(decode[1]);
	                for (String key: this.connect_list.keySet()) 
			    	{
						// this.connect_list.get(key).send_message(message);  
						System.out.println("HashMap entries CR");  		
						System.out.println(key);
			    	}
	            }
	            else if (decode[0].equals("Message"))
	            {
	            	System.out.println("Message Received:" + decode[1]);

	            	// Probably write a send response code here
	            	// String choice = System.console.readLine("Enter your Message: ");
	            }
	            else if (decode[0].equals("New-User-Added"))
	            {
	            	System.out.println("Message Received:" + decode[1]);

	            	// Probably write a send response code here
	            	this.add_user(decode[1]);
	            	for (String key: this.connect_list.keySet()) 
			    	{
						// this.connect_list.get(key).send_message(message);  
						System.out.println("HashMap entries NUA");  		
						System.out.println(key);
			    	}
	            }
	            else if (decode[0].equals("Handshake-Request"))
	            {
	            	System.out.println("Message Received:" + decode[1]);

	            	// Probably write a send response code here
	            	this.user_handshake(response);
	            	for (String key: this.connect_list.keySet()) 
			    	{
						// this.connect_list.get(key).send_message(message);  
						System.out.println("HashMap entries HR");  		
						System.out.println(key);
			    	}
	            }
	            // new Thread(new Responder(this.socket, packet)).start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void new_user(String ip_addr) throws Exception
	{
        System.out.println("New User: "+ip_addr+" joined.");

        if (this.connect_list.get(ip_addr) == null)
        {
        	this.connect_list.put(ip_addr,new indiv_connection_handler(ip_addr));
        }

        this.send_message_to_all("New-User-Added;"+ip_addr+"");
        this.connect_list.get(ip_addr).send_message("Handshake-Request;"+/*InetAddress.getLocalHost().getHostAddress()*/my_ip_address+this.get_ip_list()+"");
    }

    public String get_ip_list()
    {
    	String ip_list = "";

    	for (String key: this.connect_list.keySet()) 
    	{
			ip_list = ip_list+";"+key;
    	}

    	return ip_list;
    }

    public void add_user(String ip_addr) throws Exception  
	{
        System.out.println("New User: "+ip_addr+" joined.");

        if (this.connect_list.get(ip_addr) == null)
        {
        	this.connect_list.put(ip_addr,new indiv_connection_handler(ip_addr));
        }

        // this.send_message_to_all("New-User-Added;"+ip_addr+"");
        // this.connect_list.get(ip_addr).send_message("Handshake-Request;"+InetAddress.getLocalHost().getHostAddress()my_ip_address+"");
    }

    public void send_message_to_all(String message) throws Exception
    {
    	for (String key: this.connect_list.keySet()) 
    	{
			this.connect_list.get(key).send_message(message);    		
    	}
    }

    public void user_handshake(String resp) throws Exception
	{
        // System.out.println("New User: "+ip_addr+" joined.");

		String[] ip_addr = resp.split(";");

		for (int i = 1; i< ip_addr.length; i++)
		{
			if (this.connect_list.get(ip_addr[i]) == null)
	        {
	        	this.connect_list.put(ip_addr[i],new indiv_connection_handler(ip_addr[i]));
	        }
		}
    }
	
}

class indiv_connection_handler
{
	public InetAddress IPAddress;
	public DatagramSocket socket = null;

	public indiv_connection_handler (String ip_address) throws Exception
	{
		this.IPAddress = InetAddress.getByName(ip_address);
		this.socket = new DatagramSocket();
	}

	public void send_message(String message) throws Exception
	{
		byte[] data = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, this.IPAddress, 9876);
        this.socket.send(sendPacket);
        // System.out.println("Message sent from client");
        // DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        // Socket.receive(incomingPacket);
        // String response = new String(incomingPacket.getData(),incomingPacket.getOffset(),incomingPacket.getLength(),"UTF-8");
        // System.out.println("Response from server:" + response);
	}
}

class message_sender_thread implements Runnable
{
	public socket_handler sh;

	public message_sender_thread(socket_handler s)
	{
		this.sh = s;
	}

	public void run()
	{
		try
		{	
			while (true)
			{
				String choice = System.console().readLine("Enter your Message: ");

				this.sh.send_message_to_all("Message;"+choice);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}