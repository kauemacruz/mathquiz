/*
    Developed by Kaue Macruz
    Version 1.0
 */
package networkmathquiz;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import static networkmathquiz.Student.dataOutputStream;

public class Teacher extends javax.swing.JFrame {
    
    private DefaultTableModel operationsTableModel;
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
     //list of question object
    static ArrayList<Question> questions = new ArrayList<Question>();
    //list of question answered wrongly
    static LinkedList<String> wrongAnswers = new LinkedList<String>();
    // create binary tree of QUESTIONS
    public BinaryTree<String> BTquestions = new BinaryTree<String>();
    
    public Teacher() {
        
        String [] columnNames_operations = new String[] {"LOp", "op", "ROp","=", "Ans"};

        Object[] defaultData_operations = {"", "", "", "", ""};

        operationsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        operationsTableModel.setColumnIdentifiers(columnNames_operations);
        operationsTableModel.addRow(defaultData_operations);
        
        initComponents();
        
        // customise column sizing for operations_jTable
        resizeOperationsTableColumns();
    }

    // resize columns in bookings_JTable
    private void resizeOperationsTableColumns() {
        
        // (total must = 1)
        double[] columnWidthPercentage = {0.25f, 0.25f, 0.25f, 0.25f, 0.25f};
        int tW = Operations_jTable.getWidth();
        TableColumn column;
        TableColumnModel tableColumnModel = Operations_jTable.getColumnModel();
        int cantCols = tableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = tableColumnModel.getColumn(i);
            float pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth((int)pWidth);
        }
    }
    public static void displayGenericCollection (Collection c)
    {
        Iterator iterator = c.iterator();
        while (iterator.hasNext())
        {
            Object obj = iterator.next();
            System.out.println(obj);
        }
        System.out.println();
    }
    public ArrayList ListQuestions(String FNumber, String Operator, String LNumber, String Equal , String Answer){
        // add to list of questioon objects
        questions.add(new Question(FNumber, Operator, LNumber, Equal , Answer));
        displayGenericCollection(questions);
        return questions;
    }
     public void displayToJTable()
    {
        DefaultTableModel model = (DefaultTableModel) Operations_jTable.getModel();
        if (model.getRowCount() > 0){
            for (int a = model.getRowCount() -1; a > -1; a--) {
                model.removeRow(a);
            }
        }
        ArrayList<Question> list = questions;
        Object rowData[] = new Object[5];
        for(int i = 0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).Lop;
            rowData[1] = list.get(i).op;
            rowData[2] = list.get(i).Rop;
            rowData[3] = list.get(i).equal;
            rowData[4] = list.get(i).Ans;
            model.addRow(rowData);
        }
    }
     static ArrayList ListWrongAnswers(String wrongAnswer){
        // add to list of questioon objects
        wrongAnswers.add(wrongAnswer);
        displayGenericCollection(wrongAnswers);
        return null;
    }
    public void BubbleSort(){
        
        Question temp;
        
        for (int i = 0; i < questions.size(); i++){
            
            for (int j = 0; j < questions.size() - 1; j++){
                
                if (questions.get(i).compareTo(questions.get(j)) < 0)
                {
                    // swap values
                    temp = questions.get(i);
                    questions.set(i, questions.get(j));
                    questions.set(j, temp);
                }
            }
        }
    }
    public static void SelectionSort(){
        Question temp;

        for (int i = 0; i < questions.size() - 1; i++){

            for (int j = i + 1; j < questions.size(); j++){
                
                if (questions.get(j).compareTo(questions.get(i)) > 0)
                {
                    temp = questions.get(j);
                    questions.set(j, questions.get(i));
                    questions.set(i, temp);
                }
            }
        }
    }

    public static void InsertionSort(){
        
        Question temp;
        
        for (int i = 1; i < questions.size(); i++){
            
            for (int j = i; j > 0; j--){
                
                if(questions.get(j).compareTo(questions.get(j-1)) < 0){
                    
                    temp = questions.get(j);
                    questions.set(j, questions.get(j-1));
                    questions.set(j-1, temp);
                }
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FNumber_jTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Operator_jComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        LNumber_jTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Answer_jTextField = new javax.swing.JTextField();
        Send_jButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Operations_jTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Bubble_jButton = new javax.swing.JButton();
        Selection_jButton = new javax.swing.JButton();
        Insertion_jButton = new javax.swing.JButton();
        Exit_jButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        DisplayList_jButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        LinkedList_jTextArea = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BinaryTree_jTextArea = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        PreDisplay_jButton = new javax.swing.JButton();
        PreSave_jButton = new javax.swing.JButton();
        InDisplay_jButton = new javax.swing.JButton();
        InSave_jButton = new javax.swing.JButton();
        PostDisplay_jButton = new javax.swing.JButton();
        PostSave_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teacher");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Teacher");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Enter your question, then click send");

        jLabel3.setText("First Number");

        jLabel4.setText("Opertor:");

        Operator_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+", "-", "/", "*" }));

        jLabel5.setText("Last Number:");

        jLabel6.setText("Answer:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FNumber_jTextField)
                            .addComponent(Operator_jComboBox, 0, 82, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Answer_jTextField)
                            .addComponent(LNumber_jTextField))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(FNumber_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Operator_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LNumber_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Answer_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Send_jButton.setText("Send");
        Send_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_jButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setEnabled(false);

        Operations_jTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Operations_jTable.setModel(operationsTableModel);
        jScrollPane1.setViewportView(Operations_jTable);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Sort:");

        Bubble_jButton.setText("1-Bubble (asc)");
        Bubble_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bubble_jButtonActionPerformed(evt);
            }
        });

        Selection_jButton.setText("2-Selection (desc)");
        Selection_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Selection_jButtonActionPerformed(evt);
            }
        });

        Insertion_jButton.setText("3-Insertion (asc)");
        Insertion_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Insertion_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(Bubble_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(Selection_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Insertion_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Bubble_jButton)
                    .addComponent(Selection_jButton)
                    .addComponent(Insertion_jButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Exit_jButton.setText("Exit");
        Exit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_jButtonActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Linked List of all incorrectly answered questions");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        DisplayList_jButton.setText("Display List");
        DisplayList_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayList_jButtonActionPerformed(evt);
            }
        });

        LinkedList_jTextArea.setColumns(20);
        LinkedList_jTextArea.setRows(5);
        LinkedList_jTextArea.setEnabled(false);
        jScrollPane2.setViewportView(LinkedList_jTextArea);

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Binary Tree (of all questions - added in the order that they were asked)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        BinaryTree_jTextArea.setColumns(20);
        BinaryTree_jTextArea.setRows(5);
        BinaryTree_jTextArea.setEnabled(false);
        jScrollPane3.setViewportView(BinaryTree_jTextArea);

        jPanel7.setBackground(new java.awt.Color(51, 51, 255));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Pre-Order");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(51, 51, 255));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("In-Order");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(51, 51, 255));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Post-Order");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PreDisplay_jButton.setText("Display");
        PreDisplay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreDisplay_jButtonActionPerformed(evt);
            }
        });

        PreSave_jButton.setText("Save");
        PreSave_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreSave_jButtonActionPerformed(evt);
            }
        });

        InDisplay_jButton.setText("Display");
        InDisplay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InDisplay_jButtonActionPerformed(evt);
            }
        });

        InSave_jButton.setText("Save");
        InSave_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InSave_jButtonActionPerformed(evt);
            }
        });

        PostDisplay_jButton.setText("Display");
        PostDisplay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostDisplay_jButtonActionPerformed(evt);
            }
        });

        PostSave_jButton.setText("Save");
        PostSave_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostSave_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Send_jButton)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Exit_jButton))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PreSave_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DisplayList_jButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(PreDisplay_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(InDisplay_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InSave_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(PostDisplay_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PostSave_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_jButton)
                    .addComponent(Exit_jButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayList_jButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PostSave_jButton)
                        .addComponent(PostDisplay_jButton))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PreDisplay_jButton)
                        .addComponent(PreSave_jButton)
                        .addComponent(InSave_jButton)
                        .addComponent(InDisplay_jButton)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Send_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_jButtonActionPerformed
        String FNumber = FNumber_jTextField.getText();
        String LNumber = LNumber_jTextField.getText();
        Object Operator = Operator_jComboBox.getSelectedItem();
        //get numbers and operator and put into one string
        String question = FNumber + " " + Operator + " " + LNumber;

        //validade input with scriptEngine
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
         try {
           //try and check if inputs are integer and not empty 
           int x = Integer.parseInt(FNumber); 
           int x2 = Integer.parseInt(LNumber); 
           System.out.println("Valid input");
           //use engine to calculate maths of string 
           int Result = ((Number)engine.eval(question)).intValue();
           Answer_jTextField.setText(Integer.toString(Result));
           question += " " + Integer.toString(Result);
            String messageOut = "";
            messageOut = question.trim();
            dataOutputStream.writeUTF(messageOut);
            //add row to operations table
            ListQuestions(FNumber, Operator.toString(), LNumber, "=", Answer_jTextField.getText());
            displayToJTable();
            Send_jButton.setEnabled(false);
           //add question to binary tree and display in textarea
           BTquestions.add(Answer_jTextField.getText() + " " + "(" + FNumber + Operator.toString() + LNumber + ")");
           BinaryTree_jTextArea.append(FNumber + " " + Operator.toString()+ " " + LNumber + " " + "=" + " " + Answer_jTextField.getText() + "\n");
         }catch(NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "ERROR: One or both numeric fields are empty or contain non-nemric values!", "SORRY - CHECK YOUR NUMBERS PLEASE!",  javax.swing.JOptionPane.ERROR_MESSAGE);
         } catch (ScriptException ex) {         
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }         

        
    }//GEN-LAST:event_Send_jButtonActionPerformed

    private void Exit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_jButtonActionPerformed
         try {
            dataOutputStream.writeUTF("exit");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Exit_jButtonActionPerformed

    private void DisplayList_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayList_jButtonActionPerformed
        LinkedList_jTextArea.setText("");
        if(wrongAnswers.size() > 0){
            LinkedList_jTextArea.append("HEAD <->" + wrongAnswers + "<-> TAIL");
        }
        else{
            LinkedList_jTextArea.setText("There are no incorrect answered questions");
        }
    }//GEN-LAST:event_DisplayList_jButtonActionPerformed

    private void PreDisplay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreDisplay_jButtonActionPerformed
        if(BTquestions.size() > 0){
            BTquestions.resetTraversalString();
            BTquestions.preOrder(BTquestions.getRoot());
            BinaryTree_jTextArea.setText("PRE-ORDER:" + BTquestions.getTraversalString());
        }
        else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_PreDisplay_jButtonActionPerformed

    private void InDisplay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InDisplay_jButtonActionPerformed
         if(BTquestions.size() > 0){
            BTquestions.resetTraversalString();
            BTquestions.inOrder(BTquestions.getRoot());
            BinaryTree_jTextArea.setText("IN-ORDER:" + BTquestions.getTraversalString());
         }
        else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_InDisplay_jButtonActionPerformed

    private void PostDisplay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostDisplay_jButtonActionPerformed
         if(BTquestions.size() > 0){
            BTquestions.resetTraversalString();
            BTquestions.postOrder(BTquestions.getRoot()); 
            BinaryTree_jTextArea.setText("POST-ORDER:" + BTquestions.getTraversalString());
        }
        else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_PostDisplay_jButtonActionPerformed

    private void PreSave_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreSave_jButtonActionPerformed
        if(BTquestions.size() > 0){
            int yesOrNo = javax.swing.JOptionPane.showConfirmDialog(null,"You are about to write " + BinaryTree_jTextArea.getText() + "\nDo you wish to continue?", "External File Write", javax.swing.JOptionPane.YES_NO_OPTION);
            if (yesOrNo == javax.swing.JOptionPane.NO_OPTION){
                System.out.println("Cancel save");
            }else{
                try(FileWriter fw = new FileWriter("preorder.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(BinaryTree_jTextArea.getText());
                    System.out.println("pre order writen to file");
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_PreSave_jButtonActionPerformed

    private void InSave_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InSave_jButtonActionPerformed
        if(BTquestions.size() > 0){
            int yesOrNo = javax.swing.JOptionPane.showConfirmDialog(null,"You are about to write " + BinaryTree_jTextArea.getText() + "\nDo you wish to continue?", "External File Write", javax.swing.JOptionPane.YES_NO_OPTION);
            if (yesOrNo == javax.swing.JOptionPane.NO_OPTION){
                System.out.println("Cancel save");
            }else{
                try(FileWriter fw = new FileWriter("inorder.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(BinaryTree_jTextArea.getText());
                    System.out.println("in order writen to file");
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_InSave_jButtonActionPerformed

    private void PostSave_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostSave_jButtonActionPerformed
        if(BTquestions.size() > 0){
            int yesOrNo = javax.swing.JOptionPane.showConfirmDialog(null,"You are about to write " + BinaryTree_jTextArea.getText() + "\nDo you wish to continue?", "External File Write", javax.swing.JOptionPane.YES_NO_OPTION);
            if (yesOrNo == javax.swing.JOptionPane.NO_OPTION){
                System.out.println("Cancel save");
            }else{
                try(FileWriter fw = new FileWriter("postorder.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(BinaryTree_jTextArea.getText());
                    System.out.println("post order writen to file");
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }else{
            BinaryTree_jTextArea.setText("There are no math questions to display");
        }
    }//GEN-LAST:event_PostSave_jButtonActionPerformed

    private void Bubble_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bubble_jButtonActionPerformed
        if(questions.size()>0){
            BubbleSort();
            displayToJTable();
        }
    }//GEN-LAST:event_Bubble_jButtonActionPerformed

    private void Selection_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Selection_jButtonActionPerformed
       if(questions.size()>0){
            SelectionSort();
            displayToJTable();
        }
    }//GEN-LAST:event_Selection_jButtonActionPerformed

    private void Insertion_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Insertion_jButtonActionPerformed
        if(questions.size()>0){
            InsertionSort();
            displayToJTable();
        }
    }//GEN-LAST:event_Insertion_jButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teacher().setVisible(true);
            }
        });
        try  
        {  
            serverSocket = new ServerSocket(1201);  
            socket = serverSocket.accept();  
            dataInputStream = new DataInputStream(socket.getInputStream());  
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String messageIn = "";
            while(! messageIn.equals("exit"))
            {  
                messageIn = dataInputStream.readUTF();
                System.out.println(messageIn);
                if(messageIn.equals("n")){
                    LinkedList_jTextArea.setText("Student answered incorrectly");
                    String Operator = Operator_jComboBox.getSelectedItem().toString();
                    ListWrongAnswers(Answer_jTextField.getText() + "(" + FNumber_jTextField.getText() + Operator + LNumber_jTextField.getText() + ")");
                }
                else{
                    LinkedList_jTextArea.setText("Student answered correctly");
                }
                Send_jButton.setEnabled(true);
                    // clear send text fields
                FNumber_jTextField.setText("");
                LNumber_jTextField.setText("");
                Answer_jTextField.setText("");
                
           
            }  
            if(messageIn.equals("exit")){
                String exceptionStr = "Server Socket Error: Connection Reset";
                JOptionPane.showMessageDialog(null, exceptionStr, "SERVER ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }  
        catch (SocketException se)
        {
            String exceptionStr = "Server Socket Error: " + se.getMessage();
            JOptionPane.showMessageDialog(null, exceptionStr, "SERVER ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e)  
        {
            String exceptionStr = "Server Error: " + e.getMessage();
            JOptionPane.showMessageDialog(null, exceptionStr, "SERVER ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField Answer_jTextField;
    private javax.swing.JTextArea BinaryTree_jTextArea;
    private javax.swing.JButton Bubble_jButton;
    private javax.swing.JButton DisplayList_jButton;
    private javax.swing.JButton Exit_jButton;
    private static javax.swing.JTextField FNumber_jTextField;
    private javax.swing.JButton InDisplay_jButton;
    private javax.swing.JButton InSave_jButton;
    private javax.swing.JButton Insertion_jButton;
    private static javax.swing.JTextField LNumber_jTextField;
    private static javax.swing.JTextArea LinkedList_jTextArea;
    private javax.swing.JTable Operations_jTable;
    private static javax.swing.JComboBox<String> Operator_jComboBox;
    private javax.swing.JButton PostDisplay_jButton;
    private javax.swing.JButton PostSave_jButton;
    private javax.swing.JButton PreDisplay_jButton;
    private javax.swing.JButton PreSave_jButton;
    private javax.swing.JButton Selection_jButton;
    private static javax.swing.JButton Send_jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
