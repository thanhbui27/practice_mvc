package bap.jp.thanhbn.web_mvc.batch.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.batch.job.JobManager;

@Component
public class ControllScheduled extends JFrame {
	
	
	
    private JButton actionImportProduct;
    private JButton actionExportOrder;
    private JButton actionClearData;
    private JButton actionStopAll;
   
    @Autowired
    private JobManager jobManager; 
    
    public void InitUI() {
        setTitle("Quản lý Schedule");
        setLayout(new GridLayout(4, 1, 10, 10)); 

        actionClearData = createJobButton(jobManager.isAcionClearRunning ? "Running Clear Data": "Stop Clear Data", this::clearDataJob);
        actionExportOrder = createJobButton(jobManager.isAcionExportRunning ? "Running Export Order": "Stop Export Order",this::exportOrderJob);
        actionImportProduct = createJobButton(jobManager.isAcionImportRunning ? "Running Import Product": "Stop Import Product", this::importProductJob);
        actionStopAll = new JButton("Stop All Jobs");

        actionStopAll.addActionListener(e -> stopAllJobs());

        add(actionClearData);
        add(actionExportOrder);
        add(actionImportProduct);
        add(actionStopAll);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createJobButton(String text, Runnable jobAction) {
        JButton button = new JButton(text);
        button.setBackground(Color.green); 
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addActionListener(e -> {
            jobAction.run(); 
        });
        return button;
    }
    
    private void toggleJob(JButton button, boolean action, String text) {
        if (action) {
            button.setBackground(Color.GREEN);
            button.setText(text);
        } else {
            button.setBackground(Color.RED);
            button.setText(text);
        }
    }
    
    private void clearDataJob() {
    	
    	if(jobManager.isAcionClearRunning) {
    		jobManager.stopJob("jobClearDataUserAndProduct");
    		jobManager.isAcionClearRunning = !jobManager.isAcionClearRunning;
    	}else {
    		jobManager.isAcionClearRunning = true;
    	}	
    	toggleJob(actionClearData, jobManager.isAcionClearRunning, jobManager.isAcionClearRunning ? "Running ClearData": "Stop ClearData");
    	
    }

    private void exportOrderJob() {
    	if(jobManager.isAcionExportRunning) {
    		jobManager.stopJob("exportOrder");
        	jobManager.isAcionExportRunning = !jobManager.isAcionExportRunning;

    	}else {
    		jobManager.isAcionExportRunning = true;
    	}
    	
    	
    	toggleJob(actionExportOrder, jobManager.isAcionExportRunning, jobManager.isAcionExportRunning ? "Running Export Order": "Stop Export Order");
    }

    private void importProductJob() {
    	if(jobManager.isAcionImportRunning) {
    		jobManager.stopJob("importProduct");
        	jobManager.isAcionImportRunning = !jobManager.isAcionImportRunning;

    	}else {
    		jobManager.isAcionImportRunning = true;
    	}
    	toggleJob(actionImportProduct, jobManager.isAcionImportRunning, jobManager.isAcionImportRunning ? "Running Import Product": "Stop Import Product");
    }

    private void stopAllJobs() {
        
    }
}
