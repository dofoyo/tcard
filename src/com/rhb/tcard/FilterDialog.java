package com.rhb.tcard;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

public class FilterDialog{
	private TcardFrame tf = null;  //父窗口对象
	
	private JDialog jDialog1 = null; //创建一个空的对话框对象
	private JButton confirmButton = null; //确定按钮
	private DefaultListModel<String> selectableModel = null;  //可选项
	private DefaultListModel<String> selectedModel = null;  //已选项
	private JList<String> selectableList = null; //可选项
	private JList<String> selectedList = null;  //已选项
	private JScrollPane selectablePane = null;
	private JScrollPane selectedPane = null;
	
	public FilterDialog(JFrame jFrame){
		
		tf = (TcardFrame) jFrame;
		
		/* 初始化jDialog1
		* 指定对话框的拥有者为jFrame,标题为"Dialog",当对话框为可视时,其他构件不能
		* 接受用户的输入(静态对话框) */
		jDialog1=new JDialog(jFrame,"筛选",true);
		
		//创建一个按钮对象,该对象被添加到对话框中
		confirmButton=new JButton("确定");
		confirmButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmButtonActionPerformed(evt);
			}
		});
		selectableModel = new DefaultListModel<String>();
		if(tf.getKeys()!=null){
			for(String s : tf.getKeys()){
				selectableModel.addElement(s);			
			}			
		}
		
		selectableList = new JList<String>(selectableModel);
		selectableList.setFixedCellWidth(230);
		selectableList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = selectableList.locationToIndex(e.getPoint());
					String tmp = (String) selectableModel.getElementAt(index);
					selectedModel.addElement(tmp);
					selectedList.setModel(selectedModel);
				}
			}
		});

		
		selectedModel = new DefaultListModel<String>();
		if(tf.getFilter()!=null && !tf.getFilter().trim().isEmpty()){
			String[] ss = tf.getFilter().split(",");
			for(String s : ss){
				selectedModel.addElement(s);			
			}			
		}		selectedList = new JList<String>(selectedModel);
		selectedList.setFixedCellWidth(230);
		selectedList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = selectedList.locationToIndex(e.getPoint());
					selectedModel.removeElementAt(index);
					selectedList.setModel(selectedModel);
				}
			}
		});
		
		
		selectablePane = new JScrollPane(selectableList);
		selectedPane = new JScrollPane(selectedList);
		
	
		//将"确定"按钮对象添加至对话框容器中
		jDialog1.getContentPane().add(confirmButton,BorderLayout.SOUTH);
		jDialog1.getContentPane().add(selectablePane,BorderLayout.WEST);
		jDialog1.getContentPane().add(selectedPane,BorderLayout.EAST);
		
		
		/* 设置对话框的初始大小 */
		jDialog1.setSize(520,300);
		
		/* 设置对话框初始显示在屏幕当中的位置 */
		jDialog1.setLocation(200,200);
		
		/* 设置对话框为可见*/
		jDialog1.setVisible(true);
	}
	

	public void confirmButtonActionPerformed(ActionEvent e) {
		// 以下语句等价于jDialog1.setVisible(false);
		/* public void dispose()
		* 释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源。
		* 即这些 Component 的资源将被破坏，它们使用的所有内存都将返回到操作系统，
		* 并将它们标记为不可显示。 */
		
		if(tf!=null && selectedModel!=null){
//			System.out.println("selectedModel.size() = " + selectedModel.size());
			String ss = "";
			for(int i=0; i<selectedModel.size(); i++){
				if(i>0){
					ss += ",";
				}
				ss += selectedModel.get(i);
			}
//			System.out.println("ss: " + ss);
			tf.setFilter(ss);
		}
		
		jDialog1.dispose();
	}

}
