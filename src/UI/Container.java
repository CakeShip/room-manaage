package UI;

import Connection.DBConnect;
import Entities.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;

public class Container extends javax.swing.JFrame {
    ResultSet rs = null;
    ResultSet rs2 = null;
    JXDatePicker orig;
    
    private JTable table;
    private Point point;
    private int getIssueId;
    private int row;
    private int col;
    
    public Container() {
        initComponents();
        init();
        initToDoTable();
        
        DateStart.setFormats("yyyy-MM-dd");
        DateEnd.setFormats("yyyy-MM-dd");
        
        orig = DateStart;
    }
    
    private void initToDoTable(){
        DefaultTableModel model = null;
        rs = DBConnect.getResultSet("SELECT todo.issueTitle, room.roomName, todo.updatedDate FROM todo INNER JOIN room ON room.roomId = todo.roomId WHERE todo.status!=1");
        model = (DefaultTableModel) toDo_table.getModel();
        model.setRowCount(0);
        {
            try {
                while(rs.next()){
                    model.addRow(new Object[]{
                        rs.getString("issueTitle"),
                        rs.getString("roomName"),
                        rs.getString("updatedDate")
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        toDo_table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                
                table =(JTable) mouseEvent.getSource();
                point = mouseEvent.getPoint();
                row = table.rowAtPoint(point);
                col = table.columnAtPoint(point);
                
                
                if (mouseEvent.getClickCount() == 2 ) {
                    String issueTitle = (String) table.getValueAt(table.getSelectedRow(), 0);
                    String roomName = (String) table.getValueAt(table.getSelectedRow(), 1);
                    rs2 = DBConnect.getResultSet("SELECT * FROM todo INNER JOIN room ON room.roomId = todo.roomId WHERE room.roomName LIKE '"+roomName+"' AND todo.issueTitle LIKE '"+issueTitle+"'");
                    
                    ResultSet rs3 = DBConnect.getResultSet("SELECT * FROM room");
                    DefaultComboBoxModel combo = (DefaultComboBoxModel) resolve_edit_room.getModel();
                    combo.removeAllElements();
                    resolve_edit_room.setModel(combo);
                    
                    try {
                        while(rs3.next()){
                            resolve_edit_room.addItem(rs3.getString("roomName"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try { 
                        if(rs2.next()){
                            issue_name.setText(rs2.getString("issueTitle"));
                            room_name.setText(rs2.getString("roomName"));
                            issue_detail.setText(rs2.getString("issueDesc"));
                            
                            resolve_edit_rid.setText(Integer.toString(rs2.getInt("roomId")));
                            resolve_edit_id.setText(Integer.toString(rs2.getInt("todoId")));
                            resolve_edit_title.setText(rs2.getString("issueTitle"));
                            resolve_edit_room.setSelectedItem("roomName");
                            resolve_edit_detail.setText(rs2.getString("issueDesc"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    resolve.setVisible(true);
                }
            }
        });
    }
    
    private void init(){
        username.setText(Storage.ad.getAdminName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resolve = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        resolve_resolve_panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        issue_name = new javax.swing.JLabel();
        room_name = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        confirm_resolve = new javax.swing.JButton();
        cancel_resolve = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        issue_detail = new javax.swing.JTextArea();
        resolve_edit_panel = new javax.swing.JPanel();
        resolve_edit_title = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        resolve_edit_detail = new javax.swing.JTextArea();
        resolve_edit_room = new javax.swing.JComboBox<>();
        resolve_edit_confirm = new javax.swing.JButton();
        resolve_edit_id = new javax.swing.JLabel();
        resolve_edit_rid = new javax.swing.JLabel();
        guest = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        issue_name1 = new javax.swing.JLabel();
        delete_guest = new javax.swing.JButton();
        pait_guest = new javax.swing.JToggleButton();
        pax_guest = new javax.swing.JLabel();
        checkout = new javax.swing.JLabel();
        roomedit = new javax.swing.JDialog();
        resolve_resolve_panel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        room_edit_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        room_edit_rate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        //room_edit_capacity = new javax.swing.JTextArea();
        OccupiedBtn = new javax.swing.JRadioButton();
        room_edit_capacity = new javax.swing.JTextField();
        cancel_resolve1 = new javax.swing.JButton();
        confirm_resolve1 = new javax.swing.JButton();
        room_edit_id = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        menu = new javax.swing.JTabbedPane();
        toDo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        toDo_table = new javax.swing.JTable();
        rooms = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rooms_table1 = new javax.swing.JTable();
        newReservation = new javax.swing.JPanel();
        room = new javax.swing.JComboBox<>();
        confirm = new javax.swing.JButton();
        pax = new javax.swing.JTextField();
        guestName = new javax.swing.JTextField();
        DateStart = new org.jdesktop.swingx.JXDatePicker();
        DateEnd = new org.jdesktop.swingx.JXDatePicker();
        ValiBtn = new javax.swing.JButton();
        reservations = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reservations_table2 = new javax.swing.JTable();
        newIssue = new javax.swing.JPanel();
        issueTitle = new javax.swing.JTextField();
        room_newIssue = new javax.swing.JComboBox<>();
        confirm_newIssue = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        issueDesc = new javax.swing.JTextPane();
        errordesc = new javax.swing.JLabel();
        errortitle = new javax.swing.JLabel();
        newRoom = new javax.swing.JPanel();
        confirm_newRoom = new javax.swing.JButton();
        roomCapacity = new javax.swing.JTextField();
        roomName = new javax.swing.JTextField();
        roomRate = new javax.swing.JTextField();
        errorName = new javax.swing.JLabel();
        errorCapacity = new javax.swing.JLabel();
        errorRate = new javax.swing.JLabel();

        resolve.setTitle("Resolve Issue");
        resolve.setMinimumSize(new java.awt.Dimension(500, 350));
        resolve.setType(java.awt.Window.Type.POPUP);

        resolve_resolve_panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        issue_name.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        issue_name.setForeground(new java.awt.Color(255, 255, 255));
        issue_name.setText("<issue_name>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(issue_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issue_name)
                .addGap(30, 30, 30))
        );

        room_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room_name.setText("<room_name>");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Mark as resolved?");

        confirm_resolve.setText("Confirm");
        confirm_resolve.setFocusable(false);
        confirm_resolve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirm_resolveMouseClicked(evt);
            }
        });

        cancel_resolve.setText("Cancel");
        cancel_resolve.setFocusable(false);
        cancel_resolve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel_resolveMouseClicked(evt);
            }
        });

        issue_detail.setEditable(false);
        issue_detail.setColumns(20);
        issue_detail.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        issue_detail.setRows(5);
        issue_detail.setText("<issue_detail>");
        issue_detail.setMaximumSize(new java.awt.Dimension(224, 95));
        issue_detail.setMinimumSize(new java.awt.Dimension(224, 95));
        issue_detail.setPreferredSize(new java.awt.Dimension(224, 95));
        jScrollPane5.setViewportView(issue_detail);

        javax.swing.GroupLayout resolve_resolve_panelLayout = new javax.swing.GroupLayout(resolve_resolve_panel);
        resolve_resolve_panel.setLayout(resolve_resolve_panelLayout);
        resolve_resolve_panelLayout.setHorizontalGroup(
            resolve_resolve_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(resolve_resolve_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resolve_resolve_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room_name, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGroup(resolve_resolve_panelLayout.createSequentialGroup()
                        .addComponent(cancel_resolve)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirm_resolve)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        resolve_resolve_panelLayout.setVerticalGroup(
            resolve_resolve_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolve_resolve_panelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resolve_resolve_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm_resolve)
                    .addComponent(cancel_resolve))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Resolve Issue", resolve_resolve_panel);

        resolve_edit_panel.setBackground(new java.awt.Color(255, 255, 255));

        resolve_edit_title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resolve_edit_title.setText("<resolve_edit_title>");
        resolve_edit_title.setToolTipText("Issue title");
        resolve_edit_title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        resolve_edit_detail.setColumns(20);
        resolve_edit_detail.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        resolve_edit_detail.setRows(5);
        resolve_edit_detail.setText("<resolve_edit_detail>");
        jScrollPane6.setViewportView(resolve_edit_detail);

        resolve_edit_room.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resolve_edit_room.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12B", "47T", "47M" }));
        resolve_edit_room.setToolTipText("Room");
        resolve_edit_room.setBorder(null);
        resolve_edit_room.setFocusable(false);
        resolve_edit_room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolve_edit_roomActionPerformed(evt);
            }
        });

        resolve_edit_confirm.setText("Confirm");
        resolve_edit_confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resolve_edit_confirmMouseClicked(evt);
            }
        });

