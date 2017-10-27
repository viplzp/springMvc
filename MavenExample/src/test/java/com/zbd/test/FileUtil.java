package com.zbd.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class.getName());
	
	/**
	 * 将文件解析为String
	 * @param fullPath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getFileStr(String fullPath, String encoding) throws Exception{
		
		File file = new File(fullPath);
		if(!file.exists()){
			throw new Exception("解析文件 [" + fullPath + "] 不存在");
		}
		
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int r;
			while((r=in.read(buffer))!=-1){
				out.write(buffer,0,r);
				out.flush();
			}
			
			return new String(out.toByteArray(),encoding);
		}
		catch(Exception e){
	//		logger.error("将文件解析为String失败：" + e.getMessage(), e);
			throw e;
		}
		finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}
	
public static String getFileStr(File file, String encoding) throws Exception{
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int r;
			while((r=in.read(buffer))!=-1){
				out.write(buffer,0,r);
				out.flush();
			}
			
			return new String(out.toByteArray(),encoding);
		}
		catch(Exception e){
	//		logger.error("将文件解析为String失败：" + e.getMessage(), e);
			throw e;
		}
		finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 保存文件
	 * @param String xmlContent 需要保存成文件String
	 * @param String filePath (如：d:\hfs\test.xml)
	 * @throws Exception 
	 */
	public static void saveFile(String xmlContent, String filePath) throws Exception
	{

		String path = filePath.substring(0, filePath.lastIndexOf("/"));
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		
		WriterFile(xmlContent, path, fileName);
	}
	/**
	 * 将String组装成文件
	 * @param msg
	 * @param filePath 文件路径（不含文件名）
	 * @param fileName 文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean WriterFile(String msg, String filePath, String fileName) throws Exception
    {
        boolean flag = false;
        
        //检查文件夹是否存在
        File cFile = new File(filePath);
        if (!cFile.exists())
        {
            cFile.mkdirs();
        }
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
        	bis = new BufferedInputStream(new ByteArrayInputStream(msg.getBytes()));
            bos = new BufferedOutputStream(new FileOutputStream(filePath + "/" + fileName));
            
            int k = 0;
            byte[] buffer = new byte[8192];
            while ((k = bis.read(buffer, 0, 8192)) != -1)// 读文件
            {
                bos.write(buffer, 0, k);// 将文件写入服务器
                bos.flush();
            }
            
            flag = true;
        } catch (Exception e)
        {
        	logger.error("组装文件 ["+filePath + "/" + fileName+"] 失败：" + e.getMessage(), e);
            flag = false;
            throw e;
        } finally
        {
        	if (null != bis) bis.close();
        	if (null != bos) bos.close();
        }
        
        return flag;
    }
	
	/**
	 * 将String组装成文件 如果文件存在则添加进去
	 * @param msg
	 * @param filePath 文件路径（不含文件名）
	 * @param fileName 文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean WriterFileAppend(String msg, String filePath, String fileName) throws Exception
    {
        boolean flag = false;
        
        //检查文件夹是否存在
        File cFile = new File(filePath);
        if (!cFile.exists())
        {
            cFile.mkdirs();
        }
        File inFile = new File(filePath,fileName);
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
        	bis = new BufferedInputStream(new ByteArrayInputStream(msg.getBytes()));
            bos = new BufferedOutputStream(new FileOutputStream(inFile,true));
            
            int k = 0;
            byte[] buffer = new byte[8192];
            while ((k = bis.read(buffer, 0, 8192)) != -1)// 读文件
            {
                bos.write(buffer, 0, k);// 将文件写入服务器
                bos.flush();
            }
            
            flag = true;
        } catch (Exception e)
        {
        	logger.error("组装文件 ["+filePath + "/" + fileName+"] 失败：" + e.getMessage(), e);
            flag = false;
            throw e;
        } finally
        {
        	if (null != bis) bis.close();
        	if (null != bos) bos.close();
        }
        
        return flag;
    }
	/**
	 * 将字符串按指定的编码写入文件中
	 * @param str
	 * @param fileName 文件名（全路径）
	 * @param encoding
	 * @throws Exception
	 */
	public static void putStrToFile(String str, String fileName, String encoding) throws Exception
	{
		File file  = new File(fileName.substring(0, fileName.lastIndexOf("/")));
		if(!file.exists()){
			file.mkdirs();
		}
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(fileName);
			out.write(str.getBytes(encoding));
			out.flush();
		}
		catch(Exception e){
			logger.error("将字符串按指定的编码写入文件中失败：" + e.getMessage(), e);
			throw e;
		}
		finally{
			if(out!=null){
				out.close();
			}
		}
	}
	

	

	
    /**
     * 文件上传
     * @param file
     * @param upLoadPath 文件上传路径（不含文件名）
     * @param fileName 文件名
     * @throws IOException
     */
//    public static void fileUpLoad(FormFile file, String upLoadPath, String fileName) throws IOException
//    {
//        BufferedOutputStream out = null;
//        try
//        {
//            //用来判断上传文件夹是否已存在，不存在则新建，这个File不做流操作
//            File f = new File(upLoadPath);
//            if (!f.exists())
//            {
//                f.mkdirs();
//            }
//
//            File ff = new File(upLoadPath, fileName);
//
//            out = new BufferedOutputStream(new FileOutputStream(ff));
//            byte[] data = file.getFileData();
//            out.write(data, 0, data.length);
//            out.flush();
//        } catch (IOException e) {
//        	logger.error("文件上传异常：", e);
//            throw e;
//        } finally {
//        	if (null != out) out.close();
//        }
//    }
	
	/**
	 * 文件下载
	 * @param response
	 * @param filePath 文件所在路径（不含文件名）
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public static void fileDownLoad(HttpServletResponse response, String filePath, String fileName) 
			throws IOException
    {
        OutputStream out = null;
        FileInputStream in = null;
        try
        {
            out = response.getOutputStream();

            File file = new File(filePath, fileName);

            in = new FileInputStream(file);

            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/x-tar");

            long fileLength = file.length();
            String length = String.valueOf(fileLength);
            response.setHeader("Content_Length", length);

            int n;
            byte[] b = new byte[1024];
            while ((n = in.read(b)) != -1)
            {
                out.write(b, 0, n);
            }
            out.flush();
        } catch (IOException e) {
        	logger.error("文件下载异常：", e);
            throw e;
        } finally {
            if (null != out) out.close();
            if (null != in) in.close();
        }
    }
	
    /**
     * 删除硬盘上文件
     * 
     * @param fullPath
     * @throws IOException 
     */
    public static void deleteFile(String fullPath) throws IOException
    {
        if (null != fullPath && !"".equals(fullPath))
        {
            File f = new File(fullPath);
            f.delete();
        }
    }
}
