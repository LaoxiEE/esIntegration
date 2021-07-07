package com.tyzj.handler;

import com.tyzj.pojo.Pojo;

import java.io.*;
import java.util.*;

public class ReadPhoneCountSingleFile {

    public static Map<String, Integer> readFile(String name) {
        Map<String, Integer> map = new HashMap<>();
        try {
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                String phoneNum = str.substring(0, 11);
                if(map.containsKey(phoneNum)) {
                    map.put(phoneNum, map.get(phoneNum) + 1);
                } else {
                    map.put(phoneNum, 1);
                }
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static List<Pojo> readFilesDir(String path) {
        LinkedList<File> Dirlist = new LinkedList<File>();
        LinkedList<String> Filelist = new LinkedList<String>();
        File dir = new File(path);
        File[] files = dir.listFiles();

        for(File file : files){
            if(file.isDirectory()){
                Dirlist.add(file);
            }else{
                //处理文件内容
                Filelist.add(file.getAbsolutePath());
//                System.out.println(file.getAbsolutePath());
            }
        }

        File temp;
        List<Pojo> phoneList = new ArrayList<>();
        while(!Dirlist.isEmpty()){
            temp = Dirlist.removeFirst();
            if(temp.isDirectory()){
                files = temp.listFiles();
                if(files == null) continue;

                for(File file : files){
                    if(file.isDirectory()){
                        Dirlist.add(file);
                    }else{
                        //处理文件内容
                        Filelist.add(file.getAbsolutePath());
                        System.out.println(file.getName().substring(11, 19));
                        String date = file.getName().substring(11, 19);
//                        System.out.println(file.getParentFile());

                        Map<String, Integer> maps = readFile(file.getAbsolutePath());
                        for (Map.Entry<String, Integer> map : maps.entrySet()) {
                            String phoneNum = map.getKey();
                            int count = map.getValue();
                            Pojo pojo = new Pojo();
                            pojo.setCount(count);
                            pojo.setPhonenum(phoneNum);
                            pojo.setDate(date);
                            phoneList.add(pojo);
                        }

//                        System.out.println(file.getAbsolutePath());

                    }

                }
            }else{
                //处理文件内容,这种情况好像不会发生
                System.out.println("-------------");
            }
        }
        return phoneList;
    }

}
