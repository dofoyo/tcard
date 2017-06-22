package com.rhb.tcard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

public class TcardFrame extends javax.swing.JFrame {

	private static Logger logger = Logger.getLogger(TcardFrame.class);  

	private static final long serialVersionUID = 2901095078639975990L;

	private javax.swing.JButton openButton = new javax.swing.JButton();
	private javax.swing.JButton previousButton = new javax.swing.JButton();
	private javax.swing.JButton nextButton = new javax.swing.JButton();
	private javax.swing.JButton answerButton = new javax.swing.JButton();
	private javax.swing.JButton randomButton = new javax.swing.JButton();
	private javax.swing.JButton filterButton = new javax.swing.JButton();
	
	private javax.swing.JLabel questionImage = new javax.swing.JLabel();
	private javax.swing.JLabel answerLabel = new javax.swing.JLabel();
	private javax.swing.JLabel blankLabel = new javax.swing.JLabel();
	
	private javax.swing.JTextField answerTextField = new javax.swing.JTextField();

	private javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
	private javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();
	private javax.swing.JSeparator jSeparator3 = new javax.swing.JSeparator();
	
	private javax.swing.JToolBar jToolBar1 = new javax.swing.JToolBar();

	private javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
	
	private QuestionList questions = null;
	
	private String filter = "";
	
	public TcardFrame() {
		initComponents();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen.width, screen.height - 30);

	}
	
	public Set<String> getKeys(){
		if(this.questions!=null){
			return this.questions.getKeys();
		}else{
			return null;
		}
	}
	
	public String getFilter(){
		return this.filter;
	}
	
	public void setFilter(String filter){
		this.filter = filter;
//		System.out.println("tcardFrame.filter = " + this.filter);
		if(this.questions != null){
			questions.setFilter(filter);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		
		jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		openButton.setText("\u6253\u5f00");
		openButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(openButton);

		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator1.setMaximumSize(new java.awt.Dimension(10, 32767));
		jSeparator1.setMinimumSize(new java.awt.Dimension(10, 0));
		jToolBar1.add(jSeparator1);

		previousButton.setText("\u4e0a\u4e00\u9898");
		previousButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previousButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(previousButton);

		nextButton.setText("\u4e0b\u4e00\u9898");
		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(nextButton);

		  jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		  jSeparator2.setMaximumSize(new java.awt.Dimension(10, 32767));
		  jSeparator1.setMinimumSize(new java.awt.Dimension(10, 0));

		  jToolBar1.add(jSeparator2);

		  
		  randomButton.setText("\u968f\u673a"); 
		  randomButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					randomButtonActionPerformed(evt);
				}
			});
		  jToolBar1.add(randomButton);
		  
		  jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
		  jSeparator3.setMaximumSize(new java.awt.Dimension(10, 32767));
		  jToolBar1.add(jSeparator3);
		  
		  filterButton.setText("\u7b5b\u9009"); 
		  filterButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					filterButtonActionPerformed(evt);
				}
			});
		  jToolBar1.add(filterButton);		  
		  
		answerLabel.setText("             \u7b54\u6848\uff1a");
		jToolBar1.add(answerLabel);
		jToolBar1.add(answerTextField);

		answerButton.setText("\u63d0\u4ea4");
		answerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				answerButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(answerButton);
		
		blankLabel.setText("                                                                                                         ");
		jToolBar1.add(blankLabel);

		questionImage.setBackground(Color.green);
		questionImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		questionImage.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		questionImage.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		questionImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		questionImage.setVerticalTextPosition(javax.swing.SwingConstants.TOP);


		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						704, Short.MAX_VALUE)
				.add(questionImage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						704, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup()
						.add(jToolBar1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED)
						.add(questionImage,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								410, Short.MAX_VALUE)));
		pack();
	}

	private void answerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String text = this.answerTextField.getText().toLowerCase().trim();
		logger.info("Answer: " + text);

		Question question = questions.current();
		if(question.isRight(text)){
			logger.info("isRight: YES!");
			JOptionPane.showMessageDialog(null, "\u7b54\u6848\u6b63\u786e\uff01\u4f60\u771f\u68d2\uff01", "",JOptionPane.INFORMATION_MESSAGE);
			showQuestion(questions.next());
		}else{
			logger.info("isRight: NO! the correct answer is " + question.getAnswer());
			JOptionPane.showMessageDialog(null, "\u7b54\u6848\u9519\u8bef\uff01", "",JOptionPane.ERROR_MESSAGE);
		}
		
		this.answerTextField.requestFocus();
	}
	
	private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(questions!=null)
		showQuestion(questions.random());
	}
	
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(questions!=null)
		showQuestion(questions.next());
	}

	private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(questions!=null)
		showQuestion(questions.previous());
	}

	private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
		openQuestion();
		if(questions!=null)
		showQuestion(questions.current());
	}

	private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FilterDialog fd = new FilterDialog(this);
	}
	
	private void openQuestion() {
		fileChooser.setFileFilter(new PNGFiliter());
		fileChooser.showOpenDialog(this);
		
		if(fileChooser.getSelectedFile()!=null){
			String imageName = fileChooser.getSelectedFile().getName();
			
			File[] filearray = fileChooser.getCurrentDirectory().listFiles();
			int n = filearray.length;
			String[] filepaths = new String[n];
			for (int i = 0; i < n; i++) {
				filepaths[i] = filearray[i].getPath();
			}
			questions = new QuestionList(filepaths,imageName,this.filter);
		}
	}
	
	private void showQuestion(Question question){
		if(question != null){
			ImageIcon imageicon = new ImageIcon(question.getImagePath());
			this.setTitle(question.getImagePath());
			
			logger.info("Question: " + question.getImagePath());
			questionImage.setIcon(imageicon);
			this.answerTextField.setText("");
		}else{
			questionImage.setIcon(null);
		}
	}

	private class PNGFiliter extends FileFilter {
		boolean flag;

		public boolean accept(File file) {
			if (file.getName().toLowerCase().endsWith(".png")) {
				flag = true;
			} else if (file.isDirectory()) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		}

		public String getDescription() {
			return "PNG нд╪Ч";
		}
	}
}
