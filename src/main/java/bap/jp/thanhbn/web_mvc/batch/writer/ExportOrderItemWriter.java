package bap.jp.thanhbn.web_mvc.batch.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Order;

@Component
public class ExportOrderItemWriter implements ItemWriter<Order> {
	
    private static final String FILE_PATH = "outputOrder.csv";
    private static final String RESOURCE_FOLDER = "resources";
    
    
    @Override
    public void write(Chunk<? extends Order> chunk) throws Exception {
        // TODO Auto-generated method stub
    	BufferedWriter writer = null;
    
    	try  {
    		writer = new BufferedWriter
        		    (new OutputStreamWriter(new FileOutputStream(RESOURCE_FOLDER + "/" + FILE_PATH, true), StandardCharsets.UTF_8));                 
    		
            for (Order order : chunk.getItems()) {
                String line = order.getId() + ";" +
                              order.getUser().getUserName() + ";" +
                              order.getTotalAmount() + ";" +
                              order.getOrderDate();
                writer.write(line);
                writer.newLine();  
                System.out.println(line);  
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	if(writer == null) {
        		return;
        	}
			writer.close();
		}
    }

}
