import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

// 예약 시간을 입력하는 프레임(예약 버튼을 눌렀을 때 나타남)
class TimeFrame extends JFrame {
	private int it;
	private long time = System.currentTimeMillis();
	private int dt;

	private SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
	private SimpleDateFormat sdf3 = new SimpleDateFormat("ss");
	private String s_hour = sdf1.format(new Date(time));
	private String s_minute = sdf2.format(new Date(time));
	private String s_second = sdf3.format(new Date(time));
	private int hour = Integer.parseInt(s_hour);
	private int minute = Integer.parseInt(s_minute);
	private int second = Integer.parseInt(s_second);

	public TimeFrame() {
		it = dt = 0;

		setTitle("시간 입력");
		setSize(320, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel();

		JLabel l1 = new JLabel("예약 시간 입력");
		p1.add(l1);
		
		JTextField t1 = new JTextField(2);
		p1.add(t1);
		p1.add(new JLabel(":"));
		JTextField t2 = new JTextField(2);
		p1.add(t2);
		p1.add(new JLabel(":")); 
		JTextField t3 = new JTextField(2);
		p1.add(t3);
		
		JButton b1 = new JButton("입력");
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!(t1.getText().equals("") || t1.getText().equals("0") || t1.getText().equals("00"))) {
					it = Integer.parseInt(t1.getText());	
					hour = it - hour;
				}
				else {
					hour = 60 - hour;
				}

				if(!(t2.getText().equals("") || t2.getText().equals("0") || t2.getText().equals("00"))) {
					it = Integer.parseInt(t2.getText());	
					minute = it - minute;
				}
				else{
					minute = 60 - minute;
				}

				if(!(t3.getText().equals("") || t3.getText().equals("0") || t3.getText().equals("00"))) {
					it = Integer.parseInt(t3.getText());
					second = it - second;
				}
				else{
					second = 60 - second;
				}

				dt = (hour * 3600) + (minute * 60) + second;

				dispose();
			}
		});
		p1.add(b1);

		add(p1);

		setVisible(true);
	}

	// 예약까지 걸리는 시간 반환
	public int getDt() {
		return dt;
	}
}

