package com.wrdao.springboot.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class FileUtil {
    //1.创建文件夹
    public boolean creatDir(String str1) {
        File myFolderPath = new File(str1);
        try {
            if (!myFolderPath.exists()) {
                return myFolderPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
        return false;
    }

    //2.创建文件
    public boolean creatFile(String str1, String str2) {
        File myFilePath = new File(str1);
        try {
            if (!myFilePath.exists()) {
                return myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(str2);
            resultFile.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
        return false;
    }

    //3.删除文件
    public boolean delFile(String str1) {
        File myDelFile = new File(str1);
        try {
            return myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
        return false;
    }

    //4.删除文件夹
    public boolean delEmptyDir(String str1) {
        File delFolderPath = new File(str1);
        try {
            return delFolderPath.delete(); //删除空文件夹
        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
        return false;
    }

    //5.删除一个文件夹下所有的文件夹
    public String delListDir(String str1) {
        StringBuilder filesName = new StringBuilder();
        File delFile = new File(str1);
        File[] files = delFile.listFiles();
        if( files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if(!file.delete()){
                        filesName.append(file.getName()).append(",");
                    }
                }
            }
        }
        return filesName.toString();
    }

    //6.清空文件夹
    public boolean clearDir(String str1) {
        File delFileFolder = new File(str1);
        try {
            if (!delFileFolder.exists()) {
                delFileFolder.delete();
            }
            return delFileFolder.mkdir();
        } catch (Exception e) {
            System.out.println("清空目录操作出错");
            e.printStackTrace();
        }
        return false;
    }

    //7.读取文件
    public void readFileLine(String str1) {
        // 逐行读取数据
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader br = new BufferedReader(fr);
            String str2 = br.readLine();
            while (str2 != null) {
                str2 = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //8.写入文件
    public void writeFile(String str1, String str2) {
    // 将数据写入文件
        try {
            FileWriter fw = new FileWriter(str1);
            fw.write(str2);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //9.写入随机文件
    public void writeRandomFile(String str1) {
        try {
            RandomAccessFile logFile = new RandomAccessFile(str1, "rw");
            long lg = logFile.length();
            logFile.seek(lg);
            logFile.writeByte(0);
        } catch (IOException ioe) {
            System.out.println("无法写入文件：" + ioe.getMessage());
        }
    }

    //10.读取文件属性
    public void readFileProp(String str1) {
// 文件属性的取得
        File f = new File(str1);
        if (f.exists()) {
            System.out.println(f.getName() + "的属性如下： 文件长度为：" + f.length());
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件夹的最后修改日期为：" + new Date(f.lastModified()));
        } else {
            System.out.println(f.getName() + "的属性如下：");
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件的最后修改日期为：" + new Date(f.lastModified()));
        }
    }

    //11.写入属性
    public boolean writeFileProp(String str1) {
        File fileReadonly = new File(str1);
        try {
            return fileReadonly.setReadOnly();
        } catch (Exception e) {
            System.out.println("拒绝写访问");
        }
        return false;
    }

    //12.枚举一个文件夹中的所有文件
    public void dirFile(String str1) {
        LinkedList<String> folderList = new LinkedList<String>();
        folderList.add(str1);
        while (folderList.size() > 0) {
            File file = new File(folderList.peek());
            folderList.removeLast();
            File[] files = file.listFiles();
            ArrayList<File> fileList = new ArrayList<File>();
            if(files != null) {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        folderList.add(file1.getPath());
                    } else {
                        fileList.add(file1);
                    }
                }
                for (File f : fileList) {
                    f.getAbsoluteFile().getName();
                }
            }
        }
    }

    //13.复制文件夹
    public void copyDir(String str1, String str2) {
        LinkedList<String> folderList = new LinkedList<String>();
        folderList.add(str1);
        LinkedList<String> folderList2 = new LinkedList<String>();
        folderList2.add(str2 + str1.substring(str1.lastIndexOf("\\")));
        while (folderList.size() > 0) {
            (new File(folderList2.peek())).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File folders = new File(folderList.peek());
            String[] file = folders.list();
            File temp;
            try {
                for (String aFile : file) {
                    if (folderList.peek().endsWith(File.separator)) {
                        temp = new File(folderList.peek() + File.separator
                                + aFile);
                    } else {
                        temp = new File(folderList.peek() + File.separator + aFile);
                    }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(
                                folderList2.peek() + File.separator + (temp.getName()).toString());
                        byte[] b = new byte[5120];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) {// 如果是子文件夹
                        for (File f : temp.listFiles()) {
                            if (f.isDirectory()) {
                                folderList.add(f.getPath());
                                folderList2.add(folderList2.peek()
                                        + File.separator + f.getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                //System.out.println("复制整个文件夹内容操作出错");
                e.printStackTrace();
            }
            folderList.removeFirst();
            folderList2.removeFirst();
        }
    }

    //14.复制一个文件夹下所有的文件夹到另一个文件夹下
    public void copyDirTo(String str1, String str2) {
        File copyFolders = new File(str1);
        File[] copyFoldersList = copyFolders.listFiles();
        for (File aCopyFoldersList : copyFoldersList) {
            if (aCopyFoldersList.isDirectory()) {
                ArrayList<String> folderList = new ArrayList<String>();
                folderList.add(aCopyFoldersList.getPath());
                ArrayList<String> folderList2 = new ArrayList<String>();
                folderList2.add(str2 + "/" + aCopyFoldersList.getName());
                for (int j = 0; j < folderList.size(); j++) {
                    (new File(folderList2.get(j))).mkdirs(); //如果文件夹不存在 则建立新文件夹
                    File folders = new File(folderList.get(j));
                    String[] file = folders.list();
                    File temp;
                    try {
                        for (String aFile : file) {
                            if (folderList.get(j).endsWith(File.separator)) {
                                temp = new File(folderList.get(j) + "/" + aFile);
                            } else {
                                temp = new File(folderList.get(j) + "/" + File.separator + aFile);
                            }
                            FileInputStream input = new FileInputStream(temp);
                            if (temp.isFile()) {
                                //FileInputStream input = new FileInputStream(temp);
                                FileOutputStream output = new FileOutputStream(folderList2.get(j) + "/" + (temp.getName()).toString());
                                byte[] b = new byte[5120];
                                int len;
                                while ((len = input.read(b)) != -1) {
                                    output.write(b, 0, len);
                                }
                                output.flush();
                                output.close();
                                input.close();
                            }
                            if (temp.isDirectory()) {//如果是子文件夹
                                folderList.add(folderList.get(j) + "/" + aFile);
                                folderList2.add(folderList2.get(j) + "/" + aFile);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("复制整个文件夹内容操作出错");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //15.移动文件夹
    public void moveFile(String str1, String str2) {
        LinkedList<String> folderList = new LinkedList<String>();
        folderList.add(str1);
        LinkedList<String> folderList2 = new LinkedList<String>();
        folderList2.add(str2 + str1.substring(str1.lastIndexOf("\\")));
        while (folderList.size() > 0) {
            (new File(folderList2.peek())).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File folders = new File(folderList.peek());
            String[] file = folders.list();
            File temp;
            try {
                for (String aFile : file) {
                    if (folderList.peek().endsWith(File.separator)) {
                        temp = new File(folderList.peek() + File.separator + aFile);
                    } else {
                        temp = new File(folderList.peek() + File.separator + aFile);
                    }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(
                                folderList2.peek() + File.separator + (temp.getName()).toString());
                        byte[] b = new byte[5120];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                        if (!temp.delete())
                            System.out.println("删除单个文件操作出错!");
                    }
                    if (temp.isDirectory()) {// 如果是子文件夹
                        for (File f : temp.listFiles()) {
                            if (f.isDirectory()) {
                                folderList.add(f.getPath());
                                folderList2.add(folderList2.peek() + File.separator + f.getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // System.out.println("复制整个文件夹内容操作出错");
                e.printStackTrace();
            }
            folderList.removeFirst();
            folderList2.removeFirst();
        }
        File f = new File(str1);
        if (!f.delete()) {
            for (File file : f.listFiles()) {
                if (file.list().length == 0) {
                    System.out.println(file.getPath());
                    file.delete();
                }
            }
        }
    }
    //16.移动一个文件夹下所有的文件夹到另一个目录下
    public void moveFolders(String str1, String str2) {
        File moveFolders = new File(str1);
        File[] moveFoldersList = moveFolders.listFiles();
        for (File aMoveFoldersList : moveFoldersList) {
            if (aMoveFoldersList.isDirectory()) {
                ArrayList<String> folderList = new ArrayList<>();
                folderList.add(aMoveFoldersList.getPath());
                ArrayList<String> folderList2 = new ArrayList<>();
                folderList2.add(str2 + "/" + aMoveFoldersList.getName());
                for (int j = 0; j < folderList.size(); j++) {
                    (new File(folderList2.get(j))).mkdirs(); //如果文件夹不存在 则建立新文件夹
                    File folders = new File(folderList.get(j));
                    String[] file = folders.list();
                    File temp;
                    try {
                        for (String aFile : file) {
                            if (folderList.get(j).endsWith(File.separator)) {
                                temp = new File(folderList.get(j) + "/" + aFile);
                            } else {
                                temp = new File(folderList.get(j) + "/" + File.separator + aFile);
                            }
                            FileInputStream input = new FileInputStream(temp);
                            if (temp.isFile()) {
                                //FileInputStream input = new FileInputStream(temp);
                                FileOutputStream output = new FileOutputStream(folderList2.get(j) + "/" + (temp.getName()).toString());
                                byte[] b = new byte[5120];
                                int len;
                                while ((len = input.read(b)) != -1) {
                                    output.write(b, 0, len);
                                }
                                output.flush();
                                output.close();
                                input.close();
                                temp.delete();
                            }
                            if (temp.isDirectory()) {//如果是子文件夹
                                folderList.add(folderList.get(j) + "/" + aFile);
                                folderList2.add(folderList2.get(j) + "/" + aFile);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("复制整个文件夹内容操作出错");
                        e.printStackTrace();
                    }
                }
                aMoveFoldersList.delete();
            }
        }
    }
    //17.以一个文件夹的框架在另一个目录创建文件夹和空文件
    public void createDirDemo(String str1, String str2, boolean b) {
        ArrayList<String> folderList = new ArrayList<String>();
        folderList.add(str1);
        ArrayList<String> folderList2 = new ArrayList<String>();
        folderList2.add(str2);
        for (int j = 0; j < folderList.size(); j++) {
            (new File(folderList2.get(j))).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File folders = new File(folderList.get(j));
            String[] file = folders.list();
            File temp;
            try {
                for (String aFile : file) {
                    if (folderList.get(j).endsWith(File.separator)) {
                        temp = new File(folderList.get(j) + "/" + aFile);
                    } else {
                        temp = new File(folderList.get(j) + "/" + File.separator + aFile);
                    }
                    if (temp.isFile()) {
                        if (b) {
                            temp.createNewFile();
                        }
                    }
                    if (temp.isDirectory()) {//如果是子文件夹
                        folderList.add(folderList.get(j) + "/" + aFile);
                        folderList2.add(folderList2.get(j) + "/" + aFile);
                    }
                }
            } catch (Exception e) {
                System.out.println("复制整个文件夹内容操作出错");
                e.printStackTrace();
            }
        }
    }
    //18.复制文件
    public void copyFile(String str1,String str2) {
        int byteSum = 0;
        int byteRead;
        File oldFile = new File(str1);
        try {
            if (oldFile.exists()) { //文件存在时
                FileInputStream inStream = new FileInputStream(oldFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(new File(str2, oldFile.getName()));
                byte[] buffer = new byte[5120];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; //字节数 文件大小
                    System.out.println(byteSum);
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
    //19.复制一个文件夹下所有的文件到另一个目录
    public void copyDirFiles(String str1, String str2) {
        File copyFiles = new File(str1);
        File[] files = copyFiles.listFiles();
        if(files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    int byteSum = 0;
                    int byteRead;
                    try {
                        InputStream inStream = new FileInputStream(file); //读入原文件
                        FileOutputStream fs = new FileOutputStream(new File(str2, file.getName()));
                        byte[] buffer = new byte[5120];
                        while ((byteRead = inStream.read(buffer)) != -1) {
                            byteSum += byteRead; //字节数 文件大小
                            System.out.println(byteSum);
                            fs.write(buffer, 0, byteRead);
                        }
                        inStream.close();
                    } catch (Exception e) {
                        System.out.println("复制单个文件操作出错");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //提取扩展名
    public String fileExtension(String str1) {
        return str1.substring(str1.lastIndexOf(".") + 1);
    }
}
