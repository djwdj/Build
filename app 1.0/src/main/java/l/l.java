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

public class l extends Activity 
{
	LinearLayout l,l0,ll;
	ScrollView sc;
	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1);
	Animation a1,aa;
	TextView t;
	float d,z;
	int w,h,p,m;
	String ver,ss="\n",n="\n",nn="\n\n";
	int k,gb=1024*1024*1024,mb=1024*1024;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setNavigationBarColor(0);
			getWindow().setStatusBarColor(0);
		}else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
								 WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		d = dm.density;
		if(dm.widthPixels<dm.heightPixels)
		{
			w = dm.widthPixels;
			h = dm.heightPixels;
		}else{
			h = dm.widthPixels;
			w = dm.heightPixels;
		}
		p=w/36;
		m=w/60;
		z=w/d/24;

		lp.setMargins(m,m,m,m);

		l=new LinearLayout(this);
		l.setPadding(p,p,p,p);
		l.setGravity(Gravity.CENTER);

		a1=new TranslateAnimation(0, 0, h, 0);
		a1.setDuration(500);
		l.startAnimation(a1);

		l0=new LinearLayout(this);
		l0.setLayoutParams(lp);
		l0.setPadding(p,p,p,p);
		l0.setOrientation(LinearLayout.VERTICAL);
		l0.setBackgroundDrawable(d(p,0xffff5555));
		l0.setFocusableInTouchMode(true);// ScrollView置顶
		l.addView(l0);

		sc=new ScrollView(this);
		sc.setVerticalScrollBarEnabled(false);
		l0.addView(sc);
		
		ll=new LinearLayout(this);
		ll.setPadding(p,p,p,p);
		ll.setOrientation(LinearLayout.VERTICAL);
		sc.addView(ll);
		
		try
		{
			ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}
		
		k=Build.VERSION.SDK_INT;
		
		String[] b=
		{

			Build.BRAND,
			Build.PRODUCT,
			Build.DEVICE,
			Build.MANUFACTURER,
			Build.MODEL,
			Build.DISPLAY,
			Build.FINGERPRINT,
			Build.HOST,
			Build.ID,
			Build.TAGS,
			Build.TYPE,
			Build.USER,
			Build.VERSION.CODENAME,
			Build.VERSION.INCREMENTAL,
			Build.VERSION.RELEASE,
			Build.VERSION.SDK,
			Build.BOARD,
		},bn=
		{

			"品牌/BRAND",
			"系列/PRODUCT",
			"设备/DEVICE",
			"制造商/MANUFACTURER",
			"型号/MODEL",
			"显示/DISPLAY",
			"标识/FINGERPRINT",
			"主机/HOST",
			"ID",
			"标签/TAGS",
			"类型/TYPE",
			"用户/USER",
			"开发代号/CODENAME",
			"底包版本/INCREMENTAL",
			"android版本",
			"SDK",
			"主板/BOARD",
			
		};
		
		for(int i=0;i<b.length;i++)
		{
			if(b[i]!="")
			ss +=bn[i]+"：\n"+b[i]+nn;
		}
		
		
		if(k>=8)
		{
			
			ss+="芯片：\n"+Build.HARDWARE+nn;
			ss+="基带：\n"+(k>=14?Build.getRadioVersion():Build.RADIO)+nn;
			if(!Build.BOOTLOADER.equals(Build.UNKNOWN))
				ss+="引导：\n"+Build.BOOTLOADER+nn;
			
		}
			
		if(k>=9)
			ss+="芯片序列号：\n"+(k>=26?Build.getSerial():Build.SERIAL)+nn;
			
		if(Build.VERSION.SDK_INT>=23)
		{
			if(Build.VERSION.BASE_OS!="")
			ss+="系统底包：\n"+Build.VERSION.BASE_OS+nn;
			if(Build.VERSION.PREVIEW_SDK_INT!=0)
			ss+="开发者预览版SDK：\n"+Build.VERSION.PREVIEW_SDK_INT+nn;
			ss+="系统安全补丁：\n"+Build.VERSION.SECURITY_PATCH+nn;
		}
		ss+="系统包发布时间：\n"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Build.TIME))+n;
		tt("系统");
		t(ss);
		ss=n;
		
		T(new SimpleDateFormat("当前时间： \n yyyy年MM月dd日 E \n HH时mm分ss秒SSS").format(new Date(System.currentTimeMillis())));
		
		tt("CPU架构");
		if (k >= 21)
		{
			String[] abi=Build.SUPPORTED_ABIS;
			for (int i=0;i < abi.length;i++)
				ss += "[" + i + "]" + abi[i] + "\n";
		}else
		{
			ss+=Build.CPU_ABI+n;
			if(k>8)if(Build.CPU_ABI2!="")
				ss+=Build.CPU_ABI2+n;
		}
		t(ss);
		ss=n;
		
		
		tt("屏幕");
		ss+="宽："+dm.widthPixels+nn;
		ss+="高："+dm.heightPixels+nn;
		ss+="密度："+dm.densityDpi+nn;
		ss+="密度比例："+dm.density+nn;
		ss+="字体比例："+dm.scaledDensity+nn;
		ss+="宽像素密度："+dm.xdpi+nn;
		ss+="高像素密度："+dm.ydpi+n;
		
		t(ss);
		ss=n;
		
		
		tt("更多");
		ss+="ANDROID_ID："+Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+nn;
		ss+="IMEI："+((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()+nn; 
		ss+="SN："+((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getSimSerialNumber()+nn;
		
		ss+="mac："+sh("cat /sys/class/net/wlan0/address")+nn;
		
		ss+="储存："+data()+nn;
		
		ss+="SD："+sd()+nn;
		
		ss+="运行："+ram()+n;
		
		t(ss);
		ss=n;
		
		
		addContentView(l, new WindowManager.LayoutParams());



	}
	
	String tg(float d)
	{
		return new BigDecimal(d/gb).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	String tm(float d)
	{
		return new BigDecimal(d/mb).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	String tk(float d)
	{
		return new BigDecimal(d/1024).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	String data()
	{
		File path = Environment.getDataDirectory();
		
        StatFs stat = new StatFs(path.getPath());
		
		long blockSize = stat.getBlockSize();
      
		long totalBlocks = stat.getBlockCount();
		
		long availableBlocks = stat.getAvailableBlocks();
		
		
		return "总:"+ (blockSize*totalBlocks>gb?(tg(blockSize*totalBlocks))+"G":(tm(blockSize*totalBlocks))+"M")
		+"/可用:"+(blockSize*availableBlocks>gb?(tg(blockSize*availableBlocks))+"G":(tm(blockSize*availableBlocks))+"M");
	}
	
	String sd()
	{
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			File path = Environment.getExternalStorageDirectory();

			StatFs stat = new StatFs(path.getPath());

			long blockSize = stat.getBlockSize();

			long totalBlocks = stat.getBlockCount();

			long availableBlocks = stat.getAvailableBlocks();


			return "总:"+ (blockSize*totalBlocks>gb?(tg(blockSize*totalBlocks))+"G":(tm(blockSize*totalBlocks))+"M")
				+"/可用:"+(blockSize*availableBlocks>gb?(tg(blockSize*availableBlocks))+"G":(tm(blockSize*availableBlocks))+"M");
		}else 
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
	
	
	
	String sh(String s)
	{
		try
		{
			byte[] b = new byte[4096];
			s= new String(b, 0, Runtime.getRuntime().exec(s).getInputStream().read(b));
		}
		catch (IOException e)
		{}
		return s.trim();
	}
	
	void tt(CharSequence s)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(w/d/16);
		t.setTextColor(0xffff5555);
		t.setBackgroundDrawable(d(p,0xffffffff));
		t.setGravity(Gravity.CENTER);
		ll.addView(t);
	}

	

	void t(CharSequence s)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(z);
		t.setTextColor(0xffffffff);
		if(k>=11)
		t.setTextIsSelectable(true);
		ll.addView(t);
	}
	

	void T(String s)
	{
		Toast.makeText(this,s,50).show();
	}

	Drawable d(int r,int c)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(r);
		d.setStroke(2, 0xffeeeeee);
		return d;
	}

	

	
}


