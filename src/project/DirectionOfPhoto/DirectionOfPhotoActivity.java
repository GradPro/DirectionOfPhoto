package project.DirectionOfPhoto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class DirectionOfPhotoActivity extends Activity {
    /** Called when the activity is first created. */
	private String dict;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.tv);
        dict = getpath()+File.separator+"Pictures/";
        List<String> image = getPictures(dict);
        if(!image.isEmpty())
        	tv.setText(dict);
    }
    //取得SDcard的路徑
    public String getpath(){
    	File sdDir = null;   
        boolean sdCardExist = Environment.getExternalStorageState()     
                              .equals(android.os.Environment.MEDIA_MOUNTED);  //判断sd卡是否存在   
        if  (sdCardExist)     
        {                                    
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录   
          }     
        return sdDir.toString();   
    }
  
    //獲取某目錄底下的圖片路徑
    public List<String> getPictures(final String strPath) {
    	  List<String> list = new ArrayList<String>();
    	   
    	  File file = new File(strPath);
    	  File[] files = file.listFiles();
    	   
    	  if (files == null) {
    	      return null;
    	  }
    	   
    	  for(int i = 0; i < files.length; i++) {
    	      final File f = files[i];
    	      if(f.isFile()) {
    	          try{
    	              int idx = f.getPath().lastIndexOf(".");
    	              if (idx <= 0) {
    	                  continue;
    	              }
    	              String suffix = f.getPath().substring(idx);
    	              if (suffix.toLowerCase().equals(".jpg") ||
    	                  suffix.toLowerCase().equals(".jpeg") ||
    	                  suffix.toLowerCase().equals(".bmp") ||
    	                  suffix.toLowerCase().equals(".png") ||
    	                  suffix.toLowerCase().equals(".gif") )
    	              {
    	                  list.add(f.getPath());
    	              }
    	          } catch(Exception e) {
    	              e.printStackTrace();
    	          }
    	      }
    	  }
    	   
    	  return list;
    	}
}