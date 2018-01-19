import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDeviceReceiver;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver {
	
    public String name;
    
    public MidiInputReceiver(String name) {
        this.name = name;
    }
    public void send(MidiMessage msg, long timeStamp) {
    	System.out.println("msg received");

        byte[] aMsg = msg.getMessage();
        // take the MidiMessage msg and store it in a byte array

        // msg.getLength() returns the length of the message in bytes
        if(!Integer.toBinaryString(aMsg[0]).equals("11111111111111111111111111111000")&&
        	!Integer.toBinaryString(aMsg[0]).equals("11111111111111111111111111111110")) {
        	System.out.println(aMsg[1]);
        	System.out.println(aMsg[2]);
        }
            
            // aMsg[0] is something, velocity maybe? Not 100% sure.
            // aMsg[1] is the note value as an int. This is the important one.
            // aMsg[2] is pressed or not (0/100), it sends 100 when they key goes down, 
            
            // and 0 when the key is back up again. With a better keyboard it could maybe
            // send continuous values between then for how quickly it's pressed? 
            // I'm only using VMPK for testing on the go, so it's either 
            // clicked or not.
        
        System.out.println();
	}
    public void close() {}
}	
