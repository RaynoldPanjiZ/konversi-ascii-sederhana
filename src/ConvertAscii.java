import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class ConvertAscii extends MIDlet implements CommandListener{

	private TextField  input, output;
	private ChoiceGroup convertChoice;
	private Command cmdkonversi, cmdexit;

	public void startApp() {
		
		Form form = new Form ("Konversi Karakter ASCII");     
		input = new TextField ("Input Karakter ASCII --> ", null , 1, TextField.ANY);
		output = new TextField ("Hasil", null , 50, TextField.UNEDITABLE);

		cmdkonversi = new Command ("Konversi", Command.OK, 1);
		cmdexit = new Command ("Exit", Command.EXIT, 0);

		convertChoice = new ChoiceGroup("Opsi Konversi   -->  ", Choice.POPUP);
		convertChoice.append("dec [desimal]", null);
		convertChoice.append("bin [biner]", null);
		convertChoice.append("oct [oktal]", null);
		convertChoice.append("hex [hexadesimal]", null);
		convertChoice.append("html [HTML code]", null);


		form.setTicker(new Ticker ("yang bisa di-inputkn hanya Karakter ASCII yang printable !!"));
		form.append(convertChoice);
		form.append(input);
		form.append(output);

		form.addCommand(cmdexit);
		form.addCommand(cmdkonversi);
		form.setCommandListener(this);
		Display.getDisplay(this).setCurrent(form);   
	}
	public void pauseApp() {
	}
	public void destroyApp(boolean unconditional) {
	}
	public void commandAction (Command c, Displayable d){  

		if (c == cmdkonversi) {		
			char teks = input.getString().charAt(0);;
			Konversi kon = new Konversi(teks);
			int index = kon.dec;
			if (convertChoice.isSelected(0)) {
				output.setString(Integer.toString(index));
			} else if (convertChoice.isSelected(1)) {
				String bin = Integer.toBinaryString(index);  
				output.setString(bin);
			} else if (convertChoice.isSelected(2)) {
				String oct = Integer.toOctalString(index);
				output.setString(oct);
			} else if (convertChoice.isSelected(3)) {
				String hex = Integer.toHexString(index);
				output.setString(hex);
			} else if (convertChoice.isSelected(4)) {
				String html = "&#"+index+";";
				output.setString(html);
			}
		} else if (c == cmdexit) {
			notifyDestroyed();
		}
	}
}


class Konversi{
	int dec;
	String bin, hex, oct;	
	char ascii[] = {' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'}; 
	char[] asciigen = new char[ascii.length+32];
	
	public Konversi(char in){
		for(int i=0; i<ascii.length; i++){
			asciigen[i+32] = ascii[i];
		}
		this.dec = indexNum(asciigen, in); 
	}
	public int indexNum(char[] asciigen,char find) {
        int k=0;
        for(int i=0;i<asciigen.length;i++){
            if(find==asciigen[i]){
                k=i;
                break;
            }
        }
    	return k;
	}
}