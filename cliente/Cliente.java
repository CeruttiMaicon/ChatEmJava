package cliente;

import static javax.swing.JOptionPane.*;

public class Cliente {

  
    public static void main(String[] args) {
       
        String name = showInputDialog(null, "Digite seu nome: ", "", PLAIN_MESSAGE);
        
        Chat chat = new Chat(name);
        //Visibilidade do chat
        chat.setVisible(true);
    }
    
}
