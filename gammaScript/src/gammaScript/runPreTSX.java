package gammaScript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//###########################################################################
//###        ###
//###                      ###                                
//###########################################################################
//### 
//### 
//### 
//###
//###########################################################################
public class runPreTSX {
	public static String outputDirName="/media/rossendra/01D23527E552C810/TSX/out_sulbar2/";
	public static String inputDirName="/media/rossendra/01D23527E552C810/TSX/sulbar/";
	public static String demPath="/media/rossendra/01D23527E552C810/TSX/DEMIND/indonesia_SRTM30_rev1.tif";
	public static String geoDir="/usr/local/GAMMA_SOFTWARE-20181130/GEO/bin/";
	public static String scriptDir="/usr/local/scripts/";
	public static String dispDir="/usr/local/GAMMA_SOFTWARE-20181130/DISP/bin/";
	//public  String outputDir="/media/Data/TSX/OUTPUT/";
	public  int foundDIrInput;
	protected static String outputMakeDir,mlirs,fileOutputName,xmlFile,cosFile,namePath;
	public static double parSRes=6;
	public  ArrayList<String> listDir,listXML ;
	public static ArrayList<String> listCommand;
	public ArrayList<String> dapetXML;
	public ArrayList<String> dapetCOS;
	public Integer dem_width,mli_width;
	public FileWriter writeDiffOut;
	//public ArrayList<String> outputMakeDir;

	//List folder inside inputDirName, convert the name and find xml and cosar,
	public  ArrayList<String> ListFolder (String path){
		listDir=new ArrayList<String>(); 

		File dir=new File(path);
		File[] isiDir=dir.listFiles();


		for(int i=0;i<isiDir.length;i++) {
			if(isiDir[i].isDirectory()&&isiDir[i].exists()) {
				System.out.printf("Dir isiDir%d=%s%n",i,isiDir[i].toString());
				listDir.add(isiDir[i].toString());
			}
		}
		return listDir;

	}

