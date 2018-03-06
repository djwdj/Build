package l;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import android.os.*;
import java.util.*;
import java.text.*;
import android.provider.*;
import android.telephony.*;
import android.net.wifi.*;
import java.io.*;
import java.math.*;
import android.content.res.*;
import android.content.res.Resources.*;
import android.os.storage.*;
import java.lang.reflect.*;
import android.net.*;
import android.Manifest.*;

public class l extends Activity implements OnTouchListener,OnClickListener,Runnable
{
	LinearLayout l,l0,ll,l1,l2;
	ScrollView sc;
	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1);
	Animation a1,aa;
	//Button b;
	TextView t;
	float d,z;
	int w,h,mw,p,m;
	String s;
	String ver,ss="\n",n="\n",nn="\n\n";
	int k,gb=1024*1024*1024,mb=1024*1024,kb=1024;
	String[] b={"保存长图","酷安更新","打赏作者"};
	String[] permissions = {permission.WRITE_EXTERNAL_STORAGE,permission.READ_PHONE_STATE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setNavigationBarColor(0);
			getWindow().setStatusBarColor(0);
		}
		else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
								 WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		d = dm.density;
		w = dm.widthPixels;
		h = dm.heightPixels;
		if(w>h)mw=h;
		else mw=w;
		p = mw / 36;
		m = mw / 60;
		z = mw / d / 24;

		
		k = Build.VERSION.SDK_INT;
		
		lp.setMargins(m, m, m, m);

		l = new LinearLayout(this);
		l.setPadding(p, p, p, p);
		l.setGravity(Gravity.CENTER);

		a1 = new AlphaAnimation(0,1);
		a1.setDuration(500);
		l.startAnimation(a1);

		l0 = new LinearLayout(this);
		l0.setLayoutParams(lp);
		l0.setPadding(p, p, p, p);
		l0.setOrientation(LinearLayout.VERTICAL);
		l0.setBackgroundDrawable(d(w/12, 0xffff5555));
		l0.setFocusableInTouchMode(true);// ScrollView置顶
		l.addView(l0);

		sc = new ScrollView(this);
		sc.setVerticalScrollBarEnabled(false);
		l0.addView(sc);

		ll=new LinearLayout(this);
		ll.setOrientation(1);
		sc.addView(ll);
	
		l1 = new LinearLayout(this);
		l1.setPadding(p, p, p, p);
		l1.setClickable(true);
		l1.setOnTouchListener(this);
		l1.setBackgroundDrawable(d(w/18,0xffff5555));
		l1.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l1);

		l2 = new LinearLayout(this);
		l2.setPadding(p, p, p, p);
		l2.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l2);
		try
		{
			ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
            if (checkSelfPermission(permissions[0]) != 0)
                requestPermissions(permissions, 321);
		

		String[] b=
		{

			Build.BRAND,
			Build.PRODUCT,
			Build.DEVICE,
			Build.MODEL,
			Build.MANUFACTURER,
			Build.VERSION.RELEASE,
			Build.VERSION.SDK,
			Build.DISPLAY,
			Build.FINGERPRINT,
			Build.HOST,
			Build.ID,
			Build.TAGS,
			Build.TYPE,
			Build.USER,
			Build.VERSION.CODENAME,
			Build.VERSION.INCREMENTAL,
			Build.BOARD,
		},bn=
		{

			"品牌/BRAND",
			"系列/PRODUCT",
			"设备/DEVICE",
			"型号/MODEL",
			"制造商/MANUFACTURER",
			"android版本",
			"SDK",
			"显示/DISPLAY",
			"标识/FINGERPRINT",
			"主机/HOST",
			"ID",
			"标签/TAGS",
			"类型/TYPE",
			"用户/USER",
			"开发代号/CODENAME",
			"底包版本/INCREMENTAL",
			"主板/BOARD",

		};

		for (int i=0;i < b.length;i++)
		{
			if (b[i] != "")
				ss += bn[i] + "：\n" + b[i] + nn;
		}


		if (k >= 8)
		{

			ss += "芯片：\n" + Build.HARDWARE + nn;
			try
			{
				ss += "基带：\n" + (k >= 14 ?Build.getRadioVersion(): Build.RADIO) + nn;
			}
			catch (Exception e)
			{}

			try
			{
				if (!Build.BOOTLOADER.equals(Build.UNKNOWN))
					ss += "引导：\n" + Build.BOOTLOADER + nn;
			}
			catch (Exception e)
			{}

		}

		if (k >= 9)
		{
			ss += "芯片序列号：\n";
			if (k >= 26)try
				{
					ss += Build.getSerial();
				}
				catch (Exception e)
				{
					ss += Build.SERIAL;
				}else ss += Build.SERIAL;

			ss += nn;
		}


		if (Build.VERSION.SDK_INT >= 23)
		{
			try
			{
				if (Build.VERSION.BASE_OS != "")
					ss += "系统底包：\n" + Build.VERSION.BASE_OS + nn;
			}
			catch (Exception e)
			{}

			try
			{
				if (Build.VERSION.PREVIEW_SDK_INT != 0)
					ss += "开发者预览版SDK：\n" + Build.VERSION.PREVIEW_SDK_INT + nn;
			}
			catch (Exception e)
			{}

			try
			{
				ss += "系统安全补丁：\n" + Build.VERSION.SECURITY_PATCH + nn;
			}
			catch (Exception e)
			{}
		}

		try
		{
			ss += "系统包发布时间：\n" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Build.TIME)) + n;
		}
		catch (Exception e)
		{}
		
		
		tt("系统");
		t(ss);
		ss = n;

		//T(new SimpleDateFormat("当前时间： \n yyyy年MM月dd日 E \n HH时mm分ss秒SSS").format(new Date()));

		tt("CPU架构");
		if (k >= 21)
		{
			String[] abi=Build.SUPPORTED_ABIS;
			for (int i=0;i < abi.length;i++)
				ss += "[" + i + "]" + abi[i] + "\n";
		}
		else
		{
			ss += Build.CPU_ABI + n;
			if (k > 8)if (Build.CPU_ABI2 != "")
					ss += Build.CPU_ABI2 + n;
		}
		
		t(ss);
		ss = n;


		tt("屏幕");
		ss += "宽：" + w + nn;
		ss += "高：" + h + nn;
		ss += "密度：" + dm.densityDpi + nn;
		ss += "密度比例：" + dm.density + nn;
		ss += "字体比例：" + dm.scaledDensity + nn;
		ss += "宽像素密度：" + dm.xdpi + nn;
		ss += "高像素密度：" + dm.ydpi + nn;
		ss += "状态栏高度：" + sh() + nn;
		ss += "导航栏高度：" + nh() + nn;
		ss += "猜一猜：可能是 "+bd(Math.sqrt(Math.pow(w/dm.xdpi,2)+Math.pow(h/dm.ydpi,2)),1)+"寸手机"+n;

		t(ss);
		ss = n;
		
		tt("储存");
		
		ss += "系统路径：" + Environment.getRootDirectory() + nn;
		ss += "数据路径：" + Environment.getDataDirectory() + nn;
		ss += "缓存路径：" + Environment.getDownloadCacheDirectory() + nn;
		ss += "储存路径：" + Environment.getExternalStorageDirectory() + nn;
		
		ss += "程序apk路径：" + getPackageResourcePath() + nn;
		if(k>=24)
		ss += "程序数据路径：" + getDataDir() + nn;
		ss += "程序储存路径：" + getFilesDir() + nn;
		ss += "程序缓存路径：" + getCacheDir() + nn;
		if(k>=21)
			ss += "程序媒体路径：" + getExternalMediaDirs()[0] + nn;
		if(k>=11)
		ss += "程序数据包路径：" + getObbDir() + nn;
		
		if(k>=8)
		ss += "程序缓存路径：" + getExternalCacheDir() + nn;
		
		if ((s = getsd()) != null)
			ss += "外置储存路径：" + s + nn;
		try
		{
			ss += "外置储存：" + ta(s) + nn;
		}
		catch (Exception e)
		{}
		
		try
		{
			ss += "储存：" + ta(Environment.getDataDirectory().getPath()) + nn;
		}
		catch (Exception e)
		{}

		try
		{
			ss += "SD：" + sd() + nn;
		}
		catch (Exception e)
		{}
		
		try
		{
			ss += "运行：" + ram() + n;
		}
		catch (Exception e)
		{}
		
		t(ss);
		ss = n;
		
		
		
		new Handler().postDelayed(this, 500);
		
		b(0);
		b(1);
		b(2);
		
		addContentView(l, new WindowManager.LayoutParams());

	}

	@Override
	public void run()
	{
		tt("更多");
		
		try
		{
			ss += "ANDROID_ID：" + Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + nn;
		}
		catch (Exception e)
		{}
		try
		{
			ss += "IMEI：" + ((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId() + nn; 
			ss += "SN：" + ((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getSimSerialNumber() + nn;
			s=((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
			if(!s.equals(""))
			ss += "手机号码：" + s + nn;
		}
		catch (Exception e)
		{
			T("限制不足！");
		}

		try
		{
			ss += "mac：" + sh("cat /sys/class/net/wlan0/address") + nn;
		}
		catch (Exception e)
		{}

		if(k>=22)
			try
			{
				ss += "在"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss \n").format(new Date())+"通过【" + getPackageManager().getApplicationInfo(getReferrer().getHost(), 0).loadLabel(getPackageManager())+"】打开本软件！" + n;

			}
			catch (PackageManager.NameNotFoundException e)
			{}
			
		t(ss);
		ss = n;
		
	}


	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case 0:
				png();
				break;
			case 1:
				cool(getPackageName());
				break;
			case 2:
				try
				{
					String s;
					//s="alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/tsx03791nki4qabwu92vi97";
					s="YWxpcGF5czovL3BsYXRmb3JtYXBpL3N0YXJ0YXBwP3NhSWQ9MTAwMDAwMDcmY2xpZW50VmVyc2lvbj0zLjcuMC4wNzE4JnFyY29kZT1odHRwczovL3FyLmFsaXBheS5jb20vdHN4MDM3OTFua2k0cWFid3U5MnZpOTc";
					s=new String(android.util.Base64.decode(s,android.util.Base64.DEFAULT));
					startActivity(new Intent(null,Uri.parse(s)));
					
				}
				catch(Exception e)
				{
					T("打开支付宝失败");
				}
				break;
		}
	}



	@Override
	public boolean onTouch(View v, MotionEvent e)
	{
		switch(e.getAction())
		{
			case e.ACTION_DOWN:
				if(k>=21)
				v.setBackgroundDrawable(r(0xff00ff00));
				break;
			case e.ACTION_UP:
				v.setBackgroundDrawable(d(w/18,0xffff5555));
				break;
		}
		return false;
	}
	
	void cool(String s)
	{
		Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://www.coolapk.com/apk/%s",s)));
		try
		{
			startActivity(i.setPackage("com.coolapk.market"));
		}
		catch (Exception e)
		{
			startActivity(i.setPackage(null));
		}
	}
	
	void png()
	{
		T(saveImage(vb(l1)));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		png();
		return false;
	}

	
	int sh() 
	{
		return getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen","android"));
	}
	int nh()
	{
        try
		{
			return getResources().getDimensionPixelSize(getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
		}
		catch (Exception e)
		{
			return 0;
		}
    }
	Drawable r(int i)
	{
		return new RippleDrawable(ColorStateList.valueOf(i),null,null);
	}
	
	String bd(double d,int i)
	{
		
		return new BigDecimal(d).setScale(i,BigDecimal.ROUND_HALF_UP).toString();
	}
	String tg(float d)
	{
		return bd(d/gb,2);
	}
	
	String tm(float d)
	{
		return bd(d/mb,2);
	}
	
	String tk(float d)
	{
		return bd(d/kb,2);
	}
	  
	String getsd() {

		StorageManager mStorageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
		Class<?> storageVolumeClazz = null;
		try {
			storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
			Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
			Method getPath = storageVolumeClazz.getMethod("getPath");
			Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
			Object result = getVolumeList.invoke(mStorageManager);
			final int length = Array.getLength(result);
			for (int i = 0; i < length; i++) {
				Object storageVolumeElement = Array.get(result, i);
				String path = (String) getPath.invoke(storageVolumeElement);
				boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
				if (removable) {
					return path;
				}
			}
		} catch (ClassNotFoundException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}
	String ta(String s)
	{
        StatFs stat = new StatFs(s);

		long blockSize = stat.getBlockSize();

		long totalBlocks = stat.getBlockCount();

		long availableBlocks = stat.getAvailableBlocks();


		return "总:"+ (blockSize*totalBlocks>gb?(tg(blockSize*totalBlocks))+"G":(tm(blockSize*totalBlocks))+"M")
			+"/可用:"+(blockSize*availableBlocks>gb?(tg(blockSize*availableBlocks))+"G":(tm(blockSize*availableBlocks))+"M");
	}
	
	
	String sd()
	{
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			return ta(Environment.getExternalStorageDirectory().getPath());
		else 
			return "检测不到！";
	}
	String ram()
	{
		return "总:"+ (mt()>mb?(tm(mt()))+"G":(tk(mt()))+"M")
			+"/可用:"+(mu(this)>gb?(tg(mu(this)))+"G":(tm(mu(this)))+"M");
	}
	public static long mu(Context c)
	{
		ActivityManager am = (ActivityManager) c.getSystemService(ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);
		return mi.availMem;
	}
	
	public static long mt()
	{
		String s = "/proc/meminfo";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(s),8);
			s=br.readLine();
			br.close();
		}
		catch(IOException e){}
		s = s.substring(s.indexOf(':') + 1, s.indexOf('k')).trim();
		return Integer.parseInt(s);
	}
	
	Bitmap vb(View v)
	{
		int w = v.getWidth();  
        int h = v.getHeight();  
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
		//c.drawColor(0xffff5555);
        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;  
//		v.setDrawingCacheEnabled(true);
//		v.buildDrawingCache();
//		return Bitmap.createBitmap(v.getDf5555);rawingCache());
	}
	
	String saveImage(Bitmap bmp) {
		//<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
		//Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        if (bmp == null) {
            return "图象获取失败";
        }
        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if(k<8)appDir=Environment.getExternalStorageDirectory();
		if(!appDir.exists())
			appDir.mkdir();
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()) + ".png";
        File file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "保存失败";
    }
	
	String sh(String s)
	{
		try
		{
			byte[] b = new byte[4096];
			InputStream i=Runtime.getRuntime().exec(s).getInputStream();
			s = new String(b, 0, i.read(b));
			i.close();
		}
		catch (IOException e)
		{}
		return s.trim();
	}
	
//	SpannableStringBuilder sb(String b,String s)
//	{
//		SpannableStringBuilder sb = new SpannableStringBuilder();
//		sb.append(b);
//		sb.append("\n");
//		sb.append(s);
//		sb.setSpan(new RelativeSizeSpan(2),0,b.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		sb.setSpan(new StyleSpan(Typeface.BOLD), 0, b.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		sb.setSpan(new RelativeSizeSpan(0.8f),b.length()+1,b.length()+1+s.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		sb.setSpan(new StyleSpan(Typeface.ITALIC), b.length()+1,b.length()+1+s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		return sb;
//	}
	void b(int i)
	{
		t = new Button(this);
		t.setId(i);
		t.setText(b[i]); 
		t.setOnClickListener(this);
		t.setLayoutParams(lp);
		t.setTextSize(w/d/20);
		t.setTextColor(-1);
		t.setBackgroundDrawable(d(w,0,-1));
		t.setGravity(Gravity.CENTER);
		l2.addView(t);
	}
	void tt(CharSequence s)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(w/d/16);
		t.setTextColor(0xffff5555);
		t.setBackgroundDrawable(d(w,0xffffffff));
		t.setGravity(Gravity.CENTER);
		l1.addView(t);
	}

	

	void t(CharSequence s)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(z);
		t.setTextColor(0xffffffff);
		if(k>=11)
		t.setTextIsSelectable(true);
		l1.addView(t);
	}
	

	void T(String s)
	{
		Toast.makeText(this,s,50).show();
	}

	Drawable d(int r,int c,int s)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(r);
		d.setStroke(2, s);
		return d;
	}
	Drawable d(int r,int c)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(r);
		return d;
	}

//	@Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) 
//		{
//			getWindow().getDecorView().setSystemUiVisibility
//			(
//                //View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
//				//View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
//				//View.SYSTEM_UI_FLAG_FULLSCREEN|
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//			);
//        }
//    }
}


