package utilityLib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class csvHelper {
	
	public static void updateCSV(String fileName, String clmName, int rowNum, String data) throws IOException {
		
		File inputFile = new File(System.getProperty("user.dir")+"//inputData//"+fileName);
		// Read existing file
		
	//	CSVParser csvParser = new CSVParserBuilder().withSeparator('\t').build();
		@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader(inputFile),'\t');
		List<String[]> csvBody = reader.readAll();
		
		String[] strArray = csvBody.get(0);
		for(int j=0; j< strArray.length; j++) {
			if(strArray[j].equalsIgnoreCase(clmName)) {
				csvBody.get(rowNum)[j]=data;
			}
			reader.close();
			
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(inputFile)),'\t',
				CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.RFC4180_LINE_END)	;
		
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
		}
		
	}

}
