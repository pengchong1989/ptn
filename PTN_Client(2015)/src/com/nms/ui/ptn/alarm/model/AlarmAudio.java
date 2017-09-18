package com.nms.ui.ptn.alarm.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import com.nms.ui.manager.ExceptionManage;

	/**
	 * @param args
	 */

//播放声音的类
public class AlarmAudio {

	private String filename;
	private int alarmContinueTime;
	
	public AlarmAudio(String wavfile, int alarmContinueTime) 
	{
		filename = wavfile;
		this.alarmContinueTime = alarmContinueTime;
	}

	public void sound() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			return;
		}

		auline.start();
		int nBytesRead = 0;
		//这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
			return;
		} finally {
			auline.drain();
			auline.close();
			soundFile = null;
			try {
				audioInputStream.close();
				audioInputStream = null;
			} catch (IOException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			format = null;
			info = null;
			abData = null;
		}

	}

	public int getAlarmContinueTime() {
		return alarmContinueTime;
	}

	public static void main(String[] args) {
		AlarmAudio apw=new AlarmAudio("d:\\warning.wav",1);
		while(true){
		apw.sound();
		}
	}
	}
