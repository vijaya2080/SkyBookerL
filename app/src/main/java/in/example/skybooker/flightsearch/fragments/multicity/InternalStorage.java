package in.example.skybooker.flightsearch.fragments.multicity;

import android.content.Context;
        import android.util.Log;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;

/**
 * Created by YN42595 on 6/17/2016.
 */
public final class InternalStorage{

    private InternalStorage() {}

   /* public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    public static int countTime(Context context){
        int countTime=0,countDate=0;
        File dir =  context.getCacheDir();
        if(dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children)
            {
                Log.i("childrens[] count time.", child+"");
                if(child.contains("time")){
                    countTime++;
                }
            }
        }
        return countTime;
    }

    public static int countDate(Context context){
        int countTime=0,countDate=0;
        File dir =  context.getCacheDir();
        if(dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children)
            {
                Log.i("childrens count date[].", child+"");
                if(child.contains("date")) {
                    countDate++;
                }
            }
        }
        return countDate;
    }*/


    public static void delete(Context context){
        File dir =  context.getCacheDir();
        if(dir != null && dir.isDirectory())
        {
            //new File(dir, "").exists();
            Log.i(dir.getAbsolutePath(), "deleted all");
            //  dir.listFiles()[0].delete();
            String[] children = dir.list();
            for (String child : children)
            {
                Log.i("child names", child+"");
                boolean success = new File(dir, child).delete();
                if (success) {
                    Log.i("success","deleted");
                }
            }
        }
    }

    public static boolean isFileExist(Context context,String fileName){
        File dir =  context.getCacheDir();
        if(dir != null && dir.isDirectory()) {
            return new File(dir, fileName).exists();
        } else{
            return false;
        }
    }

    public static void deleteFile(Context context,String fileName){
        File dir =  context.getCacheDir();
        if(dir != null && dir.isDirectory()) {
            Log.i("deleteFile ", fileName+" deleted");
            boolean success = new File(dir, fileName).delete();
            if (success) {
                Log.i("success","deleted");
            }
        }
    }

    public static void saveCache(Context context, Object object,String fileName) throws IOException {
        File file;

        file = new File(context.getCacheDir(), fileName);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readCache(Context context, String fileName) throws IOException, ClassNotFoundException  {
        File file = null;

        file = new File(context.getCacheDir(), fileName);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
