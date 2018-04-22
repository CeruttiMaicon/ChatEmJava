
package cliente;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;

public class Chat extends javax.swing.JFrame {
    
    private String name;
    private Socket socket;
    private BufferedReader br;
    private InputStreamReader isr;
   
    //metodo construtor
    public Chat(String name) {
        
        //inicializa os componentes do Swing
        initComponents();
        
        //atribuição do nome para o Cliente
        this.name = name;
        
        try{
            socket = new Socket("localhost",5000);
        }catch(IOException e){
            //faz a conexão com o servidor
            showMessageDialog(null, "Não se conectou com o servidor","",ERROR_MESSAGE);
            //fecha a conexão caso ele nao conecte
            System.exit(0);
        }
        Thread();
    }
    // A thread que vai recever as mensagens
    private void Thread(){
        
        Thread tread = new Thread ( new Runnable(){
            
            String mensagem;
            
            @Override
            public void run(){
                
                try {
                     isr = new InputStreamReader(socket.getInputStream());
                     br = new BufferedReader(isr);
                     
                     while ((mensagem = br.readLine()) != null) {
                         mensagemRecebida.setText(mensagemRecebida.getText() + mensagem + "\n");
                        
                     }
                } catch ( IOException e ) {
                    showMessageDialog(null, "Erro na conexão com o servidor!", "", ERROR_MESSAGE);
                }
            }
        });
        //inicia a thread
        tread.start();  
    }
    //Inicializa o Swing - Código feito pelo NetBeans
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(mensagemRecebida);

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(mensagemEnviada);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/logo 3.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Parte final Swing - Código feito pelo NetBeans
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Mensagem aparecendo no cliente
        
        try{
            String mensagem = name + " escreveu: ";
            PrintStream ps = new PrintStream(socket.getOutputStream());
            //manda a mensagem para o campo TEXT superior
            //Pega o nome e a palavra ' escreveu: ' e atribui a mensagem digitada pelo Cliente
            mensagem += mensagemEnviada.getText();
            
            ps.println(mensagem);
            //limpar a memoria
            ps.flush();
            
            mensagemEnviada.setText("");
            
        }catch(IOException e){
            showMessageDialog(null, "Não conseguiu enviar mensagem","",ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnviadaKeyPressed
        
        //Foi necessario duplicar está parte pois eu quis fazer o chat
        //enviar mensagens apenas precisonando ENTER
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                
            try{
                String mensagem = name + " escreveu: ";
                PrintStream ps = new PrintStream(socket.getOutputStream());
                //manda a mensagem para o campo TEXT superior
                //Pega o nome e a palavra ' escreveu: ' e atribui a mensagem digitada pelo Cliente
                mensagem += mensagemEnviada.getText();

                ps.println(mensagem);
                //limpar a memoria
                ps.flush();
                //limpa a caixa de texto a cada envio de mensagem
                mensagemEnviada.setText("");

            }catch(IOException e){
                showMessageDialog(null, "Não conseguiu enviar mensagem","",ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_mensagemEnviadaKeyPressed

    //Botão de SAIR
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed


// Swing feito pelo NetBeans
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemEnviada;
    private javax.swing.JTextArea mensagemRecebida;
    // End of variables declaration//GEN-END:variables
}