        resolve_edit_id.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        resolve_edit_id.setEnabled(false);

        resolve_edit_rid.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        resolve_edit_rid.setEnabled(false);

        javax.swing.GroupLayout resolve_edit_panelLayout = new javax.swing.GroupLayout(resolve_edit_panel);
        resolve_edit_panel.setLayout(resolve_edit_panelLayout);
        resolve_edit_panelLayout.setHorizontalGroup(
            resolve_edit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolve_edit_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resolve_edit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resolve_edit_confirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resolve_edit_room, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(resolve_edit_title)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resolve_edit_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(resolve_edit_id)))
                .addContainerGap())
            .addGroup(resolve_edit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resolve_edit_panelLayout.createSequentialGroup()
                    .addContainerGap(475, Short.MAX_VALUE)
                    .addComponent(resolve_edit_rid)
                    .addGap(20, 20, 20)))
        );
        resolve_edit_panelLayout.setVerticalGroup(
            resolve_edit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolve_edit_panelLayout.createSequentialGroup()
                .addComponent(resolve_edit_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolve_edit_title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolve_edit_room, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolve_edit_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(resolve_edit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(resolve_edit_panelLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(resolve_edit_rid)
                    .addContainerGap(312, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Edit Issue", resolve_edit_panel);

        javax.swing.GroupLayout resolveLayout = new javax.swing.GroupLayout(resolve.getContentPane());
        resolve.getContentPane().setLayout(resolveLayout);
        resolveLayout.setHorizontalGroup(
            resolveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        resolveLayout.setVerticalGroup(
            resolveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        guest.setTitle("Guest");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));

        issue_name1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        issue_name1.setForeground(new java.awt.Color(255, 255, 255));
        issue_name1.setText("Kasey Cuyos @ 47M");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(issue_name1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issue_name1)
                .addGap(30, 30, 30))
        );

        delete_guest.setText("Delete");
        delete_guest.setFocusable(false);

        pait_guest.setText("Paid");
        pait_guest.setFocusable(false);

        pax_guest.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pax_guest.setText("2 people");

        checkout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        checkout.setText("Will checkout on July-3-2018");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pax_guest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(delete_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(pait_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_guest)
                    .addComponent(pait_guest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pax_guest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkout)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout guestLayout = new javax.swing.GroupLayout(guest.getContentPane());
        guest.getContentPane().setLayout(guestLayout);
        guestLayout.setHorizontalGroup(
            guestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        guestLayout.setVerticalGroup(
            guestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        roomedit.setTitle("Resolve Issue");
        roomedit.setMinimumSize(new java.awt.Dimension(500, 350));
        roomedit.setType(java.awt.Window.Type.POPUP);

        resolve_resolve_panel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        room_edit_name.setBackground(new java.awt.Color(0, 204, 204));
        room_edit_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room_edit_name.setForeground(new java.awt.Color(255, 255, 255));
        room_edit_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        room_edit_name.setToolTipText("Username");
        room_edit_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        room_edit_name.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Room Rate");

        room_edit_rate.setBackground(new java.awt.Color(0, 204, 204));
        room_edit_rate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room_edit_rate.setForeground(new java.awt.Color(255, 255, 255));
        room_edit_rate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        room_edit_rate.setToolTipText("Username");
        room_edit_rate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        room_edit_rate.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Room Capacity");

        room_edit_capacity.setBackground(new java.awt.Color(0, 204, 204));
        room_edit_capacity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room_edit_capacity.setForeground(new java.awt.Color(255, 255, 255));
        room_edit_capacity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        room_edit_capacity.setToolTipText("Username");
        room_edit_capacity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        room_edit_capacity.setCaretColor(new java.awt.Color(255, 255, 255));

        cancel_resolve1.setBackground(new java.awt.Color(255, 153, 0));
        cancel_resolve1.setText("Cancel");
        cancel_resolve1.setFocusable(false);
        cancel_resolve1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel_resolve1MouseClicked(evt);
            }
        });

        confirm_resolve1.setBackground(new java.awt.Color(153, 255, 153));
        confirm_resolve1.setText("Confirm");
        confirm_resolve1.setFocusable(false);
        confirm_resolve1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirm_resolve1MouseClicked(evt);
            }
        });
        confirm_resolve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_resolve1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(room_edit_name, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room_edit_rate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addComponent(room_edit_capacity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cancel_resolve1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(confirm_resolve1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(room_edit_name, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room_edit_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(room_edit_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_resolve1)
                    .addComponent(confirm_resolve1))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        room_edit_id.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        OccupiedBtn.setText("Occupied");
        OccupiedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OccupiedBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resolve_resolve_panel1Layout = new javax.swing.GroupLayout(resolve_resolve_panel1);
        resolve_resolve_panel1.setLayout(resolve_resolve_panel1Layout);
        resolve_resolve_panel1Layout.setHorizontalGroup(
            resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resolve_resolve_panel1Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(room_edit_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                        .addGroup(resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                        .addComponent(cancel_resolve1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirm_resolve1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OccupiedBtn)))
                .addContainerGap())
            .addGroup(resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                    .addGap(247, 247, 247)
                    .addComponent(room_edit_id)
                    .addContainerGap(248, Short.MAX_VALUE)))
        );
        resolve_resolve_panel1Layout.setVerticalGroup(
            resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(room_edit_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm_resolve1)
                    .addComponent(cancel_resolve1)
                    .addComponent(OccupiedBtn))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(resolve_resolve_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(resolve_resolve_panel1Layout.createSequentialGroup()
                    .addGap(127, 127, 127)
                    .addComponent(room_edit_id)
                    .addContainerGap(141, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout roomeditLayout = new javax.swing.GroupLayout(roomedit.getContentPane());
        roomedit.getContentPane().setLayout(roomeditLayout);
        roomeditLayout.setHorizontalGroup(
            roomeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomeditLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resolve_resolve_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        roomeditLayout.setVerticalGroup(
            roomeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resolve_resolve_panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blanket");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("User_name");

        logout.setBackground(new java.awt.Color(255, 255, 255));
        logout.setForeground(new java.awt.Color(0, 204, 204));
        logout.setText("Log out");
        logout.setToolTipText("");
        logout.setFocusable(false);
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout)
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addComponent(logout))
        );

        menu.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        menu.setFocusable(false);
        menu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                menuStateChanged(evt);
            }
        });

        toDo.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        toDo_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issue", "Room", "Reported"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(toDo_table);

        javax.swing.GroupLayout toDoLayout = new javax.swing.GroupLayout(toDo);
        toDo.setLayout(toDoLayout);
        toDoLayout.setHorizontalGroup(
            toDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        toDoLayout.setVerticalGroup(
            toDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        menu.addTab("To Do List", toDo);

        rooms.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        rooms_table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Status", "Guest"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(rooms_table1);

        javax.swing.GroupLayout roomsLayout = new javax.swing.GroupLayout(rooms);
        rooms.setLayout(roomsLayout);
        roomsLayout.setHorizontalGroup(
            roomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        roomsLayout.setVerticalGroup(
            roomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        menu.addTab("Rooms", rooms);

        newReservation.setBackground(new java.awt.Color(255, 255, 255));

        room.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12B", "47T", "47M" }));
        room.setToolTipText("Room");
        room.setBorder(null);
        room.setFocusable(false);
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });

        confirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirm.setText("Confirm");
        confirm.setFocusable(false);
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        pax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pax.setToolTipText("Number of People");
        pax.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        guestName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        guestName.setToolTipText("Guest Name");
        guestName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        DateStart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        DateStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateStartActionPerformed(evt);
            }
        });

        DateEnd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        ValiBtn.setText("Validate");
        ValiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newReservationLayout = new javax.swing.GroupLayout(newReservation);
        newReservation.setLayout(newReservationLayout);
        newReservationLayout.setHorizontalGroup(
            newReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newReservationLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(newReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pax)
                    .addComponent(confirm, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(guestName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, newReservationLayout.createSequentialGroup()
                        .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ValiBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(135, 135, 135))
        );
        newReservationLayout.setVerticalGroup(
            newReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newReservationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guestName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pax, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(newReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(room, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(ValiBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(DateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        menu.addTab("New Reservation", newReservation);

        reservations.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(null);

        reservations_table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "From", "To", "Guest"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(reservations_table2);

        javax.swing.GroupLayout reservationsLayout = new javax.swing.GroupLayout(reservations);
        reservations.setLayout(reservationsLayout);
        reservationsLayout.setHorizontalGroup(
            reservationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        reservationsLayout.setVerticalGroup(
            reservationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        menu.addTab("Reservations", reservations);

        newIssue.setBackground(new java.awt.Color(255, 255, 255));

        issueTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issueTitle.setToolTipText("Issue Title");
        issueTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        issueTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueTitleActionPerformed(evt);
            }
        });

        room_newIssue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        room_newIssue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12B", "47T", "47M" }));
        room_newIssue.setToolTipText("Room");
        room_newIssue.setBorder(null);
        room_newIssue.setFocusable(false);

        confirm_newIssue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirm_newIssue.setText("Confirm");
        confirm_newIssue.setFocusable(false);
        confirm_newIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_newIssueActionPerformed(evt);
            }
        });

        issueDesc.setBorder(null);
        issueDesc.setToolTipText("Issue Description");
        jScrollPane4.setViewportView(issueDesc);

        errordesc.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        errordesc.setForeground(new java.awt.Color(255, 153, 102));
        errordesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        errortitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        errortitle.setForeground(new java.awt.Color(255, 153, 102));
        errortitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout newIssueLayout = new javax.swing.GroupLayout(newIssue);
        newIssue.setLayout(newIssueLayout);
        newIssueLayout.setHorizontalGroup(
            newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newIssueLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(issueTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(room_newIssue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirm_newIssue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errordesc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errortitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        newIssueLayout.setVerticalGroup(
            newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newIssueLayout.createSequentialGroup()
                        .addComponent(issueTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newIssueLayout.createSequentialGroup()
                        .addComponent(errortitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)))
                .addGroup(newIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errordesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(room_newIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirm_newIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menu.addTab("New Issue", newIssue);

        newRoom.setBackground(new java.awt.Color(255, 255, 255));

        confirm_newRoom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirm_newRoom.setText("Confirm");
        confirm_newRoom.setFocusable(false);
        confirm_newRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirm_newRoomMouseClicked(evt);
            }
        });

        roomCapacity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        roomCapacity.setToolTipText("Room capacity");
        roomCapacity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        roomName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        roomName.setToolTipText("Room name");
        roomName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        roomRate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        roomRate.setToolTipText("Room rate");
        roomRate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        errorName.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        errorName.setForeground(new java.awt.Color(255, 153, 102));
        errorName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        errorCapacity.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        errorCapacity.setForeground(new java.awt.Color(255, 153, 102));
        errorCapacity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        errorRate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        errorRate.setForeground(new java.awt.Color(255, 153, 102));
        errorRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout newRoomLayout = new javax.swing.GroupLayout(newRoom);
        newRoom.setLayout(newRoomLayout);
        newRoomLayout.setHorizontalGroup(
            newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newRoomLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roomCapacity)
                    .addComponent(confirm_newRoom, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(roomName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomRate, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorRate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        newRoomLayout.setVerticalGroup(
            newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newRoomLayout.createSequentialGroup()
                .addGroup(newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(newRoomLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roomName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roomCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(errorCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roomRate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorRate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addComponent(confirm_newRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        menu.addTab("Add Room", newRoom);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void issueTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issueTitleActionPerformed

    private void confirm_newIssueActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        String issuetitle = issueTitle.getText();
        String issuedesc = issueDesc.getText();
        String varnewIssue = room_newIssue.getItemAt(room_newIssue.getSelectedIndex());
        int roomID = 0;
        if(!issuetitle.isEmpty() && !issuedesc.isEmpty()){
            rs2 = DBConnect.getResultSet("SELECT (room.roomId) FROM room WHERE room.roomName LIKE '"+varnewIssue+"'");
            try {
                if(rs2.next()){
                    roomID = rs2.getInt("roomId");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection con = DBConnect.getConnection();
            Date currDate = new Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(currDate); 
            c.add(Calendar.DATE, 1);
            currDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            String query = "INSERT INTO todo "
                + "(`userId`,`roomId`, `issueTitle`, `issueDesc`, `status`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, Storage.ad.getAdminID());
                stmt.setInt(2, roomID); // Change room ID LATER
                stmt.setString(3, issuetitle);
                stmt.setString(4, issuedesc);
                stmt.setInt(5, 0);
                stmt.setInt(6, Storage.ad.getAdminID());
                stmt.setInt(7, Storage.ad.getAdminID());
                stmt.setDate(8, sqlDate);
                stmt.setDate(9, sqlDate);
                issueTitle.setText("");
                issueDesc.setText("");
                errortitle.setText("");
                errordesc.setText("");
                JOptionPane.showMessageDialog(rootPane, "Successfully Added Issue");
                int insert = stmt.executeUpdate();
                System.out.println(sqlDate);
                System.out.println("Inserted "+insert+" rows.");
            }catch(SQLException ex){
                System.out.println(ex);
            }
        } else {
            if(issuetitle.isEmpty()){
                errortitle.setText("!");
            } else if (!issuetitle.isEmpty()){
                errortitle.setText("");
            }
            if(issuedesc.isEmpty()){
                errordesc.setText("!");
            } else if (!issuedesc.isEmpty()){
                errordesc.setText("");
            }
        }
    }
  
    private void confirm_newRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_newRoomMouseClicked
        String roomname = roomName.getText();
        Integer roomcapacity = 0;
        Float roomrate = (float) 0;
        
        try{
            roomcapacity = Integer.parseInt(roomCapacity.getText());
            errorCapacity.setText("");
        }catch(Exception e){
            errorCapacity.setText("!");
            System.out.println(e);
        }
        
        try{
            roomrate = Float.parseFloat(roomRate.getText());
            errorRate.setText("");
        }catch(Exception e){
            errorRate.setText("!");
            System.out.println(e);
        }
        if(!roomname.isEmpty() && roomcapacity > 0 && roomrate > 0){
            Connection con = DBConnect.getConnection();
            Date currDate = new Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(currDate); 
            c.add(Calendar.DATE, 1);
            currDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            String query = "INSERT INTO room "
                + "(`roomName`,`roomCapacity`, `roomVacancy`, `roomRate`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, roomname);
                stmt.setInt(2, roomcapacity);
                stmt.setInt(3, 1);
                stmt.setFloat(4, Float.parseFloat(roomRate.getText()));
                stmt.setInt(5, Storage.ad.getAdminID());
                stmt.setInt(6, Storage.ad.getAdminID());
                stmt.setDate(7, sqlDate);
                stmt.setDate(8, sqlDate);

                int insert = stmt.executeUpdate();
                errorName.setText("");
                roomCapacity.setText("");
                roomName.setText("");
                roomRate.setText("");
                JOptionPane.showMessageDialog(rootPane, "Successfully Added Room");
                System.out.println("Added new room named " + roomName.getText());
            }catch(SQLException ex){
                System.out.println(ex);
            }
        } else if(roomname.isEmpty()){
            errorName.setText("!");
        } else if(!roomname.isEmpty()){
            errorName.setText("!");
        }
    }//GEN-LAST:event_confirm_newRoomMouseClicked

    private void confirm_resolveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_resolveMouseClicked
        if(JOptionPane.showConfirmDialog(rootPane, "Resolve Issue?")==0){
            Connection con = DBConnect.getConnection();
            int issueID = 0, found = 0;
            Date currDate = new Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(currDate); 
            c.add(Calendar.DATE, 1);
            currDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            String varnewIssue = (String)table.getValueAt(table.getSelectedRow(), 0);
            String sql = "UPDATE todo SET status = ?, updatedBy = ?, updatedDate = ? WHERE todoId = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                rs2 = DBConnect.getResultSet("SELECT (todo.todoId) FROM todo WHERE todo.issueTitle LIKE '"+varnewIssue+"'");
                try {
                    if(rs2.next()){
                        issueID = rs2.getInt("todoId");
                        found = 1;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                }
                stmt.setInt(1, found);
                stmt.setInt(2, Storage.ad.getAdminID());
                stmt.setDate(3, sqlDate);
                stmt.setInt(4, issueID);
                stmt.executeUpdate();
                resolve.setVisible(false);
                initToDoTable();
            } catch (SQLException ex) {
                Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirm_resolveMouseClicked

    private void cancel_resolveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel_resolveMouseClicked
        resolve.dispose();
    }//GEN-LAST:event_cancel_resolveMouseClicked
  
    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String reserName = guestName.getText(),
               reserCount = pax.getText(),
               reserRoom = room.getItemAt(room.getSelectedIndex());
        int guestID = 0,
            roomID = 0;
        Connection con = DBConnect.getConnection();
        Date currDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
        java.sql.Date sqlDateStart = new java.sql.Date(DateStart.getDate().getTime());
        java.sql.Date sqlDateEnd = new java.sql.Date(DateEnd.getDate().getTime());
        
        String iquery = "INSERT INTO guest "
            + "(`guestName`, `guestPax`, `guestPaid`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(iquery);
            stmt.setString(1, reserName);
            stmt.setInt(2, Integer.parseInt(reserCount));
            stmt.setInt(3, 1);
            stmt.setInt(4, Storage.ad.getAdminID());
            stmt.setInt(5, Storage.ad.getAdminID());
            stmt.setDate(6, sqlDate);
            stmt.setDate(7, sqlDate);
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        rs = DBConnect.getResultSet("SELECT (guest.guestId) FROM guest WHERE guest.guestName LIKE '"+reserName+"'");
        rs2 = DBConnect.getResultSet("SELECT (room.roomId) FROM room WHERE room.roomName LIKE '"+reserRoom+"'");
        String query = "INSERT INTO reservations "
            + "(`guestId`, `roomId`, `createdBy`, `updatedBy`, `checkInDate`, `checkOutDate`, `createdDate`, `updatedDate`) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            if(rs.next() && rs2.next()){
                guestID = rs.getInt("guestId");
                roomID = rs2.getInt("roomId");
            }
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, guestID);
            stmt.setInt(2, roomID);
            stmt.setInt(3, Storage.ad.getAdminID());
            stmt.setInt(4, Storage.ad.getAdminID());
            stmt.setDate(5, sqlDateStart);
            stmt.setDate(6, sqlDateEnd);
            stmt.setDate(7, sqlDate);
            stmt.setDate(8, sqlDate);
            
//            ustmt.executeUpdate();
            int insert = stmt.executeUpdate();
            System.out.println("Inserted "+insert+" rows.");
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
    }//GEN-LAST:event_confirmActionPerformed

    private void resolve_edit_confirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resolve_edit_confirmMouseClicked
        Connection con = DBConnect.getConnection();
        int todoid = Integer.parseInt(resolve_edit_id.getText());
        
        String varnewIssue = resolve_edit_room.getItemAt(resolve_edit_room.getSelectedIndex());
        int roomid = 0;
        rs2 = DBConnect.getResultSet("SELECT * FROM room WHERE roomName LIKE '"+varnewIssue+"'");
        try {
            if(rs2.next()){
                roomid = rs2.getInt("roomId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
            Date currDate = new Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(currDate); 
            c.add(Calendar.DATE, 1);
            currDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
        String sql = "UPDATE todo SET issueTitle = ?, issueDesc = ?, roomId = ?, updatedBy = ?, updatedDate = ?  WHERE todoId = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            rs2 = DBConnect.getResultSet("SELECT (todo.todoId) FROM todo WHERE todo.issueTitle LIKE '"+varnewIssue+"'");
            stmt.setString(1, resolve_edit_title.getText());
            stmt.setString(2, resolve_edit_detail.getText());
            stmt.setInt(3, roomid);
            stmt.setInt(4, Storage.ad.getAdminID());
            stmt.setDate(5, sqlDate);
            stmt.setInt(6, todoid);
            stmt.executeUpdate();
            resolve.setVisible(false);
            initToDoTable();
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resolve_edit_confirmMouseClicked
  
    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        this.setVisible(false);
        Storage.ad = null;
        new Login().open();
    }//GEN-LAST:event_logoutMouseClicked

    private void resolve_edit_roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolve_edit_roomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resolve_edit_roomActionPerformed

    private void confirm_resolve1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_resolve1MouseClicked
        int roomcap = 0;
        float roomrate = 0;
        String roomname = room_edit_name.getText();
        int roomid = Integer.parseInt(room_edit_id.getText());      
        try{
            roomcap = Integer.parseInt(room_edit_capacity.getText());
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            roomrate = Float.parseFloat(room_edit_rate.getText());
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(roomcap > 0 && roomrate > 0 && !roomname.isEmpty()){
            Connection con = DBConnect.getConnection();
            Date currDate = new Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(currDate); 
            c.add(Calendar.DATE, 1);
            currDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            String sql = "UPDATE room SET roomName = ?, roomCapacity = ?, roomRate = ?, updatedBy = ?, updatedDate = ? WHERE roomId = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, roomname);
                stmt.setInt(2, roomcap);
                stmt.setFloat(3, roomrate);
                stmt.setInt(4, Storage.ad.getAdminID());
                stmt.setDate(5, sqlDate);
                stmt.setInt(6, roomid);
                stmt.executeUpdate();
                room_edit_rate.setText("");
                room_edit_name.setText("");
                room_edit_capacity.setText("");
                initToDoTable();
                JOptionPane.showMessageDialog(rootPane, "Successfully Updated Room");
                roomedit.setVisible(false);
                initToDoTable();
            } catch (SQLException ex) {
                Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_confirm_resolve1MouseClicked

    private void cancel_resolve1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel_resolve1MouseClicked
        roomedit.setVisible(false);
    }//GEN-LAST:event_cancel_resolve1MouseClicked

    private void username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username1ActionPerformed

    private void ValiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiBtnActionPerformed
        // TODO add your handling code here:
        JXMonthView m = (JXMonthView)DateStart.getMonthView();
        m.setSelectionModel(new SingleDaySelectionModel());
        String roomName = room.getSelectedItem().toString();
         ArrayList<Date> dt = new ArrayList();
        int roomID = 0;
        rs2 = DBConnect.getResultSet("SELECT (room.roomId) FROM room WHERE room.roomName LIKE '"+roomName+"'");
        try {
            if(rs2.next()){
                roomID = rs2.getInt("roomId");
            }
            rs = DBConnect.getResultSet("SELECT reservations.checkInDate, reservations.checkOutDate FROM reservations INNER JOIN room ON room.roomId = reservations.roomId WHERE room.roomId = "+roomID+"");
            while(rs.next()){
                Date currdat = rs.getDate("checkInDate");
                Calendar c = Calendar.getInstance();
                c.setTime(currdat);
                c.add(Calendar.DATE, 0);
                dt.add(c.getTime());
                int daysdiff = 0;
                long diff = rs.getDate("checkOutDate").getTime() - rs.getDate("checkInDate").getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
                daysdiff = (int) diffDays;
                for(int x = 0; x < daysdiff; x++){
                    dt.add(c.getTime());
                    c.setTime(currdat);
                    c.add(Calendar.DATE, 1);
                    currdat = c.getTime();

                }
                Date[] dat = dt.toArray(new Date[dt.size()]);
                DateStart.getMonthView().setUnselectableDates(dat);
                DateEnd.getMonthView().setUnselectableDates(dat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValiBtnActionPerformed

    private void menuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_menuStateChanged
        // TODO add your handling code here:
        
        String tabname = menu.getTitleAt(menu.getSelectedIndex());
        DBConnect db = new DBConnect();
        DefaultTableModel model = null;
        String vacancy = "";
        switch(tabname){
            case "Rooms": 
                    rs = DBConnect.getResultSet("SELECT * FROM room INNER JOIN reservations ON room.roomid = reservations.roomid INNER JOIN guest ON reservations.guestid = guest.guestid");
                    rs2 = DBConnect.getResultSet("SELECT * FROM room");
                    model = (DefaultTableModel) rooms_table1.getModel();
                    model.setRowCount(0);
                    {
                        try {
                            while(rs.next()){
                                model.addRow(new Object[]{
                                    rs.getString("roomName"),
                                    vacancy = (rs.getBoolean("roomVacancy") == true)? "Vacant" : "Occupied",
                                    rs.getString("guestName"),
                                });
                            }
                            while(rs2.next()){
                                if(rs2.getBoolean("roomVacancy")){
                                    model.addRow(new Object[]{
                                        rs2.getString("roomName"),
                                        vacancy = (rs2.getBoolean("roomVacancy") == true)? "Vacant" : "Occupied",
                                        null,
                                    });
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    rooms_table1.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent mouseEvent) {
                            JTable table =(JTable) mouseEvent.getSource();
                            Point point = mouseEvent.getPoint();
                            int row = table.rowAtPoint(point);
                            int col = table.columnAtPoint(point);

                            if (mouseEvent.getClickCount() == 2 ) {
                                String roomname = (String) table.getValueAt(table.getSelectedRow(), 0);
                                rs2 = DBConnect.getResultSet("SELECT * FROM room WHERE roomName LIKE '"+roomname+"'");
                                try {
                                    while(rs2.next()){
                                        room_edit_name.setText(rs2.getString("roomName"));
                                        room_edit_rate.setText(Float.toString(rs2.getFloat("roomRate")));
                                        room_edit_capacity.setText(Integer.toString(rs2.getInt("roomCapacity")));
                                        if(rs2.getBoolean("roomVacancy") == true){
                                            OccupiedBtn.setSelected(false);
                                        }else{
                                            OccupiedBtn.setSelected(true);
                                        }
                                        room_edit_id.setText(Integer.toString(rs2.getInt("roomId")));
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                roomedit.setVisible(true);
                            }
                        }
                    });
                    break;
            case "Reservations":
                    rs = DBConnect.getResultSet("SELECT * FROM room INNER JOIN reservations ON room.roomid = reservations.roomid INNER JOIN guest ON reservations.guestid = guest.guestid");
                    model = (DefaultTableModel) reservations_table2.getModel();
                    model.setRowCount(0);
                    {
                        try {
                            while(rs.next()){
                                model.addRow(new Object[]{
                                    rs.getString("roomName"),
                                    rs.getDate("checkInDate"),
                                    rs.getDate("checkOutDate"),
                                    rs.getString("guestName")
                                });
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    reservations_table2.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent mouseEvent) {
                            JTable table =(JTable) mouseEvent.getSource();
                            Point point = mouseEvent.getPoint();
                            int row = table.rowAtPoint(point);
                            int col = table.columnAtPoint(point);

                            if (mouseEvent.getClickCount() == 2 ) {
                            }
                        }
                    });
                    break;
            case "To Do List": 
                    initToDoTable();
                    break;
            case "New Issue":
                    rs = DBConnect.getResultSet("SELECT * FROM room");
                    DefaultComboBoxModel combo = (DefaultComboBoxModel) room_newIssue.getModel();
                    combo.removeAllElements();
                    room_newIssue.setModel(combo);
                    {
                        try {
                            while(rs.next()){
                                room_newIssue.addItem(rs.getString("roomName"));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            case "New Reservation":
                    rs = DBConnect.getResultSet("SELECT * FROM room");
                    DefaultComboBoxModel cmodel = (DefaultComboBoxModel) room.getModel();
                    cmodel.removeAllElements();
                    room.setModel(cmodel);
                    {
                        try {
                            while(rs.next()){
                                if(rs.getBoolean("roomVacancy")){
                                    room.addItem(rs.getString("roomName"));
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            default: break;
        }
        
    }//GEN-LAST:event_menuStateChanged

    private void DateStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateStartActionPerformed
        // TODO add your handling code here:
        DateEnd.getMonthView().setLowerBound(DateStart.getDate());
    }//GEN-LAST:event_DateStartActionPerformed

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomActionPerformed

    private void OccupiedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OccupiedBtnActionPerformed
        // TODO add your handling code here:
        Connection con = DBConnect.getConnection();
        int roomID = 0;
        Date currDate = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(currDate); 
        c.add(Calendar.DATE, 1);
        currDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
        PreparedStatement stmt = null;
        rs = DBConnect.getResultSet("SELECT room.roomId FROM room WHERE room.roomName LIKE '"+room_edit_name.getText()+"'");
        try {
            if(rs.next()){
                roomID = rs.getInt("roomId");
            }
            String sql = "UPDATE room SET roomVacancy = ?, updatedBy = ?, updatedDate = ?  WHERE roomId = ?";
           
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(2, Storage.ad.getAdminID());
            stmt.setDate(3, sqlDate);
            stmt.setInt(4, roomID);
            if(OccupiedBtn.isSelected()){
                stmt.setInt(1, 0);
            }else{
                stmt.setInt(1, 1);
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_OccupiedBtnActionPerformed
    private void confirm_resolve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm_resolve1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirm_resolve1ActionPerformed

    public void open() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            this.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker DateEnd;
    private org.jdesktop.swingx.JXDatePicker DateStart;
    private javax.swing.JRadioButton OccupiedBtn;
    private javax.swing.JButton ValiBtn;
    private javax.swing.JButton cancel_resolve;
    private javax.swing.JButton cancel_resolve1;
    private javax.swing.JLabel checkout;
    private javax.swing.JButton confirm;
    private javax.swing.JButton confirm_newIssue;
    private javax.swing.JButton confirm_newRoom;
    private javax.swing.JButton confirm_resolve;
    private javax.swing.JButton confirm_resolve1;
    private javax.swing.JButton delete_guest;
    private javax.swing.JLabel errorCapacity;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel errorRate;
    private javax.swing.JLabel errordesc;
    private javax.swing.JLabel errortitle;
    private javax.swing.JDialog guest;
    private javax.swing.JTextField guestName;
    private javax.swing.JTextPane issueDesc;
    private javax.swing.JTextField issueTitle;
    private javax.swing.JTextArea issue_detail;
    private javax.swing.JLabel issue_name;
    private javax.swing.JLabel issue_name1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logout;
    private javax.swing.JTabbedPane menu;
    private javax.swing.JPanel newIssue;
    private javax.swing.JPanel newReservation;
    private javax.swing.JPanel newRoom;
    private javax.swing.JToggleButton pait_guest;
    private javax.swing.JTextField pax;
    private javax.swing.JLabel pax_guest;
    private javax.swing.JPanel reservations;
    private javax.swing.JTable reservations_table2;
    private javax.swing.JDialog resolve;
    private javax.swing.JButton resolve_edit_confirm;
    private javax.swing.JTextArea resolve_edit_detail;
    private javax.swing.JLabel resolve_edit_id;
    private javax.swing.JPanel resolve_edit_panel;
    private javax.swing.JLabel resolve_edit_rid;
    private javax.swing.JComboBox<String> resolve_edit_room;
    private javax.swing.JTextField resolve_edit_title;
    private javax.swing.JPanel resolve_resolve_panel;
    private javax.swing.JPanel resolve_resolve_panel1;
    private javax.swing.JComboBox<String> room;
    private javax.swing.JTextField roomCapacity;
    private javax.swing.JTextField roomName;
    private javax.swing.JTextField roomRate;
    private javax.swing.JTextField room_edit_capacity;
    private javax.swing.JLabel room_edit_id;
    private javax.swing.JTextField room_edit_name;
    private javax.swing.JTextField room_edit_rate;
    private javax.swing.JLabel room_name;
    private javax.swing.JComboBox<String> room_newIssue;
    private javax.swing.JDialog roomedit;
    private javax.swing.JPanel rooms;
    private javax.swing.JTable rooms_table1;
    private javax.swing.JPanel toDo;
    private javax.swing.JTable toDo_table;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
