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
	private TcardFrame tf = null;  //�����ڶ���
	
	private JDialog jDialog1 = null; //����һ���յĶԻ������
	private JButton confirmButton = null; //ȷ����ť
	private DefaultListModel<String> selectableModel = null;  //��ѡ��
	private DefaultListModel<String> selectedModel = null;  //��ѡ��
	private JList<String> selectableList = null; //��ѡ��
	private JList<String> selectedList = null;  //��ѡ��
	private JScrollPane selectablePane = null;
	private JScrollPane selectedPane = null;
	
	public FilterDialog(JFrame jFrame){
		
		tf = (TcardFrame) jFrame;
		
		/* ��ʼ��jDialog1
		* ָ���Ի����ӵ����ΪjFrame,����Ϊ"Dialog",���Ի���Ϊ����ʱ,������������
		* �����û�������(��̬�Ի���) */
		jDialog1=new JDialog(jFrame,"ɸѡ",true);
		
		//����һ����ť����,�ö�����ӵ��Ի�����
		confirmButton=new JButton("ȷ��");
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
		
	
		//��"ȷ��"��ť����������Ի���������
		jDialog1.getContentPane().add(confirmButton,BorderLayout.SOUTH);
		jDialog1.getContentPane().add(selectablePane,BorderLayout.WEST);
		jDialog1.getContentPane().add(selectedPane,BorderLayout.EAST);
		
		
		/* ���öԻ���ĳ�ʼ��С */
		jDialog1.setSize(520,300);
		
		/* ���öԻ����ʼ��ʾ����Ļ���е�λ�� */
		jDialog1.setLocation(200,200);
		
		/* ���öԻ���Ϊ�ɼ�*/
		jDialog1.setVisible(true);
	}
	

	public void confirmButtonActionPerformed(ActionEvent e) {
		// �������ȼ���jDialog1.setVisible(false);
		/* public void dispose()
		* �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
		* ����Щ Component ����Դ�����ƻ�������ʹ�õ������ڴ涼�����ص�����ϵͳ��
		* �������Ǳ��Ϊ������ʾ�� */
		
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
