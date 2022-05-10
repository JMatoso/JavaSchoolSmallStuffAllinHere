package com.matoso.services;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class SpeechService {
    private static Synthesizer synthesizer = null;

    public SpeechService() {
        try {
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("_______________________\n");
        }
    }

    public void speak(String text) {
        try {
            synthesizer.resume();
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("_______________________\n");
        }
    }

    public void close() {
        try {
            synthesizer.deallocate();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("_______________________\n");
        }
    }
}