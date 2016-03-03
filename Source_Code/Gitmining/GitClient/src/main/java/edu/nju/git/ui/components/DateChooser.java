package edu.nju.git.ui.components;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;

/**
 * Date chooser panel
 * @author cuihao
 * @date 2015-12-08 21:26:30
 */
public class DateChooser extends JPanel {

	private static final long serialVersionUID = 4529266044762990227L;

	private Date initDate;
	private Calendar now = Calendar.getInstance();
	/**
	 * Calendar be selected
	 */
	private Calendar select;
	/**
	 * main panel of date chooser
	 */
	private JPanel monthPanel;
	/**
	 * four panels consists of the chooser
	 */
	private Header header;
	private WeekPanel week;
	private DatePanel date;
	private bottom bottom;
	
	private Font font = new Font("微软雅黑", Font.PLAIN, 20);
	private final LabelManager lm = new LabelManager();
	private JLabel showDate;
	private SimpleDateFormat sdf;
	private boolean isShow = false;
	private Popup pop;

	/**
	 * Creates a new instance of DateChooser
	 */
	public DateChooser() {
		this(new Date());
	}

	public DateChooser(Date date) {
		this(date, "yyyy年MM月dd日");
	}

	public DateChooser(String format) {
		this(new Date(), format);
	}

	public DateChooser(Date date, String format) {
		initDate = date;
		sdf = new SimpleDateFormat(format);
		select = Calendar.getInstance();
		select.setTime(initDate);
		setOpaque(false);
		setFont(font);
		initPanel();
		initLabel();
	}
	
	/**
	 * main constructor
	 * @param element
	 * @param controller
	 */
	public DateChooser(Element element, UIController controller) {
		this("yyyy-MM-dd");
		setBounds(Integer.parseInt(element.attributeValue("x")), Integer.parseInt(element.attributeValue("y")),
				Integer.parseInt(element.attributeValue("w")), Integer.parseInt(element.attributeValue("h")));
	}

