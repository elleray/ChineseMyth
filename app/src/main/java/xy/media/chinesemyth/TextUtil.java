/*
 * *
 *  Copyright (c) 2015. Dingtone, inc. All rights reserved.
 * /
 */

package xy.media.chinesemyth;

import java.io.File;
import java.io.InputStream;

/**
 * Created by elleray on 16/6/23.
 */
public class TextUtil {

    public static boolean isNull(String str){
        if(str == null || str.equals("")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUTF8(String path){
        File file = new File(path);
        try {
            InputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[3];
            in.read(b);
            in.close();
            if (b[0] == -17 && b[1] == -69 && b[2] == -65){
                return true;
            } else{
//            System.out.println(file.getName() + "：可能是GBK，也可能是其他编码");
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
