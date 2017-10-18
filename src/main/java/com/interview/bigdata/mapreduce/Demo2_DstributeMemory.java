package com.interview.bigdata.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;



//利用分布式缓存  练习矩阵相乘
/*
 * step1矩阵
 * 
1	1_0,2_3,3_-1,4_2,5_-3
2	1_1,2_3,3_4,4_-2,5_-1
3	1_0,2_1,3_4,4_-1,5_2
4	1_-2,2_2,3_-1,4_1,5_2
 * 
 * 
 * 
 */


public class Demo2_DstributeMemory extends  Configured  {
	
	public static class  Mapper_matrix extends Mapper<LongWritable, Text, Text, Text>{
		private  Text outkey = new Text();
		private  Text outvalue = new Text();
		
		public  void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException{
			String[] rowline = value.toString().split("\t");
			 
			String[] lineStrings = rowline[1].split(",");
			
			for(int i = 0; i<lineStrings.length;i++){
				String column = lineStrings[i].split("_")[0];
				String valuestr = lineStrings[i].split("_")[1];
			//key :列号    value   列值
				outkey.set(column);
				outvalue.set(valuestr);
				context.write(outkey, outvalue);
			}
			
		}
	}
	
	public static  class Reduce_matrix extends Reducer<Text, Text, Text, Text>{
		private  Text outkey2 = new Text();
		private  Text outvalue2 = new Text();
		
		public void reduce(Text key, Iterable<Text> values,Context context){
			//将列转换为行  
			StringBuffer sBuffer = new StringBuffer();
			for(Text t:values){
				sBuffer.append(t+",");
			}
			String line = null ;
			//判断最后一个,号  抹掉
			
			if(sBuffer.toString().endsWith(",")){
				line = sBuffer.substring(0, sBuffer.length()-1);
			}
			outkey2.set(key);
			outvalue2.set(line);
			
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		
		int  result = -1;
		result= new Demo2_DstributeMemory().run();
		if(result ==1){
			System.out.println("step1运行成功");
		}else if(result == -1){
			System.out.println("step1运行失败");
		}
	}
	
	
	//设置几个静态量
	static	String inpath = "hadoop/matrix/555.txt";
	static	String outpath = "hadoop/matrix/out";
	static	String hdfs = "hdfs://sla1:9000";
	

	//设置run方法
	public int run(){
		try {
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", hdfs);
			
			//创建一个job 
			
				Job job = Job.getInstance(conf,"step1");
				
				//设置job
				job.setJarByClass(Demo2_DstributeMemory.class);
				job.setMapperClass(Mapper_matrix.class);
				job.setReducerClass(Reduce_matrix.class);
				//设置mapper　输出类型 
				job.setMapOutputKeyClass(Text.class);
				job.setMapOutputValueClass(Text.class);
				//设置reducer输出类型
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(Text.class);
				//设置输入和输出路径
				
				FileSystem fs = FileSystem.get(conf);
				Path inputPath  = new Path(inpath);
				if(fs.exists(inputPath )){
					FileInputFormat.addInputPath(job, inputPath );
				}
				Path  outputPath = new Path(outpath);
				fs.delete(outputPath,true);
				
				FileOutputFormat.setOutputPath(job, outputPath);
			
				
					return job.waitForCompletion(true)?1:-1;
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
	}return -1;

}
}
