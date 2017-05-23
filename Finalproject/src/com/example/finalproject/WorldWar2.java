package com.example.finalproject;


import android.app.Activity;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.LatLng;
import android.net.Uri;
import android.os.Bundle;

	public class WorldWar2 extends Activity{
		int count,c;
		ArrayList<Locations> China;
		ArrayList<Locations> Africa;
		LatLng Mukden ,Manchuria,Shanghai ,Nanchang,Shanggao,Changde,Changsha,Taierzhuang,Nanjing,Shantou,Taiyuan,Xuzhou,Xiamen ,Wuhan ,Guangdong ,Lugoubridge ,Xinkou ,Xiushuiriver ,Guangxi,Wuyuan ;
		LatLng OperationPugilist,OperationSkorpion  ,El_Agheila ,FortCapuzzo ,Gondar ,Tobruk ,El_Alamein ,Alam_el_Halfa ,OperationBrevity ,BirHakeim ,Hill_609,Sidi_Bou_Zid ,Kasserine_Pass ,Medenine ,MarethLine,Keren,Gabon,OperationBattleaxe,OperationCompass,OperationTorch;
		CameraUpdate center;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	    }
	    public class Locations{
	    	String lname;
	    	LatLng llatLng;
	    	Uri Url;
	    	Locations(String name, LatLng latlng,Uri url)
	    	{
	    		lname = name;
	    		llatLng = latlng;
	    		Url=url;
	    	}
	    	public String getname(){
	    		return lname;
	    	}
	    	public LatLng getLatLng(){
	    		return llatLng;
	    	}
	        public Uri getUri_marker(){

	        return Url;
	    	
	    };
	    }

	    public ArrayList<Locations> setChinaLocation(){
	    	//China

	    	China= new ArrayList<Locations>();

	        Mukden = new LatLng(41.8059,123.4323);

	        Locations MukdenLoc = new Locations("Mukden", Mukden, Uri.parse("http://en.wikipedia.org/wiki/Mukden_Incident"));

	        Manchuria = new LatLng(45,125);

	        Locations ManchuriaLoc = new Locations("Manchuria", Manchuria, Uri.parse("http://en.wikipedia.org/wiki/Japanese_invasion_of_Manchuria"));

	        Shanghai = new LatLng(31.2,121.5);

	        Locations ShanghaiLoc = new Locations("Shanghai", Shanghai, Uri.parse("http://en.wikipedia.org/wiki/January_28_Incident"));

	        Nanchang = new LatLng(28.6833, 115.8833);

	        Locations NanchangLoc = new Locations("Nanchang", Nanchang, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Nanchang"));

	        Shanggao = new LatLng(28.1833,114.8833);

	        Locations ShanggaoLoc = new Locations("Shanggao", Shanggao, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Shanggao"));

	        Changde = new LatLng(29.033, 111.6833);

	        Locations ChangdeLoc = new Locations("Changde", Changde, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Changde"));

	        Changsha = new LatLng(28.1961,112.9722);

	        Locations ChangshaLoc = new Locations("Changsha", Changsha, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Changsha_(1941)"));

	        Taierzhuang = new LatLng(34.5608,117.7382);

	        Locations TaierzhuangLoc = new Locations("Taierzhuang", Taierzhuang, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Taierzhuang"));

	        Nanjing = new LatLng(32.05, 118.7667);

	        Locations NanjingLoc = new Locations("Nanjing", Nanjing, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Nanking"));

	        Shantou = new LatLng(23.35,116.6667);

	        Locations ShantouLoc = new Locations("Shantou", Shantou, Uri.parse("http://en.wikipedia.org/wiki/Swatow_Operation"));

	        Taiyuan = new LatLng(37.85, 112.55);

	        Locations TaiyuanLoc = new Locations("Taiyuan", Taiyuan, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Taiyuan"));

	        Xuzhou = new LatLng(34.2667, 117.167);

	        Locations XuzhouLoc = new Locations("Xuzhou", Xuzhou, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Xuzhou"));

	        Xiamen = new LatLng(24.5, 118.138);

	        Locations XiamenLoc = new Locations("Xiamen", Xiamen, Uri.parse("http://en.wikipedia.org/wiki/Amoy_Operation"));

	        Wuhan = new LatLng(30.5667, 114.267);

	        Locations WuhanLoc = new Locations("Wuhan", Wuhan, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Wuhan"));

	        Guangdong = new LatLng(23.09, 113.3);

	        Locations GuangdongLoc = new Locations("Guangdong", Guangdong, Uri.parse("http://en.wikipedia.org/wiki/Canton_Operation"));

	        Lugoubridge = new LatLng(39.849167, 116.213056);

	        Locations LugoubridgeLoc = new Locations("Lugoubridge", Lugoubridge, Uri.parse("http://en.wikipedia.org/wiki/Marco_Polo_Bridge_Incident"));

	        Xinkou = new LatLng(38, 112);

	        Locations XinkouLoc = new Locations("Xinkou", Xinkou,Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Xinkou"));

	        Xiushuiriver = new LatLng(28.683333, 115.883333);

	        Locations XiushuiriverLoc = new Locations("Xiushuiriver",Xiushuiriver, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Xiushui_River"));

	        Guangxi = new LatLng(23.8055, 108.984);

	        Locations GuangxiLoc = new Locations("Guangxi", Guangxi,Uri.parse("http://en.wikipedia.org/wiki/Battle_of_South_Guangxi"));

	        Wuyuan = new LatLng(31.09, 108.266111);

	        Locations WuyuanLoc = new Locations("Wuyuan", Wuyuan, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Wuyuan"));

	        China.add(MukdenLoc);

	        China.add(ManchuriaLoc);

	        China.add(ShanghaiLoc);

	        China.add(NanchangLoc);

	        China.add(ShanggaoLoc);

	        China.add(ChangdeLoc);

	        China.add(ChangshaLoc);

	        China.add(TaierzhuangLoc);

	        China.add(NanjingLoc);

	        China.add(ShantouLoc);

	        China.add(TaiyuanLoc);

	        China.add(XuzhouLoc);

	        China.add(XiamenLoc);

	        China.add(WuhanLoc);

	        China.add(GuangdongLoc);

	        China.add(LugoubridgeLoc);

	        China.add(XinkouLoc);

	        China.add(XiushuiriverLoc);

	        China.add(GuangxiLoc);  

	        China.add(WuyuanLoc);
	        return China;
	        
	    }
	    
	    public ArrayList<Locations> setAfricaLocation(){

	    	//Africa
	    	Africa= new ArrayList<Locations>();

	        El_Agheila = new LatLng(30.2667,19.2);

	        Locations El_AgheilaLoc = new Locations("El_Agheila", El_Agheila, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_El_Agheila"));

	        FortCapuzzo = new LatLng(31.580833, 25.0522);

	        Locations FortCapuzzoLoc = new Locations("FortCapuzzo", FortCapuzzo, Uri.parse("http://en.wikipedia.org/wiki/Fort_Capuzzo"));

	        Gondar = new LatLng(12.6,37.4667);

	        Locations GondarLoc = new Locations("Gondar", Gondar, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Gondar"));

	        Tobruk = new LatLng(32.0761, 23.9614);

	        Locations TobrukLoc = new Locations("Tobruk", Tobruk,Uri.parse("http://en.wikipedia.org/wiki/Siege_of_Tobruk"));

	        El_Alamein = new LatLng(30.833,28.95);

	        Locations El_AlameinLoc = new Locations("El_Alamein", El_Alamein, Uri.parse("http://en.wikipedia.org/wiki/First_Battle_of_El_Alamein"));

	        Alam_el_Halfa = new LatLng(30.6667,29.1667);

	        Locations Alam_el_HalfaLoc = new Locations("Alam_el_Halfa", Alam_el_Halfa, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Alam_el_Halfa"));

	        OperationBrevity = new LatLng(31.58,25.0522);

	        Locations OperationBrevityLoc = new Locations("OperationBrevity",OperationBrevity, Uri.parse("http://en.wikipedia.org/wiki/Operation_Brevity"));

	        BirHakeim = new LatLng(31.593869,23.479767);

	        Locations BirHakeimLoc = new Locations("BirHakeim", BirHakeim, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Bir_Hakeim"));

	        Hill_609 = new LatLng(36.9022,9.4944);

	        Locations Hill_609Loc = new Locations("Hill_609", Hill_609, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Hill_609"));

	        Sidi_Bou_Zid = new LatLng(34.8667,9.4833);

	        Locations Sidi_Bou_ZidLoc = new Locations("Sidi_Bou_Zid", Sidi_Bou_Zid, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Sidi_Bou_Zid"));

	        Kasserine_Pass = new LatLng(35.2596, 8.7424);

	        Locations Kasserine_PassLoc = new Locations("Kasserine_Pass",Kasserine_Pass, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Kasserine_Pass"));

	        OperationPugilist= new LatLng(36.5022691,-0.8848665);

	        Locations OperationPugilistLoc= new Locations("OperationPugilist", OperationPugilist,Uri.parse("http://en.wikipedia.org/wiki/Battle_of_the_Mareth_Line"));

	        Medenine = new LatLng(33.346623,10.4912567);

	        Locations MedenineLoc = new Locations("Medenine", Medenine, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Medenine"));

	        MarethLine = new LatLng(36.4553329,9.0381051);

	        Locations MarethLineLoc = new Locations("MarethLine", MarethLine, Uri.parse("http://en.wikipedia.org/wiki/Mareth_Line"));

	        Keren = new LatLng(15.776667, 38.451111);

	        Locations KerenLoc = new Locations("Keren", Keren, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Keren"));

	        Gabon = new LatLng(0.39, 9.451667);

	        Locations GabonLoc = new Locations("Gabon", Gabon, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Gabon")); 

	        OperationSkorpion = new LatLng(32.2750097,21.0145778);

	        Locations OperationSkorpionLoc = new Locations("OperationSkorpion", OperationSkorpion, Uri.parse("http://en.wikipedia.org/wiki/Operation_Skorpion"));

	        OperationBattleaxe = new LatLng(31.2443732,21.3389343);

	        Locations OperationBattleaxeLoc = new Locations("OperationBattleaxe", OperationBattleaxe, Uri.parse("http://en.wikipedia.org/wiki/Operation_Battleaxe")); 

	        OperationCompass = new LatLng(30.9350759,30.1441724);

	        Locations OperationCompassLoc = new Locations("OperationCompass", OperationCompass, Uri.parse("http://en.wikipedia.org/wiki/Operation_Compass")); 

	        OperationTorch = new LatLng(35.085, -2.029);

	        Locations OperationTorchLoc = new Locations("OperationTorch", OperationTorch, Uri.parse("http://en.wikipedia.org/wiki/Operation_Torch")); 

	        Africa.add(El_AgheilaLoc);

	        Africa.add(FortCapuzzoLoc);

	        Africa.add(GondarLoc);

	        Africa.add(TobrukLoc);

	        Africa.add(El_AlameinLoc);

	        Africa.add(Alam_el_HalfaLoc);

	        Africa.add(OperationBrevityLoc);

	        Africa.add(BirHakeimLoc);

	        Africa.add(Hill_609Loc);

	        Africa.add(Sidi_Bou_ZidLoc);

	        Africa.add(Kasserine_PassLoc);

	        Africa.add(OperationPugilistLoc);

	        Africa.add(MedenineLoc);

	        Africa.add(MarethLineLoc);

	        Africa.add(KerenLoc);

	        Africa.add(GabonLoc);

	        Africa.add(OperationSkorpionLoc);

	        Africa.add(OperationBattleaxeLoc);

	        Africa.add(OperationCompassLoc);  

	        Africa.add(OperationTorchLoc);
	        
	        return Africa;
	    }
   
	}
	
	


