package com.snp.callings.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class VCFDataStore {
	
	//private Map<String, String[]> primaryMap = new HashMap<String, String[]>();
	//private Map<String, String[]> secondaryMap = new HashMap<String, String[]>();
	private Map<String, String> primaryMap = new HashMap<String, String>();
	private Map<String, String> secondaryMap = new HashMap<String, String>();
	private Map<String, Integer> header = new HashMap<String, Integer>();

	public VCFDataStore(String vcfFile)
	{
		generateFromVcf(vcfFile);
	}

	private void  generateFromVcf(String vcfFile){
		try {

			CsvReader reader = new CsvReader(vcfFile, '\t');

			while (reader.readRecord())
			{
				String rec = reader.getRawRecord();
				if (rec.startsWith("##")) continue;
				if (rec.startsWith("#"))
				{
					String[] head_list = rec.substring(1).split("\t");
					reader.setHeaders(head_list);
					for (int i = 0; i < head_list.length; ++i)
					{
						header.put(head_list[i], i);
					}
					continue;
				}
				
				String id = reader.get("ID");
				if (id != "." && id != "")
				{
					primaryMap.put(id, reader.get("INFO"));
				}
				String secondaryID = reader.get("CHROM") + ":" + reader.get("POS") + ":" + reader.get("REF") + ":" + reader.get("ALT");
				secondaryMap.put(secondaryID, reader.get("INFO"));
			}
			
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> getPrimaryMap()
	{
		return primaryMap;
	}
	
	public Map<String, String> getSecondaryMap()
	{
		return secondaryMap;
	}

	public static void main( String[] args )
	{
		VCFDataStore j = new VCFDataStore("c:/Users/sjin1/Downloads/jackyfiles/jackyfiles/WGC033595U_combined_snps_annovar.hg19_multianno.vcf");
		
		System.out.println("primary map");
		for (Map.Entry<String, String> entry : j.getPrimaryMap().entrySet())
		{
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}
		
		System.out.println("secondary map");
		for (Map.Entry<String, String> entry : j.getSecondaryMap().entrySet())
		{
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}
	}
}
