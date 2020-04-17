package com.xiaoer.utils;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TransCharSet extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7917634520506684242L;

	public TransCharSet() {
		init();
	}

	public void init() {
		setTitle("乱码解析");
		setContentPane(createContentPane());
		setLayout(null);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension screenSize = tl.getScreenSize();
		int startX = (int)(screenSize.getWidth() / 2 - 190);
		int startY = (int)(screenSize.getHeight() / 2 - 150);
		setBounds(startX, startY, 400, 375);
		setResizable(false);
	}

	public JPanel createContentPane() {
		final JPanel pane = new JPanel();
		pane.setBounds(10, 10, 400, 260);
		pane.setBorder(new EmptyBorder(6, 6, 6, 6));

		JLabel cbl = new JLabel("源码编码:");
		cbl.setBounds(3, 5, 100, 25);
		pane.add(cbl);
		final JComboBox cb = new JComboBox(new String[] { "gbk", "utf-8", "unicode", "ISO-8859-1",
				"gb2312", "utf-16", "US-ASCII", "UTF-16BE", "UTF-16LE" });
		cb.setBounds(70, 5, 230, 25);
		pane.add(cb);

		// 第一行
		JLabel l1 = new JLabel("源码:");
		l1.setBounds(3, 35, 100, 25);
		pane.add(l1);
		final JTextField t1 = new JTextField();
		t1.setBounds(70, 35, 230, 25);
		pane.add(t1);
		JButton b1 = new JButton("粘贴");
		b1.setBounds(310, 35, 70, 25);
		pane.add(b1);

		// 第二行
		JLabel l2 = new JLabel("utf-8:");
		l2.setBounds(3, 65, 100, 25);
		pane.add(l2);
		final JTextField t2 = new JTextField();
		t2.setBounds(70, 65, 230, 25);
		pane.add(t2);
		JButton b2 = new JButton("复制");
		b2.setBounds(310, 65, 70, 25);
		pane.add(b2);

		// 第三行
		JLabel l3 = new JLabel("gbk:");
		l3.setBounds(3, 95, 100, 25);
		pane.add(l3);
		final JTextField t3 = new JTextField();
		t3.setBounds(70, 95, 230, 25);
		pane.add(t3);
		JButton b3 = new JButton("复制");
		b3.setBounds(310, 95, 70, 25);
		pane.add(b3);

		// 第四行
		JLabel l4 = new JLabel("ISO-8859-1:");
		l4.setBounds(3, 125, 100, 25);
		pane.add(l4);
		final JTextField t4 = new JTextField();
		t4.setBounds(70, 125, 230, 25);
		pane.add(t4);
		JButton b4 = new JButton("复制");
		b4.setBounds(310, 125, 70, 25);
		pane.add(b4);

		// 第五行
		JLabel l5 = new JLabel("US-ASCII:");
		l5.setBounds(3, 155, 100, 25);
		pane.add(l5);
		final JTextField t5 = new JTextField();
		t5.setBounds(70, 155, 230, 25);
		pane.add(t5);
		JButton b5 = new JButton("复制");
		b5.setBounds(310, 155, 70, 25);
		pane.add(b5);

		// 第六行
		JLabel l6 = new JLabel("UTF-16:");
		l6.setBounds(3, 185, 100, 25);
		pane.add(l6);
		final JTextField t6 = new JTextField();
		t6.setBounds(70, 185, 230, 25);
		pane.add(t6);
		JButton b6 = new JButton("复制");
		b6.setBounds(310, 185, 70, 25);
		pane.add(b6);

		// 第七行
		JLabel l7 = new JLabel("UTF-16BE:");
		l7.setBounds(3, 215, 100, 25);
		pane.add(l7);
		final JTextField t7 = new JTextField();
		t7.setBounds(70, 215, 230, 25);
		pane.add(t7);
		JButton b7 = new JButton("复制");
		b7.setBounds(310, 215, 70, 25);
		pane.add(b7);

		// 第八行
		JLabel l8 = new JLabel("UTF-16LE:");
		l8.setBounds(3, 245, 100, 25);
		pane.add(l8);
		final JTextField t8 = new JTextField();
		t8.setBounds(70, 245, 230, 25);
		pane.add(t8);
		JButton b8 = new JButton("复制");
		b8.setBounds(310, 245, 70, 25);
		pane.add(b8);

		// 第八行
		JLabel l9 = new JLabel("Unicode:");
		l9.setBounds(3, 275, 100, 25);
		pane.add(l9);
		final JTextField t9 = new JTextField();
		t9.setBounds(70, 275, 230, 25);
		pane.add(t9);
		JButton b9 = new JButton("复制");
		b9.setBounds(310, 275, 70, 25);
		pane.add(b9);

		// 粘贴
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					t1.setText((String)Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(
							DataFlavor.stringFlavor));
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedFlavorException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 复制
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t2.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t3.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t4.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t5.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t6.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t7.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t8.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 复制
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stsel = new StringSelection(t9.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
			}
		});

		// 置顶
		final JButton topbtn = new JButton("置顶");
		topbtn.setBounds(5, 308, 70, 25);
		topbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrames()[0].setAlwaysOnTop(!isAlwaysOnTop());
				if (isAlwaysOnTop()) {
					topbtn.setText("取消");
				} else {
					topbtn.setText("置顶");
				}
			}
		});
		pane.add(topbtn);

		// 转码
		JButton transbtn = new JButton("转码");
		transbtn.setBounds(80, 308, 70, 25);
		transbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 获取源码编码
					String coding = (String)cb.getSelectedItem();
					
					String code = t1.getText();
					String utf_8 = new String(code.getBytes(coding), "utf-8");
					t2.setText(utf_8);
					String gbk = new String(code.getBytes(coding), "gbk");
					t3.setText(gbk);
					String iso_8859_1 = new String(code.getBytes(coding), "ISO-8859-1");
					t4.setText(iso_8859_1);
					String us_ascii = new String(code.getBytes(coding), "US-ASCII");
					t5.setText(us_ascii);
					String utf_16 = new String(code.getBytes(coding), "UTF-16");
					t6.setText(utf_16);
					String utf_16be = new String(code.getBytes(coding), "UTF-16BE");
					t7.setText(utf_16be);
					String utf_16le = new String(code.getBytes(coding), "UTF-16LE");
					t8.setText(utf_16le);
					String unicode = new String(code.getBytes(coding), "unicode");
					t9.setText(unicode);
				} catch (Exception e2) {
					t1.setText(e2.getMessage());
				}

			}
		});
		pane.add(transbtn);

		// 编码
		JButton encodebtn = new JButton("编码");
		encodebtn.setBounds(155, 308, 70, 25);
		encodebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String code = t1.getText();
					String utf_8 = URLEncoder.encode(code, "utf-8");
					t2.setText(utf_8);
					String gbk = URLEncoder.encode(code, "gbk");
					t3.setText(gbk);
					String iso_8859_1 = URLEncoder.encode(code, "ISO-8859-1");
					t4.setText(iso_8859_1);
					String us_ascii = URLEncoder.encode(code, "US-ASCII");
					t5.setText(us_ascii);
					String utf_16 = URLEncoder.encode(code, "UTF-16");
					t6.setText(utf_16);
					String utf_16be = URLEncoder.encode(code, "UTF-16BE");
					t7.setText(utf_16be);
					String utf_16le = URLEncoder.encode(code, "UTF-16LE");
					t8.setText(utf_16le);
					String unicdoe = UnicodeTool.encodeUnicode(code);
					t9.setText(unicdoe);
				} catch (Exception e2) {
					t1.setText(e2.getMessage());
				}

			}
		});
		pane.add(encodebtn);

		// 解码
		JButton decodebtn = new JButton("解码");
		decodebtn.setBounds(230, 308, 70, 25);
		decodebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String code = t1.getText();
					String utf_8 = URLDecoder.decode(code, "utf-8");
					t2.setText(utf_8);
					String gbk = URLDecoder.decode(code, "gbk");
					t3.setText(gbk);
					String iso_8859_1 = URLDecoder.decode(code, "ISO-8859-1");
					t4.setText(iso_8859_1);
					String us_ascii = URLDecoder.decode(code, "US-ASCII");
					t5.setText(us_ascii);
					String utf_16 = URLDecoder.decode(code, "UTF-16");
					t6.setText(utf_16);
					String utf_16be = URLDecoder.decode(code, "UTF-16BE");
					t7.setText(utf_16be);
					String utf_16le = URLDecoder.decode(code, "UTF-16LE");
					t8.setText(utf_16le);
					String unicode = UnicodeTool.decodeUnicode(code);
					t9.setText(unicode);
				} catch (Exception e2) {
					t1.setText(e2.getMessage());
				}

			}
		});
		pane.add(decodebtn);

		// 退出
		JButton quit = new JButton("退出");
		quit.setBounds(310, 308, 70, 25);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		pane.add(quit);

		// 退出
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// ----end----//
		return pane;
	}

	public static void main(String[] args) {
		new TransCharSet().setVisible(true);
	}

}
