package alkemy.challenge.Challenge.Alkemy.converter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class Base64ConverterMultipartFile implements MultipartFile{
 
        private final byte[] imgContent;
        private final String header;

        @Autowired
        private static Decoder decoder;
     
        public Base64ConverterMultipartFile(byte[] imgContent, String header) {
            this.imgContent = imgContent;
            this.header = header.split(";")[0];
        }
     
        @Override
        public String getName() {
            return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
        }
     
        @Override
        public String getOriginalFilename() {
            return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
        }
     
        @Override
        public String getContentType() {
            return header.split(":")[1];
        }
     
        @Override
        public boolean isEmpty() {
            return imgContent == null || imgContent.length == 0;
        }
     
        @Override
        public long getSize() {
            return imgContent.length;
        }
     
        @Override
        public byte[] getBytes() throws IOException {
            return imgContent;
        }

        /*implementacion de metodo para convertir un String a un MultipartFile*/
        public static MultipartFile base64ToMultipart(String base64) {
            try {
                String[] baseStrs = base64.split(",");
                byte[] b = new byte[0];
                b = decoder.decode(baseStrs[1]);
     
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                return new Base64ConverterMultipartFile(b, baseStrs[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public InputStream getInputStream() throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            // TODO Auto-generated method stub
            
        }
     
}
