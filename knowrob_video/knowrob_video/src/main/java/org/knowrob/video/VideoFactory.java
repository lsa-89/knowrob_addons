package org.knowrob.video;

import java.io.*;
import java.io.File;
import java.util.StringTokenizer;
import java.lang.Double;
import java.util.ArrayList;
import java.text.DecimalFormat;

import org.ros.message.Duration;
import org.ros.message.Time;
import org.knowrob.utils.ros.RosUtilities;
import org.knowrob.tfmemory.TFMemory;
import tfjava.StampedTransform;

/**
 * 2D latex-based plan summary from log files 
 *
 * @author asil@cs.uni-bremen.de
 *
 */
public class VideoFactory 
{
        String path_of_video;

	
	public VideoFactory()
	{
	}

	public VideoFactory(String path_of_video)
	{
		this.setPathOfVideo(path_of_video);
	}

	public void setPathOfVideo(String path_of_video)
	{

		this.path_of_video = path_of_video.replaceAll("'", "");
	}

	public String getPathOfVideo()
	{
		return path_of_video;
	}

	public boolean checkExperimentVideoFolderExist(String expPath)
	{
		File folder = new File(expPath);
		if(!(folder.exists() && folder.isDirectory()))
			return false;
		else return true;
		
	}	
	public String[] giveAddressOfVideos(String expName)
	{
		ArrayList<String> urls = new ArrayList<String>();

		String path_of_exp_video;
		if(path_of_video.charAt(path_of_video.length() - 1) == '/')
			path_of_exp_video = path_of_video + expName;
		else
			path_of_exp_video = path_of_video + "/" + expName;
		if(checkExperimentVideoFolderExist(path_of_exp_video))
		{
			File folder = new File(path_of_exp_video);
			
			for (final File file : folder.listFiles())
			{
				if(!file.isDirectory())
				{
					String fileName = file.getName();
					String extension = "";
					int i = fileName.lastIndexOf('.');
					if(i > 0)
						extension = fileName.substring(i + 1);
					if (extension.equals("mp4"))
					{
						urls.add(file.getAbsolutePath());
					}
				}
			}
			return urls.toArray(new String[urls.size()]);
		}
		return null;
	}

}
