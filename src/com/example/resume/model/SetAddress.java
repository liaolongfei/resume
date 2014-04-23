package com.example.resume.model;

import com.example.resume.R;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SetAddress {
	private Activity  activity = null;
	private Spinner provinceSpinner = null;
	private Spinner citySpinner = null;
	private Spinner countrySpinner = null;
	
	private int provinceId = 0;
	private int cityId = 0;
	/**
	 * 只需要省份
	 * @param activity
	 * @param provinceSpinner
	 */
	public SetAddress(Activity activity, Spinner provinceSpinner) {
		this.activity = activity;
		this.provinceSpinner = provinceSpinner;
		provinceSpinner();
	}
	
	public SetAddress(Activity activity, Spinner provinceSpinner,Spinner citySpinner) {
		this(activity, provinceSpinner);
		this.citySpinner = citySpinner;
		provinceSpinner.setOnItemSelectedListener(new selectProvinceAction());
	}
	
	public SetAddress(Activity activity, Spinner provinceSpinner,Spinner citySpinner, Spinner countrySpinner) {
		this(activity, provinceSpinner, citySpinner);
		this.countrySpinner = countrySpinner;
		citySpinner.setOnItemSelectedListener(new selectCityAction());
	}
	
	
	private void provinceSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity,R.array.province_item, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		provinceSpinner.setAdapter(adapter);
		
	}
	
	/* 通过方法动态的添加适配器 */
	private void select(Spinner spin, ArrayAdapter<CharSequence> adapter,
			int arry) {
		// 注意这里的arry不仅仅但是一个整形，他代表了一个数组！
		adapter = ArrayAdapter.createFromResource(activity, arry,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		// spin.setSelection(0,true);
	}
	
	
	private class selectProvinceAction implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			provinceId = provinceSpinner.getSelectedItemPosition();
			if (true) {
				citySpinner.setPrompt("请选择城市");// 设置标题
				ArrayAdapter<CharSequence> city_adapter = null;
				select(citySpinner, city_adapter, MyAddress.city[provinceId]);// 城市一级的数据绑定
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	}
	
	private class selectCityAction implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
			cityId = citySpinner.getSelectedItemPosition();// 得到city的id
			ArrayAdapter<CharSequence> county_adapter = null;
			if (true) {
				// 这里开始设置县区一级的内容

				switch (provinceId) {
				case 0:
					select(countrySpinner,county_adapter,MyAddress.countyOfBeiJing[cityId]);
					break;
				case 1:
					select(countrySpinner,county_adapter,MyAddress.countyOfTianJing[cityId]);
					break;
				case 2:
					select(countrySpinner,county_adapter,MyAddress.countyOfHeBei[cityId]);
					break;
				case 3:
					select(countrySpinner,county_adapter,MyAddress.countyOfShanXi1[cityId]);
					break;
				case 4:
					select(countrySpinner,county_adapter,MyAddress.countyOfNeiMengGu[cityId]);
					break;
				case 5:
					select(countrySpinner,county_adapter,MyAddress.countyOfLiaoNing[cityId]);
					break;
				case 6:
					select(countrySpinner,county_adapter,MyAddress.countyOfJiLin[cityId]);
					break;
				case 7:
					select(countrySpinner,county_adapter,MyAddress.countyOfHeiLongJiang[cityId]);
					break;
				case 8:
					select(countrySpinner,county_adapter,MyAddress.countyOfShangHai[cityId]);
					break;
				case 9:
					select(countrySpinner,county_adapter,MyAddress.countyOfJiangSu[cityId]);
					break;
				case 10:
					select(countrySpinner,county_adapter,MyAddress.countyOfZheJiang[cityId]);
					break;
				case 11:
					select(countrySpinner,county_adapter,MyAddress.countyOfAnHui[cityId]);
					break;
				case 12:
					select(countrySpinner,county_adapter,MyAddress.countyOfFuJian[cityId]);
					break;
				case 13:
					select(countrySpinner,county_adapter,MyAddress.countyOfJiangXi[cityId]);
					break;
				case 14:
					select(countrySpinner,county_adapter,MyAddress.countyOfShanDong[cityId]);
					break;
				case 15:
					select(countrySpinner,county_adapter,MyAddress.countyOfHeNan[cityId]);
					break;
				case 16:
					select(countrySpinner,county_adapter,MyAddress.countyOfHuBei[cityId]);
					break;
				case 17:
					select(countrySpinner,county_adapter,MyAddress.countyOfHuNan[cityId]);
					break;
				case 18:
					select(countrySpinner,county_adapter,MyAddress.countyOfGuangDong[cityId]);
					break;
				case 19:
					select(countrySpinner,county_adapter,MyAddress.countyOfGuangXi[cityId]);
					break;
				case 20:
					select(countrySpinner,county_adapter,MyAddress.countyOfHaiNan[cityId]);
					break;
				case 21:
					select(countrySpinner,county_adapter,MyAddress.countyOfChongQing[cityId]);
					break;
				case 22:
					select(countrySpinner,county_adapter,MyAddress.countyOfSiChuan[cityId]);
					break;
				case 23:
					select(countrySpinner,county_adapter,MyAddress.countyOfGuiZhou[cityId]);
					break;
				case 24:
					select(countrySpinner,county_adapter,MyAddress.countyOfYunNan[cityId]);
					break;
				case 25:
					select(countrySpinner,county_adapter,MyAddress.countyOfXiZang[cityId]);
					break;
				case 26:
					select(countrySpinner,county_adapter,MyAddress.countyOfShanXi2[cityId]);
					break;
				case 27:
					select(countrySpinner,county_adapter,MyAddress.countyOfGanSu[cityId]);
					break;
				case 28:
					select(countrySpinner,county_adapter,MyAddress.countyOfQingHai[cityId]);
					break;
				case 29:
					select(countrySpinner,county_adapter,MyAddress.countyOfNingXia[cityId]);
					break;
				case 30:
					select(countrySpinner,county_adapter,MyAddress.countyOfXinJiang[cityId]);
					break;
				case 31:
					select(countrySpinner,county_adapter,MyAddress.countyOfHongKong[cityId]);
					break;
				case 32:
					select(countrySpinner,county_adapter,MyAddress.countyOfAoMen[cityId]);
					break;
				case 33:
					select(countrySpinner,county_adapter,MyAddress.countyOfTaiWan[cityId]);
					break;

				default:
					break;
				}

			}
		}

		@Override
		public void onNothingSelected(
				AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
		
	}
	

}
