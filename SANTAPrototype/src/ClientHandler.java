/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author asaas
 */
public class ClientHandler extends Thread{
    Server server;
    BufferedReader inReader;
    PrintWriter outWriter;
    
    public ClientHandler(Server s, Socket sock) {
        server = s;
        System.out.println("Client started: " + sock.getInetAddress().toString());
        
        try{
            inReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            outWriter = new PrintWriter(sock.getOutputStream());
            
        }catch(IOException e){
            System.out.println("Exception in ClientHandler");
        }
        
    }
    public void run(){
        try{        
            String input;
            while((input = inReader.readLine()) != null){  
                //System.err.println("test" + input);
                processInput(input);
            }
        }catch(IOException e){
            System.out.println("Exception in ClientHandler:  Client likely Disconnected");
        }
         
    }
    
    private void processInput(String inputstring){
        System.out.println("Processing Command");
        if(inputstring.startsWith("/add ")){
            server.setList(inputstring.substring(5));
        }else{
            if(inputstring.startsWith("/get")){
              outWriter.println("/dis" + server.getList());
              outWriter.flush();
            }
        
        }
                
    }
}






















