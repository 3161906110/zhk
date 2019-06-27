package cn.edu.fjut.listenner;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import cn.edu.fjut.client.Client;
import cn.edu.fjut.frame.Frame;

/**
 * ��������
 * 
 * @author Administrator
 *
 */
public class WebFrameMouseListener implements MouseListener {
	static int len = 25;
	Frame frame;// ����������

	public WebFrameMouseListener(Frame frame) {
		this.frame = frame;
	}

	public void mousePressed(MouseEvent e) {
		if (frame.isEnd == true || frame.myColor != frame.isBlack || frame.isInit == false)
			return;
		int x = e.getX();
		int y = e.getY();
		int row = y / len;
		int col = x / len;
		if (x - col * len < len / 2) {
			if (y - row * len < len / 2) {
				col--;
				row--;
			} else {
				col--;
			}
		} else {
			if (y - row * len < len / 2) {
				row--;
			}
		}
		if (row < 0 || row >= 19 || col < 0 || col >= 19)
			return;
		if (frame.chess[row][col].getColor() == 0) {
			int color;
			if (frame.isBlack == true)
				color = -1;
			else
				color = 1;
			frame.chess[row][col].setColor(color);
			frame.isEnd = frame.isEnd(row, col, color);
			frame.write(String.valueOf(row * 100 + col));
			frame.repaint();
			frame.isBlack = !frame.isBlack;

			if (frame.isEnd == true) {
				JOptionPane.showMessageDialog(null, "��Ϸ����");
				frame.write("win");
			}
		}
	}

	// �ɿ����,�ֵ��Է�����
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
