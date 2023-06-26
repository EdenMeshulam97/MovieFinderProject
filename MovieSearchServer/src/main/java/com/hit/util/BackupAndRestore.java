package com.hit.util;
import java.util.TimerTask;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;


public class BackupAndRestore
{
	private Timer timer;
	private Thread timerThread;
	
	
	public void backup(String fromFilePath, String toPathBackup, long delay, long period) 
	{
		
		TimerTask backupTask = new TimerTask() { 

		@Override
		public void run() {
			try
			{
				// get Files paths
				Path sourcePath = Paths.get(fromFilePath);
		        Path BackupPath = Paths.get(toPathBackup);
		        // verify source file exist
		        if(!Files.exists(sourcePath))
		        {
		        	throw new FileNotFoundException("Source File Not Found");
		        }
		        // perform backup with Files.copy()
		        Files.copy(sourcePath,BackupPath, StandardCopyOption.REPLACE_EXISTING);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}};
		
		
	
	timer = new Timer();
	timerThread = new Thread();
	timer.scheduleAtFixedRate(backupTask, delay, period);
	}
	
	public void run(String fromFilePath, String toPathBackup) {
		try
		{
			// get Files paths
			Path sourcePath = Paths.get(fromFilePath);
	        Path BackupPath = Paths.get(toPathBackup);
	        // verify source file exist
	        if(!Files.exists(sourcePath))
	        {
	        	throw new FileNotFoundException("Source File Not Found");
	        }
	        // perform backup with Files.copy()
	        Files.copy(sourcePath,BackupPath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public Object restore(String fromFilePath) 
	{
		Object result = null;
		File file = new File(fromFilePath);
		if(file.exists())
		{
			result = file;
			
		}
		return result;
	}
	
	public void stopBackup() {
		if(timer != null)
		{
			timer.cancel();
		}
		
	}
		
	
}
