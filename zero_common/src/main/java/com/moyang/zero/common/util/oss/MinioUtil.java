package com.moyang.zero.common.util.oss;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.moyang.zero.common.constant.SymbolConstant.*;

/**
 * @Author: moyang
 * @ClassName: MinioUtil
 * @Date: 2022/2/2 18:53
 * @Description: minio 初始化工具
 * @Version: V1.0
 **/
@Slf4j
public class MinioUtil {

	private static String minioUrl;
	private static String minioName;
	private static String minioPass;
	private static String bucketName;

	public static void setMinioUrl(String url) {
		minioUrl = url;
	}

	public static void setMinioName(String name) {
		minioName = name;
	}

	public static void setMinioPass(String password) {
		minioPass = password;
	}

	public static void setBucketName(String bucket) {
		bucketName = bucket;
	}

	public static String getBucketName() {
		return bucketName;
	}

	private static MinioClient minioClient = null;

	/**
	 * 初始化客户端
	 * @param minioUrl 服务器地址
	 * @param minioName 用户名
	 * @param minioPass 密码
	 */
	private static void initMinio(String minioUrl, String minioName, String minioPass) {
		if (minioClient == null) {
			try {
				log.info("initMinio .... connect " + minioUrl);
				minioClient = MinioClient.builder()
						.endpoint(minioUrl)
						.credentials(minioName, minioPass)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断文件名是否带盘符，重新处理
	 * @param fileName 文件名
	 * @return 处理后的文件名
	 */
	public static String getFileName(String fileName){
		//判断是否带有盘符信息
		int unixSep = fileName.lastIndexOf('/');
		int winSep = fileName.lastIndexOf('\\');
		int pos = Math.max(winSep, unixSep);
		if (pos != -1)  {
			fileName = fileName.substring(pos + 1);
		}
		//替换上传文件名字的特殊字符
		fileName = fileName.replace("=","").replace(",","").replace("&","")
				.replace("#", "").replace("“", "").replace("”", "");
		//替换上传文件名字中的空格
		fileName = fileName.replaceAll("\\s","");

		return fileName;
	}

	/**
	 * 清除掉所有特殊字符
	 * @param str 字符串
	 * @return 清除掉特殊字符的结果
	 * @throws PatternSyntaxException 解析错误
	 */
	public static String filter(String str) throws PatternSyntaxException {
		// 清除掉所有特殊字符
		String regEx = "[`_《》~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 上传文件
	 * @param file 文件
	 * @return 文件访问链接
	 */
	public static String upload(MultipartFile file, String bizPath, String customBucket) {
		String fileUrl = "";
		bizPath = filter(bizPath);
		String newBucket = bucketName;
		if(customBucket != null){
			newBucket = customBucket;
		}
		try {
			// 初始化客户端
			initMinio(minioUrl, minioName, minioPass);
			// 检查存储桶是否已经存在
			if(minioClient.bucketExists(BucketExistsArgs.builder().bucket(newBucket).build())) {
				log.info("Bucket already exists.");
			} else {
				// 创建一个名为ota的存储桶
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(newBucket).build());
				log.info("create a new bucket.");
			}
			InputStream stream = file.getInputStream();
			// 获取文件名
			String orgName = file.getOriginalFilename();
			if(StringUtils.isBlank(orgName)) {
				orgName = file.getName();
			}
			orgName = getFileName(orgName);

			String fileType = "";
			if (orgName.contains(POINT)) {
				fileType = orgName.substring(orgName.lastIndexOf(".") + 1);
				log.info("file type is " + fileType);
			}
			long times = System.currentTimeMillis();
			String objectName = bizPath + SLASH + times + DOWN_DASH + orgName;

			// 使用putObject上传一个本地文件到存储桶中。
			if(objectName.startsWith(SLASH)){
				objectName = objectName.substring(1);
			}
			PutObjectArgs objectArgs = PutObjectArgs.builder().object(objectName)
					.bucket(newBucket)
					.contentType("application/octet-stream")
					.stream(stream, stream.available(), -1).build();
			minioClient.putObject(objectArgs);
			stream.close();
			fileUrl = minioUrl + newBucket + SLASH + objectName;
		}catch (Exception e){
			log.error(e.getMessage(), e);
		}
		return fileUrl;
	}

	/**
	 * 文件上传
	 * @param file 文件
	 * @param bizPath 相对路径
	 * @return 文件访问链接
	 */
	public static String upload(MultipartFile file, String bizPath) {
		return upload(file, bizPath, null);
	}

	/**
	 * 获取文件流
	 * @param bucketName bucket
	 * @param objectName 对象名
	 * @return 文件流
	 */
	public static InputStream getMinioFile(String bucketName,String objectName){
		InputStream inputStream = null;
		try {
			initMinio(minioUrl, minioName, minioPass);
			GetObjectArgs objectArgs = GetObjectArgs.builder().object(objectName)
					.bucket(bucketName).build();
			inputStream = minioClient.getObject(objectArgs);
		} catch (Exception e) {
			log.info("文件获取失败" + e.getMessage());
		}
		return inputStream;
	}

	/**
	 * 删除文件
	 * @param bucketName bucket
	 * @param objectName 对象名
	 */
	public static void removeObject(String bucketName, String objectName) {
		try {
			initMinio(minioUrl, minioName,minioPass);
			RemoveObjectArgs objectArgs = RemoveObjectArgs.builder().object(objectName)
					.bucket(bucketName).build();
			minioClient.removeObject(objectArgs);
		}catch (Exception e){
			log.info("文件删除失败" + e.getMessage());
		}
	}

	/**
	 * 获取文件外链
	 * @param bucketName bucket
	 * @param objectName 对象名
	 * @param expires 有效时间
	 * @return 外链
	 */
	public static String getObjectUrl(String bucketName, String objectName, Integer expires) {
		initMinio(minioUrl, minioName,minioPass);
		try{
			GetPresignedObjectUrlArgs objectArgs = GetPresignedObjectUrlArgs.builder().object(objectName)
					.bucket(bucketName)
					.expiry(expires).build();
			String url = minioClient.getPresignedObjectUrl(objectArgs);
			return URLDecoder.decode(url, StandardCharsets.UTF_8);
		}catch (Exception e){
			log.info("文件路径获取失败" + e.getMessage());
		}
		return null;
	}


	/**
	 * 上传文件到minio
	 * @param stream 文件流
	 * @param relativePath 相对路径
	 * @return 文件链接
	 */
	public static String upload(InputStream stream, String relativePath) throws Exception {
		initMinio(minioUrl, minioName,minioPass);
		if(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
			log.info("Bucket already exists.");
		} else {
			// 创建一个名为ota的存储桶
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			log.info("create a new bucket.");
		}
		PutObjectArgs objectArgs = PutObjectArgs.builder().object(relativePath)
				.bucket(bucketName)
				.contentType("application/octet-stream")
				.stream(stream, stream.available(), -1).build();
		minioClient.putObject(objectArgs);
		stream.close();
		return minioUrl + bucketName + SLASH + relativePath;
	}

	/**
	 * 列出桶内所有对象
	 * @param bucketName 存储桶名称
	 * @param prefix 对象名称的前缀，列出有该前缀的对象，如果为null ,表示查全部
	 * @param recursive 是否递归查找，如果是false,就模拟文件夹结构查找
	 */
	public static List<Map<String,String>> listObjects(String bucketName, String prefix,
	                                                   boolean recursive, boolean useVersion1) throws Exception {
		initMinio(minioUrl, minioName, minioPass);
		List<Map<String,String>> objLists = new ArrayList<>();
		// 检查桶是否存在
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (found) {
			// 列出桶里的对象
			Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName)
					.prefix(prefix).recursive(recursive).useApiVersion1(useVersion1).build());
			for (Result<Item> result : myObjects) {
				Map<String,String> map = new HashMap<>();
				Item item = result.get();
				map.put("lastModified", item.lastModified().format(DateTimeFormatter.BASIC_ISO_DATE));
				map.put("size", String.valueOf(item.size()));
				map.put("fileName", item.objectName());
				map.put("url", getObjectUrl(bucketName, Method.GET, item.objectName()));
				objLists.add(map);
			}
		} else {
			throw new Exception("【Error】不存在桶：" + bucketName);
		}
		return objLists;
	}

	/**
	 * 获取某一个存储对象的下载链接
	 * @param bucketName 桶名
	 * @param method  方法类型
	 * @param objectName 对象名
	 * @return url 下载链接
	 * @throws Exception 服务异常
	 */
	public static String getObjectUrl(String bucketName, Method method, String objectName) throws Exception {
		return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
				.method(method)
				.bucket(bucketName)
				.object(objectName).build());
	}
}
