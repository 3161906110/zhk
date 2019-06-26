package cn.edu.fjut.listenner;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import cn.edu.fjut.client.Client;
import cn.edu.fjut.frame.Frame;
/**
 * 鼠标监听类
 * @author Administrator
 *
 */
public  class FrameMouseListener implements MouseListener{
	static int len=50;
    Frame frame;//棋盘做参数
    public  FrameMouseListener(Frame frame) {
    	this.frame=frame;
    }
    


	public void mousePressed(MouseEvent e) {
		if(frame.isEnd==true)
			return;
	     int x=e.getX();
	     int y=e.getY();
	     int row=y/len;
	     int col=x/len;
	     if(x-col*len<len/2) {
	       if(y-row*len<len/2) {
	    		col--;
	    		row--;
	    	}
	       else {
	    	   col--;
	       }
	     }
	     else {
	    	 if(y-row*len<len/2) {
		    		row--;
		    	}
	     }
	     if(frame.chess[row][col].getColor()==0) {
	    	 int color;
	    	 if(frame.isBlack==true)
	    		 color=-1;
	    	 else
	    		 color=1;
	    	 frame.chess[row][col].setColor(color);
	    	 frame.isEnd=frame.isEnd(row, col, color);
	     }
	     else {
	    	 frame.isBlack=!frame.isBlack;
	     }
	     frame.repaint();
	     if(frame.isEnd==true)
	     JOptionPane.showMessageDialog(null,"游戏结束");
	     
	}
	//松开鼠标,轮到对方下子
	public void mouseReleased(MouseEvent e) {
		frame.isBlack=!frame.isBlack;
	}
	
    public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
