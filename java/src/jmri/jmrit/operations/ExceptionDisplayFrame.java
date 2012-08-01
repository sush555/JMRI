package jmri.jmrit.operations;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

/**
 * Dialog to display the details of an Exception. The Exception and additional
 * details about what was happening when the exception occurred are passed in using an ExceptionContext object.
 * 
 * This is a preliminary version that is incomplete, but works. Copy to the clipboard needs to be added.
 * 
 * @author Gregory Madsen Copyright (C) 2012
 * 
 */
public class ExceptionDisplayFrame extends JDialog {

	private ExceptionContext context;

	// This needs MAJOR clean-up to better organize the controls and their
	// hierarchy.

	private JPanel contentPane;
	JTextArea stackTraceTextArea;
	private JPanel buttonPanel;
	private JButton copyButton;
	private JButton closeButton;

	private JLabel stackTraceLabel;
	private JButton showDetailsButton;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;

	// New stuff
	private JTextArea operationTextArea;
	private JTextArea messageTextArea;
	private JTextArea hintTextArea;
	private JPanel summaryPanel;
	private JPanel panel_4;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextArea summaryTextArea2;

	private JPanel panel_5;
	private JLabel lblNewLabel_5;
	private JTextArea typeTextArea;

	private JPanel panel_6;
	private JLabel lblNewLabel_6;
	private JTextArea toStringTextArea;

	private JPanel panel_7;
	private JLabel lblNewLabel_7;
	private JTextArea causeTextArea;

	private JPanel detailsPanel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 * 
	 */
	public ExceptionDisplayFrame(ExceptionContext context) {
		if (context == null)
			throw new IllegalArgumentException(
					"ExceptionContext argument passed to ErrorDisplayFrame constructor cannot be null.");

		this.context = context;

		InitComponents();
	}

	/**
	 * Constructor that takes just an Exception and defaults everything else.
	 * 
	 * @param ex
	 */
	public ExceptionDisplayFrame(Exception ex) {
		this.context = new ExceptionContext(ex, "Operation unavailable",
				"Hint unavailable");

		InitComponents();
	}

	private void InitComponents() {
		setTitle(context.getTitle());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		lblNewLabel_1 = new JLabel(
				"This is a prototype dialog for displaying details about exceptions that are thrown during program execution. It still needs a lot of work!");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPane.add(lblNewLabel_1);

		summaryPanel = new JPanel();
		contentPane.add(summaryPanel);

		lblNewLabel_3 = new JLabel("Summary:");
		summaryPanel.add(lblNewLabel_3);

		summaryTextArea2 = new JTextArea();
		summaryPanel.add(summaryTextArea2);

		showDetailsButton = new JButton("Show details");
		showDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		showDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				detailsPanel.setVisible(true);
				pack();
			}
		});
		contentPane.add(showDetailsButton);

		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setVisible(false);
		contentPane.add(detailsPanel);

		stackTraceTextArea = new JTextArea();
		stackTraceTextArea
				.setToolTipText("This is the trace of all of the methods that were active when the exception occurred.");

		stackTraceLabel = new JLabel("Stack trace:");
		stackTraceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		stackTraceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		detailsPanel.add(stackTraceLabel);
		detailsPanel.add(stackTraceTextArea);

		panel = new JPanel();
		detailsPanel.add(panel);

		lblNewLabel = new JLabel("Operation:");
		panel.add(lblNewLabel);

		operationTextArea = new JTextArea();
		panel.add(operationTextArea);

		panel_4 = new JPanel();
		detailsPanel.add(panel_4);

		lblNewLabel_4 = new JLabel("Messsage:");
		panel_4.add(lblNewLabel_4);

		messageTextArea = new JTextArea();
		panel_4.add(messageTextArea);

		panel_2 = new JPanel();
		detailsPanel.add(panel_2);

		lblNewLabel_2 = new JLabel("Hint:");
		panel_2.add(lblNewLabel_2);

		hintTextArea = new JTextArea();
		panel_2.add(hintTextArea);

		panel_5 = new JPanel();
		detailsPanel.add(panel_5);

		lblNewLabel_5 = new JLabel("Exception Type:");
		panel_5.add(lblNewLabel_5);

		typeTextArea = new JTextArea();
		panel_5.add(typeTextArea);

		panel_6 = new JPanel();
		detailsPanel.add(panel_6);

		lblNewLabel_6 = new JLabel("Exception toString():");
		panel_6.add(lblNewLabel_6);

		toStringTextArea = new JTextArea();
		panel_6.add(toStringTextArea);

		panel_7 = new JPanel();
		detailsPanel.add(panel_7);

		lblNewLabel_7 = new JLabel("Cause (Inner Ex):");
		panel_7.add(lblNewLabel_7);

		causeTextArea = new JTextArea();
		panel_7.add(causeTextArea);

		buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		copyButton = new JButton("Copy to Clipboard");
		copyButton.setEnabled(false);
		buttonPanel.add(copyButton);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPanel.add(closeButton);

		// Now fill in the controls...
		stackTraceTextArea.setText(context.getStackTraceAsString(10));

		// New controls
		operationTextArea.setText(context.getOperation());
		messageTextArea.setText(context.getException().getMessage());
		hintTextArea.setText(context.getHint());
		summaryTextArea2.setText(context.getSummary());
		typeTextArea.setText(context.getException().getClass().getName());

		toStringTextArea.setText(context.getException().toString());

		Throwable cause = context.getException().getCause();

		if (cause != null) {
			causeTextArea.setText(cause.toString());
		} else {
			causeTextArea.setText("null");
		}

		pack();

		setModalityType(ModalityType.DOCUMENT_MODAL);
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
