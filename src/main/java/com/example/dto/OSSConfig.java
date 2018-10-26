package com.example.dto;

import java.io.IOException;

import com.example.util.SystemConfig;

/**
 * OSS配置类
 * OSSConfig
 * 创建人:yzk 
 * 时间：2018年10月8日-下午4:58:29 
 * @version 1.0.0
 *
 */
public class OSSConfig {
	
	    private  String endpoint;//连接区域地址
	    private  String accessKeyId;//连接keyId
	    private  String accessKeySecret;//连接秘钥  
	    private  String bucketName;//需要存储的bucketName
	    private  String picLocation;//文件保存路径
	
        public  OSSConfig() {
			try {
				this.endpoint=SystemConfig.getConfigResource("endpoint");
				this.accessKeyId=SystemConfig.getConfigResource("accessKeyId");
				this.accessKeySecret=SystemConfig.getConfigResource("accessKeySecret");
				this.bucketName=SystemConfig.getConfigResource("bucketName");
				this.picLocation=SystemConfig.getConfigResource("picLocation");
			}catch (IOException  e) {
				e.printStackTrace();
			}
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getAccessKeySecret() {
			return accessKeySecret;
		}

		public void setAccessKeySecret(String accessKeySecret) {
			this.accessKeySecret = accessKeySecret;
		}

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

		public String getPicLocation() {
			return picLocation;
		}

		public void setPicLocation(String picLocation) {
			this.picLocation = picLocation;
		}
        
        

}
