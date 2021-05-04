package com.changgou.util;

import com.changgou.file.FastDFSFile;
import com.sun.demo.jvmti.hprof.Tracker;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-04-12 22:54
 **/
public class FastDFSUtil {

    static {
        try {
            final String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String[] upload(FastDFSFile fastDFSFile) throws IOException, MyException {
        final StorageClient storageClient = getStorageClient();
        //通过StorageClient访问Storage,实现文件上传，并且获取文件上传后的存储信息
        final NameValuePair[] nameValuePairs = new NameValuePair[1];
        nameValuePairs[0] = new NameValuePair("author", "yw");
        final String[] strings = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), nameValuePairs);
        return strings;
    }

    public static FileInfo getFileInfo(String group, String remoteFileName) throws MyException, IOException {
        final StorageClient storageClient = getStorageClient();
        final FileInfo file_info = storageClient.get_file_info(group, remoteFileName);
        return file_info;
    }

    public static InputStream downloadFile(String group, String remoteFileName) throws MyException, IOException {
        final StorageClient storageClient = getStorageClient();
        final byte[] bytes = storageClient.download_file(group, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }

    private static StorageClient getStorageClient() throws IOException {
        //创建TrackerClient
        final TrackerClient trackerClient = new TrackerClient();
        //访问TrackerServer获取连接信息
        final TrackerServer connection = trackerClient.getConnection();
        //通过返回的数据获取连接信息，创建StorageClient对象存储Storage连接信息
        return new StorageClient(connection, null);
    }

    public static void deleteFile(String group, String remoteFileName) throws MyException, IOException {
        final StorageClient storageClient = getStorageClient();
        storageClient.delete_file(group, remoteFileName);
    }

    /**
     * @description:  获取storage信息
     * @param: group
     * @return: org.csource.fastdfs.StorageServer
     * @author: yw
     * @date: 2021/5/4 20:23
     */
    public static StorageServer getStorage(String group) throws IOException {
        final TrackerClient trackerClient = new TrackerClient();
        final TrackerServer connection = trackerClient.getConnection();
        return trackerClient.getStoreStorage(connection);
    }

    /**
     * @description:  获取storage ip和端口信息
     * @param: group
     * @param: remoteFileName
     * @return: org.csource.fastdfs.ServerInfo[]
     * @author: yw
     * @date: 2021/5/4 20:23
     */
    public static ServerInfo[] getTracker(String group, String remoteFileName) throws IOException {
        final TrackerClient trackerClient = new TrackerClient();
        final TrackerServer connection = trackerClient.getConnection();
        return trackerClient.getFetchStorages(connection, group, remoteFileName);
    }
}
