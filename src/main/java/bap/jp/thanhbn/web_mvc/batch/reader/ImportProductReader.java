package bap.jp.thanhbn.web_mvc.batch.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Category;
import bap.jp.thanhbn.web_mvc.model.Product;

@Component
public class ImportProductReader implements ItemReader<Product> {

	
	private static final String FILE_PATH = "products.csv";
    private static final String RESOURCE_FOLDER = "resources";
	
    private List<Product> lsp = new ArrayList<Product>();
    
    private int currentIndex = 0;
    
	@Override
	public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		
		if(lsp.size() < getProductInFile().size()) {
			lsp = getProductInFile();
		}
		
		if(currentIndex < lsp.size()) {
			return lsp.get(currentIndex++);
		}
		
		return null;
	}
	
	public List<Product> getProductInFile() {
		File file = new File(RESOURCE_FOLDER+"/"+FILE_PATH);
		List<Product> list = new ArrayList<Product>();
		if(!file.exists()) {
			return null;
		}	
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
			Stream<String> lines = br.lines().skip(1);
			
			lines.forEach(e -> {
				
				try {
					String[] temp = e.split(";");
					Category cate = new Category();
					cate.setId(Integer.parseInt(temp[3]));
					Product p =new Product(temp[1],new BigDecimal(temp[2]), cate);
					
					list.add(p);
					
				}catch(Exception ex) {
					System.out.println("Dong so "+e+" bi loi "+ex.getMessage());
				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return list;
		
	}

}
