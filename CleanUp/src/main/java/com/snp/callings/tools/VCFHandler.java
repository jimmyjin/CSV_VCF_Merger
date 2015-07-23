package com.snp.callings.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.snp.callings.CleanUp.JackyResultCleanup;

public class VCFHandler {
    /**
     * 读取CSV文件
     */
     public void  readeCsv(){
		try {
			
			CsvReader products = new CsvReader("c:/test.csv", '\t');
			//products.readHeaders();
			while (products.readRecord())
			{
				String rec = products.getRawRecord();
				if (rec.startsWith("##")) continue;
				if (rec.startsWith("#"))
				{
					System.out.println(rec.substring(1));
					
				}
			}
			//System.out.println(products.get);
		
			/*products.readHeaders();

			while (products.readRecord())
			{
				String productID = products.get("A");
				String productName = products.get("B");
				
				// perform program logic here
				System.out.println(productID);
			}*/
	
			products.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
     
     /**
      * 写入CSV文件
      */
     public void writeCsv(){
         try {
            
             String csvFilePath = "c:/test.csv";
              CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("SJIS"));
              String[] contents = {"aaaaa","bbbbb","cccccc","ddddddddd"};                    
              wr.writeRecord(contents);
              wr.close();
          } catch (IOException e) {
             e.printStackTrace();
          }
     }
    public static void main( String[] args )
    {
    	VCFHandler j = new VCFHandler();
    	j.readeCsv();
        //System.out.println( "Hello World!" );
    }
}
