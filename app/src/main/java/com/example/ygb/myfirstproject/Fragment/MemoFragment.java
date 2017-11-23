package com.example.ygb.myfirstproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.StaticC.HttpConstant;
import com.example.ygb.myfirstproject.Utils.HttpUtils;
import com.example.ygb.myfirstproject.adapter.MemoItemAdapter;
import com.example.ygb.myfirstproject.entity.MemoItemData;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuanBuyuan on 2017/10/26 0026.
 */

public class MemoFragment extends Fragment implements View.OnClickListener{
    private EditText etNumber;
    private AutoCompleteTextView etCompany;
    private Button btnSearch;
    private ListView listView;
    private List<MemoItemData> mList;
 /*   private List<Map<String,String>> companyList;
    private Map<String,String> companyMap;*/
    private ArrayAdapter arrayAdapter;
    private String companyTypeJson;
    private String [] arr={"aa","aab","aac","auto"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_memo,null);
        etCompany=(AutoCompleteTextView)view.findViewById(R.id.et_company);
        etNumber=view.findViewById(R.id.et_number);
        btnSearch=view.findViewById(R.id.btn_search);
        listView=view.findViewById(R.id.lv_memo);
        companyTypeJson=HttpUtils.COMPANY;
        btnSearch.setOnClickListener(this);


        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,arr);
        etCompany.setAdapter(arrayAdapter);
        return view;

    }

    public  String getTimeYmd(String s){
        int i=s.indexOf(" ");
        return s.substring(0,i);
    }
    public String getTimeHm(String s){
        int i=s.indexOf(" ");
        int j=s.lastIndexOf(":");
        return s.substring(i+1,j);
    }

    @Override
    public void onClick(View view) {
        if (!"".equals(etNumber.getText().toString())||!"".equals(etCompany.getText().toString())){
            String Num=etNumber.getText().toString();
            String Company=etCompany.getText().toString();
            final String type=getType(Company);
            Log.d("cpany",type);
            //String type=null;
            if (type!=null){
                String url= HttpConstant.URL+"?appkey="+HttpConstant.APPKEY+"&type="+type+"&number="+Num;
                // String url="http://guolin.tech/api/china";
                RxVolley.get(url, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        // Log.d("testT",t);
                        try {
                            mList=new ArrayList<MemoItemData>();
                            JSONObject json=new JSONObject(t);
                            if (json.getInt("status") != 0) {
                                //Log.d("kdTest",json.getString("msg"));
                                Toast.makeText(getActivity(),json.getString("msg"),Toast.LENGTH_LONG).show();
                            }else {
                                String relStr=json.getString("result");
                                JSONObject resultArr=new JSONObject(relStr);
                                if (resultArr!=null){
                                    //Log.d("kdTest","已签收");
                                    //Log.d("kdTest","未签收");
                                    if (resultArr.getString("list") != null) {
                                        String listStr=resultArr.getString("list");
                                        JSONArray list =new JSONArray(listStr);
                                        for (int j = 0; j < list.length(); j++) {
                                            JSONObject listObj = (JSONObject) list.getJSONObject(j);
                                            if (listObj != null) {
                                                String time = listObj.getString("time");
                                                String status = listObj.getString("status");
                                                // Log.d("kdTest",time + " " + status);
                                                MemoItemData memoItemData=new MemoItemData(status,getTimeYmd(time),getTimeHm(time));
                                                mList.add(memoItemData);
                                            }
                                        }
                                        MemoItemAdapter memoItemAdapter=new MemoItemAdapter(getActivity(),mList);
                                        listView.setAdapter(memoItemAdapter);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else {
                Toast.makeText(getActivity(),"无效",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(),"信息不能为空",Toast.LENGTH_SHORT).show();
        }
    }


    public String getType(String company){
        String re = null;
        try {
            JSONObject jsonObject=new JSONObject(companyTypeJson);
            String result=jsonObject.getString("result");
            JSONArray arr=new JSONArray(result);
            for (int i=0;i<arr.length();i++){
                JSONObject nameAndType=arr.getJSONObject(i);

                String name=nameAndType.getString("name");
                String type=nameAndType.getString("type");
                if (company.equals(name)){
                    Log.d("cpany",type);
                    re=type;
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return re;
    }
}
