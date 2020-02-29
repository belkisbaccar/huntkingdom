/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.Datasource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import image.panier;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import user.Service.UserSession;
 

/**
 *
 * @author azizm
 */
public class panierController {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet s;
    
    
    public int get() throws SQLException
{
    int i2=0;
      UserSession n = UserSession.getInstance();
                               String s1 = n.getUserName();
                               Statement stmt1 = cnx.createStatement();
                              String SQL1 = "SELECT * FROM user  WHERE username ='" +s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                    i2=rs1.getInt(1);
                                           
                                }
        return i2;
                              
    
}
    
     public void insert(int id_user ,String nom) throws SQLException
    {
         cnx = Datasource.getInstance().getCnx();
         
        Statement stmt = cnx.createStatement();
        String SQL = "SELECT * FROM product WHERE nom ='" +nom+"'";
          ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next()){
            int x = rs.getInt(4);//le prix unitaire du produit choisi 
             int s = rs.getInt(3)-1;// 
             int m=rs.getInt("quantite");//quantite de produit dans le stock
             if(m!=0){
            st = cnx.createStatement();
        String SQL2 = "SELECT * FROM panier WHERE id ='" +id_user+"' and nomP='" +nom+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          if(rs2.next())
          {existe( id_user,  s,x,nom );}
          else {
            
            
                int i =1;
        String req="insert into panier (id,nomP,quantite,prix)values('"+id_user+"','"+nom+"','"+i+"','"+i*x+"')";
       
      
      
        String rq="UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +nom+"'";//mettre a jour le tableau product 
        
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
            st.executeUpdate(rq);
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            
          }
             }
            else 
                System.out.println("running out of stock");
            
          }
            }
            
   
    
     
     void  existe(int id_user, int s ,int x,String nom) throws SQLException
{
cnx = Datasource.getInstance().getCnx();
          Statement stt = cnx.createStatement();
        String SQL = "SELECT * FROM panier  WHERE id ='" +id_user+"'and nomP='" +nom+"'";
          ResultSet rs = stt.executeQuery(SQL);
            if(rs.next()){
                int l=rs.getInt(4)+1;// si le panier existe dja et meme id et meme produit on ajoute la quantité demande 
                int q =x*l;
        String SQL2 = "UPDATE panier SET  quantite = '"+l+"' , prix = '"+q+"'  WHERE id ='" +id_user+"'and nomP='" +nom+"'";
         st=cnx.createStatement();
            st.executeUpdate(SQL2);
           
             
            
        String SQL3 = "UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +nom+"'";
         try {
            st=cnx.createStatement();
            st.executeUpdate(SQL3);
          
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
}
     public void commande(int id_user ) throws SQLException
     {
        Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT sum(prix),SUM(quantite) FROM panier WHERE id ='"+id_user+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          
          if(rs2.next())
          {
             int x = rs2.getInt(1);
             int x2=rs2.getInt(2);
             String s="en cours ";
            // System.out.println(x);
       String SQL = "INSERT INTO commande(id,quantite,prix_total,etat) values ('"+id_user+"','" +x2+"','" +x+"','" +s+"')";
          st=cnx.createStatement();
            st.executeUpdate(SQL);
          
     }
     }
     
   	public static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
          void pdf(int id_user) throws SQLException
     {
 int i= get();
try {

         OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));



            Document document = new Document();

            PdfWriter.getInstance(document, file);


            document.open();
             document.add(new Paragraph("numero commande "+getAlphaNumericString(10)));
              document.add(new Paragraph("BIENVENU"));

             PdfPTable my_first_table = new PdfPTable(3);
             PdfPCell table_cell;
             table_cell=new PdfPCell(new Phrase(getprix(i)));
              my_first_table.addCell(table_cell);
              table_cell=new PdfPCell(new Phrase(getquantite(i)));
               my_first_table.addCell(table_cell);
               table_cell=new PdfPCell(new Phrase(getUser(i)));
                my_first_table.addCell(table_cell);
           
             // my_first_table.addCell(table_cell);
              // document.add(new Paragraph(getquantite(4)));

           document.add( my_first_table);    


            document.close();
             
            file.close();


        } catch (Exception e) {


            e.printStackTrace();


     }
}
   
		  public   String getprix(int id_user) throws SQLException
    {
        String x="";
        Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT prix_total FROM commande WHERE id ='"+id_user+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          
          if(rs2.next())
          {
              x = rs2.getString(1);
              
    }
          return x;   
          
}
     public  String getquantite(int id_user) throws SQLException
    {
        String x="";
        Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT quantite FROM commande WHERE id ='"+id_user+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          
          if(rs2.next())
          {
              x = rs2.getString(1);
             
    }
          return (x);   
          
}
      public  String  getUser(int id_user) throws SQLException
    {
        String x="";
        Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT nom FROM user WHERE id_user ='"+id_user+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          
          if(rs2.next())
          {
              x = rs2.getString(1);
             
    }
          return (x);   
          
}
     public void réduire_quantité(int id_user ,String nom) throws SQLException
    {
         cnx = Datasource.getInstance().getCnx();
         
        Statement stmt = cnx.createStatement();
        Statement stt = cnx.createStatement();
        String SQL = "SELECT * FROM product WHERE nom ='" +nom+"'";
          ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next()){
            int x = rs.getInt(4);//le prix unitaire du produit choisi 
             int s = rs.getInt(3)+1;// le mise a jour de stock de tableau product
             int m=rs.getInt(3);//quantite de produit dans le stock
              String SQL2 = "SELECT * FROM panier  WHERE id ='" +id_user+"'and nomP='" +nom+"'";
          ResultSet rs2 = stt.executeQuery(SQL2);
            if(rs2.next()){
                if(rs2.getInt(4)>=1){
             int l=rs2.getInt(4)-1;
              int q =x*l;
          String req = "UPDATE panier SET  quantite = '"+l+"' , prix = '"+q+"'  WHERE id ='" +id_user+"'and nomP='" +nom+"'";
       
      
      
        String rq="UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +nom+"'";//mettre a jour le tableau product 
        
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
            st.executeUpdate(rq);
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                else
                System.out.println("error");
            
            }
            }
            check();
          }
        void check() throws SQLException
       {
             pst=cnx.prepareStatement("delete from panier where quantite  = '"+0+"'");

        pst.execute();
       
           
           }
}


 