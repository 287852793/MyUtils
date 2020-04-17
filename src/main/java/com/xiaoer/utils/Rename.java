package com.xiaoer.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Rename extends JFrame {
	public Rename() {
		init();
	}

	public void init() {
		setTitle("文件批量重命名工具");
		setContentPane(createContentPane());
		setLayout(null);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension screenSize = tl.getScreenSize();
		int startX = (int)(screenSize.getWidth() / 2 - 210);
		int startY = (int)(screenSize.getHeight() / 2 - 150);
		setBounds(startX, startY, 450, 375);
		setResizable(false);
	}

	public JPanel createContentPane() {
		final JPanel pane = new JPanel();
		pane.setBounds(10, 10, 450, 260);
		pane.setBorder(new EmptyBorder(6, 6, 6, 6));

		/**
		 * 文件列表
		 */
		JLabel cll = new JLabel("文件列表:");
		cll.setBounds(30, 135, 100, 25);
		pane.add(cll);
		final JTextArea ta = new JTextArea();
		JScrollPane js = new JScrollPane(ta);
		js.setBounds(95, 135, 344, 140);
		pane.add(js);
		js.setVisible(true);

		final JLabel ldir = new JLabel("请选择文件夹:");
		ldir.setBounds(5, 15, 100, 25);
		pane.add(ldir);

		/**
		 * 文件夹路径选择
		 */
		final JButton choose = new JButton("浏览");
		choose.setBounds(380, 15, 60, 25);
		final JTextField dir = new JTextField();
		dir.setBounds(95, 15, 280, 25);

		final JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int returnValue = chooser.showOpenDialog(pane);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					dir.setText(file.getAbsolutePath());

					/**
					 * 列出文件
					 */
					File root = new File(file.getAbsolutePath());
					if (root.isDirectory()) {
						File[] files = root.listFiles();
						String names = "";
						for (File f : files) {
							names += f.getName() + "\n\r";
						}
						ta.setText(names);
					}
				} else {
					return;
				}

			}
		});
		pane.add(choose);
		pane.add(dir);

		/**
		 * 修改位置
		 * 
		 */

		JLabel cdl = new JLabel("修改位置:");
		cdl.setBounds(30, 105, 100, 25);
		pane.add(cdl);
		final JTextField t2 = new JTextField();
		t2.setBounds(95, 105, 345, 25);
		pane.add(t2);
		t2.setEnabled(false);

		/**
		 * 修改方式
		 */
		JLabel cbl = new JLabel("修改方式:");
		cbl.setBounds(30, 45, 100, 25);
		pane.add(cbl);
		final JComboBox cb = new JComboBox(
				new String[] { "删除匹配内容", "前端新增内容", "末端新增内容", "中间新增内容(在修改位置之后新增)" });
		cb.setBounds(95, 45, 345, 25);
		pane.add(cb);
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = (String)cb.getSelectedItem();
				if (s.indexOf("中间新增") > -1) {
					t2.setEnabled(true);
				} else {
					t2.setEnabled(false);
				}
			}
		});

		/**
		 * 修改内容
		 */
		JLabel ctl = new JLabel("修改内容:");
		ctl.setBounds(30, 75, 100, 25);
		pane.add(ctl);
		final JTextField t1 = new JTextField();
		t1.setBounds(95, 75, 345, 25);
		pane.add(t1);

		/**
		 * 执行
		 */
		JButton batchbtn = new JButton("执行");
		batchbtn.setBounds(95, 290, 120, 25);
		batchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s = (String)cb.getSelectedItem();
					String r = dir.getText();
					File root = new File(r);
					if (root.isDirectory()) {
						File[] files = root.listFiles();
						String names = "";
						String rep = t1.getText();

						if (s.indexOf("删除") > -1) {

							for (File f : files) {
								String oldName = f.getName();
								if (f.isFile()) {
									File file = new File(root + "/" + oldName.replace(rep, ""));
									f.renameTo(file);
								}
							}
						} else if (s.indexOf("前端新增") > -1) {
							for (File f : files) {
								String oldName = f.getName();
								if (f.isFile()) {
									File file = new File(root + "/" + rep + oldName);
									f.renameTo(file);
								}
							}
						} else if (s.indexOf("末端新增") > -1) {
							for (File f : files) {
								String oldName = f.getName();
								if (f.isFile()) {
									int idx = oldName.lastIndexOf("\\.");
									String newName = oldName.substring(0, idx) + rep
											+ oldName.substring(idx, oldName.length());
									File file = new File(root + "/" + newName);
									f.renameTo(file);
								}
							}
						} else if (s.indexOf("中间新增") > -1) {
							String idx = t2.getText();
							for (File f : files) {
								String oldName = f.getName();

								if (f.isFile() && oldName.indexOf(idx) > -1) {

									String newName = oldName.substring(0, oldName.indexOf(idx)
											+ idx.length())
											+ rep
											+ oldName.subSequence(oldName.indexOf(idx) + idx.length(),
											oldName.length());
									File file = new File(root + "/" + newName);
									f.renameTo(file);
								}
							}
						}
					}

					/**
					 * 刷新列表
					 */
					/**
					 * 列出文件
					 */
					if (root.isDirectory()) {
						File[] files = root.listFiles();
						String names = "";
						for (File f : files) {
							names += f.getName() + "\n\r";
						}
						ta.setText(names);
					}
				} catch (Exception e2) {
					ta.setText(e2.getMessage());
					e2.printStackTrace();
				}

			}
		});
		pane.add(batchbtn);

		/**
		 * 退出
		 */
		JButton quit = new JButton("退出");
		quit.setBounds(250, 290, 120, 25);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pane.add(quit);

		/**
		 * 退出
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		return pane;
	}

	// public static void main(String[] args) {
	// String root = "C:/Users/Administrator/Desktop";
	// File file1 = new File(root + "/" + "新建文本文档.txt");
	// File file2 = new File(root + "/" + "新建文本文档1234.txt");
	// file1.renameTo(file2);
	// }

	public static void main(String[] args) {
		new Rename().setVisible(true);
	}
}