	/**
	 * whether allow user to select
	 */
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		showDate.setEnabled(b);
	}

	/**
	 * get date
	 */
	@Deprecated
	public Date getDate() {
		return select.getTime();
	}
	/**
	 * get Calendar which user chooses
	 * @return {@link Calendar}
	 */
	public Calendar getCalendar() {
		return select;
	}

	/**
	 * initialize according to date
	 */
	private void initPanel() {
		monthPanel = new JPanel(new BorderLayout());
		monthPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel up = new JPanel(new BorderLayout());
		up.add(header = new Header(), BorderLayout.NORTH);
		week = new WeekPanel();
		up.add(week, BorderLayout.CENTER);
		date = new DatePanel();
		monthPanel.add(date, BorderLayout.CENTER);
		monthPanel.add(up, BorderLayout.NORTH);
		bottom = new bottom();
		monthPanel.add(bottom, BorderLayout.SOUTH);
		this.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {

			}

			public void ancestorRemoved(AncestorEvent event) {

			}

			/**
			 * hide if moved
			 */
			public void ancestorMoved(AncestorEvent event) {
				hidePanel();
			}
		});
	}

	/**
	 * initialize label
	 */
	private void initLabel() {
		showDate = new JLabel(sdf.format(initDate));
		showDate.setFont(font);
		showDate.setRequestFocusEnabled(true);
		showDate.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				showDate.requestFocusInWindow();
			}
		});
		this.setBackground(Color.WHITE);
		this.add(showDate, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(90, 35));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		showDate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
					showDate.setForeground(Color.RED);
				}
			}

			public void mouseExited(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					showDate.setForeground(Color.BLACK);
				}
			}

			public void mousePressed(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setForeground(Color.CYAN);
					if (isShow) {
						hidePanel();
					} else {
						showPanel(showDate);
					}
				}
			}

			public void mouseReleased(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setForeground(Color.BLACK);
				}
			}
		});
		showDate.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				hidePanel();
			}

			public void focusGained(FocusEvent e) {

			}
		});
	}

	private void refresh() {
		header.updateDate();
		date.updateDate();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void commit() {
		//System.out.println("选中的日期是：" + sdf.format(select.getTime()));
		showDate.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showDate.setText(sdf.format(select.getTime()));
		hidePanel();
	}

	/**
	 * hide chooser panel
	 */
	private void hidePanel() {
		if (pop != null) {
			isShow = false;
			pop.hide();
			pop = null;
		}
	}

	/**
	 * show date chooser panel
	 * @param owner: {@link Component}
	 */
	private void showPanel(Component owner) {
		if (pop != null) {
			pop.hide();
		}
		Point show = new Point(0, showDate.getHeight());
		SwingUtilities.convertPointToScreen(show, showDate);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = show.x;
		int y = show.y;
		if (x < 0) {
			x = 0;
		}
		if (x > size.width - 380) {
			x = size.width - 380;
		}
		if (y < size.height - 170) {
		} else {
			y -= 188;
		}
		pop = PopupFactory.getSharedInstance().getPopup(owner, monthPanel, x, y);
		pop.show();
		isShow = true;
	}

	/**
	 * Month and year chooser panel
	 */
	private class Header extends JPanel {

		private static final long serialVersionUID = 1198203955759751635L;
		JLabel yearleft, yearright, monthleft, monthright, center, centercontainer;

		public Header() {
			super(new BorderLayout());
			this.setBackground(Color.LIGHT_GRAY);
			initJP1();
		}

		private void initJP1() {
			yearleft = new JLabel("  <<", JLabel.CENTER);
			yearleft.setToolTipText("上一年");
			yearright = new JLabel(">>  ", JLabel.CENTER);
			yearright.setToolTipText("下一年");
			yearleft.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
			yearright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

			monthleft = new JLabel("  <", JLabel.RIGHT);
			monthleft.setToolTipText("上一月");
			monthright = new JLabel(">  ", JLabel.LEFT);
			monthright.setToolTipText("下一月");
			monthleft.setBorder(BorderFactory.createEmptyBorder(2, 30, 0, 0));
			monthright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 30));

			centercontainer = new JLabel("", JLabel.CENTER);
			centercontainer.setLayout(new BorderLayout());
			center = new JLabel("", JLabel.CENTER);

			centercontainer.add(monthleft, BorderLayout.WEST);
			centercontainer.add(center, BorderLayout.CENTER);
			centercontainer.add(monthright, BorderLayout.EAST);

			this.add(yearleft, BorderLayout.WEST);
			this.add(centercontainer, BorderLayout.CENTER);
			this.add(yearright, BorderLayout.EAST);
			this.setPreferredSize(new Dimension(380, 25));

			updateDate();

			yearleft.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					yearleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
					yearleft.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					yearleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					yearleft.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.YEAR, -1);
					yearleft.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					yearleft.setForeground(Color.BLACK);
				}
			});
			yearright.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					yearright.setCursor(new Cursor(Cursor.HAND_CURSOR));
					yearright.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					yearright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					yearright.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.YEAR, 1);
					yearright.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					yearright.setForeground(Color.BLACK);
				}
			});
			monthleft.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					monthleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
					monthleft.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					monthleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					monthleft.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, -1);
					monthleft.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					monthleft.setForeground(Color.BLACK);
				}
			});
			monthright.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					monthright.setCursor(new Cursor(Cursor.HAND_CURSOR));
					monthright.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					monthright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					monthright.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, 1);
					monthright.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					monthright.setForeground(Color.BLACK);
				}
			});
		}

		private void updateDate() {
			center.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
		}
	}

	/**
	 * show week
	 * 
	 */
	private class WeekPanel extends JPanel {

		private static final long serialVersionUID = 7610750394423589480L;

		public WeekPanel() {
			this.setPreferredSize(new Dimension(380, 40));
		}

		protected void paintComponent(Graphics g) {
			g.setFont(new Font("微软雅黑",Font.PLAIN,16));
			g.drawString("星期日 星期一 星期二 星期三 星期四 星期五 星期六", 5, 24);
			g.drawLine(0, 30, getWidth(), 30);
		}
	}

	/**
	 * show date
	 * @author cuihao
	 *
	 */
	private class DatePanel extends JPanel {

		private static final long serialVersionUID = -7154857273073556762L;

		public DatePanel() {
			super(new GridLayout(6, 7));
			this.setPreferredSize(new Dimension(380, 160));
			initJP3();
		}

		private void initJP3() {
			updateDate();
		}

		public void updateDate() {
			this.removeAll();
			lm.clear();
			Date temp = select.getTime();
			Calendar select = Calendar.getInstance();
			select.setTime(temp);
			select.set(Calendar.DAY_OF_MONTH, 1);
			int index = select.get(Calendar.DAY_OF_WEEK);
			int sum = (index == 1 ? 8 : index);
			select.add(Calendar.DAY_OF_MONTH, 0 - sum);
			for (int i = 0; i < 42; i++) {
				select.add(Calendar.DAY_OF_MONTH, 1);
				lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH),
						select.get(Calendar.DAY_OF_MONTH)));
			}
			for (MyLabel my : lm.getLabels()) {
				this.add(my);
			}
			select.setTime(temp);
		}
	}

	private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {

		private static final long serialVersionUID = 1962978395162795706L;
		private int year, month, day;
		private boolean isSelected;

		public MyLabel(int year, int month, int day) {
			super("" + day, JLabel.CENTER);
			this.year = year;
			this.day = day;
			this.month = month;
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.setFont(font);
			if (month == select.get(Calendar.MONTH)) {
				this.setForeground(Color.BLACK);
			} else {
				this.setForeground(Color.LIGHT_GRAY);
			}
			if (day == select.get(Calendar.DAY_OF_MONTH)) {
				this.setBackground(Color.LIGHT_GRAY);
			} else {
				this.setBackground(Color.WHITE);
			}
		}

		public boolean getIsSelected() {
			return isSelected;
		}

		public void setSelected(boolean b, boolean isDrag) {
			isSelected = b;
			if (b && !isDrag) {
				int temp = select.get(Calendar.MONTH);
				select.set(year, month, day);
				if (temp == month) {
					SwingUtilities.updateComponentTreeUI(date);
				} else {
					refresh();
				}
			}
			this.repaint();
		}

		protected void paintComponent(Graphics g) {
			if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
				// 如果当前日期是选择日期,则高亮显示
				g.setColor(new Color(160, 185, 215));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH)
					&& day == now.get(Calendar.DAY_OF_MONTH)) {
				// 如果日期和当前日期一样,则用红框
				Graphics2D gd = (Graphics2D) g;
				gd.setColor(Color.RED);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			if (isSelected) {// 如果被选中了就画出一个虚线框出来
				Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f,
						new float[] { 2.0f, 2.0f }, 1.0f);
				Graphics2D gd = (Graphics2D) g;
				gd.setStroke(s);
				gd.setColor(Color.BLACK);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			super.paintComponent(g);
		}

		public boolean contains(Point p) {
			return this.getBounds().contains(p);
		}

		private void update() {
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			isSelected = true;
			update();
		}

		public void mouseReleased(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), date);
			lm.setSelect(p, false);
			commit();
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), date);
			lm.setSelect(p, true);
		}

		public void mouseMoved(MouseEvent e) {
		}

		public int compare(MyLabel o1, MyLabel o2) {
			Calendar c1 = Calendar.getInstance();
			c1.set(o1.year, o2.month, o1.day);
			Calendar c2 = Calendar.getInstance();
			c2.set(o2.year, o2.month, o2.day);
			return c1.compareTo(c2);
		}
	}

	private class LabelManager {
		private List<MyLabel> list;

		public LabelManager() {
			list = new ArrayList<MyLabel>();
		}

		public List<MyLabel> getLabels() {
			return list;
		}

		public void addLabel(MyLabel my) {
			list.add(my);
		}

		public void clear() {
			list.clear();
		}

		public void setSelect(Point p, boolean b) {
			// 如果是拖动,则要优化一下,以提高效率
			if (b) {
				// 表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和
				// 将要显示的标签找到了就可以了
				boolean findPrevious = false, findNext = false;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						findNext = true;
						if (m.getIsSelected()) {
							findPrevious = true;
						} else {
							m.setSelected(true, b);
						}
					} else if (m.getIsSelected()) {
						findPrevious = true;
						m.setSelected(false, b);
					}
					if (findPrevious && findNext) {
						return;
					}
				}
			} else {
				MyLabel temp = null;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						temp = m;
					} else if (m.getIsSelected()) {
						m.setSelected(false, b);
					}
				}
				if (temp != null) {
					temp.setSelected(true, b);
				}
			}
		}

	}

	private class bottom extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8386513933920635380L;

		public bottom() {
			super(new BorderLayout());
			this.setPreferredSize(new Dimension(295, 20));
			this.setBackground(Color.LIGHT_GRAY);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			final JLabel jl = new JLabel("今天: " + sdf.format(new Date()));
			jl.setToolTipText("点击选择今天日期");
			this.add(jl, BorderLayout.CENTER);
			jl.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
					jl.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					jl.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					jl.setForeground(Color.WHITE);
					select.setTime(new Date());
					refresh();
					commit();
				}

				public void mouseReleased(MouseEvent me) {
					jl.setForeground(Color.BLACK);
				}
			});
		}
	}

	public static void main(String[] args) {
		final DateChooser mp = new DateChooser("yyyy-MM-dd");
		JFrame jf = new JFrame("测试日期选择器");
		jf.add(mp, BorderLayout.CENTER);
		jf.add(new JButton("测试用的"), BorderLayout.NORTH);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
