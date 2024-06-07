import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DeflateExample {

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        deflater.end();
        return output;
    }

    public static void main(String[] args) {
        try {
            String inputString = "Example string for compression";
            byte[] input = inputString.getBytes("UTF-8");
            byte[] compressed = compress(input);
            System.out.println("Compressed data: " + new String(compressed, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
