package com.souce;

import javax.speech.*;
import java.util.*;
import javax.speech.synthesis.*;

public class TextToAudio {
	String speaktext;

	public void dospeak(String speak, String voicename) {
		speaktext = speak;
		String voiceName = voicename;
		try {
			
			System.out.println("1");
			
			SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
			
			System.out.println("11");

			Synthesizer synthesizer = Central.createSynthesizer(desc);
			
			System.out.println("122");

			synthesizer.allocate();
			
			System.out.println("1233");

			synthesizer.resume();
			
			System.out.println("2");

			desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = desc.getVoices();
			Voice voice = null;

			System.out.println("3");

			for (int i = 0; i < voices.length; i++) {
				if (voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			
			System.out.println("4");

			synthesizer.getSynthesizerProperties().setVoice(voice);
			synthesizer.speakPlainText(speaktext, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			synthesizer.deallocate();
		} catch (Exception e) {
			String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
			System.out.println("" + e);
			System.out.println(message);
		}
	}

	public static void main(String[] args) {
		
		TextToAudio obj = new TextToAudio();
		
		obj.dospeak("shit", "kevin16");
	}
}