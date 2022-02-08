package com.example.testarea.Utils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SavePicUtil {

    /**
     * 向SD卡保存UUID码
     * @param UUID 生成的UUID
     * @throws IOException s
     */
    public static void saveBitmap(String UUID) throws IOException {
        // 创建目录
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Log.d("sdCardDir",sdCardDir);
        File appDir = new File(sdCardDir,"Cache");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "hrk_key" + ".txt";//这里是创建一个TXT文件保存我们的UUID
//        Log.d("appDir", String.valueOf(appDir));
//        Log.d("fileName",fileName);
        File file = new File(appDir, fileName);
        if (!file.exists()) {
//            Log.d("file.getpath",file.getPath());
            file.createNewFile();
        }
        //保存android唯一表示符
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(UUID);
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