	public  void Step1 (ArrayList<String> folderList) throws IOException{
		String result = null;
		for (int i=0;i<listDir.size();i++){
			listCommand=new ArrayList<String>();
			extractSAR(listDir.get(i).toString());
			System.out.println("listDir.size()="+listDir.size());
			try {
				//Process p=new Process();

				for (int a=0;a<listCommand.size();a++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(a));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			mliParam();
			try {
				//Process p=new Process();

				for (int b=0;b<listCommand.size();b++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(b));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			String parPath=namePath+".mli.par";
			//
			//
			int satu=1;
			//
			mlirs=getParam(parPath,"range_samples*",satu);
			listCommand=new ArrayList<String>();

			listCommand.add(geoDir+"SLC_corners "+namePath+".slc.par");
			try {
				//Process p=new Process();

				for (int c=0;c<listCommand.size();c++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(c));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					//BufferedWriter bw = null;
					String inputLine;
					File corners = new File(namePath+".corners.txt");
					FileWriter fw = new FileWriter(corners);
					//bw = new BufferedWriter(fw);
					while ((inputLine = in.readLine()) != null) {



						fw.write(inputLine);
						fw.write("\n");
						System.out.println(inputLine);
						result += inputLine;



					}
					in.close();
					fw.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode1();
			try {
				//Process p=new Process();

				for (int d=0;d<listCommand.size();d++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(d));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}

			listCommand=new ArrayList<String>();
			geoCode2();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}

			listCommand=new ArrayList<String>();
			geoCode3();
			writeDummyDiff coba=new writeDummyDiff(namePath+".mli.par",
					namePath+".diff_par","/media/rossendra/01D23527E552C810/TSX/diff_par_template");
			coba.process();
			try {
				//Process p=new Process();

				for (int f=0;f<listCommand.size();f++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(f));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode32();
			try {
				//Process p=new Process();
				writeDiffOut = new FileWriter(namePath+".diff_par.out");
				for (int f=0;f<listCommand.size();f++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(f));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						writeDiffOut.write(inputLine);
						writeDiffOut.write("\n");
						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode4();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode5();
			try {
				//Process p=new Process();
				//FileWriter fw = new FileWriter(namePath+".diff_par.out");
				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						writeDiffOut.write(inputLine);
						writeDiffOut.write("\n");
						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
					
					writeDiffOut.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode6();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode7();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode8();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			listCommand=new ArrayList<String>();
			geoCode9();
			try {
				//Process p=new Process();

				for (int e=0;e<listCommand.size();e++) {
					Runtime r = Runtime.getRuntime();                    
					Process p = r.exec(listCommand.get(e));	
					BufferedReader in =
							new BufferedReader(new InputStreamReader(p.getInputStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {

						System.out.println(inputLine);
						result += inputLine;
					}
					in.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	public void geoCode1(){
		double minLat=Double.valueOf(getParam(namePath+".corners.txt","min.*latitude*:*",3))-0.05;
		double maxLat=Double.valueOf(getParam(namePath+".corners.txt","min.*latitude*:*",7))+0.05;
		double minLon=Double.valueOf(getParam(namePath+".corners.txt","min.*longitude*:*",3))-0.05;
		double maxLon=Double.valueOf(getParam(namePath+".corners.txt","min.*longitude*:*",7))+0.05;
		listCommand.add(scriptDir+"srtm_gamma2 "+demPath+" "
				+namePath+".dem.bin "+minLon+" "+maxLat+" "+maxLon+" "+minLat);

	}

	public void geoCode2(){


		double demGrid=Double.valueOf(getParam(namePath+".dem.par","post_lon:*",1))*1852*60;
		double ovr=demGrid/parSRes;
		listCommand.add(geoDir+"gc_map "+namePath+".mli.par - "
				+namePath+".dem.par "+namePath+".dem.bin "
				+namePath+".demseg.par "+
				namePath+".demseg.bin "+namePath+".lut "+ovr+" "+ovr+" "
				+namePath+".sim.bin "+namePath+".zen.bin "+
				namePath+".ori.bin - - - - 8 2");

	}
	public void geoCode3(){
		mli_width=Integer.valueOf(getParam(namePath+".mli.par","range_samples:*",1));
		Integer mli_lines=Integer.valueOf(getParam(namePath+".mli.par","azimuth_lines:*",1));
		dem_width=Integer.valueOf(getParam(namePath+".demseg.par","width:*",1));
		//double dem_lines=Double.valueOf(getParam(namePath+".demseg.par","nlines:*",1));
		//geocode $lut $simsar $dem_width $simsar_rdc $mli_width $mli_lines 0 0
		listCommand.add(geoDir+"geocode "+namePath+".lut "
				+namePath+".sim.bin "+dem_width+" "+namePath+".sim_rdc.bin "
				+mli_width+" "+mli_lines+" 0 0");
	}

	public void geoCode32(){
		//	init_offsetm $simsar_rdc $gamma.bin $diff_par - - - - - - $coreg_snr 1024 1 > $diff_par.out

		listCommand.add(geoDir+"init_offsetm "+namePath+".sim_rdc.bin "
				+namePath+".gamma.bin "
				+namePath+".diff_par - - - - - - 0.15 1024 1");
	}
	public void geoCode4(){
		//		try{
		//			FileWriter fw = new FileWriter(namePath+".diff_par.out");
		//			fw.write("*** Initial offset estimation for multi-look intensity images ***");
		//			fw.write("\n");
		//			fw.write("*** Copyright 2016, Gamma Remote Sensing, v3.9 12-Apr-2016 clw/uw ***");
		//			fw.write("\n");
		//			fw.close();
		//
		//		} catch (IOException e) {
		//			System.out.println(e);
		//		}


		listCommand.add(geoDir+"offset_pwrm "+namePath+".sim_rdc.bin "
				+namePath+".mli.bin "+namePath+".diff_par "
				+namePath+".offs "+namePath+".snr 128 128 "
				+namePath+".offsets 1 64 64 -");
	}

	public void geoCode5(){
		//offset_fitm $output/$line/$nameid.offs $output/$line/$nameid.snr $diff_par $output/$line/$nameid.coffs $output/$line/$nameid.coffsets - 3 > $diff_par.out
		listCommand.add(geoDir+"offset_fitm "+namePath+".offs "
				+namePath+".snr "+namePath+".diff_par "
				+namePath+".coffs "+namePath+".coffsets ");
	}
	public void geoCode6(){
		//gc_map_fine $lut $dem_width $diff_par $lutf 1
		listCommand.add(geoDir+"gc_map_fine "+namePath+".lut "+
				dem_width+" "+namePath+".diff_par "
				+namePath+".lutf 1");

	}
	public void geoCode7(){

		//geocode_back $imfile $mli_width $lutf $gtc.bin $dem_width 0 2 0
		listCommand.add(geoDir+"geocode_back "+namePath+".cmli.bin "
				+mli_width+" "+namePath+".lutf "+namePath+".cmli.GTC.bin "
				+dem_width+" 0 2 0");
	}
	public void geoCode8(){

		//gamma2envi $demsegpar $gtc.hdr
		listCommand.add(scriptDir+"gamma2envi "+namePath+".demseg.par "
				+namePath+".cmli.GTC.hdr");
		//raspwr $output/$line/$id*.cmli.bin $mlirs 1 0 1 1 - - - $output/$line/$nameid.cmli.ras
		listCommand.add(dispDir+"raspwr "+namePath+".cmli.bin "
				+mlirs+" 1 0 1 1 - - - "+namePath+".cmli.ras");

	}
	public void geoCode9(){


		//geocode_back $output/$line/$id*.cmli.ras $mlirs $output/$line/$id*.lutf $gtc.image.bmp $dem_width - 0 2
		listCommand.add(geoDir+"geocode_back "+namePath+".cmli.ras "
				+mlirs+" "+namePath+".lutf "+namePath+".cmli.GTC.image.bmp "
				+dem_width+" - 0 2");
	}
	//offset_pwrm $simsar_rdc $mli $diff_par $output/$line/$nameid.offs $output/$line/$nameid.snr 128 128 $output/$line/$nameid.offsets 1 64 64 -


	public static ArrayList<String> geoCodeTest(String path,String demPath){
		listCommand=new ArrayList<String>();
		double minLat=Double.valueOf(getParam(path,"min.*latitude*:*",3))-0.05;
		double maxLat=Double.valueOf(getParam(path,"min.*latitude*:*",7))+0.05;
		double minLon=Double.valueOf(getParam(path,"min.*longitude*:*",3))-0.05;
		double maxLon=Double.valueOf(getParam(path,"min.*longitude*:*",7))+0.05;
		listCommand.add("this :"+minLon+" "+maxLat+" "+maxLon+" "+minLat);

		//double demGrid=Double.valueOf(getParam(demPath,"post_lon:*",1))*1852*60;
		//double ovr=demGrid/parSRes;
		//listCommand.add(ovr+" "+demGrid+" ");
		return listCommand;
	}




	public String fileName(List<String> nameSplit){
		String diroutputName=nameSplit.get(nameSplit.size()-1);
		//create fileOutputName
		List<String> fileOutputArray=new ArrayList<String>() ;

		fileOutputArray=Arrays.asList(diroutputName.split("_"));
		fileOutputName=fileOutputArray.get(0)+"_"+fileOutputArray.get(1)+"-"+fileOutputArray.get(13)+"-HH";
		return fileOutputName;
	}


	public  void extractSAR(String path){

		//split listDir first,get last part
		List<String> nameSplit=new ArrayList<String>() ;

		nameSplit=Arrays.asList(path.split("\\/"));
		//append command to make dir output
		listCommand.add("mkdir "+outputDirName+nameSplit.get(nameSplit.size()-1));
		outputMakeDir=nameSplit.get(nameSplit.size()-1)+"/";
		//also append $line to dir output



		//find XML
		String cari=nameSplit.get(nameSplit.size()-1)+".xml";
		System.out.println("cari="+cari);
		File[] filexml;
		try {
			filexml = Files.walk(Paths.get(path))
					.filter(p -> p.toString().endsWith(cari)).distinct()
					//.filter(p -> p.toString().matches(".xml")).distinct()
					.map(Path::toFile)
					.toArray(File[]::new);
			xmlFile =filexml[0].toString();
			System.out.println("xmlFile="+xmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		//find Cosar

		File[] fileCos;
		try {
			fileCos = Files.walk(Paths.get(path))
					.filter(p -> p.toString().endsWith(".cos")).distinct()
					//.filter(p -> p.toString().matches(".cos")).distinct()
					.map(Path::toFile)
					.toArray(File[]::new);
			cosFile =fileCos[0].toString();
			System.out.println("cosFile="+cosFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileName(nameSplit);
		namePath=outputDirName+outputMakeDir+fileOutputName;
		listCommand.add(geoDir+"par_TX_SLC "+xmlFile+" "+cosFile+" "
				+namePath+".slc.par "+namePath+".slc.bin HH");

	}


	public static  String getParam(String parPath,String regex,int column){
		BufferedReader reader;
		String found;
		String[] cut=new String[10];
		try {
			reader = new BufferedReader(new FileReader(parPath));
			String line = reader.readLine();
			while (line != null) {
				//String regex2 = regex;
				//private  final String INPUT = "cat cat cat cattie cat";
				//		//String text    =
				//				"This is the text to be searched " +
				//						"for occurrences of the http:// pattern.";
				//String text3="/home/eucliwood/Documents/TDX1/input/TDX1_SAR__SSC______SM_S_SRA_20180308T101947_20180308T101957";

				//String text5="/home/eucliwood/Documents/TDX1/input/TDX1_SAR__SSC______SM_S_SRA_20180308T101947_20180308T101957";

				//String text2=isiDir[1].toString();
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(line);   // get a matcher object
				//System.out.println("isidir[foundDIrInput]= "+isiDir[foundDIrInput].toString());

				//		int count=0;
				if (m.find()){
					//	
					//	
					//	System.out.println("m.group="+m.group(count));
					//	//count++;
					//}
					//System.out.println(m.group(0).toString());

					cut=line.split("\\s+");
					//										for (int i=0;i<cut.length;i++) {
					//											System.out.println(cut[i]);
					//					//						// read next line
					//										}
				}
				//else {
				line = reader.readLine();
				//}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("cut="+Arrays.toString(cut));
		//System.out.println("cut[1]="+cut[1]);
		return cut[column];
	}

	public void mliParam() {
		String parPath=namePath+".slc.par";
		//
		//
		int satu=1;
		//
		//		mlirs=getParam(parPath,"range_samples*",satu);
		String parInc=getParam(parPath,"incidence_angle*",satu);
		String parRPix=getParam(parPath,"range_pixel_spacing*",satu);
		String parAPix=getParam(parPath,"azimuth_pixel_spacing*",satu);

		String parMLISamp=getParam(parPath,"range_samples:*",satu);
		String parMLILat=getParam(parPath,"center_latitude:*",satu);
		String parMLILong=getParam(parPath,"center_longitude:*",satu);

		String parCorLat=getParam(parPath,"range_samples:*",satu);
		//String parMLILat=getParam(parPath,"center_latitude:*",satu);
		//String parMLILong=getParam(parPath,"center_longitude:*",satu);

		double sinInc=Math.sin(((Double.valueOf(parInc)*4*Math.atan(1)))/180);


		double parRlks=parSRes*sinInc/Double.valueOf(parRPix);
		if(parRlks<=1) {
			parRlks=1;
		}
		double parAzlks=parSRes/Double.valueOf(parAPix);
		if(parAzlks<=1) {
			parAzlks=1;
		}
		if(parRlks>=1||parAzlks>=1) {
			listCommand.add(geoDir+"multi_look "+namePath+".slc.bin "
					+namePath+".slc.par "+namePath+".mli.bin "
					+namePath+".mli.par "+parRlks+" "+parAzlks);
			//listCommand.add(parRlks+" "+parAzlks);
			listCommand.add(scriptDir+"gamma2envi "+namePath+".mli.par");
		}
		listCommand.add(geoDir+"radcal_MLI "+namePath+".mli.bin "
				+namePath+".slc.par - "+namePath+".cmli.bin - 0 0 1 0.0 -");
		listCommand.add(geoDir+"SLC_corners "+namePath+".slc.par >"
				+namePath+".corners.txt");
		//return commandMLI.toString();
		//return parInc;

	}


	public static void main (String[] ar) throws IOException {
		runPreTSX ins=new runPreTSX();
		//String path="/media/rossendra/01D23527E552C810/TSX/OUTPUT_TES/TDX1_SAR__SSC______SM_S_SRA_20180308T101947_20180308T101957/TDX1_SAR-20180308T101957-HH.corners.txt";
		//String demPath="/media/rossendra/01D23527E552C810/TSX/OUTPUT_TES/TDX1_SAR__SSC______SM_S_SRA_20180308T101947_20180308T101957/TDX1_SAR-20180308T101957-HH.dem.par";

		ins.Step1(ins.ListFolder(inputDirName));
		//System.out.println("Command Size="+ins.geoCodeTest(path).size());
		//		for(int i=0;i<geoCodeTest(path,demPath).size();i++){
		//			System.out.println("listCommand["+i+"]="+ins.geoCodeTest(path,demPath).get(i));
		//		}

	}
}