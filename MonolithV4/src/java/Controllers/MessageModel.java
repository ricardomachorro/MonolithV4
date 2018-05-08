/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Classes.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class MessageModel extends Database{
    public ArrayList<Message> get(){
        
        ArrayList<Message> messages = new ArrayList<>();
        
        try {
            
            PreparedStatement pst = getConnection().prepareStatement
            ("SELECT id, name, message, DATE_FORMAT(created_at, '%d-%m-%y a las %h:%i:%s %p') as date "
            + "FROM messages"
            + "ORDER BY created_at ASC "
            + "LIMIT 100");
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
                messages.add(new Message(rs.getInt("id"), rs.getString("name"), rs.getString("message"), rs.getString("date")));
            }
            
            getConnection().close();
            rs.close();
            pst.close();
            
        } catch (Exception e) {
        }
        
        return messages;
    }
    
    public boolean create(Message m){
        
        boolean flag = false;
        
        try {
            
            PreparedStatement pst = getConnection().prepareStatement
            ("INSERT INTO messages "
            + "(name, message, created_at) "
            + "VALUES(?,?,?)");
            
            pst.setString(1, m.getName());
            pst.setString(2, m.getMessage());
            pst.setString(3, m.getCreated_at());
            
            flag = pst.executeUpdate() == 1;
            
            getConnection().close();
            pst.close();
            
        } catch (Exception e) {
        }
        
        return flag;
        
    }
   
}