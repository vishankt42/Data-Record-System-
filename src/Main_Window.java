//package Eommerce1;

import Eommerce1.Product;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vishu tyagi
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        Show_Products_In_JTable();
    }

    String ImgPath = null;
    int pos = 0;
            
            
    public Connection getConnection()
    {
    Connection con = null;
    
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/products_db2","root","");
            
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           
            return null;
        }
    
    }
    
    // check input fields
    
    public boolean checkImputs()
    {
    if(
        txt_name.getText() == null
       || txt_price.getText() == null
          || txt_AddDate.getDate() == null  
            ) {
        return false;
    }
    else {
    try {
    Float.parseFloat(txt_price.getText());
    return  true;
    }
    catch(Exception ex)
    {
    return false;
    }
    }
    }
    
    // resize image
       
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myImage  = null;
        
        if(imagePath != null)
        {
        myImage  = new ImageIcon(imagePath);
        } else {
        myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(ibl_image.getWidth(),ibl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    
    //Display Data In JTable:
    // 1 - fill arraylist with the data
    
     public ArrayList<Product> getProductList() throws SQLException
    {
    ArrayList<Product> productList  = new ArrayList<Product>();
    Connection con = getConnection();
    String query = " SELECT * FROM products";
    
    Statement st;
    ResultSet rs;
    
    try {
    st=con.createStatement();
    rs = st.executeQuery(query);
    Product product;
    
    while(rs.next())
        
    {
    product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
    productList.add(product);
    }
    } catch (SQLException ex) {
               Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           }
    
        return productList;
        
    }    
    
    // 2- populate the JTable
     
     public void Show_Products_In_JTable()
     {
        try {
            ArrayList<Product> list = getProductList();
            DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
            //clear jtable content
            model.setRowCount(0);
            Object[] row = new Object[4];
            for(int i = 0; i < list.size(); i++)
            {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getName();
                row[2] = list.get(i).getPrice();
                row[3] = list.get(i).getAddDate();
                
                
                model.addRow(row);   
            }  } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
     
     public void ShowItem(int index) throws SQLException
     {
     txt_id.setText(Integer.toString(getProductList().get(index).getId()));
     txt_name.setText(getProductList().get(index).getName());
     txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
     
     
     try{
     Date addDate=null;
     addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
     txt_AddDate.setDate(addDate);
     }catch(ParseException ex)
     {
     Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     ibl_image.setIcon(ResizeImage(null,getProductList().get(index).getImage()));
     }
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        ibl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_First = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Previous = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Price :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Add Date :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Image :");

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_name.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_price.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_price.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_AddDate.setDateFormatString("yyyy-MM-dd ");
        txt_AddDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ibl_image.setBackground(new java.awt.Color(204, 255, 255));
        ibl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2));
        ibl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_Choose_Image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Choose_Image.setText("Choose Image");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button-Add-icon.png"))); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.setIconTextGap(15);
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("F:\\button icon\\PNG\\Button Refresh1.png")); // NOI18N
        jButton3.setText("Update");
        jButton3.setIconTextGap(10);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("F:\\button icon\\PNG\\Button Delete1.png")); // NOI18N
        jButton4.setText("Delete");
        jButton4.setIconTextGap(10);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btn_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_First.setIcon(new javax.swing.ImageIcon("F:\\button icon\\PNG\\Button First1.png")); // NOI18N
        btn_First.setText("First");
        btn_First.setIconTextGap(10);
        btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FirstActionPerformed(evt);
            }
        });

        btn_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button Next1.png"))); // NOI18N
        btn_Next.setText("Next");
        btn_Next.setIconTextGap(10);
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Previous.setIcon(new javax.swing.ImageIcon("F:\\button icon\\PNG\\Button Previous1.png")); // NOI18N
        btn_Previous.setText("Previous");
        btn_Previous.setIconTextGap(10);
        btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PreviousActionPerformed(evt);
            }
        });

        btn_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Last.setIcon(new javax.swing.ImageIcon("F:\\button icon\\PNG\\Button Last1.png")); // NOI18N
        btn_Last.setText("Last");
        btn_Last.setIconTextGap(10);
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                        .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                        .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ibl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_First)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Last)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ibl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Insert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_First)
                        .addComponent(btn_Next)
                        .addComponent(btn_Previous)
                        .addComponent(btn_Last)))
                .addGap(198, 198, 198))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(checkImputs() && txt_id.getText() !=null)
       {
       String UpdateQuery = null;
       PreparedStatement ps = null;
       Connection con = getConnection();
       
       // update wihtout image
       if(ImgPath == null)
       {
           try {
               UpdateQuery = "UPDATE products SET name = ?, price = ?"
                       + "  add_date = ? WHERE id = ?";
               ps =con.prepareStatement(UpdateQuery);
               
               ps.setString(1,txt_name.getText());
               ps.setString(2,txt_price.getText());
               
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                
                ps.setString(3, addDate);
                ps.setInt(4, Integer.parseInt(txt_id.getText()));
                 ps.executeUpdate();
                 Show_Products_In_JTable();
                 JOptionPane.showMessageDialog(null,"PRODUCT UPDATED");
                 
           } catch (SQLException ex) {
               Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       //update with image
       else{
       try{
           InputStream img = new FileInputStream(new File(ImgPath));
           
           UpdateQuery = "UPDATE products SET name = ?, price = ?"
                       + " ,add_date = ? ,image = ? WHERE id = ?";
           
           
             ps =con.prepareStatement(UpdateQuery);
               
               ps.setString(1,txt_name.getText());
                ps.setString(2,txt_price.getText());
               
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                
                ps.setString(3, addDate);
                ps.setBlob(4,img);
                ps.setInt(5, Integer.parseInt(txt_id.getText()));
                
            ps.executeUpdate();
            Show_Products_In_JTable();
           JOptionPane.showMessageDialog(null,"PRODUCT UPDATED");
           
       }catch(Exception ex)
       {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
       }
       }else {
        JOptionPane.showMessageDialog(null,"EK YHA EK SA JYADA fields KHALE HAI YHA GALAT HAI");
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      if(!txt_id.getText().equals(""))
      {
          try { 
             Connection con = getConnection();
              PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
              int id= Integer.parseInt(txt_id.getText());
              ps.setInt(1, id);
              ps.executeUpdate();
              Show_Products_In_JTable();
              JOptionPane.showMessageDialog(null,"Product Deleted");
          } catch (SQLException ex) {
              Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
              JOptionPane.showMessageDialog(null,"Product Not Deleted");
          }
      
      }else {
       JOptionPane.showMessageDialog(null,"Products Not Deleted :No Id To Delete");
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FirstActionPerformed
        try {
            pos = 0;
            ShowItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btn_FirstActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        try {
            pos++;
            
            if(pos >= getProductList().size())
            {
                pos = getProductList().size() - 1;
                
                ShowItem(pos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PreviousActionPerformed
        try {
            pos--;
            
            if(pos < 0 )
            {
                pos = 0;
            }
            ShowItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_PreviousActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        try {
            pos = getProductList().size()-1;
            ShowItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_LastActionPerformed

    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION)
        {
        File selectedFile = file.getSelectedFile();
        String path  = selectedFile.getAbsolutePath();
        ibl_image.setIcon(ResizeImage(path,null));
        ImgPath = path;
        }
        else
        {
        System.out.println(" NO FILE Selected");
        }
        
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed


    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        
        if(checkImputs() && ImgPath !=null)
        {
        
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,add_date,image) " + " value(?,?,?,?)");
                ps.setString(1,txt_name.getText());
                ps.setString(2,txt_price.getText());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, addDate);
                
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4,img);
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null,"bus kr chala gya data");
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        } else {
        JOptionPane.showMessageDialog(null,"One Or More Field Are Empty");
        }
        
        System.out.println("name => " +txt_name.getText());
        System.out.println("price => " +txt_price.getText());
        System.out.println("image => " +ImgPath);
        
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        try {
            int index = JTable_Products.getSelectedRow();
            ShowItem(index);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton btn_First;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Previous;
    private javax.swing.JLabel ibl_image;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
