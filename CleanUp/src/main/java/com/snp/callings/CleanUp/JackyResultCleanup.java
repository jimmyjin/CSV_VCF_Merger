package com.snp.callings.CleanUp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


public class JackyResultCleanup {
	
    /**
     * 读取CSV文件
     */
     public void  readeCsv(){
         /*try {    
              
             ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
             String csvFilePath = "c:/test.csv";
              CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("SJIS"));    //一般用这编码读就可以了    
              
              reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
              
              while(reader.readRecord()){ //逐行读入除表头的数据    
                  csvList.add(reader.getValues());
              }            
              reader.close();
              
              for(int row=0;row<csvList.size();row++){
                  
                  String  cell = csvList.get(row)[0]; //取得第row行第0列的数据
                  System.out.println(cell);
                  
              }
              
              
         }catch(Exception ex){
             System.out.println(ex);
         }*/
		try {
			
			CsvReader products = new CsvReader("c:/test.csv", '\t');
			//products.readHeaders();
			while (products.readRecord())
			{
				String productID = products.get("A");
				String productName = products.get("B");
				
				// perform program logic here
				//System.out.println(products.getCurrentRecord());
				System.out.println(products.getRawRecord());
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
    	JackyResultCleanup j = new JackyResultCleanup();
    	j.readeCsv();
        //System.out.println( "Hello World!" );
    }
    
}