// The Download Manager.
public class DownloadManager extends JFrame
implements Observer
{
	// Add download text field.
	private JTextField addTextField;
	
	// Download table's data model.
	private DownloadsTableModel tableModel;
	
	// Table listing downloads.
	private JTable table;
	
	// These are the buttons for managing the selected download.
	private JButton pauseButton, resumeButton;
	private JButton cancelButton, clearButton;
	
	// Currently selected download.
	private Download selectedDownload;
	
	// Flag for whether or not table selection is being cleared.
	private boolean clearing;
	
	private TimeFrame tf;

	private String res = "";
	public String getRes() {
		return res;
	}

	private int dt;
	public int getDt() {
		return dt;
	}

	// Constructor for Download Manager.
	public DownloadManager()
	{
		// Set application title.
		setTitle("Download Manager");
		
		// Set window size.
		setSize(640, 480);
		
		// Handle window closing events.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				actionExit();
			}
		});
		
		// Set up file menu.
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("Exit",
		KeyEvent.VK_X);
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionExit();
			}
		});
		fileMenu.add(fileExitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	
		// Set up add panel.
		JPanel addPanel = new JPanel();
		addTextField = new JTextField(30);
		addPanel.add(addTextField);
		JButton addButton = new JButton("Add Download");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAdd();
			}
		});
		addPanel.add(addButton);
		

		JButton reserveButton = new JButton("Reservation");
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력 창 띄우기
				tf = new TimeFrame();
				dt = tf.getDt();
				res = e.getActionCommand();
				actionAdd();
			}
		});
		addPanel.add(reserveButton);
		
		
		// Set up Downloads table.
		tableModel = new DownloadsTableModel();
		table = new JTable(tableModel);
		table.getSelectionModel().addListSelectionListener(new
		ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				tableSelectionChanged();
			}
		});
		// Allow only one row at a time to be selected.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		// Set up ProgressBar as renderer for progress column.
		ProgressRenderer renderer = new ProgressRenderer(0, 100);
		renderer.setStringPainted(true); // show progress text
		table.setDefaultRenderer(JProgressBar.class, renderer);
		
		// Set table's row height large enough to fit JProgressBar.
		table.setRowHeight((int) renderer.getPreferredSize().getHeight());
		
		// Set up downloads panel.
		JPanel downloadsPanel = new JPanel();
		downloadsPanel.setBorder(
		BorderFactory.createTitledBorder("Downloads"));
		downloadsPanel.setLayout(new BorderLayout());
		downloadsPanel.add(new JScrollPane(table),
		BorderLayout.CENTER);
		
		// Set up buttons panel.
		JPanel buttonsPanel = new JPanel();
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPause();
			}
		});
		pauseButton.setEnabled(false);
		buttonsPanel.add(pauseButton);
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionResume();
			}
		});
		resumeButton.setEnabled(false);
		buttonsPanel.add(resumeButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancel();
			}
		});
		cancelButton.setEnabled(false);
		buttonsPanel.add(cancelButton);
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionClear();
			}
		});
		clearButton.setEnabled(false);
		buttonsPanel.add(clearButton);
	
		// Add panels to display.
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(addPanel, BorderLayout.NORTH);
		getContentPane().add(downloadsPanel, BorderLayout.CENTER);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	// Exit this program.
	private void actionExit() {
		System.exit(0);
	}
	
	// Add a new download.
	private void actionAdd() {
		URL verifiedUrl = verifyUrl(addTextField.getText());
		if (verifiedUrl != null) {
			tableModel.addDownload(new Download(verifiedUrl));
			addTextField.setText(""); // reset add text field
		} else {
			tf.dispose();
			JOptionPane.showMessageDialog(this,
			"Invalid Download URL", "Error",
			JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Verify download URL.
	private URL verifyUrl(String url) {
		// Only allow HTTP URLs.
		if (!(url.toLowerCase().startsWith("http://") && url.toLowerCase().startsWith("ftp://") && url.toLowerCase().startsWith("https://")))
			return null;
		
		// Verify format of URL.
		URL verifiedUrl = null;
		try {
			verifiedUrl = new URL(url);
		} catch (Exception e) {
			return null;
		}
		
		// Make sure URL specifies a file.
		if (verifiedUrl.getFile().length() < 2)	// '/'로 시작하기 때문 ex) /text~
			return null;
		
		return verifiedUrl;
	}
	
	// Called when table row selection changes.
	private void tableSelectionChanged() {
		/* Unregister from receiving notifications
		from the last selected download. */
		if (selectedDownload != null)
			selectedDownload.deleteObserver(DownloadManager.this);
		
		/* If not in the middle of clearing a download,
		set the selected download and register to
		receive notifications from it. */
		if (!clearing) {
			selectedDownload = tableModel.getDownload(table.getSelectedRow());
			selectedDownload.addObserver(DownloadManager.this);
			updateButtons();
		}
	}
	
	// Pause the selected download.
	private void actionPause() {
		selectedDownload.pause();
		updateButtons();
	}
	
	// Resume the selected download.
	private void actionResume() {
		selectedDownload.resume();
		updateButtons();
	}
	
	// Cancel the selected download.
	private void actionCancel() {
		selectedDownload.cancel();
		updateButtons();
	}
	
	// Clear the selected download.
	private void actionClear() {
		clearing = true;
		tableModel.clearDownload(table.getSelectedRow());
		clearing = false;
		selectedDownload = null;
		updateButtons();
	}
	
	/* Update each button's state based off of the
	currently selected download's status. */
	private void updateButtons() {
		if (selectedDownload != null) {
			int status = selectedDownload.getStatus();
			switch (status) {
				case Download.DOWNLOADING:
					pauseButton.setEnabled(true);
					resumeButton.setEnabled(false);
					cancelButton.setEnabled(true);
					clearButton.setEnabled(false);
					break;
				case Download.PAUSED:
					pauseButton.setEnabled(false);
					resumeButton.setEnabled(true);
					cancelButton.setEnabled(true);
					clearButton.setEnabled(false);
					break;
				case Download.ERROR:
					pauseButton.setEnabled(false);
					resumeButton.setEnabled(true);
					cancelButton.setEnabled(false);
					clearButton.setEnabled(true);
					break;
				default: // COMPLETE or CANCELLED
					pauseButton.setEnabled(false);
					resumeButton.setEnabled(false);
					cancelButton.setEnabled(false);
					clearButton.setEnabled(true);
			}
		} else {
			// No download is selected in table.
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			cancelButton.setEnabled(false);
			clearButton.setEnabled(false);
		}
	}
	
	/* Update is called when a Download notifies its
	observers of any changes. */
	public void update(Observable o, Object arg) {
		// Update buttons if the selected download has changed.
		if (selectedDownload != null && selectedDownload.equals(o))
			updateButtons();
	}
	
	// Run the Download Manager.
	public static void main(String[] args) {
		DownloadManager manager = new DownloadManager();
		manager.show();
	}
}