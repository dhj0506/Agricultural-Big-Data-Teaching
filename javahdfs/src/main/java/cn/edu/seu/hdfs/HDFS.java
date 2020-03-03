package cn.edu.seu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFS {

    private static final String HDFS_URL = "hdfs://192.168.1.102:9000";
    private static final String HDFS_USER = "hadoop";

    // 创建目录
    public static void mkdir(){
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(HDFS_URL),configuration, HDFS_USER);
            Path path = new Path("/hdfs");
            fileSystem.mkdirs(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if(fileSystem != null){
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 上传文件
    @Test
    public void upload(){
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(HDFS_URL),configuration, HDFS_USER);
            fileSystem.copyFromLocalFile(new Path("/Users/wanghui/Desktop/jdk-8u231-linux-x64.tar.gz"), new Path("/tools/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if(fileSystem != null){
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // 删除文件
    public static void deletFile(){
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(HDFS_URL),configuration, HDFS_USER);
            fileSystem.delete(new Path("/tools/jdk-8u231-linux-x64.tar.gz"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if(fileSystem != null){
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 查看目录信息
    public static  void listStatus(){
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(HDFS_URL),configuration, HDFS_USER);
            FileStatus[] statuses = fileSystem.listStatus(new Path("/data"));
            for(FileStatus status : statuses){
                System.out.print(status.getPermission() + "\t");
                System.out.print(status.getOwner() + ":");
                System.out.print(status.getGroup() + "\t");
                System.out.print(status.getBlockSize() + "\t");
                System.out.println(status.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if(fileSystem != null){
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // mkdir();
        // upload();
        // deletFile();

        listStatus();
    }

}
