package cn.edu.fjut.chess;

/**
 * 棋子类
 * 
 * @author Administrator
 *
 */
public class Chess {
	static int len = 50;
	int x, y;// 坐标

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	int color;// 颜色,-1,0,1

	public Chess(int row, int col) {
		x = (col + 1) * len;
		y = (row + 1) * len;
		color = 0;
	}
}
